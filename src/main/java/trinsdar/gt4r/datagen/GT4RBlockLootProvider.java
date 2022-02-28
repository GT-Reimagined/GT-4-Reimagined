package trinsdar.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.mixin.BlockLootTablesAccessor;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.ore.BlockOreStone;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.block.BlockNonSolidMachine;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RBlockLootProvider extends AntimatterBlockLootProvider {
    public GT4RBlockLootProvider(String providerDomain, String providerName, DataGenerator gen) {
        super(providerDomain, providerName, gen);
    }

    @Override
    protected void loot() {
        super.loot();
        AntimatterAPI.all(BlockMachineMaterial.class, providerDomain, this::add);
        AntimatterAPI.all(BlockNonSolidMachine.class, providerDomain, this::add);

        AntimatterAPI.all(BlockCasing.class, providerDomain, this::add);
        tables.put(GT4RData.RUBBER_LEAVES, b -> createLeavesDrops(GT4RData.RUBBER_LEAVES, GT4RData.RUBBER_SAPLING, 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F));
        this.add(GT4RData.RUBBER_LOG);
        this.add(GT4RData.RUBBER_SAPLING);
        this.add(GT4RData.SAP_BAG);
        AntimatterAPI.all(BlockOre.class, this::addToFortune);
        AntimatterAPI.all(BlockOreStone.class, this::addToStone);
    }

    @Override
    protected void addToFortune(BlockOre block) {
        if (block.getOreType() == ORE){
            if (block.getStoneType() == SAND || block.getStoneType() == SAND_RED || block.getStoneType() == GRAVEL){
                tables.put(block, this::build);
                return;
            }
            Material mat = block.getMaterial();
            if (mat == Lapis || mat == Redstone){
                Item item = mat == Redstone ? Items.REDSTONE : GEM.get(mat);
                if (mat == Lapis){
                    tables.put(block, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, ItemLootEntry.lootTableItem(item).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
                } else if (mat == Redstone){
                    tables.put(block, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, ItemLootEntry.lootTableItem(item).apply(SetCount.setCount(RandomValueRange.between(4.0F, 5.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
                }
            } else if (mat == Cinnabar || mat == Sphalerite || mat == Pyrite){
                LootTable.Builder builder = createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(DUST.get(mat)).apply(SetCount.setCount(RandomValueRange.between(2.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
                if (mat == Cinnabar){
                    builder = builder.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(ItemLootEntry.lootTableItem(DUST.get(Redstone)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4))));
                } else if (mat == Sphalerite){
                    builder = builder.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(ItemLootEntry.lootTableItem(DUST.get(Zinc)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(ItemLootEntry.lootTableItem(GEM.get(YellowGarnet)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 32))));
                }
                LootTable.Builder finalBuilder = builder;
                tables.put(block, b -> finalBuilder);
            } else if (mat == Sodalite){
                tables.put(block, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, ItemLootEntry.lootTableItem(GEM.get(mat)).apply(SetCount.setCount(RandomValueRange.between(6.0F, 6.0F))).apply(GT4RRandomDropBonus.uniformBonusCount(Enchantments.BLOCK_FORTUNE, 3)))).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(ItemLootEntry.lootTableItem(DUST.get(Aluminium)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))));
            }
        }
        //super.addToFortune(block);
    }

    protected void addToStone(BlockOreStone block) {
        if (block.getMaterial() == Salt || block.getMaterial() == RockSalt) {
            tables.put(block, b -> createOreDrop(b, DUST.get(block.getMaterial())));
        }
    }
}
