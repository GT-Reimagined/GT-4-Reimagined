package org.gtreimagined.gt4r.loader;

import org.gtreimagined.gtlib.datagen.GTLoot;
import org.gtreimagined.gtlib.item.ItemBattery;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.gtreimagined.gt4r.GT4RConfig;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.data.Materials;

import static org.gtreimagined.gtlib.data.GTTools.*;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTMaterialTypes.DUST;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Emerald;
import static org.gtreimagined.gtlib.data.GTLibMaterials.Flint;
import static org.gtreimagined.gt4r.data.Materials.*;

public class LootLoader {
    public static void init(){
        if (!GT4RConfig.ADD_LOOT.get()) return;
        GTLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, SWORD.getToolStack(Flint), 1, 1, 1);
        GTLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, PICKAXE.getToolStack(Flint), 1, 1, 1);
        GTLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, AXE.getToolStack(Flint), 1, 1, 1);
        GTLoot.addItem(BuiltInLootTables.SPAWN_BONUS_CHEST, SHOVEL.getToolStack(Flint), 1, 1, 1);

        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Materials.Silver,1), 1, 6, 120);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Materials.Lead, 1), 1, 6, 30);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Steel, 1), 1, 6, 60);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, INGOT.get(Bronze, 1), 1, 6, 60);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Emerald, 1), 1, 6, 20);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Ruby, 1), 1, 6, 20);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Sapphire, 1), 1, 6, 20);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(GreenSapphire, 1), 1, 6, 20);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(Olivine, 1), 1, 6, 20);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(RedGarnet, 1), 1, 6, 40);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, GEM.get(YellowGarnet, 1), 1, 6, 40);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, DUST.get(Manganese, 1), 1, 3, 60);
        GTLoot.addItem(BuiltInLootTables.SIMPLE_DUNGEON, DUST.get(Chromium, 1), 1, 3, 40);

        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, INGOT.get(Silver, 1), 4, 16, 12);
        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, INGOT.get(Platinum, 1), 2, 8, 4);
        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(Ruby, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(Sapphire, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(GreenSapphire, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(Olivine, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(RedGarnet, 1), 2, 8, 4);
        GTLoot.addItem(BuiltInLootTables.DESERT_PYRAMID, GEM.get(YellowGarnet, 1), 2, 8, 4);

        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, ItemBattery.getFilledBattery(GT4RItems.ZPM), 1, 1, 1);
        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, INGOT.get(Bronze, 1), 4, 16, 12);
        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(Ruby, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(Sapphire, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(GreenSapphire, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(Olivine, 1), 2, 8, 2);
        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(RedGarnet, 1), 2, 8, 4);
        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE, GEM.get(YellowGarnet, 1), 2, 8, 4);

        GTLoot.addItem(BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER, new ItemStack(Items.FIRE_CHARGE, 1), 2, 8, 30);

        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Silver, 1), 1, 4, 12);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Lead, 1), 1, 4, 3);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Steel, 1), 1, 4, 6);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, INGOT.get(Bronze, 1), 1, 4, 6);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Sapphire, 1), 1, 4, 2);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(GreenSapphire, 1), 1, 4, 2);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Olivine, 1), 1, 4, 2);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(RedGarnet, 1), 1, 4, 4);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(YellowGarnet, 1), 1, 4, 4);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Ruby, 1), 1, 4, 2);
        GTLoot.addItem(BuiltInLootTables.ABANDONED_MINESHAFT, GEM.get(Emerald, 1), 1, 4, 2);

        GTLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, DUST.get(Chromium, 1), 1, 4, 6);
        GTLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, DUST.get(Manganese, 1), 2, 4, 12);
        GTLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, INGOT.get(Steel, 1), 4, 12, 12);
        GTLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, INGOT.get(Bronze, 1), 4, 12, 12);
        GTLoot.addItem(BuiltInLootTables.VILLAGE_WEAPONSMITH, INGOT.get(Brass, 1), 4, 12, 12);

        GTLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, DUST.get(Chromium, 1), 1, 8, 6);
        GTLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, DUST.get(Manganese, 1), 1, 8, 12);
        GTLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, INGOT.get(Steel, 1), 8, 32, 12);
        GTLoot.addItem(BuiltInLootTables.STRONGHOLD_CROSSING, INGOT.get(Bronze, 1), 8, 32, 12);
    }
}
