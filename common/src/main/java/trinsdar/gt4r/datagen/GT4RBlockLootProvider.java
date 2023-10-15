package trinsdar.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.mixin.BlockLootTablesAccessor;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.ore.BlockOreStone;
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
import trinsdar.gt4r.block.BlockNonSolidMachine;
import trinsdar.gt4r.material.GT4RMaterialEvent;

import java.util.function.Function;

import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE_STONE;
import static trinsdar.gt4r.data.Materials.*;

public class GT4RBlockLootProvider extends AntimatterBlockLootProvider {
    public GT4RBlockLootProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    protected void loot() {
        super.loot();
        AntimatterAPI.all(BlockNonSolidMachine.class, providerDomain, this::add);

        AntimatterAPI.all(BlockCasing.class, providerDomain, this::add);
        if (!AntimatterAPI.isModLoaded("gti")){
            oreDrops();
        }
    }

    public void oreDrops(){
        overrideOre(AntimatterMaterials.Redstone, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        overrideOre(AntimatterMaterials.Lapis, b -> {
            return createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
        });
        overrideOre(Sodalite, b -> {
            return createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(AntimatterMaterialTypes.GEM.get(Sodalite)).apply(SetItemCountFunction.setCount(UniformGenerator.between(6.0F, 6.0F))).apply(GT4RRandomDropBonus.uniformBonusCount(Enchantments.BLOCK_FORTUNE, 3)))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(AntimatterMaterialTypes.DUST.get(Aluminium)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4))));
        });
        Function<BlockOre, LootTable.Builder> function = (b) -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(AntimatterMaterialTypes.DUST.get(b.getMaterial())).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
        overrideOre(Pyrite, function);
        overrideOre(Cinnabar, b -> function.apply(b).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Redstone)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))));
        overrideOre(Sphalerite, b -> function.apply(b).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(AntimatterMaterialTypes.DUST.get(Zinc)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(AntimatterMaterialTypes.GEM.get(YellowGarnet)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 32)))));
        Function<BlockOreStone, LootTable.Builder> function1 = (b) -> createOreDrop(b, AntimatterMaterialTypes.DUST.get(b.getMaterial()));
        overrideBlock(ORE_STONE.get().get(Salt).asBlock(), b -> function1.apply((BlockOreStone) b));
        overrideBlock(ORE_STONE.get().get(RockSalt).asBlock(), b -> function1.apply((BlockOreStone) b));
    }
}
