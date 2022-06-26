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
                    tables.put(block, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
                } else if (mat == Redstone){
                    tables.put(block, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
                }
            } else if (mat == Cinnabar || mat == Sphalerite || mat == Pyrite){
                LootTable.Builder builder = createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(DUST.get(mat)).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
                if (mat == Cinnabar){
                    builder = builder.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(DUST.get(Redstone)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4))));
                } else if (mat == Sphalerite){
                    builder = builder.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(DUST.get(Zinc)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(GEM.get(YellowGarnet)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 32))));
                }
                LootTable.Builder finalBuilder = builder;
                tables.put(block, b -> finalBuilder);
            } else if (mat == Sodalite){
                tables.put(block, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(GEM.get(mat)).apply(SetItemCountFunction.setCount(UniformGenerator.between(6.0F, 6.0F))).apply(GT4RRandomDropBonus.uniformBonusCount(Enchantments.BLOCK_FORTUNE, 3)))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(DUST.get(Aluminium)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))));
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
