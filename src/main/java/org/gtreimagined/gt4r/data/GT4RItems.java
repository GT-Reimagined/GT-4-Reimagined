package org.gtreimagined.gt4r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.item.ItemMultiTextureBattery;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.behaviour.BehaviourAOEBreak;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import org.gtreimagined.gtcore.data.GTCoreTools;
import org.gtreimagined.gtcore.item.ItemPowerUnit;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.client.RecipeRenderer;
import org.gtreimagined.gt4r.items.ItemCraftingModule;
import org.gtreimagined.gt4r.items.ItemElectricTool;
import org.gtreimagined.gt4r.items.ItemRockCutterUnit;
import org.gtreimagined.gt4r.items.ItemStorageOrb;

import static org.gtreimagined.gt4r.data.Materials.*;

public class GT4RItems {

    public static void init(Dist side) {
        if (side.isClient()){
            RecipeRenderer.clientMaps();
        }
    }

    ;

    public static ItemPowerUnit RockCutterPowerUnit = new ItemRockCutterUnit(GT4RRef.ID, "rock_cutter_power_unit", Aluminium);

    public static ItemBasic<?> ComputerMonitor = new ItemBasic<>(GT4RRef.ID, "computer_monitor").tip("Can be placed on machines as a cover");
    public static ItemCover ConveyorModule = AntimatterAPI.get(ItemCover.class, GT4RCovers.COVER_CONVEYOR.getId(), GT4RRef.ID);
    public static ItemCover CraftingModule = AntimatterAPI.get(ItemCraftingModule.class, GT4RCovers.COVER_CRAFTING.getId(), GT4RRef.ID);
    public static ItemCover Drain = AntimatterAPI.get(ItemCover.class, GT4RCovers.COVER_DRAIN.getId(), GT4RRef.ID);
    public static ItemCover PumpModule = AntimatterAPI.get(ItemCover.class, GT4RCovers.COVER_PUMP.getId(), GT4RRef.ID);
    public static ItemCover RedstoneMachineController = AntimatterAPI.get(ItemCover.class, GT4RCovers.COVER_REDSTONE_MACHINE_CONTROLLER.getId(), GT4RRef.ID);
    public static ItemBasic<?> Shutter = new ItemBasic<>(GT4RRef.ID, "shutter").tip("Can be placed on machines as a cover");

    public static ItemBasic<?> SteelUpgrade = new ItemBasic<>(GT4RRef.ID, "steel_upgrade").tip("Can be used to upgrade bronze steam machines to steel steam machines");
    public static ItemBasic<?> OverclockerUpgrade = new ItemBasic<>(GT4RRef.ID, "overclocker_upgrade");
    public static ItemBasic<?> TransformerUpgrade = new ItemBasic<>(GT4RRef.ID, "transformer_upgrade");
    public static ItemBasic<?> SteamUpgrade = new ItemBasic<>(GT4RRef.ID, "steam_upgrade");
    public static ItemBasic<?> HVTransformerUpgrade = new ItemBasic<>(GT4RRef.ID, "hv_transformer_upgrade");
    public static ItemBasic<?> MufflerUpgrade = new ItemBasic<>(GT4RRef.ID, "muffler_upgrade");

    public static ItemFluidCell CellTin = new ItemFluidCell(GT4RRef.ID,Tin, 1000);

    public static ItemBasic<?> CopperCoil = new ItemBasic<>(GT4RRef.ID, "copper_coil");
    public static ItemBasic<?> CupronickelHeatingCoil = new ItemBasic<>(GT4RRef.ID, "cupronickel_heating_coil");
    public static ItemBasic<?> KanthalHeatingCoil = new ItemBasic<>(GT4RRef.ID, "kanthal_heating_coil");
    public static ItemBasic<?> NichromeHeatingCoil = new ItemBasic<>(GT4RRef.ID, "nichrome_heating_coil");
    public static ItemBasic<?> ItemFilter = new ItemBasic<>(GT4RRef.ID, "item_filter");
    public static ItemBasic<?> ThickNeutronReflector = new ItemBasic<>(GT4RRef.ID, "thick_neutron_reflector");
    public static ItemBasic<?> NeutronReflector = new ItemBasic<>(GT4RRef.ID, "neutron_reflector");
    public static ItemBasic<?> ItemSuperconductor = new ItemBasic<>(GT4RRef.ID, "superconductor").tip("Conducts Energy Losslessly");
    public static ItemBasic<?> FrequencyTransmitter = new ItemBasic<>(GT4RRef.ID, "frequency_transmitter", new Item.Properties().tab(Ref.TAB_ITEMS).stacksTo(1));

    public static ItemBasic<?> LavaFilter = new ItemBasic<>(GT4RRef.ID, "lava_filter", new Item.Properties().tab(Ref.TAB_ITEMS).defaultDurability(100));

