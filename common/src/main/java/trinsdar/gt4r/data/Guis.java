package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.slot.ISlotProvider;
import muramasa.antimatter.gui.widget.ProgressWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.registration.Side;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.gui.widgets.CoalBoilerFuelWidget;
import trinsdar.gt4r.gui.widgets.CoalBoilerWidget;
import trinsdar.gt4r.gui.widgets.FusionButtonWidget;

import java.util.function.BiFunction;

import static muramasa.antimatter.gui.ButtonOverlay.*;
import static muramasa.antimatter.gui.SlotType.*;
import static muramasa.antimatter.gui.Widget.builder;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.data.SlotTypes.*;
import static trinsdar.gt4r.gui.ButtonOverlays.*;

public class Guis {

    public static GuiData MULTI_DISPLAY = new GuiData("gt4r", "multi_display").setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63));
    public static GuiData MULTI_DISPLAY_MORE_FLUID = new GuiData("gt4r", "multi_display_more_fluid").setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63).add(FL_OUT, 107, 45).add(FL_OUT, 125, 45).add(FL_OUT, 143, 45));
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData("gt4r", "multi_display").setSlots(ISlotProvider.DEFAULT().add(MULTI_DISPLAY.getSlots()));
    public static GuiData BASIC_TANK = new GuiData("gt4r", "basic_tank").setBackgroundTexture("basic_tank").setSlots(ISlotProvider.DEFAULT().add(CELL_IN, 8, 17).add(CELL_OUT, 8, 53).add(FL_IN, 55, 43));

    public static GuiData MULTIBLOCK = new GuiData("gt4r", "multiblock").setBackgroundTexture("multiblock").setSlots(ISlotProvider.DEFAULT().add(STORAGE, 152, 5));
    public static GuiData ORE_BYPRODUCTS_OLD = new GuiData("gt4r", "ore_byproducts_old") {
        @Override
        public ResourceLocation getTexture(Tier tier,String type) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34));

    public static GuiData ORE_BYPRODUCTS = new GuiData("gt4r", "ore_byproducts") {
        @Override
        public ResourceLocation getTexture(Tier tier,String type) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setArea(3, 3, 170, 160).setSlots(ISlotProvider.DEFAULT()
            .add(IT_IN, 25, 4).add(IT_IN,4, 25).add(IT_IN, 25, 26).add(IT_IN, 25, 49).add(IT_IN, 115, 48).add(IT_IN, 24, 72).add(IT_IN, 51, 81).add(IT_IN, 71, 81).add(IT_IN, 98,72).add(IT_IN, 134,72).add(IT_IN, 4, 124)
            .add(IT_IN, 4, 4).add(IT_IN, 4, 48).add(IT_IN, 4, 106).add(IT_IN, 65, 26).add(IT_IN, 138, 48).add(IT_IN, 24, 93).add(IT_IN, 98, 93)
            .add(IT_IN, 65, 49)
            .add(FL_IN, 43, 26).add(FL_IN, 43, 49)
            .add(IT_OUT, 47, 4).add(IT_OUT, 83, 26).add(IT_OUT, 156, 48).add(IT_OUT, 4, 67).add(IT_OUT, 24, 110).add(IT_OUT, 51, 102).add(IT_OUT, 51, 120).add(IT_OUT, 71, 102).add(IT_OUT, 71, 120).add(IT_OUT, 98, 111).add(IT_OUT, 134, 93).add(IT_OUT, 134, 111).add(IT_OUT, 4, 146).add(IT_OUT, 24, 146)
            .add(IT_OUT, 83, 49)
    );
    public static GuiData LOCKER = new GuiData("gt4r","locker").setSlots(ISlotProvider.DEFAULT().add(STORAGE, 80, 8).add(STORAGE, 80, 8 + (18)).add(STORAGE, 80, 8 + (2 * 18)).add(STORAGE, 80, 8 + (3 * 18)));
    public static GuiData CHARGING_LOCKER = new GuiData("gt4r","charging_locker").setSlots(ISlotProvider.DEFAULT().add(ENERGY, 80, 8).add(ENERGY, 80, 8 + (18)).add(ENERGY, 80, 8 + (2 * 18)).add(ENERGY, 80, 8 + (3 * 18)));

    static ResourceLocation buttonLocation = new ResourceLocation(GT4RRef.ID, "textures/gui/button/gui_buttons.png");

    public static void init(Side side) {

        AntimatterAPI.registerJEICategory(RecipeMaps.ORE_BYPRODUCTS, Guis.ORE_BYPRODUCTS);
        AntimatterAPI.registerJEICategory(RecipeMaps.INT_CIRCUITS, Guis.ORE_BYPRODUCTS_OLD);
        AntimatterAPI.registerJEICategory(RecipeMaps.FLUID_EXTRACTOR_COILS, Guis.ORE_BYPRODUCTS_OLD);

        //TODO changing slots of a machine in world, will crash from GTItemHandler.validateSlot()

        initMaterialMachine(side);

        slots();
        backgroundTextures();
        machineData();
        widgets();
    }

    private static void initMaterialMachine(Side side){
        //TODO move these textures to background folder
        BiFunction<Boolean, String, ResourceLocation> textures = (c, l) -> new ResourceLocation(GT4RRef.ID, "textures/gui/machine/" + (c ? "charging_" : "") + l + ".png");
        QUANTUM_CHEST.setGUI(MenuHandlers.QUANTUM_CHEST_HANDLER);



        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 9; ++j) {
                DIGITAL_CHEST.add(STORAGE, 8 + j * 18, 8 + (i * 18));
            }
        }
        DIGITAL_CHEST.add(DATA, 80, 119);
    }

    public static void slots(){
        ALLOY_SMELTER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        ASSEMBLER.add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34)
                .add(IT_IN, 53, 34).add(IT_OUT, 107, 25)
                .add(FL_IN, 53, 63)
                .add(ENERGY, 80, 63);
        BENDER.add(ALLOY_SMELTER);
        CANNER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        COMPRESSOR.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        CUTTER.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY, 80, 63);
        FURNACE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        EXTRACTOR.add(COMPRESSOR);
        EXTRUDER.add(ALLOY_SMELTER);
        LATHE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY, 80, 63);
        MACERATOR.add(COMPRESSOR);
        MACERATOR.add(MV, COMPRESSOR).add(MV, IT_OUT, 125, 25).add(MV, IT_OUT, 143, 25);
        RECYCLER.add(COMPRESSOR).add(FL_IN, 53, 63);
        SCANNER.add(COMPRESSOR);
        WIRE_MILL.add(COMPRESSOR);
        CENTRIFUGE.add(IT_IN, 35, 25)
                .add(FL_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 125, 7).add(IT_OUT, 143, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25)
                .add(FL_OUT, 107, 43).add(FL_OUT, 125, 43).add(FL_OUT, 143, 43)
                .add(FL_OUT, 107, 61).add(FL_OUT, 125, 61).add(FL_OUT, 143, 61)
                .add(ENERGY, 17, 25);
        ELECTROLYZER.add(CENTRIFUGE).add(IT_IN, 35, 43).add(FL_IN,53, 43);
        THERMAL_CENTRIFUGE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(ENERGY,
                80, 63);
        ORE_WASHER.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        CHEMICAL_REACTOR.add(IT_IN, 26, 16).add(IT_IN, 44, 16)
                .add(FL_IN, 17, 34).add(FL_IN, 35, 34).add(FL_IN, 53, 34)
                .add(IT_OUT, 116, 16).add(IT_OUT, 134, 16)
                .add(FL_OUT, 107, 34).add(FL_OUT, 125, 34).add(FL_OUT, 143, 34)
                .add(ENERGY, 80, 63);
        FLUID_CANNER.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        DISASSEMBLER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107 + 18, 7).add(IT_OUT, 107 + 18 * 2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107 + 18, 25).add(IT_OUT, 107 + 18 * 2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107 + 18, 43).add(IT_OUT, 107 + 18 * 2, 43)
                .add(ENERGY, 80, 63);
        MASS_FABRICATOR.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        REPLICATOR.add(FLUID_CANNER);
        FERMENTER.add(FLUID_CANNER);
        FLUID_EXTRACTOR.add(COMPRESSOR).add(FL_OUT, 107, 63);
        FLUID_SOLIDIFIER.add(COMPRESSOR).add(FL_IN, 53, 63);
        DISTILLERY.add(FLUID_CANNER);
        BATH.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63);
        FORGE_HAMMER.add(FURNACE);
        SIFTER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16)
                .add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34)
                .add(ENERGY, 80, 63);
        DUSTBIN
                .add(IT_IN, 17, 7).add(IT_IN, 17+18, 7).add(IT_IN, 17+18*2, 7)
                .add(IT_IN, 17, 25).add(IT_IN, 17+18, 25).add(IT_IN, 17+18*2, 25)
                .add(IT_IN, 17, 43).add(IT_IN, 17+18, 43).add(IT_IN, 17+18*2, 43)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43);
        COKE_OVEN.add(IT_IN, 53, 25, new ResourceLocation(GT4RRef.ID, "primitive_ingot"))
                .add(IT_OUT, 107, 25, new ResourceLocation(GT4RRef.ID, "primitive_ingot"))
                .add(FL_OUT, 125, 25, new ResourceLocation(GT4RRef.ID, "primitive_cell"));
        ResourceLocation bat = new ResourceLocation(GT4RRef.ID, "battery");
        BATTERY_BUFFER_FOUR.add(ENERGY, 71, 27, bat).add(ENERGY, 89, 27, bat).add(ENERGY, 71, 45, bat).add(ENERGY, 89, 45, bat);
        BATTERY_BUFFER_ONE.add(ENERGY, 80, 40, bat);
        BATTERY_BUFFER_EIGHT
                .add(ENERGY,53,27, bat).add(ENERGY,71,27, bat).add(ENERGY,89,27, bat).add(ENERGY,107,27, bat)
                .add(ENERGY,53,45, bat).add(ENERGY,71,45, bat).add(ENERGY,89,45, bat).add(ENERGY,107,45, bat);

        SOLID_FUEL_BOILER.add(CELL_IN, 44, 26).add(CELL_OUT, 44, 62).add(IT_OUT, 116, 26).add(IT_IN, 116, 62);
        SOLID_FUEL_BOILER.add(CELL_IN, 44, 26).add(CELL_OUT, 44, 62).add(IT_OUT, 116, 26).add(STEEL,
                IT_IN, 116, 62);

        STEAM_ALLOY_SMELTER.add(ALLOY_SMELTER).add(FL_IN, 53, 63);
        STEAM_CUTTER.add(CUTTER).add(FL_IN, 35, 63);
        STEAM_COMPRESSOR.add(COMPRESSOR).add(FL_IN, 53, 63);
        STEAM_FURNACE.add(FURNACE).add(FL_IN, 53, 63);
        STEAM_EXTRACTOR.add(EXTRACTOR).add(FL_IN, 53, 63);
        STEAM_MACERATOR.add(MACERATOR).add(FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.add(FORGE_HAMMER).add(FL_IN, 53, 63);
        STEAM_TURBINE.add(BASIC_TANK.getSlots());
        GAS_TURBINE.add(BASIC_TANK.getSlots());
        DIESEL_GENERATOR.add(BASIC_TANK.getSlots());
        SEMIFLUID_GENERATOR.add(BASIC_TANK.getSlots());
        HEAT_EXCHANGER.add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63);

        INFINITE_STEAM.add(BASIC_TANK.getSlots());

        QUANTUM_TANK.add(BASIC_TANK.getSlots());
        PRIMITIVE_BLAST_FURNACE.add(IT_IN, 53, 16, new ResourceLocation(GT4RRef.ID, "primitive_ingot"))
                .add(IT_IN, 53, 34, new ResourceLocation(GT4RRef.ID, "primitive_fire"))
                .add(IT_IN, 53, 52, new ResourceLocation(GT4RRef.ID, "primitive_fire"))
                .add(IT_OUT, 107, 25, new ResourceLocation(GT4RRef.ID, "primitive_ingot"))
                .add(IT_OUT, 125, 25, new ResourceLocation(GT4RRef.ID, "primitive_dust"))
                .add(IT_OUT, 143, 25, new ResourceLocation(GT4RRef.ID, "primitive_dust"));
        BLAST_FURNACE.add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(SlotTypes.COIL, 8, 63);
        PYROLYSIS_OVEN.add(COKE_OVEN).add(SlotTypes.COIL, 8, 63);
        DISTILLATION_TOWER.add(FL_IN, 62, 41).add(FL_OUT, 98, 59).add(FL_OUT, 98, 41).add(FL_OUT, 98, 23).add(FL_OUT, 98, 5).add(FL_OUT, 116, 23).add(FL_OUT, 116, 5).add(IT_OUT, 116, 41).add(IT_OUT, 116, 59).add(ENERGY, 62, 59);
        VACUUM_FREEZER.add(COMPRESSOR);
        IMPLOSION_COMPRESSOR.add(ALLOY_SMELTER).add(IT_OUT, 125, 25);
        INDUSTRIAL_SAWMILL.add(ORE_WASHER);
        INDUSTRIAL_GRINDER.add(BATH);
        LARGE_GAS_TURBINE.add(STORAGE, 152, 5);
        LARGE_STEAM_TURBINE.add(LARGE_GAS_TURBINE);
        THERMAL_BOILER.add(STORAGE, 152, 5);

        QUANTUM_CHEST.add(QUANTUM, 80, 35);

        HATCH_MUFFLER.add(IT_IN, 79, 34);

        HATCH_ITEM_I.add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);

        HATCH_ITEM_O.add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);

        HATCH_FLUID_I.add(FL_IN, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);

        HATCH_FLUID_O.add(FL_OUT, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);
        FUSION_FLUID_INJECTOR.add(HATCH_FLUID_I);
        FUSION_FLUID_EXTRACTOR.add(HATCH_FLUID_O);
        FUSION_ITEM_EXTRACTOR.add(IT_OUT, 79, 34);
        FUSION_ITEM_INJECTOR.add(IT_IN, 79, 34);
        DIGITAL_TANK.add(BASIC_TANK.getSlots()).add(DATA, 107, 64);
        ELECTRIC_ITEM_FILTER
                .add(DISPLAY_SETTABLE, 18, 6, new ResourceLocation(GT4RRef.ID, "blank")).add(DISPLAY_SETTABLE, 35, 6, new ResourceLocation(GT4RRef.ID, "blank")).add(DISPLAY_SETTABLE, 52, 6, new ResourceLocation(GT4RRef.ID, "blank"))
                .add(DISPLAY_SETTABLE, 18, 23, new ResourceLocation(GT4RRef.ID, "blank")).add(DISPLAY_SETTABLE, 35, 23, new ResourceLocation(GT4RRef.ID, "blank")).add(DISPLAY_SETTABLE, 52, 23, new ResourceLocation(GT4RRef.ID, "blank"))
                .add(DISPLAY_SETTABLE, 18, 40, new ResourceLocation(GT4RRef.ID, "blank")).add(DISPLAY_SETTABLE, 35, 40, new ResourceLocation(GT4RRef.ID, "blank")).add(DISPLAY_SETTABLE, 52, 40, new ResourceLocation(GT4RRef.ID, "blank"))
                .add(STORAGE, 98, 5).add(STORAGE, 98 + 18, 5)
                .add(STORAGE, 98 + 18 * 2, 5)
                .add(STORAGE, 98, 23).add(STORAGE, 98 + 18, 23)
                .add(STORAGE, 98 + 18 * 2, 23)
                .add(STORAGE, 98, 41).add(STORAGE, 98 + 18, 41)
                .add(STORAGE, 98 + 18 * 2, 41);

        ELECTRIC_TYPE_FILTER
                .add(DISPLAY_SETTABLE, 35, 23, new ResourceLocation(GT4RRef.ID, "blank"))
                .add(STORAGE, 98, 5).add(STORAGE, 98 + 18, 5)
                .add(STORAGE, 98 + 18 * 2, 5)
                .add(STORAGE, 98, 23).add(STORAGE, 98 + 18, 23)
                .add(STORAGE, 98 + 18 * 2, 23)
                .add(STORAGE, 98, 41).add(STORAGE, 98 + 18, 41)
                .add(STORAGE, 98 + 18 * 2, 41);
        ELECTRIC_ITEM_TRANSLOCATOR
                .add(DISPLAY_SETTABLE, 63, 6, new ResourceLocation(GT4RRef.ID,"blank")).add(DISPLAY_SETTABLE, 80, 6, new ResourceLocation(GT4RRef.ID,"blank")).add(DISPLAY_SETTABLE, 97, 6, new ResourceLocation(GT4RRef.ID,"blank"))
                .add(DISPLAY_SETTABLE, 63, 23, new ResourceLocation(GT4RRef.ID,"blank")).add(DISPLAY_SETTABLE, 80, 23, new ResourceLocation(GT4RRef.ID,"blank")).add(DISPLAY_SETTABLE, 97, 23, new ResourceLocation(GT4RRef.ID,"blank"))
                .add(DISPLAY_SETTABLE, 63, 40, new ResourceLocation(GT4RRef.ID,"blank")).add(DISPLAY_SETTABLE, 80, 40, new ResourceLocation(GT4RRef.ID,"blank")).add(DISPLAY_SETTABLE, 97, 40, new ResourceLocation(GT4RRef.ID,"blank"));

        ELECTRIC_FLUID_TRANSLOCATOR
                .add(FLUID_DISPLAY_SETTABLE, 63, 6, new ResourceLocation(GT4RRef.ID,"blank")).add(FLUID_DISPLAY_SETTABLE, 80, 6, new ResourceLocation(GT4RRef.ID,"blank")).add(FLUID_DISPLAY_SETTABLE, 97, 6, new ResourceLocation(GT4RRef.ID,"blank"))
                .add(FLUID_DISPLAY_SETTABLE, 63, 23, new ResourceLocation(GT4RRef.ID,"blank")).add(FLUID_DISPLAY_SETTABLE, 80, 23, new ResourceLocation(GT4RRef.ID,"blank")).add(FLUID_DISPLAY_SETTABLE, 97, 23, new ResourceLocation(GT4RRef.ID,"blank"))
                .add(FLUID_DISPLAY_SETTABLE, 63, 40, new ResourceLocation(GT4RRef.ID,"blank")).add(FLUID_DISPLAY_SETTABLE, 80, 40, new ResourceLocation(GT4RRef.ID,"blank")).add(FLUID_DISPLAY_SETTABLE, 97, 40, new ResourceLocation(GT4RRef.ID,"blank"));

    }

    public static void backgroundTextures(){
        MACERATOR.getGui().setBackgroundTexture("machine_macerator");
        FORGE_HAMMER.getGui().setBackgroundTexture("machine_forge_hammer");
        CENTRIFUGE.getGui().setBackgroundTexture("centrifuge");
        ELECTROLYZER.getGui().setBackgroundTexture("centrifuge");
        COKE_OVEN.getGui().setBackgroundTexture("coke_oven");
        PRIMITIVE_BLAST_FURNACE.getGui().setBackgroundTexture("primitive_blast_furnace");
        COKE_OVEN.getGui().setBackgroundTexture("coke_oven");
        STEAM_TURBINE.getGui().setBackgroundTexture("basic_tank");
        GAS_TURBINE.getGui().setBackgroundTexture("basic_tank");
        DIESEL_GENERATOR.getGui().setBackgroundTexture("basic_tank");
        SEMIFLUID_GENERATOR.getGui().setBackgroundTexture("basic_tank");
        INFINITE_STEAM.getGui().setBackgroundTexture("basic_tank");
        QUANTUM_TANK.getGui().setBackgroundTexture("basic_tank");
        ELECTRIC_ITEM_FILTER.getGui().setBackgroundTexture("electric_item_filter");
        ELECTRIC_TYPE_FILTER.getGui().setBackgroundTexture("electric_type_filter");
        ELECTRIC_ITEM_TRANSLOCATOR.getGui().setBackgroundTexture("electric_item_translocator");
        ELECTRIC_FLUID_TRANSLOCATOR.getGui().setBackgroundTexture("electric_fluid_translocator");
        LARGE_GAS_TURBINE.getGui().setBackgroundTexture("multiblock");
        LARGE_STEAM_TURBINE.getGui().setBackgroundTexture("multiblock");
        THERMAL_BOILER.getGui().setBackgroundTexture("multiblock");
        HEAT_EXCHANGER.getGui().setBackgroundTexture("multiblock");
        DIGITAL_CHEST.getGui().setBackgroundTexture("digital_chest");
        FUSION_REACTOR.getGui().setBackgroundTexture("fusion_control_computer");
    }

    public static void machineData(){
        ASSEMBLER.getGui().getMachineData().setProgressLocation("assembler");
        CANNER.getGui().getMachineData().setProgressLocation("canner");
        COMPRESSOR.getGui().getMachineData().setProgressLocation("compressor");
        CUTTER.getGui().getMachineData().setProgressLocation("cutter");
        EXTRACTOR.getGui().getMachineData().setProgressLocation("extractor");
        EXTRUDER.getGui().getMachineData().setProgressLocation("extruder");
        LATHE.getGui().getMachineData().setProgressLocation("lathe");
        MACERATOR.getGui().getMachineData().setProgressLocation("macerator");
        WIRE_MILL.getGui().getMachineData().setProgressLocation("wiremill");
        CENTRIFUGE.getGui().getMachineData().setProgressLocation("extractor");
        ELECTROLYZER.getGui().getMachineData().setProgressLocation("extractor");
        ORE_WASHER.getGui().getMachineData().setProgressLocation("ore_washer");
        CHEMICAL_REACTOR.getGui().getMachineData().setProgressLocation("chemical_reactor");
        FLUID_CANNER.getGui().getMachineData().setProgressLocation("canner");
        FERMENTER.getGui().getMachineData().setProgressLocation("chemical_reactor");
        FLUID_EXTRACTOR.getGui().getMachineData().setProgressLocation("extractor");
        DISTILLERY.getGui().getMachineData().setProgressLocation("chemical_reactor");
        BATH.getGui().getMachineData().setProgressLocation("ore_washer");
        SIFTER.getGui().getMachineData().setProgressLocation("sifter");
        COKE_OVEN.getGui().getMachineData().setProgressLocation("coke_oven");
        PRIMITIVE_BLAST_FURNACE.getGui().getMachineData().setProgressLocation("coke_oven");
        FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false).getGui().getMachineData().setMachineStatePos(84, 46).setProgressLocation("forge_hammer");
        STEAM_FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false).getGui().getMachineData().setMachineStatePos(80, 50);
        DISTILLATION_TOWER.setGuiProgressBarForJEI(BarDir.TOP, true);
        DISTILLATION_TOWER.getGui().getMachineData().setProgressLocation("distillation_tower")
                .setProgressSize(16, 72).setProgressPos(80, 4).setMachineStatePos(65, 25);
        DIGITAL_CHEST.getGui().setYSize(221).setPlayerYOffset(56);
        FUSION_REACTOR.getGui().setYSize(182).setTitleDrawingAllowed(false).setEnablePlayerSlots(false)
                .getMachineData().setProgressLocation("fusion_reactor").setProgressPos(163, 4).setProgressSize(149, 16);
    }

    public static void widgets(){

        ELECTRIC_ITEM_FILTER.getCallbacks().remove(1);
        ELECTRIC_TYPE_FILTER.getCallbacks().remove(1);
        ELECTRIC_ITEM_TRANSLOCATOR.getCallbacks().remove(1);
        ELECTRIC_FLUID_TRANSLOCATOR.getCallbacks().remove(1);
        SOLID_FUEL_BOILER.addGuiCallback(t -> {
            t.addWidget(CoalBoilerWidget.build().setSize(70, 25, 36, 54))
                    .addWidget(CoalBoilerFuelWidget.build().setSize(115, 43, 18, 18));
        });
        FUSION_REACTOR.addGuiCallback(t -> {
            t.addButton(155, 23, ButtonOverlay.NO_OVERLAY, false).addButton(155, 41, ButtonOverlay.NO_OVERLAY, false).addButton(155, 59, ButtonOverlay.NO_OVERLAY, false).addWidget(makeProgress()).addWidget(FusionButtonWidget.build());
        });
        TRANSFORMER_DIGITAL.addGuiCallback(t -> {
            t.addButton(10, 18, APAD_LEFT, false)
                    .addButton(25, 18, PAD_LEFT, false)
                    .addButton(10, 33, APAD_LEFT, false)
                    .addButton(25, 33, PAD_LEFT, false)
                    .addButton(10, 48, APAD_LEFT, false)
                    .addButton(25, 48, PAD_LEFT, false)
                    .addButton(10, 63, APAD_LEFT, false)
                    .addButton(25, 63, PAD_LEFT, false)
                    .addButton(137, 18, PAD_RIGHT, false)
                    .addButton(152, 18, APAD_RIGHT, false)
                    .addButton(137, 33, PAD_RIGHT, false)
                    .addButton(152, 33, APAD_RIGHT, false)
                    .addButton(137, 48, PAD_RIGHT, false)
                    .addButton(152, 48, APAD_RIGHT, false)
                    .addButton(137, 63, PAD_RIGHT, false)
                    .addButton(152, 63, APAD_RIGHT, false);
        });
        DIGITAL_CHEST.addGuiCallback(t -> {
            t.addButton(8, 119, DATA_IN, true, "tooltip.gt4r.upload_chest").addButton(26, 119, DATA_OUT, true, "tooltip.gt4r.download_orb").addButton(44, 119, CHEST, true);
        });
        DIGITAL_TANK.addGuiCallback(t -> {
            t.addButton(54, 64, DATA_IN, true, "tooltip.gt4r.upload_chest").addButton(72, 64, DATA_OUT, true, "tooltip.gt4r.download_orb");
        });
    }

    public static WidgetSupplier makeProgress(){
        return builder(ProgressWidget::new);
    }
}
