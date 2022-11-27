package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.SIFTING;

public class SiftingLoader {
    public static void init() {
        SIFTING.RB().ii(of(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialTag(Ruby), 1)).io(AntimatterMaterialTypes.GEM.get(Ruby, 1), AntimatterMaterialTypes.DUST.get(Ruby, 1)).chances(0.45, 0.35).add(800, 16);
        SIFTING.RB().ii(of(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialTag(Sapphire), 1)).io(AntimatterMaterialTypes.GEM.get(Sapphire, 1), AntimatterMaterialTypes.DUST.get(Sapphire, 1)).chances(0.45, 0.35).add(800, 16);
        SIFTING.RB().ii(of(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialTag(Olivine), 1)).io(AntimatterMaterialTypes.GEM.get(Olivine, 1), AntimatterMaterialTypes.DUST.get(Olivine, 1)).chances(0.45, 0.35).add(800, 16);
        SIFTING.RB().ii(of(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialTag(AntimatterMaterials.Diamond), 1)).io(new ItemStack(Items.DIAMOND, 1), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Diamond, 1)).chances(0.45, 0.35).add(800, 16);
        SIFTING.RB().ii(of(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialTag(AntimatterMaterials.Emerald), 1)).io(new ItemStack(Items.EMERALD, 1), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Emerald, 1)).chances(0.45, 0.35).add(800, 16);
        // for when ic2 comes along to 1.16
        //SIFTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Iridium), 1)).io(new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), new ItemStack(Items.Iridium_ore, 1), DUST.get(Iridium, 1)).chances(1, 4, 15, 20, 40, 50).add(800, 16);
        SIFTING.RB().ii(of(AntimatterMaterialTypes.CRUSHED_PURIFIED.getMaterialTag(AntimatterMaterials.Lapis), 1)).io(new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.LAPIS_LAZULI, 1), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Lapis, 1)).chances(0.01, 0.04, 0.15, 0.2, 0.4, 0.5).add(800, 16);
        SIFTING.RB().ii(of(TagUtils.getForgelikeItemTag("gravel"), 1)).io(new ItemStack(Items.FLINT, 1)).add(800, 16);
    }
}
