package trinsdar.gt4r.datagen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockStone;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.mixin.BlockLootTablesAccessor;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.ore.BlockOreStone;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.block.BlockNonSolidMachine;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Machines;
import trinsdar.gt4r.data.Materials;

import java.util.Random;
import java.util.function.Function;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GEM;
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
        AntimatterAPI.all(BlockStone.class, providerDomain, b -> {
            /*if (b.getType().getMaterial() == Quartzite){
                tables.put(b, b2 -> droppingItemWithFortune(b, GEM.get(Quartzite)));
            }*/
            this.add(b);
        });
        AntimatterAPI.all(BlockCasing.class, providerDomain, this::add);
        tables.put(GT4RData.RUBBER_LEAVES, b -> droppingWithChancesAndSticks(GT4RData.RUBBER_LEAVES, GT4RData.RUBBER_SAPLING, 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F));
        this.add(GT4RData.RUBBER_LOG);
        this.add(GT4RData.RUBBER_SAPLING);
        this.add(GT4RData.SAP_BAG);
    }

    @Override
    protected void add(Block block) {
        if (block instanceof BlockOre){
            Material mat = ((BlockOre)block).getMaterial();
            if (mat.has(GEM) || mat == Redstone){
                Item item = mat == Redstone ? Items.REDSTONE : GEM.get(mat);
                if (mat == Lapis){
                    tables.put(block, b -> droppingWithSilkTouch(b, withExplosionDecay(b, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
                } else if (mat == Redstone){
                    tables.put(block, b -> droppingWithSilkTouch(b, withExplosionDecay(b, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 5.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
                } else {
                    tables.put(block, b -> droppingItemWithFortune(b, item));
                }
                return;
            } else if (mat == Cinnabar || mat == Sphalerite || mat == Pyrite){
                LootTable.Builder builder = droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(DUST.get(mat)).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
                if (mat == Cinnabar){
                    builder = builder.addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(BlockLootTablesAccessor.getNoSilkTouch()).addEntry(ItemLootEntry.builder(DUST.get(Redstone)).acceptFunction(GT4RRandomDropBonus.randomDrops(Enchantments.FORTUNE, 4))));
                } else if (mat == Sphalerite){
                    builder = builder.addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(BlockLootTablesAccessor.getNoSilkTouch()).addEntry(ItemLootEntry.builder(DUST.get(Zinc)).acceptFunction(GT4RRandomDropBonus.randomDrops(Enchantments.FORTUNE, 4)))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(BlockLootTablesAccessor.getNoSilkTouch()).addEntry(ItemLootEntry.builder(GEM.get(YellowGarnet)).acceptFunction(GT4RRandomDropBonus.randomDrops(Enchantments.FORTUNE, 32))));
                }
                LootTable.Builder finalBuilder = builder;
                tables.put(block, b -> finalBuilder);
                return;
            } else if (mat == Sodalite){
                tables.put(block, b -> droppingWithSilkTouch(b, withExplosionDecay(b, ItemLootEntry.builder(DUST.get(mat)).acceptFunction(SetCount.builder(RandomValueRange.of(6.0F, 6.0F))).acceptFunction(GT4RRandomDropBonus.uniformBonusCount(Enchantments.FORTUNE, 3)))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(BlockLootTablesAccessor.getNoSilkTouch()).addEntry(ItemLootEntry.builder(DUST.get(Aluminium)).acceptFunction(GT4RRandomDropBonus.randomDrops(Enchantments.FORTUNE, 4)))));
                return;
            }
        }
        if (block instanceof BlockOreStone && (((BlockOreStone)block).getMaterial() == Salt || ((BlockOreStone)block).getMaterial() == RockSalt)){
            tables.put(block, b -> droppingItemWithFortune(b, DUST.get(((BlockOreStone)block).getMaterial())));
            return;
        }
        tables.put(block, this::build);
    }
}
