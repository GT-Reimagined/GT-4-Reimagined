package org.gtreimagined.gt4r.reactor.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorChamber;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidBlock;

import com.gtnewhorizons.modularui.api.ModularUITextures;
import com.gtnewhorizons.modularui.api.drawable.AdaptableUITexture;
import com.gtnewhorizons.modularui.api.forge.InvWrapper;
import com.gtnewhorizons.modularui.api.math.CrossAxisAlignment;
import com.gtnewhorizons.modularui.api.math.MainAxisAlignment;
import com.gtnewhorizons.modularui.api.math.Size;
import com.gtnewhorizons.modularui.api.screen.ITileWithModularUI;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.api.widget.Widget;
import com.gtnewhorizons.modularui.common.widget.Column;
import com.gtnewhorizons.modularui.common.widget.FakeSyncWidget;
import com.gtnewhorizons.modularui.common.widget.FluidSlotWidget;
import com.gtnewhorizons.modularui.common.widget.MultiChildWidget;
import com.gtnewhorizons.modularui.common.widget.Row;
import com.gtnewhorizons.modularui.common.widget.SlotGroup;
import com.gtnewhorizons.modularui.common.widget.TextWidget;
import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;
import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.CoolantRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.CoolantRegistry.Coolant;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.IReactorBlock.ReactorEnableState;
import com.recursive_pineapple.nuclear_horizons.utils.DirectionUtil;

import cofh.api.energy.IEnergyReceiver;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.GTValues;
import gregtech.api.interfaces.tileentity.IDebugableTileEntity;
import gregtech.api.interfaces.tileentity.IEnergyConnected;
import gregtech.api.logic.PowerLogic;
import gregtech.api.logic.interfaces.PowerLogicHost;
import gregtech.api.util.GTUtility;

