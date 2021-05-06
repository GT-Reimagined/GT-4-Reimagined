package trinsdar.gt4r.data;

import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.structure.BlockStateElement;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;

import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.*;

public class Structures {

    /** Special Case Elements **/
    public static BlockStateElement AIR_OR_LAVA = new BlockStateElement("air_or_lava", (w, p, s) -> s.getBlock().isAir(s, w, p) || s.getFluidState().getFluid() == Fluids.LAVA/* || s.getBlock() == Blocks.FLOWING_LAVA*/);
    public static BlockStateElement AIR = new BlockStateElement("air", (w, p, s) -> s.getBlock().isAir(s, w, p));
    public static BlockStateElement WATER = new BlockStateElement("water", (w, p, s) -> s.getFluidState().getFluid() == Fluids.WATER);


    public static ComponentIdWrapper BRICK_WRAPPER = new ComponentIdWrapper("slave_controller");

    public static void init() {
        COKE_OVEN.setStructure(b -> b
            .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
            .at("C", "slave_controller", BRICK_WRAPPER, FIRE_BRICKS).at("M", COKE_OVEN)
            .build().offset(2, -1)
        );
        PRIMITIVE_BLAST_FURNACE.setStructure(b -> b
            .of("CCC", "CCC", "CCC").of("CCC", "CBM", "CCC").of("CCC", "CBC", "CCC").of(2).of("CCC", "CAC", "CCC")
            .at("C", "slave_controller", BRICK_WRAPPER, FIRE_BRICKS).at("B", AIR_OR_LAVA).at("M", PRIMITIVE_BLAST_FURNACE).at("A", AIR)
            .build().offset(2, -1)
        );
        BLAST_FURNACE.setStructure(b -> b
            .of("CCC ", "CCCM", "CCC ").of("CCC ", "CLC ", "CCC ").of(1).of("CCC ", "CCC ", "CCC ")
            .at("M", BLAST_FURNACE).at("C", STANDARD_MACHINE_CASING, REINFORCED_MACHINE_CASING, ADVANCED_MACHINE_CASING).at("L", AIR_OR_LAVA)
            .build().offset(3, 0)
        );
        VACUUM_FREEZER.setStructure(b -> b
            .of("CCC", "CcC", "CCC").of("CcC", "cAc", "CcC").of(0).of("   ", " M ", "   ")
            .at("M", VACUUM_FREEZER).at("C", REINFORCED_MACHINE_CASING).at("c", ADVANCED_MACHINE_CASING)
            .build().offset(1, -3).min(20, REINFORCED_MACHINE_CASING).min(6, ADVANCED_MACHINE_CASING)
        );
        IMPLOSION_COMPRESSOR.setStructure(b -> b
            .of("cCc", "CCC", "cCc").of("CCC", "CAC", "CCC").of(0).of("AAA", "AMA", "AAA")
            .at("M", IMPLOSION_COMPRESSOR).at("C", REINFORCED_MACHINE_CASING).at("c", STANDARD_MACHINE_CASING)
            .build().offset(1, -3).min(18, REINFORCED_MACHINE_CASING).min(8, STANDARD_MACHINE_CASING)
        );
        INDUSTRIAL_GRINDER.setStructure(b -> b
                .of("ccc ", "ccc ", "ccc ").of("CCC ", "CWCM", "CCC ").of(0)
                .at("M", INDUSTRIAL_GRINDER).at("C", REINFORCED_MACHINE_CASING).at("c", STANDARD_MACHINE_CASING).at("W", WATER)
                .build().offset(3, -1)
        );
        INDUSTRIAL_SAWMILL.setStructure(b -> b
                .of("ccc", "cCc", "ccc").of("   ", " M ", "   ")
                .at("M", INDUSTRIAL_SAWMILL).at("C", REINFORCED_MACHINE_CASING).at("c", STANDARD_MACHINE_CASING)
                .build().offset(1, -1)
        );
        DISTILLATION_TOWER.setStructure(b -> b
                .of("CCC ", "CCCM", "CCC ").of("ccc ", "c c ", "ccc ").of("CCC ", "C C ", "CCC ").of(1).of("CCC ", "CCC ", "CCC ")
                .at("M", DISTILLATION_TOWER).at("C", STANDARD_MACHINE_CASING).at("c", ADVANCED_MACHINE_CASING)
                .build().offset(3, 0)
        );
        LARGE_STEAM_TURBINE.setStructure(b -> b
            .of("CCCC", "CCCC", "CCCC").of("CHHC", "EAAM", "CHHC").of(0)
            .at("M", LARGE_STEAM_TURBINE).at("C", STANDARD_MACHINE_CASING).at("H", STANDARD_MACHINE_CASING, HATCH_FLUID_I, HATCH_FLUID_O).at("E", HATCH_DYNAMO)
            .build().offset(3, -1).min(28, STANDARD_MACHINE_CASING).min(1, HATCH_FLUID_I, HATCH_FLUID_O)
        );
        LARGE_GAS_TURBINE.setStructure(b -> b
                .of("CCCC", "CCCC", "CCCC").of("CHHC", "EAAM", "CHHC").of(0)
                .at("M", LARGE_GAS_TURBINE).at("C", REINFORCED_MACHINE_CASING).at("H", STANDARD_MACHINE_CASING, HATCH_FLUID_I, HATCH_FLUID_O).at("E", HATCH_DYNAMO)
                .build().offset(3, -1).min(28, STANDARD_MACHINE_CASING).min(1, HATCH_FLUID_I, HATCH_FLUID_O)
        );
        FUSION_REACTOR.setStructure(b -> b
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
            ).of(0)
            .at("O", ADVANCED_MACHINE_CASING).at("C", FUSION_COIL).at("M", FUSION_REACTOR).at("B", ADVANCED_MACHINE_CASING, FUSION_MATERIAL_INJECTOR).at("H", ADVANCED_MACHINE_CASING, FUSION_MATERIAL_EXTRACTOR, FUSION_ENERGY_EXTRACTOR).at("E", ADVANCED_MACHINE_CASING, FUSION_ENERGY_INJECTOR)
            .build().offset(2, -1).min(2, FUSION_MATERIAL_INJECTOR).min(1, FUSION_MATERIAL_EXTRACTOR).min(4, FUSION_ENERGY_INJECTOR).min(1, FUSION_ENERGY_EXTRACTOR));
    }

    public static class ComponentIdWrapper implements IAntimatterObject {
        String id;
        public ComponentIdWrapper(String id){
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
