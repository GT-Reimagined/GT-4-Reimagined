package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.slot.ISlotProvider;
import muramasa.antimatter.gui.widget.BackgroundWidget;
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
import static muramasa.antimatter.machine.Tier.BRONZE;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;
import static muramasa.antimatter.machine.Tier.STEEL;
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

        ALLOY_SMELTER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        ASSEMBLER.add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25)
                .add(ENERGY,80, 63).getGui().getMachineData().setProgressLocation("assembler");
        BENDER.add(ALLOY_SMELTER);
        CANNER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63)
                .getGui().getMachineData().setProgressLocation("canner");
        COMPRESSOR.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63)
                .getGui().getMachineData().setProgressLocation("compressor");
        CUTTER.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY,80, 63)
                .getGui().getMachineData().setProgressLocation("cutter");
        FORGE_HAMMER.add(COMPRESSOR).getGui().setBackgroundTexture("machine_forge_hammer").getMachineData().setProgressLocation("forge_hammer");
        FURNACE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        EXTRACTOR.add(COMPRESSOR).getGui().getMachineData().setProgressLocation("extractor");
        EXTRUDER.add(ALLOY_SMELTER).getGui().getMachineData().setProgressLocation("extruder");
        LATHE.add(CUTTER).getGui().getMachineData().setProgressLocation("lathe");
        MACERATOR.add(MV, COMPRESSOR).add(MV, IT_OUT, 125, 25).add(MV, IT_OUT, 143, 25)
                .getGui().setBackgroundTexture("machine_macerator").getMachineData().setProgressLocation("macerator");
        MACERATOR.add(LV, COMPRESSOR);
        RECYCLER.add(COMPRESSOR);
        WIRE_MILL.add(COMPRESSOR).getGui().getMachineData().setProgressLocation("wiremill");
        CENTRIFUGE.add(IT_IN, 35, 25)
                .add(FL_IN, 53, 25)
                .add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16)
                .add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34)
                .add(FL_OUT, 44, 63).add(FL_OUT, 62, 63).add(FL_OUT, 80, 63)
                .add(FL_OUT, 98, 63).add(FL_OUT, 116, 63).add(FL_OUT, 134, 63)
                .add(ENERGY, 17, 25).getGui().getMachineData().setProgressLocation("extractor");
        ELECTROLYZER.add(CENTRIFUGE).getGui().getMachineData().setProgressLocation("extractor");

        THERMAL_CENTRIFUGE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(ENERGY,80, 63);
        ORE_WASHER.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63).getGui().getMachineData().setProgressLocation("ore_washer");
        CHEMICAL_REACTOR.add(IT_IN, 26, 16).add(IT_IN, 44, 16)
                .add(FL_IN, 17, 34).add(FL_IN, 35, 34).add(FL_IN, 53, 34)
                .add(IT_OUT, 116, 16).add(IT_OUT, 134, 16)
                .add(FL_OUT, 107, 34).add(FL_OUT, 125, 34).add(FL_OUT, 143, 34)
                .add(ENERGY, 80, 63).getGui().getMachineData().setProgressLocation("chemical_reactor");
        FLUID_CANNER.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63)
                .getGui().getMachineData().setProgressLocation("canner");
        FLUID_EXTRACTOR.add(COMPRESSOR).add(FL_OUT, 107, 63).getGui().getMachineData().setProgressLocation("extractor");
        FLUID_SOLIDIFIER.add(COMPRESSOR).add(FL_IN, 53, 63);
        FERMENTER.add(FLUID_CANNER).getGui().getMachineData().setProgressLocation("chemical_reactor");
        DISTILLERY.add(FLUID_CANNER).add(FL_OUT, 125, 63).getGui().getMachineData().setProgressLocation("chemical_reactor");
        DISASSEMBLER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43)
                .add(ENERGY,80, 63);
        SIFTER.add(DISASSEMBLER).getGui().getMachineData().setProgressLocation("sifter");
        BATH.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY, 80, 63)
                .getGui().getMachineData().setProgressLocation("ore_washer");
        DUSTBIN
                .add(IT_IN, 17, 7).add(IT_IN, 17+18, 7).add(IT_IN, 17+18*2, 7)
                .add(IT_IN, 17, 25).add(IT_IN, 17+18, 25).add(IT_IN, 17+18*2, 25)
                .add(IT_IN, 17, 43).add(IT_IN, 17+18, 43).add(IT_IN, 17+18*2, 43)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43);
        MASS_FABRICATOR.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);

        COKE_OVEN.add(IT_IN, 53,25).add(IT_OUT,107,25).add(IT_OUT,125,25).add(IT_OUT,143,25).add(FL_OUT, 107, 63);
        BLAST_FURNACE.add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(SlotTypes.COIL, 8, 63);
        PRIMITIVE_BLAST_FURNACE.add(IT_IN, 35, 16).add(IT_IN, 35, 34).add(IT_IN, 35, 52).add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25);
        PYROLYSIS_OVEN.add(COKE_OVEN).add(SlotTypes.COIL, 8, 63);
        FUSION_REACTOR.getGui().setEnablePlayerSlots(false);
        DISTILLATION_TOWER.add(FL_IN, 62, 41).add(FL_OUT, 98, 59).add(FL_OUT, 98, 41).add(FL_OUT, 98, 23).add(FL_OUT, 98, 5).add(FL_OUT, 116, 23).add(FL_OUT, 116, 5).add(IT_OUT, 116, 41).add(IT_OUT, 116, 59).add(ENERGY, 62, 59);
        VACUUM_FREEZER.add(COMPRESSOR);
        IMPLOSION_COMPRESSOR.add(ALLOY_SMELTER).add(IT_OUT, 125, 25);
        INDUSTRIAL_SAWMILL.add(ORE_WASHER);
        INDUSTRIAL_GRINDER.add(BATH);
        LARGE_GAS_TURBINE.add(ROTOR, 152, 5);
        LARGE_STEAM_TURBINE.add(LARGE_GAS_TURBINE);
        THERMAL_BOILER.add(FILTER, 152, 5);
        PUMP.add(STORAGE, 53, 25).add(FL_OUT, 107, 25).add(ENERGY, 80, 63);

        ELECTRIC_ITEM_FILTER
                .add(DISPLAY_SETTABLE, 18, 6).add(DISPLAY_SETTABLE, 35, 6).add(DISPLAY_SETTABLE, 52, 6)
                .add(DISPLAY_SETTABLE, 18, 23).add(DISPLAY_SETTABLE, 35, 23).add(DISPLAY_SETTABLE, 52, 23)
                .add(DISPLAY_SETTABLE, 18, 40).add(DISPLAY_SETTABLE, 35, 40).add(DISPLAY_SETTABLE, 52, 40)
                .add(FILTERABLE, 98, 5).add(FILTERABLE, 98+18, 5).add(FILTERABLE, 98+18*2, 5)
                .add(FILTERABLE, 98, 23).add(FILTERABLE, 98+18, 23).add(FILTERABLE, 98+18*2, 23)
                .add(FILTERABLE, 98, 41).add(FILTERABLE, 98+18, 41).add(FILTERABLE, 98+18*2, 41);

        ELECTRIC_TYPE_FILTER
                .add(DISPLAY_SETTABLE, 35, 23)
                .add(FILTERABLE, 98, 5).add(FILTERABLE, 98+18, 5).add(FILTERABLE, 98+18*2, 5)
                .add(FILTERABLE, 98, 23).add(FILTERABLE, 98+18, 23).add(FILTERABLE, 98+18*2, 23)
                .add(FILTERABLE, 98, 41).add(FILTERABLE, 98+18, 41).add(FILTERABLE, 98+18*2, 41);

        ELECTRIC_ITEM_TRANSLOCATOR
                .add(DISPLAY_SETTABLE, 63, 6).add(DISPLAY_SETTABLE, 80, 6).add(DISPLAY_SETTABLE, 97, 6)
                .add(DISPLAY_SETTABLE, 63, 23).add(DISPLAY_SETTABLE, 80, 23).add(DISPLAY_SETTABLE, 97, 23)
                .add(DISPLAY_SETTABLE, 63, 40).add(DISPLAY_SETTABLE, 80, 40).add(DISPLAY_SETTABLE, 97, 40);

        ELECTRIC_FLUID_TRANSLOCATOR
                .add(FLUID_DISPLAY_SETTABLE, 63, 6).add(FLUID_DISPLAY_SETTABLE, 80, 6).add(FLUID_DISPLAY_SETTABLE, 97, 6)
                .add(FLUID_DISPLAY_SETTABLE, 63, 23).add(FLUID_DISPLAY_SETTABLE, 80, 23).add(FLUID_DISPLAY_SETTABLE, 97, 23)
                .add(FLUID_DISPLAY_SETTABLE, 63, 40).add(FLUID_DISPLAY_SETTABLE, 80, 40).add(FLUID_DISPLAY_SETTABLE, 97, 40);

        BATTERY_BUFFER_ONE.add(ENERGY,80,40);
        BATTERY_BUFFER_FOUR.add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,71,45).add(ENERGY,89,45);
        BATTERY_BUFFER_EIGHT
                .add(ENERGY,53,27).add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,107,27)
                .add(ENERGY,53,45).add(ENERGY,71,45).add(ENERGY,89,45).add(ENERGY,107,45);

        COAL_BOILER.add(BRONZE, CELL_IN, 44, 26).add(BRONZE, CELL_OUT, 44, 62).add(BRONZE, IT_OUT, 116, 26).add(BRONZE, IT_IN, 116, 62).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(BRONZE, BRONZE).put(STEEL, STEEL));
        COAL_BOILER.add(STEEL, CELL_IN, 44, 26).add(STEEL, CELL_OUT, 44, 62).add(STEEL, IT_OUT, 116, 26).add(STEEL, IT_IN, 116, 62);


        STEAM_ALLOY_SMELTER.add(BRONZE, ALLOY_SMELTER).add(BRONZE, FL_IN, 53, 63).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(BRONZE, BRONZE).put(STEEL, STEEL));
        STEAM_ALLOY_SMELTER.add(STEEL, ALLOY_SMELTER).add(STEEL, FL_IN, 53, 63);
        STEAM_COMPRESSOR.add(BRONZE, COMPRESSOR).add(BRONZE, FL_IN, 53, 63).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(BRONZE, BRONZE).put(STEEL, STEEL));
        STEAM_COMPRESSOR.add(STEEL, COMPRESSOR).add(STEEL, FL_IN, 53, 63);
        STEAM_FURNACE.add(BRONZE, FURNACE).add(BRONZE, FL_IN, 53, 63).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(BRONZE, BRONZE).put(STEEL, STEEL));
        STEAM_FURNACE.add(STEEL, FURNACE).add(STEEL, FL_IN, 53, 63);
        STEAM_EXTRACTOR.add(BRONZE, EXTRACTOR).add(BRONZE, FL_IN, 53, 63).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(BRONZE, BRONZE).put(STEEL, STEEL));
        STEAM_EXTRACTOR.add(STEEL, EXTRACTOR).add(STEEL, FL_IN, 53, 63);
        STEAM_MACERATOR.add(BRONZE, MACERATOR, LV).add(BRONZE, FL_IN, 53, 63).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(BRONZE, BRONZE).put(STEEL, STEEL));
        STEAM_MACERATOR.add(STEEL, MACERATOR, LV).add(STEEL, FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.add(BRONZE, FORGE_HAMMER).add(BRONZE, FL_IN, 53, 63).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(BRONZE, BRONZE).put(STEEL, STEEL));

        STEAM_TURBINE.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        GAS_TURBINE.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        DIESEL_GENERATOR.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        SEMIFLUID_GENERATOR.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        HEAT_EXCHANGER.add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63);


        INFINITE_STEAM.add(CELL_IN, 80, 17).add(CELL_OUT, 80, 53).add(FL_OUT, 60, 43);

        QUANTUM_TANK.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
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

        FUSION_REACTOR.getGui().setBackgroundTexture("fusion_control_computer").setEnablePlayerSlots(false)
                .getMachineData().setProgressLocation("fusion_reactor").setProgressPos(163, 4).setProgressSize(149, 16);
        FUSION_REACTOR.addGuiCallback(t -> {
            t.addButton(155, 23, NO_OVERLAY, false).addButton(155, 41, NO_OVERLAY, false).addButton(155, 59, NO_OVERLAY, false).addWidget(makeProgress()).addWidget(FusionButtonWidget.build());
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
        DISTILLATION_TOWER.setGuiProgressBarForJEI(BarDir.TOP, true);
        DISTILLATION_TOWER.getGui().getMachineData().setProgressLocation("distillation_tower")
                .setProgressSize(16, 72).setProgressPos(80, 4).setMachineStatePos(65, 25);
        FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false);
        STEAM_FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false);
        initWidgets();
    }

    public static void initWidgets(){
        ELECTRIC_ITEM_FILTER.getCallbacks().remove(1);
        ELECTRIC_TYPE_FILTER.getCallbacks().remove(1);
        ELECTRIC_ITEM_TRANSLOCATOR.getCallbacks().remove(1);
        ELECTRIC_FLUID_TRANSLOCATOR.getCallbacks().remove(1);
        COAL_BOILER.addGuiCallback(t -> {
            t.addWidget(CoalBoilerWidget.build().setSize(70, 25, 36, 54)).addWidget(CoalBoilerFuelWidget.build().setSize(115, 43, 18, 18));
        });
        PLATINUM_CABINET.getCallbacks().remove(0);
        OSMIUM_CABINET.getCallbacks().remove(0);
        PLATINUM_CABINET.addGuiCallback(t -> t.addWidget(BackgroundWidget.build(t.handler.getGuiTexture(),t.handler.guiSize(), t.handler.guiHeight(), 256, 266)));
        OSMIUM_CABINET.addGuiCallback(t -> t.addWidget(BackgroundWidget.build(t.handler.getGuiTexture(),t.handler.guiSize(), t.handler.guiHeight(), 256, 276)));
        PLATINUM_CHEST.getCallbacks().remove(0);
        OSMIUM_CHEST.getCallbacks().remove(0);
        PLATINUM_CHEST.addGuiCallback(t -> t.addWidget(BackgroundWidget.build(t.handler.getGuiTexture(),t.handler.guiSize(), t.handler.guiHeight(), 256, 266)));
        OSMIUM_CHEST.addGuiCallback(t -> t.addWidget(BackgroundWidget.build(t.handler.getGuiTexture(),t.handler.guiSize(), t.handler.guiHeight(), 256, 276)));
    }

    private static void initMaterialMachine(Side side){
        //TODO move these textures to background folder
        BiFunction<Boolean, String, ResourceLocation> textures = (c, l) -> new ResourceLocation(GT4RRef.ID, "textures/gui/machine/" + (c ? "charging_" : "") + l + ".png");

        FUSION_REACTOR.setGUI(MenuHandlers.FUSION_MENU_HANDLER);
        QUANTUM_CHEST.setGUI(MenuHandlers.QUANTUM_CHEST_HANDLER);
        DIGITAL_CHEST.setGUI(MenuHandlers.DIGITAL_CHEST_HANDLER);
        IRON_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        ALUMINIUM_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        WROUGHT_IRON_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        BRASS_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        CUPRONICKEL_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        ELECTRUM_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        GOLD_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        SILVER_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        MAGNALIUM_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        PLATINUM_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_EIGHT);
        OSMIUM_CABINET.setGUI(MenuHandlers.CABINET_HANDLER_NINE);
        IRON_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        ALUMINIUM_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        WROUGHT_IRON_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        BRASS_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        CUPRONICKEL_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        ELECTRUM_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        GOLD_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        SILVER_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        MAGNALIUM_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        PLATINUM_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_eight"));
        OSMIUM_CABINET.getGui().setOverrideLocation(textures.apply(false, "cabinet_nine"));

        IRON_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        ALUMINIUM_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        WROUGHT_IRON_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        BRASS_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        CUPRONICKEL_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SIX);
        ELECTRUM_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        GOLD_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        SILVER_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        MAGNALIUM_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_SEVEN);
        PLATINUM_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_EIGHT);
        OSMIUM_CHEST.setGUI(MenuHandlers.CABINET_HANDLER_NINE);
        IRON_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        ALUMINIUM_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        WROUGHT_IRON_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        BRASS_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        CUPRONICKEL_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_six"));
        ELECTRUM_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        GOLD_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        SILVER_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        MAGNALIUM_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_seven"));
        PLATINUM_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_eight"));
        OSMIUM_CHEST.getGui().setOverrideLocation(textures.apply(false, "cabinet_nine"));
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; ++j) {
                if (i < 6){
                    IRON_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    ALUMINIUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    WROUGHT_IRON_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    BRASS_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    CUPRONICKEL_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    IRON_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    ALUMINIUM_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    WROUGHT_IRON_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    BRASS_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    CUPRONICKEL_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    DIGITAL_CHEST.add(FILTERABLE, 8 + j * 18, 8 + (i * 18));
                }
                if (i < 7){
                    ELECTRUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    GOLD_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    SILVER_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    MAGNALIUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    ELECTRUM_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    GOLD_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    SILVER_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    MAGNALIUM_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                if (i < 8){
                    PLATINUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    PLATINUM_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                OSMIUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                OSMIUM_CHEST.add(STORAGE, 12 + j * 18, 18 + (i * 18));
            }
        }
        DIGITAL_CHEST.add(DATA, 80, 119);
    }

    public static WidgetSupplier makeProgress(){
        return builder(ProgressWidget::new);
    }
}
