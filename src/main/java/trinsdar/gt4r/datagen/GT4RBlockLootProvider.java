package trinsdar.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockStone;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Materials;

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
            }
        }
        if (block instanceof BlockStone && (((BlockStone)block).getType().getMaterial() == Salt || ((BlockStone)block).getType().getMaterial() == RockSalt)){
            tables.put(block, b -> droppingItemWithFortune(b, DUST.get(((BlockStone)block).getType().getMaterial())));
            return;
        }
        tables.put(block, this::build);
    }
}
