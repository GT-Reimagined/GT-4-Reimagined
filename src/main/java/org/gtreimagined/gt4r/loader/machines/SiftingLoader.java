package org.gtreimagined.gt4r.loader.machines;

import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.SIFTER;

public class SiftingLoader {
    public static void init() {
        SIFTER.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Ruby), 1)).io(GEM.get(Ruby, 1), DUST.get(Ruby, 1)).outputChances(0.45, 0.35).add("ruby",800, 16);
        SIFTER.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Sapphire), 1)).io(GEM.get(Sapphire, 1), DUST.get(Sapphire, 1)).outputChances(0.45, 0.35).add("sapphire",800, 16);
        SIFTER.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Olivine), 1)).io(GEM.get(Olivine, 1), DUST.get(Olivine, 1)).outputChances(0.45, 0.35).add("olivine",800, 16);
        SIFTER.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Diamond), 1)).io(new ItemStack(Items.DIAMOND, 1), DUST.get(Diamond, 1)).outputChances(0.45, 0.35).add("diamond",800, 16);
        SIFTER.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Emerald), 1)).io(new ItemStack(Items.EMERALD, 1), DUST.get(Emerald, 1)).outputChances(0.45, 0.35).add("emerald",800, 16);
        // for when ic2 comes along to 1.16
        //SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Iridium), 1)).io(new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), DUST.get(Iridium, 1)).chances(1, 4, 15, 20, 40, 50).add(800, 16);
        SIFTER.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Lapis), 1)).io(new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), DUST.get(Lapis, 1)).outputChances(0.01, 0.04, 0.15, 0.2, 0.4, 0.5).add("lapis",800, 16);
        SIFTER.RB().ii(of(TagUtils.getForgelikeItemTag("gravel"), 1)).io(new ItemStack(Items.FLINT, 1)).add("flint",800, 16);
    }
}
