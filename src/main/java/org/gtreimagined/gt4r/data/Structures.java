package org.gtreimagined.gt4r.data;

import com.gtnewhorizon.structurelib.structure.IStructureElement;
import com.gtnewhorizon.structurelib.structure.StructureUtility;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.structure.FakeTileElement;
import muramasa.antimatter.structure.PatternBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityCokeOven;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityDistillationTower;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityFusionReactor;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityImplosionCompressor;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityIndustrialBlastFurnace;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityIndustrialGrinder;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityIndustrialSawmill;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityLargeTurbine;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityPrimitiveBlastFurnace;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityPyrolysisOven;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityThermalBoiler;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityVacuumFreezer;

import java.util.function.BiFunction;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.NONE;
import static muramasa.antimatter.structure.AntimatterStructureUtility.ofHatch;
import static org.gtreimagined.gt4r.data.Machines.*;

public class Structures {

    /** Special Case Elements **/
    public static IStructureElement<?> AIR_OR_LAVA = ofChain(StructureUtility.isAir(), StructureUtility.ofBlockAdder((w, b) -> b == Blocks.LAVA/* || b == LAVA*/, Blocks.LAVA));
    /*public static BlockStateElement AIR_OR_LAVA = new BlockStateElement("air_or_lava", (w, p, s) -> s.isAir() || s.getFluidState().getType() == Fluids.LAVA*//* || s.getBlock() == Blocks.FLOWING_LAVA*//*);
    public static BlockStateElement AIR = new BlockStateElement("air", (w, p, s) -> s.isAir());
    public static BlockStateElement WATER = new BlockStateElement("water", (w, p, s) -> s.getFluidState().getType() == Fluids.WATER);*/


    public static FakeTileElement<BlockEntityCokeOven> BRICK = new FakeTileElement<>(GT4RBlocks.FIRE_BRICKS);
    public static FakeTileElement<BlockEntityPyrolysisOven> CASING = new FakeTileElement<>(GT4RBlocks.STANDARD_MACHINE_CASING);

