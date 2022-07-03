package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.Ref;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.CustomTags.DUSTS_COALS;
import static trinsdar.gt4r.data.GT4RData.INT_CIRCUITS;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.BASIC_BLASTING;
import static trinsdar.gt4r.data.RecipeMaps.BLASTING;

public class Blasting {
    public static int mixedOreYield = Ref.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(CoalCoke, 2), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(of(DUSTS_COALS, 4), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 4)).add(7200);
        BASIC_BLASTING.RB().ii(of(DUSTS_COALS, 2), DUST.getMaterialIngredient(Steel, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(GEM.getMaterialIngredient(CoalCoke, 2), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 4), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 4)).add(7200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Carbon, 2), INGOT.getMaterialIngredient(Iron, 1)).io(INGOT.get(Steel,1), DUST_TINY.get(DarkAsh, 2)).add(7200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 2), of(DUSTS_COALS, 1)).io(INGOT.get(Lead, 1), INGOT.get(Silver, 1), DUST_TINY.get(DarkAsh, 1)).add(7200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 4), DUST.getMaterialIngredient(CoalCoke, 1)).io(INGOT.get(Lead, 2), INGOT.get(Silver, 2), DUST_TINY.get(DarkAsh, 2)).add(14400);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Pyrite, 3), DUST.getMaterialIngredient(Calcite, 1), of(DUSTS_COALS, 1)).io(new ItemStack(Items.IRON_INGOT, 2), DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(of(ORE.getMaterialTag(Iron), 1), DUST.getMaterialIngredient(Calcite, 1), of(DUSTS_COALS, 1)).io(new ItemStack(Items.IRON_INGOT, 3), DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 2), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(INGOT.get(Lead, 1), INGOT.get(Silver, 1), DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Galena, 4), GEM.getMaterialIngredient(CoalCoke, 1)).io(INGOT.get(Lead, 2), INGOT.get(Silver, 2), DUST_TINY.get(DarkAsh, 2)).add(2400);
        BASIC_BLASTING.RB().ii(DUST.getMaterialIngredient(Pyrite, 3), DUST.getMaterialIngredient(Calcite, 1), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(new ItemStack(Items.IRON_INGOT, 2), DUST_TINY.get(DarkAsh, 1)).add(1200);
        BASIC_BLASTING.RB().ii(of(ORE.getMaterialTag(Iron), 1), DUST.getMaterialIngredient(Calcite, 1), of(TagUtils.getItemTag(new ResourceLocation("minecraft", "coals")), 1)).io(new ItemStack(Items.IRON_INGOT, 3), DUST_TINY.get(DarkAsh, 1)).add(1200);
        final int multiplier = 1;
        //Tag<Item> rockTag = getForgeItemTag()
        BLASTING.RB().ii(of(TagUtils.getForgeItemTag("ores/iron"), 1), of(DUST.get(Calcite, 1))).io(new ItemStack(Items.IRON_INGOT, 3), DUST.get(DarkAsh, 1)).add(500, 120, 1500);
        BLASTING.RB().ii(of(DUST.get(Pyrite, 3)), of(DUST.get(Calcite, 1))).io(new ItemStack(Items.IRON_INGOT, 2)).add(500, 120, 1500);
        Material m = Aluminium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        m = Silicon;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        m = Steel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron, 1)), of(DUST.get(Carbon, 1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron, 1)), of(DUST.get(CoalCoke, 1))).io(INGOT.get(m, 1)).add(600, 120, m.getBlastTemp());
        m = Titanium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        m = TungstenSteel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Tungsten,1)), of(INGOT.get(Steel,1))).io(INGOT_HOT.get(m, 2), DUST.get(DarkAsh, 4)).add(1700, 120, m.getBlastTemp());
        m = Netherite;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(DUST.get(NetheriteScrap,4)), of(INGOT.get(Gold,4))).io(INGOT.get(m, 1), DUST.get(DarkAsh, 4)).add(1700, 120, m.getBlastTemp());
        m = TungstenCarbide;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Tungsten,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(m, 2), DUST.get(DarkAsh, 4)).add(1700, 120, m.getBlastTemp());
        m = Tungsten;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        m = Iridium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        m = Galena;
        BLASTING.RB().ii(of(DUST.get(m,2))).io(INGOT.get(Lead, 1), INGOT.get(Silver, 1)).add(600, 120, 1500);
        m = Osmium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        m = Chrome;
        BLASTING.RB().ii(of(DUST.get(m,1)), INT_CIRCUITS.get(1)).io(INGOT.get(m, 1)).add(1700, 120, m.getBlastTemp());
        m = Osmiridium;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10000, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Osmium,1)), of(INGOT.get(Iridium, 1))).io(INGOT_HOT.get(m, 2)).add(10000, 120, m.getBlastTemp());
        m = StainlessSteel;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT.get(m, 1)).add(10200, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron,6)), of(INGOT.get(Nickel, 1)), of(INGOT.get(Chrome, 1)), of(INGOT.get(Manganese, 1))).io(INGOT.get(m, 9)).add(10200, 120, m.getBlastTemp());
        m = Kanthal;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(5100, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Iron,1)), of(INGOT.get(Aluminium, 1)), of(INGOT.get(Chrome, 1))).io(INGOT_HOT.get(m, 3)).add(5100, 120, m.getBlastTemp());
        m = Nichrome;
        BLASTING.RB().ii(of(DUST.get(m,1))).io(INGOT_HOT.get(m, 1)).add(10200, 120, m.getBlastTemp());
        BLASTING.RB().ii(of(INGOT.get(Nickel,4)), of(INGOT.get(Chrome, 1)), INT_CIRCUITS.get(2)).io(INGOT_HOT.get(m, 5)).add(10200, 120, m.getBlastTemp());
    }
}
