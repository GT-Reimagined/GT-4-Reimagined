package trinsdar.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.client.ScreenFactories;
import trinsdar.gt4r.gui.ContainerCabinet;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.gui.MenuHandlerCrafting;
import trinsdar.gt4r.gui.MenuHandlerCraftingItem;
import trinsdar.gt4r.tile.multi.TileEntityDistillationTower;
import trinsdar.gt4r.tile.multi.TileEntityFusionReactor;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialBlastFurnace;
import trinsdar.gt4r.tile.single.TileEntityCoalBoiler;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

import static muramasa.antimatter.gui.ButtonBody.*;
import static muramasa.antimatter.gui.SlotType.*;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.GT4RData.COVER_CONVEYOR;
import static trinsdar.gt4r.data.GT4RData.COVER_CRAFTING;
import static trinsdar.gt4r.data.GT4RData.COVER_PUMP;
import static trinsdar.gt4r.data.GT4RData.COVER_REDSTONE_MACHINE_CONTROLLER;
import static trinsdar.gt4r.data.Machines.*;
import static trinsdar.gt4r.data.SlotTypes.*;
import static trinsdar.gt4r.gui.ButtonOverlays.*;
import static trinsdar.gt4r.gui.ButtonOverlays.EXPORT;
import static trinsdar.gt4r.gui.ButtonOverlays.IMPORT;
import static trinsdar.gt4r.gui.ButtonOverlays.TORCH_OFF;
import static trinsdar.gt4r.gui.ButtonOverlays.TORCH_ON;

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

    public static GuiData WORKBENCH = new GuiData("gt4r","workbench");
    public static GuiData CHARGING_WORKBENCH = new GuiData("gt4r","charging_workbench");
    public static GuiData LOCKER = new GuiData("gt4r","locker").add(STORAGE, 80, 8).add(STORAGE, 80, 8 + (18)).add(STORAGE, 80, 8 + (2 * 18)).add(STORAGE, 80, 8 + (3 * 18));
    public static GuiData CHARGING_LOCKER = new GuiData("gt4r","charging_locker").add(ENERGY, 80, 8).add(ENERGY, 80, 8 + (18)).add(ENERGY, 80, 8 + (2 * 18)).add(ENERGY, 80, 8 + (3 * 18));

    public static MenuHandlerMachine<TileEntityCoalBoiler,? extends ContainerMachine> COAL_BOILER_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_coal_boiler") {
        @Override
        public ContainerBasicMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerBasicMachine( (TileEntityMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_COAL_BOILER;
        }
    };

    public static MenuHandlerMachine<TileEntityFusionReactor, ? extends ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerMultiMachine((TileEntityMultiMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_FUSION_REACTOR;
        }
    };

    public static MenuHandlerMachine<TileEntityDistillationTower, ? extends ContainerBasicMachine> DISTILLATION_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_distillation_tower") {
        @Override
        public ContainerBasicMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerBasicMachine((TileEntityBasicMultiMachine) tile, playerInv, this, windowId) : null;
        }
        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_DISTILLATION_TOWER;
        }
    };

    public static MenuHandlerMachine<TileEntityIndustrialBlastFurnace, ? extends ContainerBasicMachine> IBF_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_industrial_blast_furnace") {
        @Override
        public ContainerBasicMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerBasicMachine((TileEntityBasicMultiMachine) tile, playerInv, this, windowId) : null;
        }
        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_IBF;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SIX = new MenuHandlerMachine(Ref.ID, "container_cabinet_six") {
        @Override
        public ContainerCabinet getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_SIX;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_SEVEN = new MenuHandlerMachine(Ref.ID, "container_cabinet_seven") {
        @Override
        public ContainerCabinet getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_SEVEN;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_EIGHT = new MenuHandlerMachine(Ref.ID, "container_cabinet_eight") {
        @Override
        public ContainerCabinet getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_EIGHT;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerCabinet> CABINET_HANDLER_NINE = new MenuHandlerMachine(Ref.ID, "container_cabinet_nine") {
        @Override
        public ContainerCabinet getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerCabinet((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_CABINET_NINE;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerWorkbench> WORKBENCH_HANDLER = new MenuHandlerMachine(Ref.ID, "container_workbench") {
        @Override
        public ContainerWorkbench getMenu(Object tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMaterial ? new ContainerWorkbench((TileEntityMaterial) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ScreenFactories.SCREEN_WORKBENCH;
        }
    };

    public static MenuHandlerMachine<? extends TileEntityMaterial, ? extends ContainerBasicMachine> LOCKER_HANDLER = new MenuHandlerMachine(Ref.ID, "container_locker") {
        @Override
        public ContainerBasicMachine getMenu(Object tile, PlayerInventory playerInv, int windowId) {
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

        ALLOY_SMELTER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        ASSEMBLER.getGui().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25)
                .add(ENERGY,80, 63);
        BENDER.getGui().add(ALLOY_SMELTER);
        CANNER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        COMPRESSOR.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        CUTTER.getGui().add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY,80, 63);
        FORGE_HAMMER.getGui().add(COMPRESSOR).setDir(BarDir.BOTTOM).setBarFill(false);
        FURNACE.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        EXTRACTOR.getGui().add(COMPRESSOR);
        EXTRUDER.getGui().add(ALLOY_SMELTER);
        LATHE.getGui().add(CUTTER);
        MACERATOR.getGui().add(MV, COMPRESSOR).add(MV, IT_OUT, 125, 25).add(MV, IT_OUT, 143, 25);
        MACERATOR.getGui().add(LV, COMPRESSOR);
        RECYCLER.getGui().add(COMPRESSOR);
        WIRE_MILL.getGui().add(COMPRESSOR);
        CENTRIFUGE.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(FL_IN, 8, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY,26, 63).add(FL_OUT, 44, 63).add(FL_OUT, 62, 63).add(FL_OUT, 80, 63).add(FL_OUT, 98, 63).add(FL_OUT, 116, 63).add(FL_OUT, 134, 63).setIO(9, 46, 14, 14).setItem(44, 63, 16, 16).setFluid(62, 63, 16, 16);
        ELECTROLYZER.getGui().add(CENTRIFUGE).setIO(9, 46, 14, 14).setItem(35, 45, 16, 16).setFluid(53, 45, 16, 16);
        THERMAL_CENTRIFUGE.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(ENERGY,80, 63);
        ORE_WASHER.getGui().add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63);
        CHEMICAL_REACTOR.getGui().add(IT_IN, 17, 25).add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(FL_IN, 53, 63).add(FL_IN, 35, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(ENERGY, 80, 63);
        FLUID_CANNER.getGui().add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        FLUID_EXTRACTOR.getGui().add(COMPRESSOR).add(FL_OUT, 107, 63);
        FLUID_SOLIDIFIER.getGui().add(COMPRESSOR).add(FL_IN, 53, 63);
        FERMENTER.getGui().add(FLUID_CANNER);
        DISTILLERY.getGui().add(FLUID_CANNER).add(FL_OUT, 125, 63);
        DISASSEMBLER.getGui().add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43)
                .add(ENERGY,80, 63);
        SIFTER.getGui().add(DISASSEMBLER);
        BATH.getGui().add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY, 80, 63);
        DUSTBIN.getGui()
                .add(IT_IN, 17, 7).add(IT_IN, 17+18, 7).add(IT_IN, 17+18*2, 7)
                .add(IT_IN, 17, 25).add(IT_IN, 17+18, 25).add(IT_IN, 17+18*2, 25)
                .add(IT_IN, 17, 43).add(IT_IN, 17+18, 43).add(IT_IN, 17+18*2, 43)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43).setHasIOButton(false);
        MASS_FABRICATOR.getGui().add(COMPRESSOR);

        COKE_OVEN.getGui().add(IT_IN, 53,25).add(IT_OUT,107,25).add(IT_OUT,125,25).add(IT_OUT,143,25).add(FL_OUT, 107, 63);
        BLAST_FURNACE.getGui().add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(SlotTypes.COIL, 8, 63).setHasIOButton(false);
        PRIMITIVE_BLAST_FURNACE.getGui().add(IT_IN, 35, 16).add(IT_IN, 35, 34).add(IT_IN, 35, 52).add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).setPadding(0, 0, 0, 0);
        FUSION_REACTOR.getGui().setEnablePlayerSlots(false);
        DISTILLATION_TOWER.getGui().add(FL_IN, 62, 41).add(FL_OUT, 98, 59).add(FL_OUT, 98, 41).add(FL_OUT, 98, 23).add(FL_OUT, 98, 5).add(FL_OUT, 116, 23).add(FL_OUT, 116, 5).add(IT_OUT, 116, 41).add(IT_OUT, 116, 59).add(ENERGY, 62, 59).setDir(BarDir.TOP).setProgress(80, 4, 16, 72).setState(66, 26, 8, 8).setStateLocation(176, 108).setHasIOButton(false);
        VACUUM_FREEZER.getGui().add(COMPRESSOR).setHasIOButton(false);
        IMPLOSION_COMPRESSOR.getGui().add(ALLOY_SMELTER).add(IT_OUT, 125, 25).setHasIOButton(false);
        INDUSTRIAL_SAWMILL.getGui().add(ORE_WASHER).setHasIOButton(false);
        INDUSTRIAL_GRINDER.getGui().add(BATH).setHasIOButton(false);
        LARGE_GAS_TURBINE.getGui().add(ROTOR, 152, 5);
        LARGE_STEAM_TURBINE.getGui().add(LARGE_GAS_TURBINE);
        THERMAL_BOILER.getGui().add(FILTER, 152, 5);
        PUMP.getGui().add(IT_IN, 53, 25).add(FL_OUT, 107, 25).add(ENERGY, 80, 63);



        BATTERY_BUFFER_ONE.getGui().add(ENERGY,80,40).setHasIOButton(false);
        BATTERY_BUFFER_FOUR.getGui().add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,71,45).add(ENERGY,89,45).setHasIOButton(false);
        BATTERY_BUFFER_EIGHT.getGui()
                .add(ENERGY,53,27).add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,107,27)
                .add(ENERGY,53,45).add(ENERGY,71,45).add(ENERGY,89,45).add(ENERGY,107,45).setHasIOButton(false);

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
        STEAM_FORGE_HAMMER.getGui().add(BRONZE, FORGE_HAMMER).add(BRONZE, FL_IN, 53, 63).setDir(BarDir.BOTTOM).setBarFill(false);

        STEAM_TURBINE.getGui().add(BASIC_TANK).setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        GAS_TURBINE.getGui().add(BASIC_TANK).setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        DIESEL_GENERATOR.getGui().add(BASIC_TANK).setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        SEMIFLUID_GENERATOR.getGui().add(BASIC_TANK).setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        HEAT_EXCHANGER.getGui().add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63);


        INFINITE_STEAM.getGui().add(CELL_IN, 80, 17).add(CELL_OUT, 80, 53).add(FL_OUT, 60, 43);

        QUANTUM_TANK.getGui().add(BASIC_TANK).setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        QUANTUM_CHEST.getGui().add(IT_IN, 80, 17).add(DISPLAY, 60, 43).add(IT_OUT, 80, 53);

        HATCH_MUFFLER.getGui().add(IT_IN, 79, 34);

        HATCH_ITEM_I.getGui().add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);

        HATCH_ITEM_O.getGui().add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);

        HATCH_FLUID_I.getGui().add(FL_IN, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);

        HATCH_FLUID_O.getGui().add(FL_OUT, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);
        FUSION_FLUID_INJECTOR.getGui().add(HATCH_FLUID_I);
        FUSION_FLUID_EXTRACTOR.getGui().add(HATCH_FLUID_O);
        FUSION_ITEM_EXTRACTOR.getGui().add(IT_OUT, 79, 34);
        FUSION_ITEM_INJECTOR.getGui().add(IT_IN, 79, 34);

        COVER_CRAFTING.setGui(new GuiData(COVER_CRAFTING, COVER_CRAFTING_HANDLER));

        if (side.isClient()){
            BRONZE_WORKBENCH.getGui().addButton(136, 28, 16, 16, NO_OVERLAY);
            BRONZE_WORKBENCH.getGui().addButton(154, 28, 16, 16, NO_OVERLAY);
            IRON_WORKBENCH.getGui().addButton(136, 28, 16, 16, NO_OVERLAY);
            IRON_WORKBENCH.getGui().addButton(154, 28, 16, 16, NO_OVERLAY);
            ALUMINIUM_WORKBENCH.getGui().addButton(136, 28, 16, 16, NO_OVERLAY);
            ALUMINIUM_WORKBENCH.getGui().addButton(154, 28, 16, 16, NO_OVERLAY);
            IRON_CHARGING_WORKBENCH.getGui().addButton(136, 28, 16, 16, NO_OVERLAY);
            IRON_CHARGING_WORKBENCH.getGui().addButton(154, 28, 16, 16, NO_OVERLAY);
            ALUMINIUM_CHARGING_WORKBENCH.getGui().addButton(136, 28, 16, 16, NO_OVERLAY);
            ALUMINIUM_CHARGING_WORKBENCH.getGui().addButton(154, 28, 16, 16, NO_OVERLAY);
            ButtonBody[][] overlays = new ButtonBody[][]{{IMPORT, IMPORT_CONDITIONAL, IMPORT_INVERT_CONDITIONAL, EXPORT, EXPORT_CONDITIONAL, EXPORT_INVERT_CONDITIONAL}, {IMPORT_EXPORT, IMPORT_EXPORT_CONDITIONAL, IMPORT_EXPORT_INVERT_CONDITIONAL, EXPORT_IMPORT, EXPORT_IMPORT_CONDITIONAL, EXPORT_IMPORT_INVERT_CONDITIONAL}};

            for (int x = 0; x < 6; x++){
                for (int y = 0; y < 2; y++){
                    COVER_CONVEYOR.getGui().addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                    COVER_PUMP.getGui().addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                }
            }
            COVER_REDSTONE_MACHINE_CONTROLLER.getGui().addButton(61, 34, 16, 16, TORCH_ON).addButton(79, 34, 16, 16, TORCH_OFF).addButton(97, 34, 16, 16, REDSTONE);
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

    private static void initMaterialMachine(Dist side){
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                WORKBENCH.add(STORAGE, 8 + (x * 18), 8 + (y * 18));
                CHARGING_WORKBENCH.add(STORAGE, 8 + (x * 18), 8 + (y * 18));
            }
        }
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++){
                WORKBENCH.add(CRAFTING, 82 + (x * 18), 28 + (y * 18));
                CHARGING_WORKBENCH.add(CRAFTING, 82 + (x * 18), 28 + (y * 18));
            }
        }
        for (int x = 0; x < 5; x++){
            WORKBENCH.add(TOOLS, 82 + (x * 18), 8);
            CHARGING_WORKBENCH.add(TOOL_CHARGE, 82 + (x * 18), 8);
        }
        WORKBENCH.add(PARK, 154, 46);
        CHARGING_WORKBENCH.add(PARK, 154, 46);
        if (side.isClient()){
            WORKBENCH.addButton(136, 28, 16, 16, NO_OVERLAY, "Export Crafting stacks to storage");
            WORKBENCH.addButton(154, 28, 16, 16, NO_OVERLAY, "Export Crafting stacks to player");
            CHARGING_WORKBENCH.addButton(136, 28, 16, 16, NO_OVERLAY);
            CHARGING_WORKBENCH.addButton(154, 28, 16, 16, NO_OVERLAY);
        }
        BRONZE_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        IRON_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        ALUMINIUM_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        IRON_CHARGING_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        ALUMINIUM_CHARGING_WORKBENCH.setGUI(WORKBENCH_HANDLER);
        IRON_LOCKER.setGUI(LOCKER_HANDLER);
        ALUMINIUM_LOCKER.setGUI(LOCKER_HANDLER);
        IRON_CHARGING_LOCKER.setGUI(LOCKER_HANDLER);
        ALUMINIUM_CHARGING_LOCKER.setGUI(LOCKER_HANDLER);
        BRONZE_WORKBENCH.getGui().add(WORKBENCH);
        IRON_WORKBENCH.getGui().add(WORKBENCH);
        ALUMINIUM_WORKBENCH.getGui().add(WORKBENCH);
        IRON_CHARGING_WORKBENCH.getGui().add(CHARGING_WORKBENCH);
        ALUMINIUM_CHARGING_WORKBENCH.getGui().add(CHARGING_WORKBENCH);
        IRON_LOCKER.getGui().add(LOCKER);
        ALUMINIUM_LOCKER.getGui().add(LOCKER);
        IRON_CHARGING_LOCKER.getGui().add(CHARGING_LOCKER);
        ALUMINIUM_CHARGING_LOCKER.getGui().add(CHARGING_LOCKER);
        COAL_BOILER.setGUI(COAL_BOILER_MENU_HANDLER);
        FUSION_REACTOR.setGUI(FUSION_MENU_HANDLER);
        //DISTILLATION_TOWER.setGUI(DISTILLATION_MENU_HANDLER);
        BLAST_FURNACE.setGUI(IBF_MENU_HANDLER);
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
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; ++j) {
                if (i < 6){
                    IRON_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                    ALUMINIUM_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                    WROUGHT_IRON_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                    BRASS_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                    CUPRONICKEL_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                if (i < 7){
                    ELECTRUM_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                    GOLD_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                    SILVER_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                    MAGNALIUM_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                if (i < 8){
                    PLATINUM_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
                }
                OSMIUM_CABINET.getGui().add(SlotTypes.STORAGE, 12 + j * 18, 18 + (i * 18));
            }
        }
    }
}
