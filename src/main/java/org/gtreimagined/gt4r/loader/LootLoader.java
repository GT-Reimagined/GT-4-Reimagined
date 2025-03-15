package org.gtreimagined.gt4r.loader;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.AntimatterLoot;
import muramasa.antimatter.item.ItemBattery;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.gtreimagined.gt4r.GT4RConfig;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.data.Materials;

import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.Emerald;
import static muramasa.antimatter.data.AntimatterMaterials.Flint;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreMaterials.GreenSapphire;

public class LootLoader {
    public static void init(){
        if (!GT4RConfig.ADD_LOOT.get()) return;
        AntimatterLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, SWORD.getToolStack(Flint), 1, 1, 1);
        AntimatterLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, PICKAXE.getToolStack(Flint), 1, 1, 1);
        AntimatterLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, AXE.getToolStack(Flint), 1, 1, 1);
        AntimatterLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, SHOVEL.getToolStack(Flint), 1, 1, 1);

        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Materials.Silver,1), 1, 6, 120);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Materials.Lead, 1), 1, 6, 30);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Steel, 1), 1, 6, 60);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Bronze, 1), 1, 6, 60);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Emerald, 1), 1, 6, 20);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Ruby, 1), 1, 6, 20);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Sapphire, 1), 1, 6, 20);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(GreenSapphire, 1), 1, 6, 20);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Olivine, 1), 1, 6, 20);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(RedGarnet, 1), 1, 6, 40);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(YellowGarnet, 1), 1, 6, 40);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, DUST.get(Manganese, 1), 1, 3, 60);
        AntimatterLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, DUST.get(Chromium, 1), 1, 3, 40);

        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, INGOT.get(Silver, 1), 4, 16, 12);
        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, INGOT.get(Platinum, 1), 2, 8, 4);
        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(Ruby, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(Sapphire, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(GreenSapphire, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(Olivine, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(RedGarnet, 1), 2, 8, 4);
        AntimatterLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(YellowGarnet, 1), 2, 8, 4);

        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, ItemBattery.getFilledBattery(GT4RItems.ZPM), 1, 1, 1);
        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, INGOT.get(Bronze, 1), 4, 16, 12);
        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(Ruby, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(Sapphire, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(GreenSapphire, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(Olivine, 1), 2, 8, 2);
        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(RedGarnet, 1), 2, 8, 4);
        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(YellowGarnet, 1), 2, 8, 4);

        AntimatterLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER, new ItemStack(Items.FIRE_CHARGE, 1), 2, 8, 30);

        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Silver, 1), 1, 4, 12);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Lead, 1), 1, 4, 3);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Steel, 1), 1, 4, 6);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Bronze, 1), 1, 4, 6);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Sapphire, 1), 1, 4, 2);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(GreenSapphire, 1), 1, 4, 2);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Olivine, 1), 1, 4, 2);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(RedGarnet, 1), 1, 4, 4);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(YellowGarnet, 1), 1, 4, 4);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Ruby, 1), 1, 4, 2);
        AntimatterLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Emerald, 1), 1, 4, 2);

        AntimatterLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, DUST.get(Chromium, 1), 1, 4, 6);
        AntimatterLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, DUST.get(Manganese, 1), 2, 4, 12);
        AntimatterLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, INGOT.get(Steel, 1), 4, 12, 12);
        AntimatterLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, INGOT.get(Bronze, 1), 4, 12, 12);
        AntimatterLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, INGOT.get(Brass, 1), 4, 12, 12);

        AntimatterLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, DUST.get(Chromium, 1), 1, 8, 6);
        AntimatterLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, DUST.get(Manganese, 1), 1, 8, 12);
        AntimatterLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, INGOT.get(Steel, 1), 8, 32, 12);
        AntimatterLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, INGOT.get(Bronze, 1), 8, 32, 12);
    }
}
