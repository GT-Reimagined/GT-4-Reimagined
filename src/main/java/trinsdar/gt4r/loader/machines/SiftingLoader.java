package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.util.TagUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.SIFTING;

public class SiftingLoader {
    public static void init() {
        SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Ruby), 1)).io(GEM.get(Ruby, 1), DUST.get(Ruby, 1)).chances(45, 35).add(800, 16);
        SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Sapphire), 1)).io(GEM.get(Sapphire, 1), DUST.get(Sapphire, 1)).chances(45, 35).add(800, 16);
        SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Olivine), 1)).io(GEM.get(Olivine, 1), DUST.get(Olivine, 1)).chances(45, 35).add(800, 16);
        SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Diamond), 1)).io(new ItemStack(Items.DIAMOND, 1), DUST.get(Diamond, 1)).chances(45, 35).add(800, 16);
        SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Emerald), 1)).io(new ItemStack(Items.EMERALD, 1), DUST.get(Emerald, 1)).chances(45, 35).add(800, 16);
        // for when ic2 comes along to 1.16
        //SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Iridium), 1)).io(new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), DUST.get(Iridium, 1)).chances(1, 4, 15, 20, 40, 50).add(800, 16);
        SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Lapis), 1)).io(new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), DUST.get(Lapis, 1)).chances(1, 4, 15, 20, 40, 50).add(800, 16);
        SIFTING.RB().ii(of(TagUtils.getForgeItemTag("gravel"), 1)).io(new ItemStack(Items.FLINT, 1)).add(800, 16);
    }
}
