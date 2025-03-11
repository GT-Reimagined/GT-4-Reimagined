package org.gtreimagined.gt4r.reactor.tile;

import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.FluidUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.gtreimagined.gt4r.reactor.Config;
import org.gtreimagined.gt4r.reactor.components.ComponentRegistry;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.fluids.CoolantRegistry;
import org.gtreimagined.gt4r.reactor.fluids.CoolantRegistry.Coolant;

import javax.annotation.Nullable;
import java.util.ArrayList;

import static org.gtreimagined.gt4r.data.Materials.DistilledWater;

public class BlockEntityReactorCore extends BlockEntityBasicMultiMachine<BlockEntityReactorCore> implements IReactorGrid {
    public static final int ROW_COUNT = 6;
    public static final int COL_COUNT = 9;

    private static final int REACTOR_TICK_SPEED = 10;
    private static final int REACTOR_STRUCTURE_CHECK_PERIOD = 10 * 20;


    private int chambers = 0;
    private IComponentAdapter[] components = new IComponentAdapter[ROW_COUNT * COL_COUNT];

    private int tickCounter = 0;

    boolean isActive = false;
    boolean isFluid = false;

    int storedHeat = 0;
    int addedHeat = 0;
    int roundedHeat = 0;

    int addedEU = 0;

    private Integer hullHeatCache = null;

    Coolant coolantCache;

    private ArrayList<IReactorBlock> reactorBlocks = new ArrayList<>();

