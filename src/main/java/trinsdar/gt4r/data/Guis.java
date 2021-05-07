package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerHatch;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.tile.multi.TileEntityHatch;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import trinsdar.gt4r.gui.ButtonOverlays;

import static muramasa.antimatter.gui.ButtonBody.*;
import static muramasa.antimatter.gui.SlotType.*;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.GT4RData.COVER_CONVEYOR;
import static trinsdar.gt4r.data.GT4RData.COVER_PUMP;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.data.SlotTypes.DISPLAY;
import static trinsdar.gt4r.gui.ButtonOverlays.*;
import static trinsdar.gt4r.gui.ButtonOverlays.EXPORT;
import static trinsdar.gt4r.gui.ButtonOverlays.IMPORT;

public class Guis {

    public static GuiData MULTI_DISPLAY = new GuiData("gt4r", "multi_display").add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63);
    public static GuiData MULTI_DISPLAY_MORE_FLUID = new GuiData("gt4r", "multi_display_more_fluid").add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63).add(FL_OUT, 107, 45).add(FL_OUT, 125, 45).add(FL_OUT, 143, 45);
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData("gt4r", "multi_display").add(MULTI_DISPLAY).setPadding(0, 0, 0, 0);
    public static GuiData BASIC_TANK = new GuiData("gt4r", "basic_tank").add(CELL_IN, 80, 17).add(CELL_OUT, 80, 53).add(FL_IN, 60, 43);

    public static GuiData ORE_BYPRODUCTS = new GuiData("gt4r", "ore_byproducts") {
        @Override
        public ResourceLocation getTexture(Tier tier,String type) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setPadding(0, 0, 0, 0).add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34);

    public static MenuHandlerMachine<ContainerHatch> HATCH_MENU_HANDLER_CUSTOM = new MenuHandlerMachine<ContainerHatch>(Ref.ID, "container_hatch_custom") {
        @Override
        public ContainerHatch getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityHatch ? new ContainerHatch((TileEntityHatch) tile, playerInv, this, windowId) : null;
        }
    };

    public static MenuHandlerMachine<ContainerBasicMachine> COAL_BOILER_MENU_HANDLER = new MenuHandlerMachine<ContainerBasicMachine>(Ref.ID, "container_coal_boiler") {
        @Override
        public ContainerBasicMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerBasicMachine((TileEntityMachine) tile, playerInv, this, windowId) : null;
        }
    };

    public static MenuHandlerMachine<ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine<ContainerMultiMachine>(Ref.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerMultiMachine((TileEntityMultiMachine) tile, playerInv, this, windowId) : null;
        }
    };

    public static MenuHandlerMachine<ContainerBasicMachine> DISTILLATION_MENU_HANDLER = new MenuHandlerMachine<ContainerBasicMachine>(Ref.ID, "container_distillation_tower") {
        @Override
        public ContainerBasicMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerBasicMachine((TileEntityBasicMultiMachine) tile, playerInv, this, windowId) : null;
        }
    };

    public static MenuHandlerMachine<ContainerBasicMachine> IBF_MENU_HANDLER = new MenuHandlerMachine<ContainerBasicMachine>(Ref.ID, "container_industrial_blast_furnace") {
        @Override
        public ContainerBasicMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerBasicMachine((TileEntityBasicMultiMachine) tile, playerInv, this, windowId) : null;
        }
    };

    public static void init(Dist side) {

        AntimatterAPI.registerJEICategory(RecipeMaps.ORE_BYPRODUCTS, Guis.ORE_BYPRODUCTS);
//        GregTechAPI.registerJEICategory(RecipeMaps.SMELTING, Guis.MULTI_DISPLAY_COMPACT);
        AntimatterAPI.registerJEICategory(RecipeMaps.STEAM_FUELS, Guis.MULTI_DISPLAY_COMPACT, STEAM_TURBINE);
        AntimatterAPI.registerJEICategory(RecipeMaps.SEMIFLUID_FUELS, Guis.MULTI_DISPLAY_COMPACT, SEMIFLUID_GENERATOR);
        AntimatterAPI.registerJEICategory(RecipeMaps.GAS_FUELS, Guis.MULTI_DISPLAY_COMPACT, GAS_TURBINE);
        AntimatterAPI.registerJEICategory(RecipeMaps.DIESEL_FUELS, Guis.MULTI_DISPLAY_COMPACT, DIESEL_GENERATOR);
        AntimatterAPI.registerJEICategory(RecipeMaps.LAVA_FUELS, Guis.MULTI_DISPLAY_COMPACT, HEAT_EXCHANGER);

        // extruder, chemical reactor, thermal centrifuge, all multiblocks

        //TODO changing slots of a machine in world, will crash from GTItemHandler.validateSlot()

        COAL_BOILER.setGUI(COAL_BOILER_MENU_HANDLER);
        FUSION_REACTOR.setGUI(FUSION_MENU_HANDLER);
        DISTILLATION_TOWER.setGUI(DISTILLATION_MENU_HANDLER);
        BLAST_FURNACE.setGUI(IBF_MENU_HANDLER);


        ALLOY_SMELTER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        ASSEMBLER.getGui().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25)
                .add(ENERGY,80, 62);
        BENDER.getGui().add(ALLOY_SMELTER);
        CANNER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        COMPRESSOR.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        CUTTER.getGui().add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY,80, 63);
        FORGE_HAMMER.getGui().add(COMPRESSOR).setDir(BarDir.TOP);
        FURNACE.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        EXTRACTOR.getGui().add(COMPRESSOR);
        EXTRUDER.getGui().add(ALLOY_SMELTER);
        LATHE.getGui().add(CUTTER);
        MACERATOR.getGui().add(MV, COMPRESSOR).add(MV, IT_OUT, 125, 25).add(MV, IT_OUT, 143, 25);
        MACERATOR.getGui().add(LV, COMPRESSOR);
        RECYCLER.getGui().add(COMPRESSOR);
        WIRE_MILL.getGui().add(COMPRESSOR);
        CENTRIFUGE.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(FL_IN, 26, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY,8, 63).add(FL_OUT, 44, 63).add(FL_OUT, 62, 63).add(FL_OUT, 80, 63).add(FL_OUT, 98, 63).add(FL_OUT, 116, 63).add(FL_OUT, 134, 63);
        ELECTROLYZER.getGui().add(CENTRIFUGE);
        THERMAL_CENTRIFUGE.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(ENERGY,80, 63);
        ORE_WASHER.getGui().add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63);
        CHEMICAL_REACTOR.getGui().add(IT_IN, 17, 25).add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(FL_IN, 53, 63).add(FL_IN, 35, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(ENERGY, 80, 63);
        FLUID_CANNER.getGui().add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        FLUID_EXTRACTOR.getGui().add(COMPRESSOR).add(FL_OUT, 107, 63);
        FLUID_SOLIDIFIER.getGui().add(COMPRESSOR).add(FL_IN, 53, 63);
        DISASSEMBLER.getGui().add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43)
                .add(ENERGY,80, 63);
        SIFTER.getGui().add(DISASSEMBLER);
        BATH.getGui().add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY, 80, 63);
        MASS_FABRICATOR.getGui().add(COMPRESSOR);

        COKE_OVEN.getGui().add(IT_IN, 53,25).add(IT_OUT,107,25).add(IT_OUT,125,25).add(IT_OUT,143,25).add(FL_OUT, 107, 63);
        BLAST_FURNACE.getGui().add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(SlotTypes.COIL, 8, 63);
        PRIMITIVE_BLAST_FURNACE.getGui().add(IT_IN, 35, 16).add(IT_IN, 35, 34).add(IT_IN, 35, 52).add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).setPadding(0, 0, 0, 0);
        FUSION_REACTOR.getGui().setEnablePlayerSlots(false);
        DISTILLATION_TOWER.getGui().add(FL_IN, 62, 41).add(FL_OUT, 98, 59).add(FL_OUT, 98, 41).add(FL_OUT, 98, 23).add(FL_OUT, 98, 5).add(FL_OUT, 116, 23).add(FL_OUT, 116, 5).add(IT_OUT, 116, 41).add(IT_OUT, 116, 59).add(ENERGY, 62, 59);
        VACUUM_FREEZER.getGui().add(COMPRESSOR);
        IMPLOSION_COMPRESSOR.getGui().add(ALLOY_SMELTER).add(IT_OUT, 125, 25);
        INDUSTRIAL_SAWMILL.getGui().add(ORE_WASHER);
        INDUSTRIAL_GRINDER.getGui().add(BATH);


        BATTERY_BUFFER_FOUR.getGui().add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,71,45).add(ENERGY,89,45);
        BATTERY_BUFFER_ONE.getGui().add(ENERGY,80,40);
        BATTERY_BUFFER_NINE.getGui().add(ENERGY,62,21).add(ENERGY,80,21).add(ENERGY,98,21)
                .add(ENERGY,62,39).add(ENERGY,80,39).add(ENERGY,98,39)
                .add(ENERGY,62,57).add(ENERGY,80,57).add(ENERGY,98,57);

        COAL_BOILER.getGui().add(BRONZE, CELL_IN, 44, 26).add(BRONZE, CELL_OUT, 44, 62).add(BRONZE, IT_OUT, 116, 26).add(BRONZE, IT_IN, 116, 62);
        COAL_BOILER.getGui().add(STEEL, CELL_IN, 44, 26).add(STEEL, CELL_OUT, 44, 62).add(STEEL, IT_OUT, 116, 26).add(STEEL, IT_IN, 116, 62);

        STEAM_ALLOY_SMELTER.getGui().add(BRONZE, ALLOY_SMELTER).add(BRONZE, FL_IN, 53, 63);
        STEAM_ALLOY_SMELTER.getGui().add(STEEL, ALLOY_SMELTER).add(STEEL, FL_IN, 53, 63);
        STEAM_COMPRESSOR.getGui().add(BRONZE, COMPRESSOR).add(BRONZE, FL_IN, 53, 63);
        STEAM_COMPRESSOR.getGui().add(STEEL, COMPRESSOR).add(STEEL, FL_IN, 53, 63);
        STEAM_FURNACE.getGui().add(BRONZE, FURNACE).add(BRONZE, FL_IN, 53, 63);
        STEAM_FURNACE.getGui().add(STEEL, FURNACE).add(STEEL, FL_IN, 53, 63);
        STEAM_EXTRACTOR.getGui().add(BRONZE, EXTRACTOR).add(BRONZE, FL_IN, 53, 63);
        STEAM_EXTRACTOR.getGui().add(STEEL, EXTRACTOR).add(STEEL, FL_IN, 53, 63);
        STEAM_MACERATOR.getGui().add(BRONZE, MACERATOR, LV).add(BRONZE, FL_IN, 53, 63);
        STEAM_MACERATOR.getGui().add(STEEL, MACERATOR, LV).add(STEEL, FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.getGui().add(BRONZE, FORGE_HAMMER).add(BRONZE, FL_IN, 53, 63);

        STEAM_TURBINE.getGui().add(BASIC_TANK);
        GAS_TURBINE.getGui().add(BASIC_TANK);
        DIESEL_GENERATOR.getGui().add(BASIC_TANK);
        SEMIFLUID_GENERATOR.getGui().add(BASIC_TANK);


        INFINITE_STEAM.getGui().add(CELL_IN, 80, 17).add(CELL_OUT, 80, 53).add(FL_OUT, 60, 43);

        QUANTUM_TANK.getGui().add(BASIC_TANK);
        QUANTUM_CHEST.getGui().add(IT_IN, 80, 17).add(DISPLAY, 60, 43).add(IT_OUT, 80, 53);

        HATCH_MUFFLER.getGui().add(IT_IN, 79, 34);

        HATCH_ITEM_I.getGui().add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);

        HATCH_ITEM_O.getGui().add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);

        HATCH_FLUID_I.getGui().add(FL_IN, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);


        HATCH_FLUID_O.getGui().add(FL_OUT, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);
        FUSION_MATERIAL_EXTRACTOR.getGui().add(FL_OUT, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58).add(IT_OUT, 61, 34).add(IT_OUT, 97, 34).add(IT_OUT, 79, 16).add(IT_OUT, 79, 52);
        FUSION_MATERIAL_INJECTOR.getGui().add(FL_IN, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58).add(IT_IN, 61, 34).add(IT_IN, 97, 34).add(IT_IN, 79, 16).add(IT_IN, 79, 52);

        if (side.isClient()){
            ButtonBody[][] overlays = new ButtonBody[][]{{IMPORT, IMPORT_CONDITIONAL, IMPORT_INVERT_CONDITIONAL, EXPORT, EXPORT_CONDITIONAL, EXPORT_INVERT_CONDITIONAL}, {IMPORT_EXPORT, IMPORT_EXPORT_CONDITIONAL, IMPORT_EXPORT_INVERT_CONDITIONAL, EXPORT_IMPORT, EXPORT_IMPORT_CONDITIONAL, EXPORT_IMPORT_INVERT_CONDITIONAL}};

            for (int x = 0; x < 6; x++){
                for (int y = 0; y < 2; y++){
                    COVER_CONVEYOR.getGui().addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                    COVER_PUMP.getGui().addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                }
            }
            FUSION_REACTOR.getGui().addButton(155, 23, 16, 16, NO_OVERLAY).addButton(155, 41, 16, 16, NO_OVERLAY).addButton(155, 59, 16, 16, NO_OVERLAY);
            TRANSFORMER_DIGITAL.getGui()
                    .addButton(10, 18, 14, 14, APAD_LEFT)
                    .addButton(25, 18, 14, 14, PAD_LEFT)
                    .addButton(10, 33, 14, 14, APAD_LEFT)
                    .addButton(25, 33, 14, 14, PAD_LEFT)
                    .addButton(10, 48, 14, 14, APAD_LEFT)
                    .addButton(25, 48, 14, 14, PAD_LEFT)
                    .addButton(10, 63, 14, 14, APAD_LEFT)
                    .addButton(25, 63, 14, 14, PAD_LEFT)
                    .addButton(137, 18, 14, 14, PAD_RIGHT)
                    .addButton(152, 18, 14, 14, APAD_RIGHT)
                    .addButton(137, 33, 14, 14, PAD_RIGHT)
                    .addButton(152, 33, 14, 14, APAD_RIGHT)
                    .addButton(137, 48, 14, 14, PAD_RIGHT)
                    .addButton(152, 48, 14, 14, APAD_RIGHT)
                    .addButton(137, 63, 14, 14, PAD_RIGHT)
                    .addButton(152, 63, 14, 14, APAD_RIGHT);
        }
    }
}
