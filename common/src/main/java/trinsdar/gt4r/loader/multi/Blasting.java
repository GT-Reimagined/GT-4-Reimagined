package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.Ref;

import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.CustomTags.DUSTS_COALS;
import static trinsdar.gt4r.data.GT4RData.INT_CIRCUITS;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BASIC_BLASTING;
import static trinsdar.gt4r.data.RecipeMaps.BLASTING;

public class Blasting {
    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(CoalCoke, 2), AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).io(AntimatterMaterialTypes.INGOT.get(Steel,1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(of(DUSTS_COALS, 4), AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).io(AntimatterMaterialTypes.INGOT.get(Steel,1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 4)).add(7200);
        BASIC_BLASTING.RB().ii(of(DUSTS_COALS, 2), AntimatterMaterialTypes.DUST.getMaterialIngredient(Steel, 1)).io(AntimatterMaterialTypes.INGOT.get(Steel,1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.GEM.getMaterialIngredient(CoalCoke, 2), AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).io(AntimatterMaterialTypes.INGOT.get(Steel,1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 4), AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).io(AntimatterMaterialTypes.INGOT.get(Steel,1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 4)).add(7200);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Carbon, 2), AntimatterMaterialTypes.INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).io(AntimatterMaterialTypes.INGOT.get(Steel,1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Galena, 2), of(DUSTS_COALS, 1)).io(AntimatterMaterialTypes.INGOT.get(Lead, 1), AntimatterMaterialTypes.INGOT.get(Silver, 1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 1)).add(7200);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Galena, 4), AntimatterMaterialTypes.DUST.getMaterialIngredient(CoalCoke, 1)).io(AntimatterMaterialTypes.INGOT.get(Lead, 2), AntimatterMaterialTypes.INGOT.get(Silver, 2), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 2)).add(14400);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Pyrite, 3), AntimatterMaterialTypes.DUST.getMaterialIngredient(Calcite, 1), of(DUSTS_COALS, 1)).io(new ItemStack(Items.IRON_INGOT, 2), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(of(AntimatterMaterialTypes.ORE.getMaterialTag(AntimatterMaterials.Iron), 1), AntimatterMaterialTypes.DUST.getMaterialIngredient(Calcite, 1), of(DUSTS_COALS, 1)).io(new ItemStack(Items.IRON_INGOT, 3), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Galena, 2), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(AntimatterMaterialTypes.INGOT.get(Lead, 1), AntimatterMaterialTypes.INGOT.get(Silver, 1), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Galena, 4), AntimatterMaterialTypes.GEM.getMaterialIngredient(CoalCoke, 1)).io(AntimatterMaterialTypes.INGOT.get(Lead, 2), AntimatterMaterialTypes.INGOT.get(Silver, 2), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 2)).add(2400);
        BASIC_BLASTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Pyrite, 3), AntimatterMaterialTypes.DUST.getMaterialIngredient(Calcite, 1), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(new ItemStack(Items.IRON_INGOT, 2), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(of(AntimatterMaterialTypes.ORE.getMaterialTag(AntimatterMaterials.Iron), 1), AntimatterMaterialTypes.DUST.getMaterialIngredient(Calcite, 1), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(new ItemStack(Items.IRON_INGOT, 3), AntimatterMaterialTypes.DUST_TINY.get(DarkAsh, 1)).add(1200);
        final int multiplier = 1;
        //Tag<Item> rockTag = getForgelikeItemTag()
        BLASTING.RB().ii(of(TagUtils.getForgelikeItemTag("ores/iron"), 1), of(AntimatterMaterialTypes.DUST.get(Calcite, 1))).io(new ItemStack(Items.IRON_INGOT, 3), AntimatterMaterialTypes.DUST.get(DarkAsh, 1)).add(500, 120, 1500);
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Pyrite, 3)), of(AntimatterMaterialTypes.DUST.get(Calcite, 1))).io(new ItemStack(Items.IRON_INGOT, 2)).add(500, 120, 1500);
        Material m = Aluminium;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Silicon;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Steel;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(AntimatterMaterials.Iron, 1)), of(AntimatterMaterialTypes.DUST.get(Carbon, 1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(AntimatterMaterials.Iron, 1)), of(AntimatterMaterialTypes.DUST.get(CoalCoke, 1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Titanium;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = TungstenSteel;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(Tungsten,1)), of(AntimatterMaterialTypes.INGOT.get(Steel,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 2), AntimatterMaterialTypes.DUST.get(DarkAsh, 4)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = AntimatterMaterials.Netherite;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.NetheriteScrap,4)), of(AntimatterMaterialTypes.INGOT.get(AntimatterMaterials.Gold,4))).io(AntimatterMaterialTypes.INGOT.get(m, 1), AntimatterMaterialTypes.DUST.get(DarkAsh, 4)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = TungstenCarbide;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(Tungsten,1)), of(AntimatterMaterialTypes.DUST.get(Carbon,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 2), AntimatterMaterialTypes.DUST.get(DarkAsh, 4)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Tungsten;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Iridium;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Galena;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,2))).io(AntimatterMaterialTypes.INGOT.get(Lead, 1), AntimatterMaterialTypes.INGOT.get(Silver, 1)).add(600, 120, 1500);
        m = Osmium;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Chrome;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1)), INT_CIRCUITS.get(1)).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Osmiridium;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(Osmium,1)), of(AntimatterMaterialTypes.INGOT.get(Iridium, 1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 2)).add(10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = StainlessSteel;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT.get(m, 1)).add(10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(AntimatterMaterials.Iron,6)), of(AntimatterMaterialTypes.INGOT.get(Nickel, 1)), of(AntimatterMaterialTypes.INGOT.get(Chrome, 1)), of(AntimatterMaterialTypes.INGOT.get(Manganese, 1))).io(AntimatterMaterialTypes.INGOT.get(m, 9)).add(10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Kanthal;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(5100, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(AntimatterMaterials.Iron,1)), of(AntimatterMaterialTypes.INGOT.get(Aluminium, 1)), of(AntimatterMaterialTypes.INGOT.get(Chrome, 1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 3)).add(5100, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Nichrome;
        BLASTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(m,1))).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 1)).add(10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(AntimatterMaterialTypes.INGOT.get(Nickel,4)), of(AntimatterMaterialTypes.INGOT.get(Chrome, 1)), INT_CIRCUITS.get(2)).io(AntimatterMaterialTypes.INGOT_HOT.get(m, 5)).add(10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
    }
}
