package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.slot.ISlotProvider;
import muramasa.antimatter.gui.widget.BackgroundWidget;
import muramasa.antimatter.gui.widget.IOWidget;
import muramasa.antimatter.gui.widget.MachineStateWidget;
import muramasa.antimatter.gui.widget.ProgressWidget;
import muramasa.antimatter.gui.widget.TextWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.int4;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.client.ScreenFactories;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.gui.widgets.FilterButtonArrayWidget;
import trinsdar.gt4r.gui.widgets.FusionButtonWidget;
import trinsdar.gt4r.gui.MenuHandlerCrafting;
import trinsdar.gt4r.gui.MenuHandlerCraftingItem;
import trinsdar.gt4r.gui.widgets.CoalBoilerWidget;
import trinsdar.gt4r.gui.widgets.MachineStateWidgetMoved;
import trinsdar.gt4r.gui.widgets.TranslocatorButtonArrayWidget;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

import java.util.function.BiFunction;

import static muramasa.antimatter.gui.ButtonBody.*;
import static muramasa.antimatter.gui.SlotType.*;
import static muramasa.antimatter.gui.Widget.builder;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.data.SlotTypes.*;
import static trinsdar.gt4r.gui.ButtonOverlays.*;
import static trinsdar.gt4r.gui.ButtonOverlays.EXPORT;
import static trinsdar.gt4r.gui.ButtonOverlays.IMPORT;
import static trinsdar.gt4r.gui.ButtonOverlays.TORCH_OFF;
import static trinsdar.gt4r.gui.ButtonOverlays.TORCH_ON;

public class Guis {

