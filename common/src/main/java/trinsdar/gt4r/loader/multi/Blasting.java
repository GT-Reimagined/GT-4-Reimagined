package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.Ref;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.CustomTags.DUSTS_COALS;
import static trinsdar.gt4r.data.GT4RData.INT_CIRCUITS;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BASIC_BLASTING;
import static trinsdar.gt4r.data.RecipeMaps.BLASTING;

public class Blasting {
    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(CoalCoke, 2), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add("steel",7200);
        BASIC_BLASTING.RB().ii(of(DUSTS_COALS, 4), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 4)).add("steel_1",7200);
        BASIC_BLASTING.RB().ii(of(DUSTS_COALS, 2), DUST.getMaterialIngredient(Steel, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add("steel_2",7200);
        BASIC_BLASTING.RB().ii(GEM.getMaterialIngredient(CoalCoke, 2), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add("steel_3",7200);
        BASIC_BLASTING.RB().ii(of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 4), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 4)).add("steel_4",7200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Carbon, 2), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add("steel_5",7200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 2), of(DUSTS_COALS, 1)).io(INGOT.get(Lead, 1), INGOT.get(Silver, 1), DUST_TINY.get(DarkAsh, 1)).add("galena",7200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 4), DUST.getMaterialIngredient(CoalCoke, 1)).io(INGOT.get(Lead, 2), INGOT.get(Silver, 2), DUST_TINY.get(DarkAsh, 2)).add("galena_1",14400);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Pyrite, 3), DUST.getMaterialIngredient(Calcite, 1), of(DUSTS_COALS, 1)).io(new ItemStack(Items.IRON_INGOT, 2), DUST_TINY.get(DarkAsh, 1)).add("iron",1200);
        BASIC_BLASTING.RB().ii(of(ORE.getMaterialTag(Iron), 1), DUST.getMaterialIngredient(Calcite, 1), of(DUSTS_COALS, 1)).io(new ItemStack(Items.IRON_INGOT, 3), DUST_TINY.get(DarkAsh, 1)).add("iron_1",1200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 2), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(INGOT.get(Lead, 1), INGOT.get(Silver, 1), DUST_TINY.get(DarkAsh, 1)).add("galena_2",1200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 4), GEM.getMaterialIngredient(CoalCoke, 1)).io(INGOT.get(Lead, 2), INGOT.get(Silver, 2), DUST_TINY.get(DarkAsh, 2)).add("galena_3",2400);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Pyrite, 3), DUST.getMaterialIngredient(Calcite, 1), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(new ItemStack(Items.IRON_INGOT, 2), DUST_TINY.get(DarkAsh, 1)).add("iron_2",1200);
        BASIC_BLASTING.RB().ii(of(ORE.getMaterialTag(Iron), 1), DUST.getMaterialIngredient(Calcite, 1), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(new ItemStack(Items.IRON_INGOT, 3), DUST_TINY.get(DarkAsh, 1)).add("iron_3",1200);
        final int multiplier = 1;
        //Tag<Item> rockTag = getForgelikeItemTag()
        BLASTING.RB().ii(of(TagUtils.getForgelikeItemTag("ores/iron"), 1), of(DUST.get(Calcite, 1))).io(new ItemStack(Items.IRON_INGOT, 3), DUST.get(DarkAsh, 1)).add("iron",500, 120, 1500);
        BLASTING.RB().ii(of(DUST.get(Pyrite, 3)), of(DUST.get(Calcite, 1))).io(new ItemStack(Items.IRON_INGOT, 2)).add("iron_1",500, 120, 1500);
        Material m = Aluminium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add("aluminium",600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Silicon;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add("silicon",600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Steel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add("steel",600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Iron, 1)), of(DUST.get(Carbon, 1))).io(INGOT.get(m, 1)).add("steel_1",600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Iron, 1)), of(DUST.get(CoalCoke, 1))).io(INGOT.get(m, 1)).add("steel_2",600, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Titanium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add("titanium",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = TungstenSteel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("tungsten_steel",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Tungsten,1)), of(INGOT.get(Steel,1))).io(INGOT_HOT.get(m, 2), DUST.get(DarkAsh, 4)).add("tungsten_steel_1",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Netherite;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add("netherrite",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(DUST.get(NetheriteScrap,4)), of(INGOT.get(Gold,4))).io(INGOT.get(m, 1), DUST.get(DarkAsh, 4)).add("netherrite_1",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = TungstenCarbide;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("tungsten_carbide",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Tungsten,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(m, 2), DUST.get(DarkAsh, 4)).add("tungsten_carbide_1",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Tungsten;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("tungsten",10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Iridium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("iridium",10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Galena;
        BLASTING.RB().ii(of(DUST.get(m,2))).io(INGOT.get(Lead, 1), INGOT.get(Silver, 1)).add("galena",600, 120, 1500);
        m = Osmium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("osmium",10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Chrome;
        BLASTING.RB().ii(of(DUST.get(m,1)), INT_CIRCUITS.get(1)).io(INGOT.get(m, 1)).add("chrome",1700, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Osmiridium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("osmiridium",10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Osmium,1)), of(INGOT.get(Iridium, 1))).io(INGOT_HOT.get(m, 2)).add("osmiridium_1",10000, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = StainlessSteel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add("stainless_steel",10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Iron,6)), of(INGOT.get(Nickel, 1)), of(INGOT.get(Chrome, 1)), of(INGOT.get(Manganese, 1))).io(INGOT.get(m, 9)).add("stainless_steel_1",10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Kanthal;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("kanthal",5100, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Iron,1)), of(INGOT.get(Aluminium, 1)), of(INGOT.get(Chrome, 1))).io(INGOT_HOT.get(m, 3)).add("kanthal",5100, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        m = Nichrome;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add("nichrome",10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
        BLASTING.RB().ii(of(INGOT.get(Nickel,4)), of(INGOT.get(Chrome, 1)), INT_CIRCUITS.get(2)).io(INGOT_HOT.get(m, 5)).add("nichrome",10200, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
    }
}