    public static void initPatterns() {
        //TODO after patterns are fixed
        COKE_OVEN.setStructurePattern(b -> b
                .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
                .at("C", GT4RBlocks.FIRE_BRICKS.defaultBlockState()).at("M", COKE_OVEN, NONE, Direction.SOUTH)
                .description(COKE_OVEN.getDisplayName(COKE_OVEN.getFirstTier()))
                .build()
        );
        PatternBuilder builder = new PatternBuilder().of("CCC ", "CCM", "CCC ").of("CCC ", "CLC ", "CCC ").of("CCC ", "CCC ", "CCC ")
                .at("M", PYROLYSIS_OVEN, PYROLYSIS_OVEN.getFirstTier(), Direction.SOUTH).at("C", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState());
        PYROLYSIS_OVEN.setStructurePattern(
                builder.at("L", Blocks.LAVA.defaultBlockState()).description(Blocks.LAVA.getDescriptionId()).build()
        );
        builder = new PatternBuilder().of("CCC", "CCC", "CCC").of("CCC", "CBM", "CCC").of("CCC", "CBC", "CCC").of(2)
                .at("C", GT4RBlocks.FIRE_BRICKS.defaultBlockState()).at("M", PRIMITIVE_BLAST_FURNACE, PRIMITIVE_BLAST_FURNACE.getFirstTier(), Direction.SOUTH);
        PRIMITIVE_BLAST_FURNACE.setStructurePattern(
                builder.at("B", Blocks.AIR.defaultBlockState()).description(Blocks.AIR.getDescriptionId()).build()
        );
        builder = new PatternBuilder().of("CCC ", "CCCM", "CCC ").of("CCC ", "CLC ", "CCC ").of(1).of("CCC ", "CCC ", "CCC ")
                .at("M", BLAST_FURNACE, BLAST_FURNACE.getFirstTier(), Direction.SOUTH).scale(0.9f).tip(1, 0, 1, "You can mix and match any of the 3 casings");
        BLAST_FURNACE.setStructurePattern(
                builder.at("C", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState()).at("L", Blocks.LAVA.defaultBlockState()).description(GT4RBlocks.STANDARD_MACHINE_CASING.getDescriptionId() + " With " + Blocks.LAVA.getDescriptionId()).build(),
                builder.at("C", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).at("L", Blocks.LAVA.defaultBlockState()).description(GT4RBlocks.REINFORCED_MACHINE_CASING.getDescriptionId() + " With " + Blocks.LAVA.getDescriptionId()).build(),
                builder.at("C", GT4RBlocks.ADVANCED_MACHINE_CASING.defaultBlockState()).at("L", Blocks.LAVA.defaultBlockState()).description(GT4RBlocks.ADVANCED_MACHINE_CASING.getDescriptionId() + " With " + Blocks.LAVA.getDescriptionId()).build()
        );
        VACUUM_FREEZER.setStructurePattern(b -> b
                .of("CCC", "CcC", "CCC").of("CcC", "cAc", "CcC").of(0).of("   ", " M ", "   ")
                .at("M", VACUUM_FREEZER, VACUUM_FREEZER.getFirstTier(), Direction.SOUTH).at("C", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).at("c", GT4RBlocks.ADVANCED_MACHINE_CASING.defaultBlockState())
                .description(VACUUM_FREEZER.getBlockState(VACUUM_FREEZER.getFirstTier()).getDescriptionId())
                .build()
        );
        IMPLOSION_COMPRESSOR.setStructurePattern(b -> b
                .of("cCc", "CCC", "cCc").of("CCC", "CAC", "CCC").of(0).of("   ", " M ", "   ")
                .at("M", IMPLOSION_COMPRESSOR, IMPLOSION_COMPRESSOR.getFirstTier(), Direction.SOUTH).at("C", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).at("c", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState())
                .description(IMPLOSION_COMPRESSOR.getBlockState(IMPLOSION_COMPRESSOR.getFirstTier()).getDescriptionId())
                .build()
        );
        INDUSTRIAL_GRINDER.setStructurePattern(b -> b
                .of("ccc ", "ccc ", "ccc ").of("CCC ", "CWCM", "CCC ").of(0)
                .at("M", INDUSTRIAL_GRINDER, INDUSTRIAL_GRINDER.getFirstTier(), Direction.SOUTH).at("C", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).at("c", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState()).at("W", Blocks.WATER.defaultBlockState())
                .description(INDUSTRIAL_GRINDER.getBlockState(INDUSTRIAL_GRINDER.getFirstTier()).getDescriptionId())
                .build()
        );
        INDUSTRIAL_SAWMILL.setStructurePattern(b -> b
                .of("ccc", "cCc", "ccc").of("   ", " M ", "   ")
                .at("M", INDUSTRIAL_SAWMILL, INDUSTRIAL_SAWMILL.getFirstTier(), Direction.SOUTH).at("C", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).at("c", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState())
                .description(INDUSTRIAL_SAWMILL.getBlockState(INDUSTRIAL_SAWMILL.getFirstTier()).getDescriptionId())
                .build()
        );
        DISTILLATION_TOWER.setStructurePattern(b -> b
                .of("CCC ", "CCCM", "CCC ").of("ccc ", "c c ", "ccc ").of("CCC ", "C C ", "CCC ").of(1).of("CCC ", "CCC ", "CCC ")
                .at("M", DISTILLATION_TOWER, DISTILLATION_TOWER.getFirstTier(), Direction.SOUTH).at("C", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState()).at("c", GT4RBlocks.ADVANCED_MACHINE_CASING.defaultBlockState())
                .description(DISTILLATION_TOWER.getBlockState(DISTILLATION_TOWER.getFirstTier()).getDescriptionId())
                .build()
        );
        BiFunction<Machine<?>, Block, PatternBuilder> builder2 = (m, b) -> new PatternBuilder().of("CCCC", "CCCC", "CCCC").of("ChhC", "EAAM", "CHHC").of(0)
                .at("M", m, m.getFirstTier(), Direction.SOUTH).at("C", b.defaultBlockState()).at("E", HATCH_DYNAMO, HATCH_DYNAMO.getFirstTier(), Direction.SOUTH);

        LARGE_STEAM_TURBINE.setStructurePattern(
                builder2.apply(LARGE_STEAM_TURBINE, GT4RBlocks.STANDARD_MACHINE_CASING).at("H", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState()).at("h", GT4RBlocks.STANDARD_MACHINE_CASING.defaultBlockState()).description(GT4RBlocks.STANDARD_MACHINE_CASING.getDescriptionId()).build(),
                builder2.apply(LARGE_STEAM_TURBINE, GT4RBlocks.STANDARD_MACHINE_CASING).at("H", HATCH_FLUID_I, LV, Direction.WEST).at("h", HATCH_FLUID_I, LV, Direction.EAST).description(HATCH_FLUID_I.getBlockState(LV).getDescriptionId()).build(),
                builder2.apply(LARGE_STEAM_TURBINE, GT4RBlocks.STANDARD_MACHINE_CASING).at("H", HATCH_FLUID_O, LV, Direction.WEST).at("h", HATCH_FLUID_O, LV, Direction.EAST).description(HATCH_FLUID_O.getBlockState(LV).getDescriptionId()).build(),
                builder2.apply(LARGE_STEAM_TURBINE, GT4RBlocks.STANDARD_MACHINE_CASING).at("H", HATCH_MUFFLER, LV, Direction.WEST).at("h", HATCH_MUFFLER, LV, Direction.EAST).description(HATCH_MUFFLER.getBlockState(LV).getDescriptionId()).build()
        );
        builder = new PatternBuilder().of("CCC", "CHC", "CCC").of("CHC", "HAM", "CHC").of(0).at("M", THERMAL_BOILER, THERMAL_BOILER.getFirstTier(), Direction.SOUTH).at("C", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState());
        THERMAL_BOILER.setStructurePattern(
                builder.at("H", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).description(GT4RBlocks.REINFORCED_MACHINE_CASING.getDescriptionId()).build(),
                builder.at("H", HATCH_FLUID_I, LV, Direction.EAST).description(HATCH_FLUID_I.getBlockState(LV).getDescriptionId()).build(),
                builder.at("H", HATCH_FLUID_O, LV, Direction.EAST).description(HATCH_FLUID_O.getBlockState(LV).getDescriptionId()).build(),
                builder.at("H", HATCH_ITEM_O, LV, Direction.EAST).description(HATCH_ITEM_O.getBlockState(LV).getDescriptionId()).build()
        );
        LARGE_GAS_TURBINE.setStructurePattern(
                builder2.apply(LARGE_GAS_TURBINE, GT4RBlocks.REINFORCED_MACHINE_CASING).at("H", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).at("h", GT4RBlocks.REINFORCED_MACHINE_CASING.defaultBlockState()).description(GT4RBlocks.REINFORCED_MACHINE_CASING.getDescriptionId()).build(),
                builder2.apply(LARGE_GAS_TURBINE, GT4RBlocks.REINFORCED_MACHINE_CASING).at("H", HATCH_FLUID_I, LV, Direction.WEST).at("h", HATCH_FLUID_I, LV, Direction.EAST).description(HATCH_FLUID_I.getBlockState(LV).getDescriptionId()).build(),
                builder2.apply(LARGE_GAS_TURBINE, GT4RBlocks.REINFORCED_MACHINE_CASING).at("H", HATCH_FLUID_O, LV, Direction.WEST).at("h", HATCH_FLUID_O, LV, Direction.EAST).description(HATCH_FLUID_O.getBlockState(LV).getDescriptionId()).build(),
                builder2.apply(LARGE_GAS_TURBINE, GT4RBlocks.REINFORCED_MACHINE_CASING).at("H", HATCH_MUFFLER, LV, Direction.WEST).at("h", HATCH_MUFFLER, LV, Direction.EAST).description(HATCH_MUFFLER.getBlockState(LV).getDescriptionId()).build()
        );
        builder = new PatternBuilder().of(
                "               ",
                "      BOB      ",
                "    OO   OO    ",
                "   O       O   ",
                "  O         O  ",
                "  O         O  ",
                " B           B ",
                " O           O ",
                " B           B ",
                "  O         O  ",
                "  O         O  ",
                "   O       O   ",
                "    OO   OO    ",
                "      BOB      ",
                "               "
        ).of(
                "      HOH      ",
                "    OOCCCOO    ",
                "   ECCHOHCCE   ",
                "  ECEO   OECE  ",
                " OCE       ECO ",
                " OCO       OCO ",
                "HCH         HCH",
                "OCM         OCO",
                "HCH         HCH",
                " OCO       OCO ",
                " OCE       ECO ",
                "  ECEO   OECE  ",
                "   ECCHOHCCE   ",
                "    OOCCCOO    ",
                "      HOH      "
        ).of(0).at("O", GT4RBlocks.ADVANCED_MACHINE_CASING.defaultBlockState()).at("C", GT4RBlocks.FUSION_COIL.defaultBlockState()).at("M", FUSION_REACTOR, FUSION_REACTOR.getFirstTier(), Direction.SOUTH).at("E", FUSION_ENERGY_INJECTOR, FUSION_ENERGY_INJECTOR.getFirstTier(), Direction.SOUTH).scale(0.5f);
        FUSION_REACTOR.setStructurePattern(
                builder.at("B", FUSION_ITEM_INJECTOR, FUSION_ITEM_INJECTOR.getFirstTier(), Direction.SOUTH).at("H", FUSION_ITEM_EXTRACTOR, FUSION_ITEM_EXTRACTOR.getFirstTier(), Direction.SOUTH).description(FUSION_REACTOR.getDisplayName(FUSION_REACTOR.getFirstTier()).getString() + " Item").build(),
                builder.at("B", FUSION_FLUID_INJECTOR, FUSION_FLUID_INJECTOR.getFirstTier(), Direction.SOUTH).at("H", FUSION_FLUID_EXTRACTOR, FUSION_FLUID_EXTRACTOR.getFirstTier(), Direction.SOUTH).description(FUSION_REACTOR.getDisplayName(FUSION_REACTOR.getFirstTier()).getString() + " Fluid").build()
        );
    }