public class TileReactorCore extends TileEntity
    implements IInventory, IReactorGrid, ITileWithModularUI, IEnergyConnected, IDebugableTileEntity {

    public static final int ROW_COUNT = 6;
    public static final int COL_COUNT = 9;

    private static final int REACTOR_TICK_SPEED = 10;
    private static final int REACTOR_STRUCTURE_CHECK_PERIOD = 10 * 20;

    private final ArrayList<EntityPlayer> viewingPlayers = new ArrayList<>();

    private int chambers = 0;
    private ItemStack[] contents = new ItemStack[ROW_COUNT * COL_COUNT];
    private IComponentAdapter[] components = new IComponentAdapter[ROW_COUNT * COL_COUNT];

    private int tickCounter = 0;

    boolean isActive = false;
    boolean isFluid = false;

    int storedHeat = 0;
    int addedHeat = 0;
    int roundedHeat = 0;

    int voltage = 0;
    int maxStoredEU = 4_194_304; // 2 ^ 22
    int storedEU = 0;
    int addedEU = 0;

    private Integer hullHeatCache = null;

    Coolant coolantCache;
    FluidTank coolantTank = new FluidTank(10_000);
    // change for testing distilled water->steam conversion
    // since so much steam is produced per HU, you need a large output buffer to capture useful
    // steam/s production
    FluidTank hotCoolantTank = new FluidTank(200_000);

    private ArrayList<IReactorBlock> reactorBlocks = new ArrayList<>();

    private double heatRatio = 0;

    public TileReactorCore() {

    }

    // #endregion

    // #region Tile Entity Logic

    public int getColumnCount() {
        return chambers + 3;
    }

    public void setChamberCount(int chambers) {
        if (chambers == this.chambers) {
            return;
        }

        this.chambers = chambers;

        for (var player : viewingPlayers.toArray(new EntityPlayer[viewingPlayers.size()])) {
            player.closeScreen();
        }

        viewingPlayers.clear();

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (col >= this.getColumnCount()) {
                    var item = contents[row * COL_COUNT + col];
                    contents[row * COL_COUNT + col] = null;
                    if (item != null && !worldObj.isRemote) {
                        worldObj
                            .spawnEntityInWorld(new EntityItem(worldObj, this.xCoord, this.yCoord, this.zCoord, item));
                    }
                }
            }
        }
    }

    public void dropInventory() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                var item = contents[row * COL_COUNT + col];
                contents[row * COL_COUNT + col] = null;
                if (item != null && !worldObj.isRemote) {
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, this.xCoord, this.yCoord, this.zCoord, item));
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("version", 1);

        compound.setInteger("heat", this.storedHeat);
        compound.setInteger("roundedHeat", this.roundedHeat);
        compound.setInteger("storedEU", this.storedEU);
        compound.setInteger("tickCooldown", this.tickCounter);
        compound.setBoolean("isActive", this.isActive);

        compound.setBoolean("isFluid", this.isFluid);
        compound.setTag("coolantTank", coolantTank.writeToNBT(new NBTTagCompound()));
        compound.setTag("hotCoolantTank", hotCoolantTank.writeToNBT(new NBTTagCompound()));

        var grid = new NBTTagCompound();

        for (int i = 0; i < contents.length; i++) {
            var item = contents[i];
            if (item != null) {
                grid.setTag(Integer.toString(i), item.writeToNBT(new NBTTagCompound()));
            }
        }

        compound.setTag("grid", grid);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        int version = compound.getInteger("version");

        switch (version) {
            case 1: {
                this.storedHeat = compound.getInteger("heat");
                this.roundedHeat = compound.getInteger("roundedHeat");
                this.storedEU = compound.getInteger("storedEU");
                this.tickCounter = compound.getInteger("tickCooldown");
                this.isActive = compound.getBoolean("isActive");

                this.isFluid = compound.getBoolean("isFluid");
                this.coolantTank.readFromNBT(compound.getCompoundTag("coolantTank"));
                this.hotCoolantTank.readFromNBT(compound.getCompoundTag("hotCoolantTank"));

                if (this.coolantTank.getFluid() != null && !CoolantRegistry.isColdCoolant(
                    this.coolantTank.getFluid()
                        .getFluid())) {
                    // TODO: figure out if there's a mechanism to migrate fluids
                    this.coolantTank.setFluid(null);
                }

                if (this.hotCoolantTank.getFluid() != null && !CoolantRegistry.isHotCoolant(
                    this.hotCoolantTank.getFluid()
                        .getFluid())) {
                    this.hotCoolantTank.setFluid(null);
                }

                var grid = compound.getCompoundTag("grid");

                Arrays.fill(this.contents, null);
                Arrays.fill(this.components, null);

                for (int i = 0; i < contents.length; i++) {
                    if (grid.hasKey(Integer.toString(i))) {
                        contents[i] = new ItemStack(Blocks.air);
                        contents[i].readFromNBT(grid.getCompoundTag(Integer.toString(i)));

                        if (contents[i].getItem() == null) {
                            contents[i] = null;
                        }
                    }
                }

                break;
            }
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        this.setChamberCount(getAttachedChambers(worldObj, xCoord, yCoord, zCoord));

        this.tickCounter++;

        if (!this.worldObj.isRemote) {
            if (this.tickCounter % REACTOR_TICK_SPEED == 0) {
                boolean wasActive = isActive;

                this.isActive = worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0;

                for (var dir : DirectionUtil.values()) {
                    // spotless:off
                    this.isActive |= worldObj.getBlockPowerInput(
                        dir.offsetX + xCoord,
                        dir.offsetY + yCoord,
                        dir.offsetZ + zCoord
                    ) > 0;
                    // spotless:on
                }

                if (this.isFluid) {
                    boolean anyActive = false, anyInhibiting = false;

                    for (var block : reactorBlocks) {
                        var state = block.getEnableState();
                        anyActive |= state == ReactorEnableState.Active;
                        anyInhibiting |= state == ReactorEnableState.Inhibiting;
                    }

                    isActive |= anyActive;

                    if (anyInhibiting) {
                        isActive = false;
                    }
                }

                if ((this.tickCounter / REACTOR_TICK_SPEED) % 2 == 0) {
                    this.doHeatTick();
                } else {
                    this.doEUTick();
                }

                if (wasActive != isActive) {
                    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, isActive ? 1 : 0, 3);
                }

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

            if (this.tickCounter % REACTOR_STRUCTURE_CHECK_PERIOD == 0) {
                doStructureCheck();
            }

            this.emitEnergy();
        } else {
            if (heatRatio >= 0.4) {
                spawnSmoke(xCoord, yCoord, zCoord);

                for (var d : DirectionUtil.values()) {
                    if (d.getBlock(worldObj, xCoord, yCoord, zCoord) == BlockList.REACTOR_CHAMBER) {
                        spawnSmoke(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);
                    }
                }
            }
        }
    }

    private void spawnSmoke(int x, int y, int z) {
        if (worldObj.getBlock(x, y + 1, z)
            .isBlockNormalCube()) {
            return;
        }

        worldObj.spawnParticle(
            "smoke",
            x + Math.random() * 0.8 + 0.1,
            y + 1.1,
            z + Math.random() * 0.8 + 0.1,
            0,
            0.01 * (Math.random() * 0.5 + 1),
            0);
    }

    @Override
    public Packet getDescriptionPacket() {
        var data = new NBTTagCompound();

        data.setInteger("chambers", chambers);
        data.setBoolean("isActive", isActive);
        data.setInteger("storedEU", storedEU);
        data.setInteger("storedHeat", storedHeat);
        data.setDouble("heatRatio", heatRatio);
        data.setInteger("roundedHeat", roundedHeat);
        data.setInteger("addedHeat", addedHeat);
        data.setInteger("addedEU", addedEU);
        data.setBoolean("isFluid", isFluid);
        data.setTag("coolantTank", coolantTank.writeToNBT(new NBTTagCompound()));
        data.setTag("hotCoolantTank", hotCoolantTank.writeToNBT(new NBTTagCompound()));

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, data);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        var data = pkt.func_148857_g();

        this.chambers = data.getInteger("chambers");
        this.isActive = data.getBoolean("isActive");
        this.storedEU = data.getInteger("storedEU");
        this.storedHeat = data.getInteger("storedHeat");
        this.heatRatio = data.getDouble("heatRatio");
        this.roundedHeat = data.getInteger("roundedHeat");
        this.addedHeat = data.getInteger("addedHeat");
        this.addedEU = data.getInteger("addedEU");
        this.isFluid = data.getBoolean("isFluid");
        this.coolantTank.readFromNBT(data.getCompoundTag("coolantTank"));
        this.hotCoolantTank.readFromNBT(data.getCompoundTag("hotCoolantTank"));
    }

    public static int getAttachedChambers(World worldIn, int x, int y, int z) {
        int chamberCount = 0;

        for (var d : DirectionUtil.values()) {
            if (d.getBlock(worldIn, x, y, z) == BlockList.REACTOR_CHAMBER) {
                chamberCount++;
            }
        }

        return chamberCount;
    }

    public void addViewingPlayer(EntityPlayer player) {
        this.viewingPlayers.add(player);
    }

    public void removeViewingPlayer(EntityPlayer player) {
        this.viewingPlayers.remove(player);
    }

    // #endregion

    // #region Energy Logic

    @Override
    public byte getColorization() {
        return -1;
    }

    @Override
    public byte setColorization(byte arg0) {
        return -1;
    }

    @Override
    public long injectEnergyUnits(ForgeDirection arg0, long arg1, long arg2) {
        return 0;
    }

    @Override
    public boolean inputEnergyFrom(ForgeDirection arg0) {
        return false;
    }

    @Override
    public boolean outputsEnergyTo(ForgeDirection arg0) {
        return true;
    }

    private void emitEnergy() {
        if (this.voltage == 0) this.voltage = 32;

        int availableAmps = this.storedEU / this.voltage;

        if (availableAmps > 0) {
            long consumedAmps = emitEnergyToNetwork(this.voltage, availableAmps, this);

            for (var dir : DirectionUtil.values()) {
                if ((availableAmps - consumedAmps) <= 0) {
                    break;
                }

                if (dir.getTileEntity(worldObj, xCoord, yCoord, zCoord) instanceof TileReactorChamber chamber) {
                    consumedAmps += emitEnergyToNetwork(this.voltage, availableAmps - consumedAmps, chamber);
                }
            }

            this.storedEU -= consumedAmps * this.voltage;
        }
    }

    private static long emitEnergyToNetwork(long voltage, long amperage, TileEntity emitter) {
        long usedAmperes = 0;

        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (usedAmperes > amperage) break;

            ForgeDirection oppositeSide = Objects.requireNonNull(side.getOpposite());
            TileEntity tTileEntity = emitter.getWorldObj()
                .getTileEntity(
                    emitter.xCoord + side.offsetX,
                    emitter.yCoord + side.offsetY,
                    emitter.zCoord + side.offsetZ);
            if (tTileEntity instanceof PowerLogicHost host) {

                PowerLogic logic = host.getPowerLogic(oppositeSide);
                if (logic == null || logic.isEnergyReceiver()) {
                    continue;
                }

                usedAmperes += logic.injectEnergy(voltage, amperage - usedAmperes);
            } else if (tTileEntity instanceof IEnergyConnected energyConnected) {
                usedAmperes += energyConnected.injectEnergyUnits(oppositeSide, voltage, amperage - usedAmperes);

            } else if (tTileEntity instanceof ic2.api.energy.tile.IEnergySink sink) {
                if (sink.acceptsEnergyFrom(emitter, oppositeSide)) {
                    while (amperage > usedAmperes && sink.getDemandedEnergy() > 0
                        && sink.injectEnergy(oppositeSide, voltage, voltage) < voltage) usedAmperes++;
                }
            } else if (GregTechAPI.mOutputRF && tTileEntity instanceof IEnergyReceiver receiver) {
                final int rfOut = GTUtility.safeInt(voltage * GregTechAPI.mEUtoRF / 100);
                if (receiver.receiveEnergy(oppositeSide, rfOut, true) == rfOut) {
                    receiver.receiveEnergy(oppositeSide, rfOut, false);
                    usedAmperes++;
                }
            }
        }
        return usedAmperes;
    }

    // #endregion

    // #region Inventory Logic

    private void onComponentInvalidated(int index) {
        this.components[index] = null;
    }

    private int transformSlotIndex(int invSlot) {
        int cols = this.getColumnCount();

        int col = invSlot % cols;
        int row = invSlot / cols;

        return row * COL_COUNT + col;
    }

    @Override
    public int getSizeInventory() {
        return this.getColumnCount() * ROW_COUNT;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        index = transformSlotIndex(index);

        return this.contents[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        index = transformSlotIndex(index);

        var item = this.contents[index];

        if (item == null) {
            return null;
        }

        this.onComponentInvalidated(index);

        this.markDirty();

        int consumed = Math.min(item.stackSize, count);

        var out = item.splitStack(consumed);

        if (item.stackSize <= 0) {
            this.contents[index] = null;
        }

        return out;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        index = transformSlotIndex(index);

        var item = this.contents[index];

        if (item == null) {
            return null;
        }

        this.contents[index] = null;
        this.onComponentInvalidated(index);

        this.markDirty();

        return item;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        index = transformSlotIndex(index);

        if (stack != null && stack.stackSize <= 0) {
            stack = null;
        }

        this.contents[index] = stack;
        this.onComponentInvalidated(index);

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName() {
        return BlockList.REACTOR_CORE_NAME;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return ComponentRegistry.isReactorItem(stack);
    }

    // #endregion

    // #region Reactor Grid Logic

    public void resetHullHeatCache() {
        this.hullHeatCache = null;
    }

    private void doHeatTick() {
        this.addedHeat = 0;

        // a component could change their hull heat increase each tick, so we have to invalidate this here and not when
        // components change
        resetHullHeatCache();

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                var component = getComponent(col, row);
                if (component != null) {
                    component.onHeatTick();
                }
            }
        }

        for (var reactorBlock : reactorBlocks) {
            reactorBlock.onHeatTick(this);
        }

        heatRatio = ((double) storedHeat) / ((double) this.getMaxHullHeat());

        doHeatDamage();
    }

    private void doEUTick() {
        this.addedEU = 0;

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                var component = getComponent(col, row);
                if (component != null) {
                    component.onEnergyTick();
                }
            }
        }

        for (var reactorBlock : reactorBlocks) {
            reactorBlock.onEnergyTick(this);
        }

        if (isFluid) {
            addedEU = 0;
            storedEU = 0;
            maxStoredEU = 0;
            voltage = 32;
        }

        if (this.addedEU > 0) {
            int perTick = this.addedEU / 20;

            int voltageTier = (int) (Math.ceil(Math.log(perTick / 8) / Math.log(4)));

            this.voltage = (int) (Math.pow(4, voltageTier) * 8);

            this.maxStoredEU = voltage * 20 * 30;
        }

        if (this.storedEU > this.maxStoredEU) {
            this.storedEU = this.maxStoredEU;
        }
    }

    private static final DamageSource RADIATION_DAMAGE = new DamageSource("nh_radiation");

    private static double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    private void doHeatDamage() {
        final int DAMAGE_RADIUS = 3;

        // flames
        if (heatRatio >= 0.4) {
            for (int i = 0; i < 10; i++) {
                int x = xCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int y = yCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int z = zCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);

                var block = worldObj.getBlock(x, y, z);

                if (block.isFlammable(worldObj, x, y, z, ForgeDirection.UNKNOWN)) {
                    block.breakBlock(worldObj, x, y, z, block, worldObj.getBlockMetadata(x, y, z));
                    worldObj.setBlock(x, y, z, Blocks.fire, 0, 3);
                    break;
                }
            }
        }

        // evaporation
        if (heatRatio >= 0.5) {
            for (int i = 0; i < 10; i++) {
                int x = xCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int y = yCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int z = zCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);

                var block = worldObj.getBlock(x, y, z);

                if (block instanceof IFluidBlock fluidBlock && fluidBlock.getFluid()
                    .getTemperature() < 375) {
                    worldObj.setBlock(x, y, z, Blocks.air, 0, 3);
                    // fire hiss
                    worldObj.playAuxSFX(1004, x, y, z, 0);
                    break;
                }
            }
        }

        // damage
        if (heatRatio >= 0.7) {
            var entities = worldObj.getEntitiesWithinAABB(
                EntityLivingBase.class,
                AxisAlignedBB.getBoundingBox(
                    xCoord - DAMAGE_RADIUS,
                    yCoord - DAMAGE_RADIUS,
                    zCoord - DAMAGE_RADIUS,
                    xCoord + DAMAGE_RADIUS,
                    yCoord + DAMAGE_RADIUS,
                    zCoord + DAMAGE_RADIUS));

            for (var entity : entities) {
                entity.attackEntityFrom(RADIATION_DAMAGE, 4);
            }
        }

        // lava
        if (heatRatio >= 0.85) {
            for (int i = 0; i < 10; i++) {
                int x = xCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int y = yCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int z = zCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);

                // don't melt the reactor or its chambers, because why would we want to do that? :tootroll:
                if ((Math.abs(x - xCoord) + Math.abs(y - yCoord) + Math.abs(z - zCoord)) <= 1) {
                    continue;
                }

                var block = worldObj.getBlock(x, y, z);

                if (!block.isAir(worldObj, x, y, z) && block.getBlockHardness(worldObj, x, y, z) < 5) {
                    worldObj.setBlock(x, y, z, Blocks.flowing_lava, 1, 3);
                    // fire hiss
                    worldObj.playAuxSFX(1004, x, y, z, 0);
                    break;
                }
            }
        }

        // explosion
        if (heatRatio >= 1) {
            worldObj.newExplosion(
                null,
                xCoord + 0.5,
                yCoord + 0.5,
                zCoord + 0.5,
                (float) (30 * getExplosionRadiusMultiplier()),
                true,
                true);
        }
    }

    @Override
    public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
        ArrayList<String> info = new ArrayList<>();

        info.add(isActive ? "§aActive§r" : "§cInactive§r");
        info.add(isFluid ? "Fluid: §atrue§r" : "Fluid: §cfalse§r");

        String heatColour = "§a";

        if (heatRatio >= 0.4) heatColour = "§e";
        if (heatRatio >= 0.6) heatColour = "§6";
        if (heatRatio >= 0.8) heatColour = "§c";

        info.add(String.format("§rStored Heat: %s%,d HU§r / §e%,d HU§r", heatColour, storedHeat, getMaxHullHeat()));
        info.add(String.format("§rHeat Generation Rate: §e%,d HU/s§r", addedHeat));
        if (coolantTank.getFluidAmount() > 0 && coolantTank.getFluid() != null
            && coolantTank.getFluid()
                .getFluid() != null) {
            info.add(
                String.format(
                    "§rStored Coolant: §a%,d L§r / §e%,d L§r §7%s§r",
                    coolantTank.getFluidAmount(),
                    coolantTank.getCapacity(),
                    coolantTank.getFluid()
                        .getLocalizedName()));
        } else {
            info.add(String.format("Stored Coolant: §eEmpty§r"));
        }
        if (hotCoolantTank.getFluidAmount() > 0 && hotCoolantTank.getFluid() != null
            && hotCoolantTank.getFluid()
                .getFluid() != null) {
            info.add(
                String.format(
                    "§rStored Hot Coolant: §a%,d L§r / §e%,d L§r §7%s§r",
                    hotCoolantTank.getFluidAmount(),
                    hotCoolantTank.getCapacity(),
                    hotCoolantTank.getFluid()
                        .getLocalizedName()));
        } else {
            info.add(String.format("Stored Hot Coolant: §eEmpty§r"));
        }
        info.add(String.format("§rStored Energy: §a%,d§r EU / §e%,d§r EU", storedEU, maxStoredEU));
        info.add(String.format("§rEnergy Generation Rate: §e%,d§r EU/t", addedEU / 20));
        info.add(
            String.format("§rMax Out: §c%,d EU/t (%s)§r at §c1§r A", voltage, GTValues.VN[GTUtility.getTier(voltage)]));

        return info;
    }

    @Override
    public int getWidth() {
        return COL_COUNT;
    }

    @Override
    public int getHeight() {
        return ROW_COUNT;
    }

    @Override
    public @Nullable IComponentAdapter getComponent(int x, int y) {
        if (x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(
                String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if (y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(
                String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = y * COL_COUNT + x;

        var adapter = this.components[index];
        if (adapter != null) {
            return adapter;
        }

        var item = this.contents[index];
        if (item != null) {
            adapter = ComponentRegistry.getAdapter(item, this, x, y);
            this.components[index] = adapter;
        }

        return adapter;
    }

    @Override
    public @Nullable ItemStack getItem(int x, int y) {
        if (x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(
                String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if (y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(
                String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = y * COL_COUNT + x;

        return this.contents[index];
    }

    @Override
    public void setItem(int x, int y, @Nullable ItemStack item) {
        if (x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(
                String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if (y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(
                String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = y * COL_COUNT + x;

        this.contents[index] = item;
        this.components[index] = item != null && ComponentRegistry.isReactorItem(item)
            ? ComponentRegistry.getAdapter(item, this, x, y)
            : null;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public int getHullHeat() {
        return storedHeat;
    }

    @Override
    public int getMaxHullHeat() {
        if (hullHeatCache != null) {
            return hullHeatCache;
        }

        int maxHeat = 5000;

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                var component = getComponent(col, row);
                if (component != null) {
                    maxHeat += component.getReactorMaxHeatIncrease();
                }
            }
        }

        hullHeatCache = maxHeat;

        return maxHeat;
    }

    private double getExplosionRadiusMultiplier() {
        double mult = 1.0;

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                var component = getComponent(col, row);
                if (component != null) {
                    mult *= component.getExplosionRadiusMultiplier();
                }
            }
        }

        return mult;
    }

    @Override
    public void setHullHeat(int newHeat) {
        this.storedHeat = newHeat;
    }

    @Override
    public void addHullHeat(int delta) {
        this.storedHeat += delta;
    }

    @Override
    public int addAirHeat(int airHeat) {
        if (this.isFluid) {
            if (this.coolantTank.getFluidAmount() == 0) {
                return airHeat;
            }

            // cache this because it will be called several times a second
            if ((coolantCache == null || coolantCache.cold != this.coolantTank.getFluid()
                .getFluid())) {
                if (this.coolantTank.getFluidAmount() == 0) {
                    coolantCache = null;
                } else {
                    coolantCache = CoolantRegistry.getCoolantInfo(
                        this.coolantTank.getFluid()
                            .getFluid());
                }
            }

            if (coolantCache == null) {
                return airHeat;
            }

            this.roundedHeat += airHeat * Config.FLUID_NUKE_HU_MULTIPLIER;

            int heatableCoolant = Math.min(
                this.coolantTank.getFluidAmount(),
                this.hotCoolantTank.getCapacity() - this.hotCoolantTank.getFluidAmount());

            int consumedCoolant;
            // BWR
            if (this.coolantCache.cold.getName()
                .equals("distilled_water")) {
                consumedCoolant = Math.min(
                    roundedHeat / (coolantCache.specificHeatCapacity),
                    Math.min(
                        this.coolantTank.getFluidAmount(),
                        (this.hotCoolantTank.getCapacity() - this.hotCoolantTank.getFluidAmount())
                            / Config.BWR_STEAM_PER_HU_MULTIPLIER));
            }
            // conventional coolants
            else {
                consumedCoolant = Math.min(roundedHeat / coolantCache.specificHeatCapacity, heatableCoolant);
            }
            this.roundedHeat -= consumedCoolant * coolantCache.specificHeatCapacity;
            this.addedHeat += consumedCoolant * coolantCache.specificHeatCapacity;

            // for BWRs, convert distilled water to a configured amount of steam instead of the same quantity of hot
            // coolant
            if (this.coolantCache.cold.getName()
                .equals("distilled_water")) {
                this.coolantTank.drain(consumedCoolant, true);
                this.hotCoolantTank
                    .fill(new FluidStack(coolantCache.hot, consumedCoolant * Config.BWR_STEAM_PER_HU_MULTIPLIER), true);
            } else {
                this.coolantTank.drain(consumedCoolant, true);
                this.hotCoolantTank.fill(new FluidStack(coolantCache.hot, consumedCoolant), true);
            }

            return 0;
        } else {
            return 0;
        }
    }

    @Override
    public void addEU(double eu) {
        if (!isFluid) {
            this.storedEU += eu * Config.REACTOR_EU_MULTIPLIER;
            this.addedEU += eu * Config.REACTOR_EU_MULTIPLIER;
        }
    }

    @Override
    public boolean isFluid() {
        return isFluid;
    }

    // #endregion

    // #region Reactor Structure Logic

    private static boolean isInsideCube(int coord) {
        return coord == -1 || coord == 0 || coord == 1;
    }

    private void doStructureCheck() {
        this.isFluid = true;
        this.reactorBlocks.clear();

        for (int relX = -2; relX < 3; relX++) {
            for (int relY = -2; relY < 3; relY++) {
                for (int relZ = -2; relZ < 3; relZ++) {
                    if (relX == 0 && relY == 0 && relZ == 0) {
                        continue;
                    }

                    int insideCubeCount = 0;

                    if (isInsideCube(relX)) insideCubeCount++;
                    if (isInsideCube(relY)) insideCubeCount++;
                    if (isInsideCube(relZ)) insideCubeCount++;

                    boolean isInternal = insideCubeCount == 3;
                    // boolean isFace = insideCubeCount == 2;
                    boolean isEdge = insideCubeCount == 1;
                    boolean isCorner = insideCubeCount == 0;

                    // within the center 3x3x3
                    if (isInternal) {
                        continue;
                    }

                    int x = xCoord + relX, y = yCoord + relY, z = zCoord + relZ;

                    if (!worldObj.blockExists(x, y, z)) {
                        this.isFluid = false;
                        continue;
                    }

                    Block block = worldObj.getBlock(x, y, z);

                    // valid in any spot on the surface
                    if (block == BlockList.PRESSURE_VESSEL) {
                        continue;
                    }

                    // edges & corners must be pressure vessels
                    if (isEdge || isCorner) {
                        if (block != BlockList.PRESSURE_VESSEL) {
                            this.isFluid = false;
                        }
                        continue;
                    }

                    // a face can be any IReactorBlock
                    if (block.hasTileEntity(worldObj.getBlockMetadata(x, y, z))) {
                        if (worldObj.getTileEntity(x, y, z) instanceof IReactorBlock reactorBlock) {
                            reactorBlock.setReactor(this);
                            reactorBlocks.add(reactorBlock);
                            continue;
                        }
                    }

                    this.isFluid = false;
                }
            }
        }
    }

    // #endregion
}