    public static ItemBasic<?> MachineParts = new ItemBasic<>(GT4RRef.ID, "machine_parts");
    public static ItemBasic<?> StorageDataOrb = new ItemStorageOrb(GT4RRef.ID, "storage_data_orb").tip("A High Capacity Data Storage");

    public static ItemBasic<?> SmallBatteryHull = new ItemBasic<>(GT4RRef.ID, "small_battery_hull").tip("An empty LV Battery Hull");
    public static ItemBasic<?> MediumBatteryHull = new ItemBasic<>(GT4RRef.ID, "medium_battery_hull").tip("An empty MV Battery Hull");
    public static ItemBasic<?> LargeBatteryHull = new ItemBasic<>(GT4RRef.ID, "large_battery_hull").tip("An empty HV Battery Hull");
    public static ItemBasic<?> REBattery = new ItemMultiTextureBattery(GT4RRef.ID, "re_battery", Tier.LV, 10000, true).tip("Reusable");
    public static ItemBasic<?> EnergyCrystal = new ItemMultiTextureBattery(GT4RRef.ID, "energy_crystal", Tier.MV, 100_000, true);
    public static ItemBasic<?> LapotronCrystal = new ItemMultiTextureBattery(GT4RRef.ID, "lapotron_crystal", Tier.HV, 1_000_000, true);
    public static ItemBasic<?> LapotronicEnergyOrb = new ItemMultiTextureBattery(GT4RRef.ID, "lapotronic_energy_orb", Tier.EV, 10_000_000, true);
    public static ItemBasic<?> LapotronicEnergyOrbCluster = new ItemMultiTextureBattery(GT4RRef.ID, "lapotronic_energy_orb_cluster", Tier.IV, 100_000_000, true);

    public static ItemElectricTool Drill = new ItemElectricTool("drill", GTCoreTools.DRILL, Steel, 6.0f, 5.5f, 2, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool DiamondDrill = new ItemElectricTool("diamond_drill", GTCoreTools.DRILL, Steel, 8.0f, 6.0f, 3, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool AdvancedDrill = new ItemElectricTool("advanced_drill", GTCoreTools.DRILL, TungstenSteel, 10.0f, 9.0f, 4, 2, b -> true);
    public static ItemElectricTool Chainsaw = new ItemElectricTool("chainsaw", GTCoreTools.CHAINSAW, Steel, 6.0f, 8.5f, 2, 1, b -> true);
    public static ItemElectricTool AdvancedChainsaw = new ItemElectricTool("advanced_chainsaw", GTCoreTools.CHAINSAW, TungstenSteel, 10.0f, 12f, 4, 2, b -> true);
    public static ItemElectricTool ElectricWrench = new ItemElectricTool("electric_wrench", GTCoreTools.ELECTRIC_WRENCH, Steel, 6.0f, 4f, 2, 1, b -> true);
    public static ItemElectricTool AdvancedWrench = new ItemElectricTool("advanced_wrench", GTCoreTools.ELECTRIC_WRENCH, TungstenSteel, 10.0f, 7.5f, 4, 2, b -> true);
    public static ItemElectricTool ElectricWrenchAlt = new ItemElectricTool("electric_wrench_alt", GTCoreTools.ELECTRIC_WRENCH_ALT, Steel, 6.0f, 4f, 2, 1, b -> true);
    public static ItemElectricTool AdvancedWrenchAlt = new ItemElectricTool("advanced_wrench_alt", GTCoreTools.ELECTRIC_WRENCH_ALT, TungstenSteel, 10.0f, 7.5f, 4, 2, b -> true);
    public static ItemElectricTool ElectricScrewdriver = new ItemElectricTool("electric_screwdriver", GTCoreTools.ELECTRIC_SCREWDRIVER, Steel, 6.0f, 2.5f, 2, 1, b -> true);
    public static ItemElectricTool RockCutter = new ItemElectricTool("rock_cutter", ToolTypes.ROCK_CUTTER, AntimatterMaterials.Diamond, 8.0f, 1.0f, 3, 1, b -> true);
    public static ItemElectricTool BronzeJackHammer = new ItemElectricTool("bronze_jackhammer", GTCoreTools.JACKHAMMER, Bronze, 6.0f, 5f, 2, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool SteelJackHammer = new ItemElectricTool("steel_jackhammer", GTCoreTools.JACKHAMMER, StainlessSteel, 8.0f, 5.5f, 2, 1, b -> !(b instanceof BehaviourAOEBreak));
    public static ItemElectricTool DiamondJackHammer = new ItemElectricTool("diamond_jackhammer", GTCoreTools.JACKHAMMER, AntimatterMaterials.Diamond, 10.0f, 6.0f, 3, 2, b -> true);

    public static ItemBasic<?> ZPM = new ItemMultiTextureBattery(GT4RRef.ID, "zpm", Tier.ZPM, 100000000000L, false);
    //public static ItemBasic<?> BatteryEnergyOrbCluster = new ItemBasic<>(Ref.ID, "battery_energy_orb_cluster");

}