    public static void init() {
        COKE_OVEN.setStructure(BlockEntityCokeOven.class, b -> b.part("main")
            .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
            .at('C', BRICK)
                .offset(1, 1, 0).build()
        );
        PYROLYSIS_OVEN.setStructure(BlockEntityPyrolysisOven.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("CCC", "CLC", "CCC").of("CCC", "C~C", "CCC").build()
                .at('C', CASING).at('L', AIR_OR_LAVA)
                .offset(1, 2, 0).build()
        );
        PRIMITIVE_BLAST_FURNACE.setStructure(BlockEntityPrimitiveBlastFurnace.class, b -> b.part("main")
                .of("CCC", "C-C", "CCC").of("CCC", "CBC", "CCC").of("C~C", "CBC", "CCC").of("CCC", "CCC", "CCC").build()
                .at('C', GT4RBlocks.FIRE_BRICKS).at('B', AIR_OR_LAVA)
                .offset(1, 2, 0).build()
        );
        BLAST_FURNACE.setStructure(BlockEntityIndustrialBlastFurnace.class, b -> b.part("main")
            .of("   ","CCC", "CCC", "CCC").of("   ", "CCC", "CLC", "CCC").of(1).of(" ~ ","CCC", "CCC", "CCC").build()
            .atElement('C', onElementPass((e, c, w, x, y, z) -> {
                c.incrementBaseHeatingCapacity(c.getHeatPerCasing(w.getBlockState(new BlockPos(x,y,z)).getBlock()));
            } , ofChain(
                    ofBlock(GT4RBlocks.STANDARD_MACHINE_CASING),
                    ofBlock(GT4RBlocks.REINFORCED_MACHINE_CASING),
                    ofBlock(GT4RBlocks.ADVANCED_MACHINE_CASING)))).atElement('L', onElementPass((e, c, w, x, y, z) -> {
                    c.incrementBaseHeatingCapacity(c.getHeatPerCasing(w.getBlockState(new BlockPos(x,y,z)).getBlock()));
                }, (IStructureElement<BlockEntityIndustrialBlastFurnace>) AIR_OR_LAVA))
                .offset(1,3,0).build()
        );
        VACUUM_FREEZER.setStructure(BlockEntityVacuumFreezer.class, b -> b.part("main")
                .of("   ", " ~ ", "   ").of("CCC", "CcC", "CCC").of("CcC", "c-c", "CcC").of(1).build()
            .at('C', GT4RBlocks.REINFORCED_MACHINE_CASING).at('c', GT4RBlocks.ADVANCED_MACHINE_CASING)
                .offset(1,0,-1).build()
        );
        IMPLOSION_COMPRESSOR.setStructure(BlockEntityImplosionCompressor.class, b -> b.part("main")
                .of("   ", " ~ ", "   ").of("cCc", "CCC", "cCc").of("CCC", "C-C", "CCC").of(1).build()
                .at('C', GT4RBlocks.REINFORCED_MACHINE_CASING).at('c', GT4RBlocks.STANDARD_MACHINE_CASING)
                .offset(1,0,-1).build()
        );
        INDUSTRIAL_GRINDER.setStructure(BlockEntityIndustrialGrinder.class, b -> b.part("main")
                .of("   ","ccc", "ccc", "ccc").of(" ~ ", "CCC", "CWC", "CCC").of(0).build()
                .at('C', GT4RBlocks.REINFORCED_MACHINE_CASING).at('c', GT4RBlocks.STANDARD_MACHINE_CASING).at('W', StructureUtility.ofBlock(Blocks.WATER))
                .offset(1,1, 0).build()
        );
        INDUSTRIAL_SAWMILL.setStructure(BlockEntityIndustrialSawmill.class, b -> b.part("main")
                .of("   ", " ~ ", "   ").of("ccc", "cCc", "ccc").build()
                .at('C', GT4RBlocks.REINFORCED_MACHINE_CASING).at('c', GT4RBlocks.STANDARD_MACHINE_CASING)
                .offset(1, 0, -1).build()
        );
        DISTILLATION_TOWER.setStructure(BlockEntityDistillationTower.class, b -> b.part("main")
                .of("   ","ccc", "c c", "ccc").of("   ","CCC", "C-C", "CCC").of(1).of("   ","CCC", "CCC", "CCC").of(" ~ ","CCC", "CCC", "CCC").build()
                .at('C', GT4RBlocks.STANDARD_MACHINE_CASING).at('c', GT4RBlocks.ADVANCED_MACHINE_CASING)
                .offset(1, 4, 0).build()
        );
        THERMAL_BOILER.setStructure(BlockEntityThermalBoiler.class, b -> b.part("main")
                .of("CCC", "CHC", "CCC").of("C~C", "H-H", "CHC").of(0).build()
                .at('C', GT4RBlocks.REINFORCED_MACHINE_CASING).at('H', GT4RBlocks.REINFORCED_MACHINE_CASING, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ITEM_O)
                .min(2, HATCH_FLUID_I).min(1, HATCH_FLUID_O).offset(1,1,0).build());
        LARGE_GAS_TURBINE.setStructure(BlockEntityLargeTurbine.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC", "CCC").of("C~C", "H-H", "H-H", "CEC").of(0).build()
                .atElement('C', StructureUtility.lazy(t -> ofBlock(t.getCasing())))
                .atElement('H', StructureUtility.<BlockEntityLargeTurbine>ofChain(
                        StructureUtility.lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(HATCH_FLUID_I),
                        ofHatch(HATCH_FLUID_O)))
                .atElement('E', ofHatch(HATCH_DYNAMO, (t, world, pos, machine, handler) -> {
                    if (handler.getTile() instanceof BlockEntityMachine<?> entityMachine){
                        if (t.getMachineTier().getVoltage() <= entityMachine.getMachineTier().getVoltage()){
                            t.addComponent(machine.getId(), handler);
                            return true;
                        }
                    }
                    return false;
                }))
                .min(1, HATCH_FLUID_I, HATCH_FLUID_O).offset(1, 1, 0).build()
        );

        LARGE_STEAM_TURBINE.setStructure(BlockEntityLargeTurbine.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC", "CCC").of("C~C", "H-H", "H-H", "CEC").of(0).build()
                .atElement('C', StructureUtility.lazy(t -> ofBlock(t.getCasing())))
                .atElement('H', StructureUtility.<BlockEntityLargeTurbine>ofChain(
                        StructureUtility.lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(HATCH_FLUID_I),
                        ofHatch(HATCH_FLUID_O)))
                .atElement('E', ofHatch(HATCH_DYNAMO, (t, world, pos, machine, handler) -> {
                    if (handler.getTile() instanceof BlockEntityMachine<?> entityMachine){
                        if (t.getMachineTier().getVoltage() <= entityMachine.getMachineTier().getVoltage()){
                            t.addComponent(machine.getId(), handler);
                            return true;
                        }
                    }
                    return false;
                }))
                .min(1, HATCH_FLUID_I, HATCH_FLUID_O).offset(1, 1, 0).build()
        );
        FUSION_REACTOR.setStructure(BlockEntityFusionReactor.class, b -> b.part("main")
                .of(
                        "               ",
                        "      BOB      ",
                        "    OO   OO    ",
                        "   O       O   ",
                        "  O         O  ",
                        "  O         O  ",
                        " B           B ",
                        " O           O ",
                        " B           B ",
                        "  O         O  ",
                        "  O         O  ",
                        "   O       O   ",
                        "    OO   OO    ",
                        "      BOB      ",
                        "               "
                ).of("      XOX      ",
                        "    OOCCCOO    ",
                        "   ECCXOXCCE   ",
                        "  ECEO   OECE  ",
                        " OCE       ECO ",
                        " OCO       OCO ",
                        "XCX         XCX",
                        "OCO         OCO",
                        "XCX         XCX",
                        " OCO       OCO ",
                        " OCE       ECO ",
                        "  ECEO   OECE  ",
                        "   ECCX~XCCE   ",
                        "    OOCCCOO    ",
                        "      XOX      ").of(0).build()
                .at('O', GT4RBlocks.ADVANCED_MACHINE_CASING).at('C', GT4RBlocks.FUSION_COIL)
                .at('B', GT4RBlocks.ADVANCED_MACHINE_CASING, FUSION_ITEM_INJECTOR, FUSION_FLUID_INJECTOR)
                .at('X', GT4RBlocks.ADVANCED_MACHINE_CASING, FUSION_ITEM_EXTRACTOR, FUSION_FLUID_EXTRACTOR)
                .at('E', GT4RBlocks.ADVANCED_MACHINE_CASING, FUSION_ENERGY_INJECTOR)
                .offset(7, 1, 12).min(2, FUSION_FLUID_INJECTOR).min(1, FUSION_FLUID_EXTRACTOR).min(4, FUSION_ENERGY_INJECTOR).build());
    }
}