    private double heatRatio = 0;
    public BlockEntityReactorCore(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new ReactorFluidHandler(this));
        this.energyHandler.set(() -> new MachineEnergyHandler<>(this, 0L, 4_194_304L, 0L, 0L, 0, 1));
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
            energyHandler.ifPresent(e -> {
                e.setCapacty(0L);
                e.extractEu(e.getEnergy(), false);
                e.setOutputVoltage(32L);
            });
        }

        if (this.addedEU > 0) {
            int perTick = this.addedEU / 20;

            int voltageTier = (int) (Math.ceil(Math.log(perTick / 8) / Math.log(4)));

            energyHandler.ifPresent(e -> {
                int voltage = (int) (Math.pow(4, voltageTier) * 8);
                e.setOutputVoltage(voltage);
                e.setCapacty(voltage * 20 * 30L);
            });
        }

        energyHandler.ifPresent(e -> {
            if (e.getEnergy() > e.getCapacity()){
                e.extractEu(e.getEnergy() - e.getCapacity(), false);
            }
        });
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
                int x = xCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int y = yCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);
                int z = zCoord + (int) map(Math.random(), 0, 1, -DAMAGE_RADIUS, DAMAGE_RADIUS);

                BlockPos pos = new BlockPos(x, y, z);
                var block = level.getBlockState(pos);

                if (block.isFlammable(level, pos, null)){
                    level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 2);
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

                BlockPos pos = new BlockPos(x, y, z);
                var fluid = level.getFluidState(pos);

                if (FluidUtils.getFluidTemperature(fluid.getType()) < 375){
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                    break;
                }
            }
        }

        // damage
        if (heatRatio >= 0.7) {
            var entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    new AABB(
                            xCoord - DAMAGE_RADIUS,
                            yCoord - DAMAGE_RADIUS,
                            zCoord - DAMAGE_RADIUS,
                            xCoord + DAMAGE_RADIUS,
                            yCoord + DAMAGE_RADIUS,
                            zCoord + DAMAGE_RADIUS));

            for (var entity : entities) {
                entity.hurt(RADIATION_DAMAGE, 4);
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


                BlockPos pos = new BlockPos(x, y, z);
                var block = level.getBlockState(pos);
                if (!block.isAir() && block.getDestroySpeed(level, pos) < 5){
                    level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
                    level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                    break;
                }
            }
        }

        // explosion
        if (heatRatio >= 1) {
            level.explode(
                    null,
                    xCoord + 0.5,
                    yCoord + 0.5,
                    zCoord + 0.5,
                    (float) (30 * getExplosionRadiusMultiplier()),
                    true,
                    BlockInteraction.DESTROY);
        }
    }

    /*@Override
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
    }*/

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

        int index = x * ROW_COUNT + y;

        var adapter = this.components[index];
        if (adapter != null) {
            return adapter;
        }

        var item = this.itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getStackInSlot(index)).orElse(ItemStack.EMPTY);
        if (!item.isEmpty()) {
            adapter = ComponentRegistry.getAdapter(item, this, x, y);
            this.components[index] = adapter;
        }

        return adapter;
    }

    @Override
    public ItemStack getItem(int x, int y) {
        if (x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(
                    String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if (y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(
                    String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = x * ROW_COUNT + y;

        return this.itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getStackInSlot(index)).orElse(ItemStack.EMPTY);
    }

    @Override
    public void setItem(int x, int y, ItemStack item) {
        if (x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(
                    String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if (y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(
                    String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = x * ROW_COUNT + y;

        this.itemHandler.ifPresent(i -> i.getHandler(SlotType.STORAGE).setStackInSlot(index, item));
        this.components[index] = !item.isEmpty() && ComponentRegistry.isReactorItem(item)
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
            return this.fluidHandler.map(f -> {
                FluidTank coolantTank = f.getInputTanks().getTank(0);
                FluidTank hotCoolantTank = f.getOutputTanks().getTank(0);
                if (coolantTank == null) return 0;
                if (coolantTank.getFluidAmount() == 0) {
                    return airHeat;
                }

                // cache this because it will be called several times a second
                if ((coolantCache == null || coolantCache.cold != coolantTank.getFluid()
                        .getFluid())) {
                    if (coolantTank.getFluidAmount() == 0) {
                        coolantCache = null;
                    } else {
                        coolantCache = CoolantRegistry.getCoolantInfo(
                                coolantTank.getFluid()
                                        .getFluid());
                    }
                }

                if (coolantCache == null) {
                    return airHeat;
                }

                this.roundedHeat += airHeat * Config.FLUID_NUKE_HU_MULTIPLIER;

                int heatableCoolant = Math.min(
                        coolantTank.getFluidAmount(),
                        hotCoolantTank.getCapacity() - hotCoolantTank.getFluidAmount());

                int consumedCoolant;
                // BWR
                if (this.coolantCache.cold.is(DistilledWater.getFluidTag())) {
                    consumedCoolant = Math.min(
                            roundedHeat / (coolantCache.specificHeatCapacity),
                            Math.min(
                                    coolantTank.getFluidAmount(),
                                    (hotCoolantTank.getCapacity() - hotCoolantTank.getFluidAmount())
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
                if (this.coolantCache.cold.is(DistilledWater.getFluidTag())) {
                    coolantTank.drain(consumedCoolant, FluidAction.EXECUTE);
                    hotCoolantTank
                            .fill(new FluidStack(coolantCache.hot, consumedCoolant * Config.BWR_STEAM_PER_HU_MULTIPLIER), FluidAction.EXECUTE);
                } else {
                    coolantTank.drain(consumedCoolant, FluidAction.EXECUTE);
                    hotCoolantTank.fill(new FluidStack(coolantCache.hot, consumedCoolant), FluidAction.EXECUTE);
                }

                return 0;
            }).orElse(0);
        } else {
            return 0;
        }
    }

    @Override
    public void addEU(double eu) {
        if (!isFluid) {
            this.energyHandler.ifPresent(e -> e.insertInternal((long) (eu * Config.REACTOR_EU_MULTIPLIER), false));
            this.addedEU += (int) (eu * Config.REACTOR_EU_MULTIPLIER);
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

        @Override
        public boolean canInput() {
            return super.canInput() && tile.isFluid;
        }
    }
}
