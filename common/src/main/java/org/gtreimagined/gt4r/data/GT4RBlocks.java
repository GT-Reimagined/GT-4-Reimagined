package org.gtreimagined.gt4r.data;

import earth.terrarium.botarium.common.registry.fluid.FluidProperties;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fluids.FluidAttributes;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.block.BlockCasing;
import org.gtreimagined.gt4r.block.BlockColoredWall;
import org.gtreimagined.gt4r.block.BlockFakeCasing;

import static muramasa.antimatter.fluid.AntimatterFluid.OVERLAY_TEXTURE;
import static org.gtreimagined.gt4r.data.Materials.*;

public class GT4RBlocks {
    public static final BlockFakeCasing STANDARD_MACHINE_CASING = new BlockFakeCasing(GT4RRef.ID, "standard_machine_casing");
    public static final BlockCasing REINFORCED_MACHINE_CASING = new BlockCasing(GT4RRef.ID, "reinforced_machine_casing");
    public static final BlockCasing ADVANCED_MACHINE_CASING = new BlockCasing(GT4RRef.ID, "advanced_machine_casing");
    public static final BlockCasing TUNGSTENSTEEL_REINFORCED_STONE = new BlockCasing(GT4RRef.ID, "tungstensteel_reinforced_stone");
    public static final BlockCasing IRIDIUM_TUNGSTENSTEEL_REINFORCED_STONE = new BlockCasing(GT4RRef.ID, "iridium_tungstensteel_reinforced_stone");
    public static final BlockCasing HIGHLY_ADVANCED_MACHINE_BLOCK = new BlockCasing(GT4RRef.ID, "highly_advanced_machine_block");
    public static final BlockFakeCasing FIRE_BRICKS = new BlockFakeCasing(GT4RRef.ID, "fire_bricks", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DIRT).strength(1.0f, 10.0f).sound(SoundType.STONE));
    public static final BlockCasing IRIDIUM_REINFORCED_STONE = new BlockCasing(GT4RRef.ID, "iridium_reinforced_stone", Block.Properties.of(Material.STONE).strength(80.0f, 150.0f).sound(SoundType.STONE).requiresCorrectToolForDrops());
    public static final BlockCasing FUSION_COIL = new BlockCasing(GT4RRef.ID, "fusion_coil");
    public static final BlockColoredWall WOOD_WALL = new BlockColoredWall(GT4RRef.ID, AntimatterMaterials.Wood, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final BlockColoredWall STEEL_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Steel);
    public static final BlockColoredWall INVAR_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Invar);
    public static final BlockColoredWall STAINLESS_STEEL_WALL = new BlockColoredWall(GT4RRef.ID, Materials.StainlessSteel);
    public static final BlockColoredWall TITANIUM_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Titanium);
    public static final BlockColoredWall NETHERITE_WALL = new BlockColoredWall(GT4RRef.ID, AntimatterMaterials.Netherite);
    public static final BlockColoredWall TUNGSTENSTEEL_WALL = new BlockColoredWall(GT4RRef.ID, Materials.TungstenSteel);
    public static final BlockColoredWall TUNGSTEN_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Tungsten);
    public static final Cable<?> CABLE_SOLDERING_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, SolderingAlloy, 0.02, Tier.ULV).amps(1));
    public static final Cable<?> CABLE_LEAD = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Lead, 2, Tier.LV).amps(2));
    public static final Cable<?> CABLE_TIN = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Tin, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_COPPER = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, AntimatterMaterials.Copper, 2, Tier.MV).amps(1));
    public static final Cable<?> CABLE_NICKEL = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Nickel, 3, Tier.MV).amps(3));
    public static final Cable<?> CABLE_GOLD = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, AntimatterMaterials.Gold, 2, Tier.HV).amps(3));
    public static final Cable<?> CABLE_ELECTRUM = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Electrum, 2, Tier.HV).amps(2));
    public static final Cable<?> CABLE_STEEL = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Steel, 2, Tier.EV).amps(2));
    public static final Cable<?> CABLE_ALUMINIUM = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Aluminium, 1, Tier.EV).amps(1));
    public static final Cable<?> CABLE_OSMIUM = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Osmium, 2, Tier.IV).amps(4));
    public static final Cable<?> CABLE_TUNGSTEN = AntimatterAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Tungsten, 2, Tier.IV).amps(2));
    public static final Wire<?> WIRE_SOLDERING_ALLOY = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, SolderingAlloy, 0.04, Tier.ULV).amps(1));
    public static final Wire<?> WIRE_LEAD = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Lead, 4, Tier.LV).amps(2));
    public static final Wire<?> WIRE_TIN = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Tin, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_COPPER = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, AntimatterMaterials.Copper, 4, Tier.MV).amps(1));
    public static final Wire<?> WIRE_NICKEL = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Nickel, 6, Tier.MV).amps(3));
    public static final Wire<?> WIRE_GOLD = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, AntimatterMaterials.Gold, 6, Tier.HV).amps(3));
    public static final Wire<?> WIRE_ELECTRUM = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Electrum, 4, Tier.HV).amps(2));
    public static final Wire<?> WIRE_STEEL = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Steel, 4, Tier.EV).amps(2));
    public static final Wire<?> WIRE_ALUMINIUM = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Aluminium, 1, Tier.EV).amps(1));
    public static final Wire<?> WIRE_OSMIUM = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Osmium, 4, Tier.IV).amps(4));
    public static final Wire<?> WIRE_TUNGSTEN = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Tungsten, 4, Tier.IV).amps(2));
    public static final Wire<?> WIRE_SUPERCONDUCTOR = AntimatterAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Superconductor, 0, Tier.MAX).amps(4)); //MAX
    public static final FluidPipe<?> FLUID_PIPE_WOOD = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, AntimatterMaterials.Wood, 350, false).caps(1).pressures(getPressures(150)));
    public static final FluidPipe<?> FLUID_PIPE_PLASTIC = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GT4RRef.ID, Plastic, 370, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_COPPER = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, AntimatterMaterials.Copper, 1696, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_BRONZE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Bronze, 1696, true).caps(1).pressures(getPressures(450)));
    public static final FluidPipe<?> FLUID_PIPE_INVAR = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Invar, 2395, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Steel, 2557, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STAINLESS_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, StainlessSteel, 2428, true).caps(1).pressures(getPressures(750)));
    public static final FluidPipe<?> FLUID_PIPE_NETHERRITE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, AntimatterMaterials.Netherite, 2807, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Tungsten, 4618, true).caps(1).pressures(getPressures(1050)));
    public static final FluidPipe<?> FLUID_PIPE_TITANIUM = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GT4RRef.ID, Titanium, 1668, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, TungstenSteel, 3587, true).caps(1).pressures(getPressures(1200)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_CARBIDE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, TungstenCarbide, 3837, true).caps(1).pressures(getPressures(1350)));
    public static final FluidPipe<?> FLUID_PIPE_HP = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, HighPressure, 3422, true).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(1).pressures(10000));
    public static final ItemPipe<?> ITEM_PIPE_BRASS = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Brass).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_CUPRONICKEL = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Cupronickel).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_ELECTRUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Electrum).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_MAGNALIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Magnalium).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_PLATINUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Platinum).stepsize(8192).caps(0, 0, 0, 4, 8, 16));
    public static final ItemPipe<?> ITEM_PIPE_OSMIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Osmium).stepsize(4096).caps(0, 0, 0, 8, 16, 32));
    public static final ItemPipe<?> ITEM_PIPE_HC = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, HighCapacity).stepsize(4096).caps(64));
    public static final ItemPipe<?> ITEM_PIPE_OSMIRIDIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Osmiridium).stepsize(1024).caps(0, 0, 0, 32, 64, 128));
    public static final AntimatterFluid PAHOEHOE_LAVA = AntimatterAPI.register(AntimatterFluid.class, new AntimatterFluid(GT4RRef.ID,"pahoehoe_lava", prepareAttributes(), prepareProperties()));

    public static void init(){
        if (AntimatterAPI.isModLoaded(GT4RRef.MOD_TFC)){
            GT4RBlocks.TFCData.init();
        }
    }

    private static int[] getPressures(int basePressure){
        basePressure *= 20;
        return new int[]{basePressure / 6, basePressure / 6, basePressure / 3, basePressure, basePressure * 2, basePressure * 4};
    }

    private static FluidAttributes.Builder prepareAttributes() {
        return FluidAttributes.builder(PAHOEHOE_STILL_TEXTURE, PAHOEHOE_STILL_TEXTURE).overlay(OVERLAY_TEXTURE)
                .viscosity(3000).density(6000).temperature(1200).sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY);
    }

    private static Block.Properties prepareProperties() {
        return Block.Properties.of(Material.WATER).strength(100.0F).noDrops().lightLevel(s -> 9);
    }

    public static class TFCData{
        public static final ItemPipe<?> ITEM_PIPE_STERLING_SILVER = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, SterlingSilver).caps(0, 0, 0, 2, 4, 8));
        public static final ItemPipe<?> ITEM_PIPE_ROSE_GOLD = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, RoseGold).caps(0, 0, 0, 2, 4, 8));
        public static final ItemPipe<?> ITEM_PIPE_BLACK_BRONZE = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, BlackBronze).caps(0, 0, 0, 2, 4, 8));
        public static void init(){
            //NOOP
        }
    }
}
