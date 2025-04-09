package org.gtreimagined.gt4r.datagen;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.data.VanillaStoneTypes;
import org.gtreimagined.gtlib.datagen.providers.GTBlockLootProvider;
import org.gtreimagined.gtlib.mixin.BlockLootTablesAccessor;
import org.gtreimagined.gtlib.ore.BlockOre;
import org.gtreimagined.gtlib.ore.BlockOreStone;
import org.gtreimagined.gtlib.ore.CobbleStoneType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.gtreimagined.gt4r.block.BlockCasing;
import org.gtreimagined.gt4r.block.BlockFakeCasing;
import org.gtreimagined.gt4r.block.BlockNonSolidMachine;

import java.util.function.Function;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.data.GTLibMaterials.*;
import static org.gtreimagined.gt4r.data.Materials.*;

public class GT4RBlockLootProvider extends GTBlockLootProvider {
    public GT4RBlockLootProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    protected void loot() {
        super.loot();
        GTAPI.all(BlockNonSolidMachine.class, providerDomain, this::add);
        GTAPI.all(BlockCasing.class, providerDomain, this::add);
        GTAPI.all(BlockFakeCasing.class, providerDomain, this::add);
        if (!GTAPI.isModLoaded("gt5r")){
            oreDrops();
        }
    }

    public void oreDrops(){
        overrideOre(GTLibMaterials.Redstone, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        overrideOre(GTLibMaterials.Lapis, b -> {
            return createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
        });
        overrideOre(Sodalite, b -> {
            return createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(GTMaterialTypes.GEM.get(Sodalite)).apply(SetItemCountFunction.setCount(UniformGenerator.between(6.0F, 6.0F))).apply(GT4RRandomDropBonus.uniformBonusCount(Enchantments.BLOCK_FORTUNE, 3)))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(GTMaterialTypes.DUST.get(Aluminium)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4))));
        });
        Function<BlockOre, LootTable.Builder> function = (b) -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(GTMaterialTypes.DUST.get(b.getMaterial())).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
        overrideOre(Pyrite, function);
        overrideOre(Cinnabar, b -> function.apply(b).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(GTMaterialTypes.DUST.get(GTLibMaterials.Redstone)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))));
        overrideOre(Sphalerite, b -> function.apply(b).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(GTMaterialTypes.DUST.get(Zinc)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 4)))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootTablesAccessor.getNoSilkTouch()).add(LootItem.lootTableItem(GTMaterialTypes.GEM.get(YellowGarnet)).apply(GT4RRandomDropBonus.randomDrops(Enchantments.BLOCK_FORTUNE, 32)))));
        Function<BlockOreStone, LootTable.Builder> function1 = (b) -> createOreDrop(b, GTMaterialTypes.DUST.get(b.getMaterial()));
        overrideBlock(ORE_STONE.get().get(Salt).asBlock(), b -> function1.apply((BlockOreStone) b));
        overrideBlock(ORE_STONE.get().get(RockSalt).asBlock(), b -> function1.apply((BlockOreStone) b));
        overrideOre(Coal, b -> createOreDrop(b, GEM.get(Coal)));
        overrideOre(Diamond, b -> createOreDrop(b, GEM.get(Diamond)));
        overrideOre(Emerald, b -> createOreDrop(b, GEM.get(Emerald)));
        overrideOre(Ruby, b -> createOreDrop(b, GEM.get(Ruby)));
        overrideOre(Sapphire, b -> createOreDrop(b, GEM.get(Sapphire)));
        /*tables.put(Blocks.LAPIS_ORE, b -> createOreDrop(b, RAW_ORE.get(Lapis)));
        tables.put(Blocks.DEEPSLATE_LAPIS_ORE, b -> createOreDrop(b, RAW_ORE.get(Lapis)));
        tables.put(Blocks.REDSTONE_ORE, b -> createOreDrop(b, RAW_ORE.get(Redstone)));
        tables.put(Blocks.DEEPSLATE_REDSTONE_ORE, b -> createOreDrop(b, RAW_ORE.get(Redstone)));*/
        tables.put(Blocks.COPPER_ORE, b -> createOreDrop(b, RAW_ORE.get(Copper)));
        tables.put(Blocks.DEEPSLATE_COPPER_ORE, b -> createOreDrop(b, RAW_ORE.get(Copper)));
        tables.put(Blocks.ANCIENT_DEBRIS, b -> createOreDrop(b, RAW_ORE.get(NetheriteScrap)));
        tables.put(Blocks.ANDESITE, b -> createSingleItemTableWithSilkTouch(Blocks.ANDESITE, ((CobbleStoneType) VanillaStoneTypes.ANDESITE).getBlock("cobble")));
        tables.put(Blocks.DIORITE, b -> createSingleItemTableWithSilkTouch(Blocks.DIORITE, ((CobbleStoneType) VanillaStoneTypes.DIORITE).getBlock("cobble")));
        tables.put(Blocks.GRANITE, b -> createSingleItemTableWithSilkTouch(Blocks.GRANITE, ((CobbleStoneType) VanillaStoneTypes.GRANITE).getBlock("cobble")));
        tables.put(Blocks.BASALT, b -> createSingleItemTableWithSilkTouch(Blocks.BASALT, ((CobbleStoneType) VanillaStoneTypes.BASALT).getBlock("cobble")));
        /*if (GTAPI.isModLoaded(Ref.MOD_AE)){
            tables.put(AppliedEnergisticsRegistrar.getAe2Block("quartz_ore"), b -> createOreDrop(b, RAW_ORE.get(Materials.CertusQuartz)));
            tables.put(AppliedEnergisticsRegistrar.getAe2Block("deepslate_quartz_ore"), b -> createOreDrop(b, RAW_ORE.get(Materials.CertusQuartz)));
        }
        if (GTAPI.isModLoaded("ad_astra")){
            tables.put(SpaceModRegistrar.getSpaceBlock("mars_diamond_ore"), b -> createOreDrop(b, RAW_ORE.get(Diamond)));
        }*/
    }
}
