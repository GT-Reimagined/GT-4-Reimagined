package org.gtreimagined.gt4r.reactor.tile;

import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.gtreimagined.gt4r.reactor.components.ComponentRegistry;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.fluids.CoolantRegistry;
import org.gtreimagined.gt4r.reactor.fluids.CoolantRegistry.Coolant;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class BlockEntityReactorCore extends BlockEntityBasicMultiMachine<BlockEntityReactorCore> implements IReactorGrid {
    public static final int ROW_COUNT = 6;
    public static final int COL_COUNT = 9;

    private static final int REACTOR_TICK_SPEED = 10;
    private static final int REACTOR_STRUCTURE_CHECK_PERIOD = 10 * 20;


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

    private ArrayList<IReactorBlock> reactorBlocks = new ArrayList<>();

    private double heatRatio = 0;
    public BlockEntityReactorCore(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new MachineFluidHandler<>(this, ));
    }

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

    private static final DamageSource RADIATION_DAMAGE = new DamageSource("gt4r_radiation");

    private static double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    private void doHeatDamage() {
        final int DAMAGE_RADIUS = 3;

        int xCoord = this.getBlockPos().getX();
        int yCoord = this.getBlockPos().getY();
        int zCoord = this.getBlockPos().getZ();

        // flames
        if (heatRatio >= 0.4) {
            for (int i = 0; i < 10; i++) {
                MutableBlockPos pos = this.getBlockPos().mutable();
                int x = xCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int y = yCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int z = zCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);

                var block = level.getBlockState(new BlockPos(x, y, z));

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

    public static class ReactorFluidHandler extends MachineFluidHandler<BlockEntityReactorCore> {
        public ReactorFluidHandler(BlockEntityReactorCore tile) {
            super(tile);
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, SlotType.FL_IN, b -> {
                b.tank(10_000);
                return b;
            }));
            // change for testing distilled water->steam conversion
            // since so much steam is produced per HU, you need a large output buffer to capture useful
            // steam/s production
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, SlotType.FL_OUT, b -> {
                b.tank(200_000);
                return b;
            }));
        }
    }
}
