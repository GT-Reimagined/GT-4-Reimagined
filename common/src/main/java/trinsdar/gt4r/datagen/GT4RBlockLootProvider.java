package trinsdar.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.mixin.BlockLootTablesAccessor;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.ore.BlockOreStone;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.block.BlockNonSolidMachine;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RBlockLootProvider extends AntimatterBlockLootProvider {
    public GT4RBlockLootProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    protected void loot() {
        super.loot();
        AntimatterAPI.all(BlockMachineMaterial.class, providerDomain, this::add);
        AntimatterAPI.all(BlockNonSolidMachine.class, providerDomain, this::add);

        AntimatterAPI.all(BlockCasing.class, providerDomain, this::add);
        this.add(GT4RData.SAP_BAG);
    }

    @Override
    protected void addToFortune(BlockOre block) {
        if (block.getOreType() == ORE){
            if (block.getStoneType() == SAND || block.getStoneType() == SAND_RED || block.getStoneType() == GRAVEL){
                tables.put(block, this::build);
            }
        }
    }
}