    public static GuiData MULTI_DISPLAY = new GuiData("gt4r", "multi_display").setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63));
    public static GuiData MULTI_DISPLAY_MORE_FLUID = new GuiData("gt4r", "multi_display_more_fluid").setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63).add(FL_OUT, 107, 45).add(FL_OUT, 125, 45).add(FL_OUT, 143, 45));
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData("gt4r", "multi_display").setSlots(ISlotProvider.DEFAULT().add(MULTI_DISPLAY.getSlots())).setPadding(0, 0, 0, 0);
    public static GuiData BASIC_TANK = new GuiData("gt4r", "basic_tank").setSlots(ISlotProvider.DEFAULT().add(CELL_IN, 80, 17).add(CELL_OUT, 80, 53).add(FL_IN, 60, 43));

    public static GuiData ORE_BYPRODUCTS = new GuiData("gt4r", "ore_byproducts") {
        @Override
        public ResourceLocation getTexture(Tier tier,String type) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setPadding(0, 0, 0, 0).setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34));

    public static GuiData WORKBENCH = new GuiData("gt4r","workbench").setSlots(ISlotProvider.DEFAULT());
    public static GuiData CHARGING_WORKBENCH = new GuiData("gt4r","charging_workbench").setSlots(ISlotProvider.DEFAULT());
    public static GuiData LOCKER = new GuiData("gt4r","locker").setSlots(ISlotProvider.DEFAULT().add(STORAGE, 80, 8).add(STORAGE, 80, 8 + (18)).add(STORAGE, 80, 8 + (2 * 18)).add(STORAGE, 80, 8 + (3 * 18)));
    public static GuiData CHARGING_LOCKER = new GuiData("gt4r","charging_locker").setSlots(ISlotProvider.DEFAULT().add(ENERGY, 80, 8).add(ENERGY, 80, 8 + (18)).add(ENERGY, 80, 8 + (2 * 18)).add(ENERGY, 80, 8 + (3 * 18)));

    static ResourceLocation buttonLocation = new ResourceLocation(Ref.ID, "textures/gui/button/gui_buttons.png");

    public static MenuHandlerMachine<TileEntityFusionReactor, ? extends ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerMultiMachine((TileEntityMultiMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_FUSION_REACTOR;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SIX = new MenuHandlerMachine(Ref.ID, "container_cabinet_six") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_SIX;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SEVEN = new MenuHandlerMachine(Ref.ID, "container_cabinet_seven") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_SEVEN;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_EIGHT = new MenuHandlerMachine(Ref.ID, "container_cabinet_eight") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_EIGHT;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_NINE = new MenuHandlerMachine(Ref.ID, "container_cabinet_nine") {
        @Override
        public ContainerCabinet getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_NINE;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerWorkbench> WORKBENCH_HANDLER = new MenuHandlerMachine(Ref.ID, "container_workbench") {
        @Override
        public ContainerWorkbench getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerWorkbench((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_WORKBENCH;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerBasicMachine> LOCKER_HANDLER = new MenuHandlerMachine(Ref.ID, "container_locker") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerBasicMachine<>((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_LOCKER;
        }
    };

    public static MenuHandlerCrafting COVER_CRAFTING_HANDLER = new MenuHandlerCrafting(Ref.ID, "crafting_grid");
    public static MenuHandlerCraftingItem ITEM_CRAFTING_HANDLER = new MenuHandlerCraftingItem(Ref.ID, "crafting_item");

    public static void init(Dist side) {

        AntimatterAPI.registerJEICategory(RecipeMaps.ORE_BYPRODUCTS, Guis.ORE_BYPRODUCTS);
        AntimatterAPI.registerJEICategory(RecipeMaps.INT_CIRCUITS, Guis.ORE_BYPRODUCTS);

        //TODO changing slots of a machine in world, will crash from GTItemHandler.validateSlot()

        initMaterialMachine(side);

        ALLOY_SMELTER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        ASSEMBLER.add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25)
                .add(ENERGY,80, 63);
        BENDER.add(ALLOY_SMELTER);
        CANNER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        COMPRESSOR.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        CUTTER.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY,80, 63);
        FORGE_HAMMER.add(COMPRESSOR);
        FURNACE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        EXTRACTOR.add(COMPRESSOR);
        EXTRUDER.add(ALLOY_SMELTER);
        LATHE.add(CUTTER);
        MACERATOR.add(MV, COMPRESSOR).add(MV, IT_OUT, 125, 25).add(MV, IT_OUT, 143, 25).setGuiTiers(new ImmutableMap.Builder<Tier, Tier>().put(LV, LV).put(MV, MV));;
        MACERATOR.add(LV, COMPRESSOR);
        RECYCLER.add(COMPRESSOR);
        WIRE_MILL.add(COMPRESSOR);
        CENTRIFUGE.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(FL_IN, 8, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY,26, 63).add(FL_OUT, 44, 63).add(FL_OUT, 62, 63).add(FL_OUT, 80, 63).add(FL_OUT, 98, 63).add(FL_OUT, 116, 63).add(FL_OUT, 134, 63);
        ELECTROLYZER.add(CENTRIFUGE);

        THERMAL_CENTRIFUGE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(ENERGY,80, 63);
        ORE_WASHER.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63);
        CHEMICAL_REACTOR.add(IT_IN, 17, 25).add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(FL_IN, 53, 63).add(FL_IN, 35, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(ENERGY, 80, 63);
        FLUID_CANNER.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        FLUID_EXTRACTOR.add(COMPRESSOR).add(FL_OUT, 107, 63);
        FLUID_SOLIDIFIER.add(COMPRESSOR).add(FL_IN, 53, 63);
        FERMENTER.add(FLUID_CANNER);
        DISTILLERY.add(FLUID_CANNER).add(FL_OUT, 125, 63);
        DISASSEMBLER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43)
                .add(ENERGY,80, 63);
        SIFTER.add(DISASSEMBLER);
        BATH.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY, 80, 63);
        DUSTBIN
                .add(IT_IN, 17, 7).add(IT_IN, 17+18, 7).add(IT_IN, 17+18*2, 7)
                .add(IT_IN, 17, 25).add(IT_IN, 17+18, 25).add(IT_IN, 17+18*2, 25)
                .add(IT_IN, 17, 43).add(IT_IN, 17+18, 43).add(IT_IN, 17+18*2, 43)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43);
        MASS_FABRICATOR.add(COMPRESSOR);

        COKE_OVEN.add(IT_IN, 53,25).add(IT_OUT,107,25).add(IT_OUT,125,25).add(IT_OUT,143,25).add(FL_OUT, 107, 63);
        BLAST_FURNACE.add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(SlotTypes.COIL, 8, 63);
        PRIMITIVE_BLAST_FURNACE.add(IT_IN, 35, 16).add(IT_IN, 35, 34).add(IT_IN, 35, 52).add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).getGui().setPadding(0, 0, 0, 0);
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
        QUANTUM_CHEST.add(IT_IN, 80, 17).add(DISPLAY, 60, 43).add(IT_OUT, 80, 53);

        HATCH_MUFFLER.add(IT_IN, 79, 34);

        HATCH_ITEM_I.add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);

        HATCH_ITEM_O.add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);

        HATCH_FLUID_I.add(FL_IN, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);

        HATCH_FLUID_O.add(FL_OUT, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);
        FUSION_FLUID_INJECTOR.add(HATCH_FLUID_I);
        FUSION_FLUID_EXTRACTOR.add(HATCH_FLUID_O);
        FUSION_ITEM_EXTRACTOR.add(IT_OUT, 79, 34);
        FUSION_ITEM_INJECTOR.add(IT_IN, 79, 34);

        COVER_CRAFTING.setGui(new GuiData(COVER_CRAFTING, COVER_CRAFTING_HANDLER));

        if (side.isClient()){
            PUMP.addGuiCallback(g -> g.addWidget(IOWidget.build(9,63,16,16)));
            BRONZE_WORKBENCH.addGuiCallback(t -> {
                t.addButton(136, 28, 16, 16, NO_OVERLAY);
                t.addButton(154, 28, 16, 16, NO_OVERLAY);
            });

            IRON_WORKBENCH.addGuiCallback(t -> {
                t.addButton(136, 28, 16, 16, NO_OVERLAY);
                t.addButton(154, 28, 16, 16, NO_OVERLAY);
            });

            ALUMINIUM_WORKBENCH.addGuiCallback(t -> {
                t.addButton(136, 28, 16, 16, NO_OVERLAY);
                t.addButton(154, 28, 16, 16, NO_OVERLAY);
            });

            ALUMINIUM_CHARGING_WORKBENCH.addGuiCallback(t -> {
                t.addButton(136, 28, 16, 16, NO_OVERLAY);
                t.addButton(154, 28, 16, 16, NO_OVERLAY);
            });

            IRON_CHARGING_WORKBENCH.addGuiCallback(t -> {
                t.addButton(136, 28, 16, 16, NO_OVERLAY);
                t.addButton(154, 28, 16, 16, NO_OVERLAY);
            });

            ButtonBody[][] overlays = new ButtonBody[][]{{IMPORT, IMPORT_CONDITIONAL, IMPORT_INVERT_CONDITIONAL, EXPORT, EXPORT_CONDITIONAL, EXPORT_INVERT_CONDITIONAL}, {IMPORT_EXPORT, IMPORT_EXPORT_CONDITIONAL, IMPORT_EXPORT_INVERT_CONDITIONAL, EXPORT_IMPORT, EXPORT_IMPORT_CONDITIONAL, EXPORT_IMPORT_INVERT_CONDITIONAL}};

            COVER_CONVEYOR.addGuiCallback(t -> {
                for (int x = 0; x < 6; x++){
                    for (int y = 0; y < 2; y++){
                        t.addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                    }
                }
            });
            COVER_ITEM_TRANSPORT_VALVE.addGuiCallback(t -> {
                for (int x = 0; x < 6; x++){
                    for (int y = 0; y < 2; y++){
                        t.addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                    }
                }
            });
            COVER_PUMP.addGuiCallback(t -> {
                for (int x = 0; x < 6; x++){
                    for (int y = 0; y < 2; y++){
                        t.addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                    }
                }
            });
            COVER_REDSTONE_MACHINE_CONTROLLER.addGuiCallback(t -> {
                t.addButton(61, 34, 16, 16, TORCH_ON).addButton(79, 34, 16, 16, TORCH_OFF).addButton(97, 34, 16, 16, REDSTONE);
            });
            FUSION_REACTOR.addGuiCallback(t -> {
                t.addButton(155, 23, 16, 16, NO_OVERLAY).addButton(155, 41, 16, 16, NO_OVERLAY).addButton(155, 59, 16, 16, NO_OVERLAY).addWidget(makeProgress(BarDir.LEFT, true, new int4(0, 235, 149, 16)).setSize(4,162, 149, 16)).addWidget(FusionButtonWidget.build());
            });
            TRANSFORMER_DIGITAL.addGuiCallback(t -> {
                t.addButton(10, 18, 14, 14, APAD_LEFT)
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
            });
            INFINITE_STORAGE.addGuiCallback(t -> {
                        t.addButton(10, 18, 14, 14, APAD_LEFT)
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
            });
            initWidgets();
        }
    }

    public static void initWidgets(){
        ELECTRIC_ITEM_FILTER.getCallbacks().remove(1);
        ELECTRIC_ITEM_FILTER.addGuiCallback(t -> {
            t.addWidget(FilterButtonArrayWidget.build());
        });
        ELECTRIC_TYPE_FILTER.getCallbacks().remove(1);
        ELECTRIC_TYPE_FILTER.addGuiCallback(t -> {
            t.addWidget(FilterButtonArrayWidget.build());
        });
        ELECTRIC_ITEM_TRANSLOCATOR.getCallbacks().remove(1);
        ELECTRIC_ITEM_TRANSLOCATOR.addGuiCallback(t -> {
            t.addWidget(TranslocatorButtonArrayWidget.build());
        });
        ELECTRIC_FLUID_TRANSLOCATOR.getCallbacks().remove(1);
        ELECTRIC_FLUID_TRANSLOCATOR.addGuiCallback(t -> {
            t.addWidget(TranslocatorButtonArrayWidget.build());
        });
        FORGE_HAMMER.getCallbacks().remove(1);
        FORGE_HAMMER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide())
                    .addWidget(ProgressWidget.build(BarDir.BOTTOM, false))
                    .addWidget(MachineStateWidget.build().setPos(84,46).setWH(8,8))
                    .addWidget(IOWidget.build(9,63,16,16));
        });
        STEAM_FORGE_HAMMER.getCallbacks().remove(1);
        STEAM_FORGE_HAMMER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide())
                    .addWidget(ProgressWidget.build(BarDir.BOTTOM, false))
                    .addWidget(MachineStateWidget.build().setPos(84,46).setWH(8,8));
        });
        COAL_BOILER.getCallbacks().remove(1);
        COAL_BOILER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide());
            t.addWidget(CoalBoilerWidget.build().setSize(70, 25, 63, 54));
        });
        CENTRIFUGE.getCallbacks().remove(1);
        ELECTROLYZER.getCallbacks().remove(1);
        CENTRIFUGE.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide())
                    .addWidget(ProgressWidget.build(BarDir.LEFT, true))
                    .addWidget(MachineStateWidget.build().setPos(84,46).setWH(8,8))
                    .addWidget(IOWidget.build(9,45,16,16));
        });
        ELECTROLYZER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide())
                    .addWidget(ProgressWidget.build(BarDir.LEFT, true))
                    .addWidget(MachineStateWidget.build().setPos(84,46).setWH(8,8))
                    .addWidget(IOWidget.build(9,45,16,16));
        });
        DISTILLATION_TOWER.getCallbacks().remove(1);
        DISTILLATION_TOWER.addGuiCallback(t -> t.addWidget(ProgressWidget.build(BarDir.TOP, true).setSize(80, 4, 16, 72)).addWidget(MachineStateWidgetMoved.build(176, 108).setPos(66, 26).setWH(8, 8)));
        PLATINUM_CABINET.getCallbacks().remove(0);
        OSMIUM_CABINET.getCallbacks().remove(0);
        PLATINUM_CABINET.addGuiCallback(t -> t.addWidget(BackgroundWidget.build(t.handler.getGuiTexture(),t.handler.guiSize(), t.handler.guiHeight(), 256, 266)));
        OSMIUM_CABINET.addGuiCallback(t -> t.addWidget(BackgroundWidget.build(t.handler.getGuiTexture(),t.handler.guiSize(), t.handler.guiHeight(), 256, 276)));
    }

    private static void initMaterialMachine(Dist side){
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                WORKBENCH.getSlots().add(STORAGE, 8 + (x * 18), 8 + (y * 18));
                CHARGING_WORKBENCH.getSlots().add(STORAGE, 8 + (x * 18), 8 + (y * 18));
            }
        }
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++){
                WORKBENCH.getSlots().add(CRAFTING, 82 + (x * 18), 28 + (y * 18));
                CHARGING_WORKBENCH.getSlots().add(CRAFTING, 82 + (x * 18), 28 + (y * 18));
            }
        }
        for (int x = 0; x < 5; x++){
            WORKBENCH.getSlots().add(TOOLS, 82 + (x * 18), 8);
            CHARGING_WORKBENCH.getSlots().add(TOOL_CHARGE, 82 + (x * 18), 8);
        }
        WORKBENCH.getSlots().add(PARK, 154, 46);
        CHARGING_WORKBENCH.getSlots().add(PARK, 154, 46);
       /* WORKBENCH.widget(addButton(136, 28, 16, 16, NO_OVERLAY, 0));
        WORKBENCH.widget(addButton(154, 28, 16, 16, NO_OVERLAY, 1));
        CHARGING_WORKBENCH.widget(addButton(136, 28, 16, 16, NO_OVERLAY, 0));
        CHARGING_WORKBENCH.widget(addButton(154, 28, 16, 16, NO_OVERLAY, 1));*/
        BiFunction<Boolean, String, ResourceLocation> textures = (c, l) -> new ResourceLocation(Ref.ID, "textures/gui/machine/" + (c ? "charging_" : "") + l + ".png");

        BRONZE_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        IRON_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        ALUMINIUM_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        IRON_CHARGING_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        ALUMINIUM_CHARGING_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        IRON_LOCKER.setGUI(LOCKER_HANDLER);
        ALUMINIUM_LOCKER.setGUI(LOCKER_HANDLER);
        IRON_CHARGING_LOCKER.setGUI(LOCKER_HANDLER);
        ALUMINIUM_CHARGING_LOCKER.setGUI(LOCKER_HANDLER);
        BRONZE_WORKBENCH.add(WORKBENCH.getSlots()).getGui().setOverrideLocation(textures.apply(false, "workbench"));
        IRON_WORKBENCH.add(WORKBENCH.getSlots()).getGui().setOverrideLocation(textures.apply(false, "workbench"));
        ALUMINIUM_WORKBENCH.add(WORKBENCH.getSlots()).getGui().setOverrideLocation(textures.apply(false, "workbench"));
        IRON_CHARGING_WORKBENCH.add(CHARGING_WORKBENCH.getSlots()).getGui().setOverrideLocation(textures.apply(true, "workbench"));
        ALUMINIUM_CHARGING_WORKBENCH.add(CHARGING_WORKBENCH.getSlots()).getGui().setOverrideLocation(textures.apply(true, "workbench"));
        IRON_LOCKER.add(LOCKER.getSlots()).getGui().setOverrideLocation(textures.apply(false, "locker"));
        ALUMINIUM_LOCKER.add(LOCKER.getSlots()).getGui().setOverrideLocation(textures.apply(false, "locker"));
        IRON_CHARGING_LOCKER.add(CHARGING_LOCKER.getSlots()).getGui().setOverrideLocation(textures.apply(true, "locker"));
        ALUMINIUM_CHARGING_LOCKER.add(CHARGING_LOCKER.getSlots()).getGui().setOverrideLocation(textures.apply(true, "locker"));

        FUSION_REACTOR.setGUI(FUSION_MENU_HANDLER);
        IRON_CABINET.setGUI(CABINET_HANDLER_SIX);
        ALUMINIUM_CABINET.setGUI(CABINET_HANDLER_SIX);
        WROUGHT_IRON_CABINET.setGUI(CABINET_HANDLER_SIX);
        BRASS_CABINET.setGUI(CABINET_HANDLER_SIX);
        CUPRONICKEL_CABINET.setGUI(CABINET_HANDLER_SIX);
        ELECTRUM_CABINET.setGUI(CABINET_HANDLER_SEVEN);
        GOLD_CABINET.setGUI(CABINET_HANDLER_SEVEN);
        SILVER_CABINET.setGUI(CABINET_HANDLER_SEVEN);
        MAGNALIUM_CABINET.setGUI(CABINET_HANDLER_SEVEN);
        PLATINUM_CABINET.setGUI(CABINET_HANDLER_EIGHT);
        OSMIUM_CABINET.setGUI(CABINET_HANDLER_NINE);
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
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; ++j) {
                if (i < 6){
                    IRON_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    ALUMINIUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    WROUGHT_IRON_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    BRASS_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    CUPRONICKEL_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                if (i < 7){
                    ELECTRUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    GOLD_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    SILVER_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                    MAGNALIUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                if (i < 8){
                    PLATINUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                OSMIUM_CABINET.add(STORAGE, 12 + j * 18, 18 + (i * 18));
            }
        }
    }

    public static WidgetSupplier makeProgress(BarDir dir, boolean barFill, int4 loc){
        return builder((screen, handler) -> new ProgressWidget(screen, handler, loc, dir, dir.getPos().x + 6, dir.getPos().y + 6, dir.getUV().z, dir.getUV().w, barFill));
    }
}
