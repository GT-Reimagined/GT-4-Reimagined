package org.gtreimagined.gt4r.data;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.fluid.GTFluid;
import org.gtreimagined.gtlib.machine.Tier;
import org.gtreimagined.gtlib.pipe.PipeSize;
import org.gtreimagined.gtlib.pipe.types.Cable;
import org.gtreimagined.gtlib.pipe.types.FluidPipe;
import org.gtreimagined.gtlib.pipe.types.ItemPipe;
import org.gtreimagined.gtlib.pipe.types.Wire;
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

import static org.gtreimagined.gtlib.fluid.GTFluid.OVERLAY_TEXTURE;
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
    public static final BlockColoredWall WOOD_WALL = new BlockColoredWall(GT4RRef.ID, GTLibMaterials.Wood, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final BlockColoredWall STEEL_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Steel);
    public static final BlockColoredWall INVAR_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Invar);
    public static final BlockColoredWall STAINLESS_STEEL_WALL = new BlockColoredWall(GT4RRef.ID, Materials.StainlessSteel);
    public static final BlockColoredWall TITANIUM_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Titanium);
    public static final BlockColoredWall NETHERITE_WALL = new BlockColoredWall(GT4RRef.ID, GTLibMaterials.Netherite);
    public static final BlockColoredWall TUNGSTENSTEEL_WALL = new BlockColoredWall(GT4RRef.ID, Materials.TungstenSteel);
    public static final BlockColoredWall TUNGSTEN_WALL = new BlockColoredWall(GT4RRef.ID, Materials.Tungsten);
    public static final Cable<?> CABLE_SOLDERING_ALLOY = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, SolderingAlloy, 0.02, Tier.ULV).amps(1));
    public static final Cable<?> CABLE_LEAD = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Lead, 2, Tier.LV).amps(2));
    public static final Cable<?> CABLE_TIN = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Tin, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_COPPER = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, GTLibMaterials.Copper, 2, Tier.MV).amps(1));
    public static final Cable<?> CABLE_NICKEL = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Nickel, 3, Tier.MV).amps(3));
    public static final Cable<?> CABLE_GOLD = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, GTLibMaterials.Gold, 2, Tier.HV).amps(3));
    public static final Cable<?> CABLE_ELECTRUM = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Electrum, 2, Tier.HV).amps(2));
    public static final Cable<?> CABLE_STEEL = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Steel, 2, Tier.EV).amps(2));
    public static final Cable<?> CABLE_ALUMINIUM = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Aluminium, 1, Tier.EV).amps(1));
    public static final Cable<?> CABLE_OSMIUM = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Osmium, 2, Tier.IV).amps(4));
    public static final Cable<?> CABLE_TUNGSTEN = GTAPI.register(Cable.class, new Cable<>(GT4RRef.ID, Tungsten, 2, Tier.IV).amps(2));
    public static final Wire<?> WIRE_SOLDERING_ALLOY = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, SolderingAlloy, 0.04, Tier.ULV).amps(1));
    public static final Wire<?> WIRE_LEAD = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Lead, 4, Tier.LV).amps(2));
    public static final Wire<?> WIRE_TIN = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Tin, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_COPPER = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, GTLibMaterials.Copper, 4, Tier.MV).amps(1));
    public static final Wire<?> WIRE_NICKEL = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Nickel, 6, Tier.MV).amps(3));
    public static final Wire<?> WIRE_GOLD = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, GTLibMaterials.Gold, 6, Tier.HV).amps(3));
    public static final Wire<?> WIRE_ELECTRUM = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Electrum, 4, Tier.HV).amps(2));
    public static final Wire<?> WIRE_STEEL = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Steel, 4, Tier.EV).amps(2));
    public static final Wire<?> WIRE_ALUMINIUM = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Aluminium, 1, Tier.EV).amps(1));
    public static final Wire<?> WIRE_OSMIUM = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Osmium, 4, Tier.IV).amps(4));
    public static final Wire<?> WIRE_TUNGSTEN = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Tungsten, 4, Tier.IV).amps(2));
    public static final Wire<?> WIRE_SUPERCONDUCTOR = GTAPI.register(Wire.class, new Wire<>(GT4RRef.ID, Superconductor, 0, Tier.MAX).amps(4)); //MAX
    public static final FluidPipe<?> FLUID_PIPE_WOOD = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, GTLibMaterials.Wood, 350, false).caps(1).pressures(getPressures(150)));
    public static final FluidPipe<?> FLUID_PIPE_PLASTIC = GTAPI.register(FluidPipe.class, new FluidPipe<>(GT4RRef.ID, Plastic, 370, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_COPPER = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, GTLibMaterials.Copper, 1696, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_BRONZE = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Bronze, 1696, true).caps(1).pressures(getPressures(450)));
    public static final FluidPipe<?> FLUID_PIPE_INVAR = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Invar, 2395, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STEEL = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Steel, 2557, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STAINLESS_STEEL = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, StainlessSteel, 2428, true).caps(1).pressures(getPressures(750)));
    public static final FluidPipe<?> FLUID_PIPE_NETHERRITE = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, GTLibMaterials.Netherite, 2807, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, Tungsten, 4618, true).caps(1).pressures(getPressures(1050)));
    public static final FluidPipe<?> FLUID_PIPE_TITANIUM = GTAPI.register(FluidPipe.class, new FluidPipe<>(GT4RRef.ID, Titanium, 1668, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_STEEL = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, TungstenSteel, 3587, true).caps(1).pressures(getPressures(1200)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_CARBIDE = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, TungstenCarbide, 3837, true).caps(1).pressures(getPressures(1350)));
    public static final FluidPipe<?> FLUID_PIPE_HP = GTAPI.register(FluidPipe.class,new FluidPipe<>(GT4RRef.ID, HighPressure, 3422, true).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(1).pressures(10000));
    public static final ItemPipe<?> ITEM_PIPE_BRASS = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Brass).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_CUPRONICKEL = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Cupronickel).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_ELECTRUM = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Electrum).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_MAGNALIUM = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Magnalium).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_PLATINUM = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Platinum).stepsize(8192).caps(0, 0, 0, 4, 8, 16));
    public static final ItemPipe<?> ITEM_PIPE_OSMIUM = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Osmium).stepsize(4096).caps(0, 0, 0, 8, 16, 32));
    public static final ItemPipe<?> ITEM_PIPE_HC = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, HighCapacity).stepsize(4096).caps(64));
    public static final ItemPipe<?> ITEM_PIPE_OSMIRIDIUM = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, Osmiridium).stepsize(1024).caps(0, 0, 0, 32, 64, 128));
    public static final GTFluid PAHOEHOE_LAVA = GTAPI.register(GTFluid.class, new GTFluid(GT4RRef.ID,"pahoehoe_lava", prepareAttributes(), prepareProperties()));

    public static void init(){
        if (GTAPI.isModLoaded(GT4RRef.MOD_TFC)){
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
        public static final ItemPipe<?> ITEM_PIPE_STERLING_SILVER = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, SterlingSilver).caps(0, 0, 0, 2, 4, 8));
        public static final ItemPipe<?> ITEM_PIPE_ROSE_GOLD = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, RoseGold).caps(0, 0, 0, 2, 4, 8));
        public static final ItemPipe<?> ITEM_PIPE_BLACK_BRONZE = GTAPI.register(ItemPipe.class, new ItemPipe<>(GT4RRef.ID, BlackBronze).caps(0, 0, 0, 2, 4, 8));
        public static void init(){
            //NOOP
        }
    }
}
