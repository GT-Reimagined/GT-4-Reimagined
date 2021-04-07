package trinsdar.gt4r.loader.multi;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.Materials;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.INDUSTRIAL_GRINDING;

public class IndustrialGrinder {
    public static void init(){
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (m == Diamond || m == Emerald || m == Olivine || m == Lapis || m == Redstone || m == Iridium || m == Platinum){
                return;
            }
            if (m == Bauxite){
                addGrinderRecipe(m, Water, CRUSHED_PURIFIED.get(m, 2 * m.getOreMulti()), DUST.get(Grossular, 1), DUST.get(Aluminium, 1));
                return;
            }
            int multiplier = (m == Ruby || m == Sapphire) ? 2 : 1;
            MaterialTypeItem<?> dustByproduct = DUST_SMALL;
            if (m == Sodalite){
                dustByproduct = DUST;
            }
            Material oreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : m.getMacerateInto();
            Material oreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : oreByProduct1;

            addGrinderRecipe(m, Water, CRUSHED_PURIFIED.get(m, 2 * m.getOreMulti()), dustByproduct.get(oreByProduct1, multiplier), dustByproduct.get(oreByProduct2, multiplier));
        });
        addGrinderRecipe(Platinum, Water, CRUSHED_PURIFIED.get(Platinum, 2), DUST.get(Nickel, 1), DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(Diamond, Water, CRUSHED_PURIFIED.get(Diamond, 2), DUST_SMALL.get(Diamond, 2), DUST.get(Coal, 1));
        addGrinderRecipe(Olivine, Water, CRUSHED_PURIFIED.get(Olivine, 2), DUST_SMALL.get(Olivine, 2), DUST_SMALL.get(Pyrope, 2));
        addGrinderRecipe(Emerald, Water, CRUSHED_PURIFIED.get(Emerald, 2), DUST_SMALL.get(Emerald, 2), DUST_SMALL.get(Aluminium, 2));
        addGrinderRecipe(Lapis, Water, CRUSHED_PURIFIED.get(Lapis, 12), DUST.get(Lazurite, 3));
        addGrinderRecipe(Redstone, Water, CRUSHED_PURIFIED.get(Redstone, 10), DUST_SMALL.get(Cinnabar, 1), DUST_SMALL.get(Glowstone, 1));
        addGrinderRecipe(Iridium, Water, CRUSHED_PURIFIED.get(Iridium, 2), DUST_SMALL.get(Iridium, 2), DUST_SMALL.get(Platinum, 2));
        addGrinderRecipe(Iridium, Mercury, CRUSHED_PURIFIED.get(Iridium, 2), DUST.get(Platinum, 1));
        addGrinderRecipe(Platinum, Mercury, CRUSHED_PURIFIED.get(Platinum, 3), DUST.get(Nickel, 1), DUST_TINY.get(Iridium, 2));
        addGrinderRecipe(Galena, Mercury, CRUSHED_PURIFIED.get(Galena, 2), DUST_SMALL.get(Sulfur, 1), DUST.get(Silver, 1));
        addGrinderRecipe(Gold, Mercury, CRUSHED_PURIFIED.get(Gold, 3), DUST_SMALL.get(Copper, 1), DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(Copper, Mercury, CRUSHED_PURIFIED.get(Copper, 2), DUST_SMALL.get(Nickel, 1), DUST.get(Gold, 1));
        addGrinderRecipe(Uraninite, Mercury, CRUSHED_PURIFIED.get(Uraninite, 2), DUST_SMALL.get(Uranium235, 1), DUST.get(Lead, 1));
        addGrinderRecipe(Iron, SodiumPersulfate, CRUSHED_PURIFIED.get(Iron, 2), DUST.get(Nickel, 1), DUST_SMALL.get(Tin, 1));
        addGrinderRecipe(Sphalerite, SodiumPersulfate, CRUSHED_PURIFIED.get(Sphalerite, 2), DUST.get(Zinc, 1), DUST_SMALL.get(YellowGarnet, 1));
        addGrinderRecipe(Tetrahedrite, SodiumPersulfate, CRUSHED_PURIFIED.get(Tetrahedrite, 3), DUST_SMALL.get(Zinc, 1), DUST_SMALL.get(Antimony, 1));
        addGrinderRecipe(Tin, SodiumPersulfate, CRUSHED_PURIFIED.get(Tin, 2), DUST.get(Zinc, 1), DUST_SMALL.get(Iron, 1));
        addGrinderRecipe(Copper, SodiumPersulfate, CRUSHED_PURIFIED.get(Copper, 3), DUST_SMALL.get(Nickel, 1), DUST_SMALL.get(Gold, 1));
        addGrinderRecipe(Gold, SodiumPersulfate, CRUSHED_PURIFIED.get(Gold, 2), DUST.get(Copper, 1), DUST_SMALL.get(Nickel, 1));
        addGrinderRecipe(Uraninite, SodiumPersulfate, CRUSHED_PURIFIED.get(Uraninite, 3), DUST_SMALL.get(Uranium235, 1), DUST_SMALL.get(Lead, 1));
        addGrinderRecipe(Bauxite, SulfuricAcid, CRUSHED_PURIFIED.get(Bauxite, 4), DUST.get(Aluminium, 1), DUST_SMALL.get(Titanium, 1));
        addGrinderRecipe(Ruby, SulfuricAcid, GEM.get(Ruby, 2), DUST_SMALL.get(RedGarnet, 1));
        addGrinderRecipe(Sapphire, SulfuricAcid, GEM.get(Sapphire, 2), DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(Diamond, SulfuricAcid, new ItemStack(Items.DIAMOND, 2));
        addGrinderRecipe(Emerald, SulfuricAcid, new ItemStack(Items.EMERALD, 2), DUST_SMALL.get(Aluminium, 1));
        addGrinderRecipe(Olivine, SulfuricAcid, GEM.get(Olivine, 2), DUST_SMALL.get(Pyrope, 2));

        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GT4RData.GRANITE_BLACK.getState().getBlock(), 16))).fi(Water.getLiquid(1000)).io(DUST.get(BlackGranite, 16), DUST_SMALL.get(Thorium, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(GT4RData.GRANITE_RED.getState().getBlock(), 16))).fi(Water.getLiquid(1000)).io(DUST.get(RedGranite, 16), DUST_SMALL.get(Uranium238, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(new ItemStack(Items.END_STONE, 1))).fi(Water.getLiquid(1000)).io(DUST.get(Endstone, 16), DUST_TINY.get(Tungsten, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(ORE.getMaterialTag(Coal), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.COAL), DUST.get(Coal, 1), DUST_SMALL.get(Thorium, 1)).add(100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(ORE.getMaterialTag(Quartz), 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.QUARTZ, 4), DUST_SMALL.get(Netherrack, 2)).add(100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.TERRACOTTA, 2)).fi(Water.getLiquid(1000)).io(DUST.get(Clay, 2)).add(100, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 16)).fi(Water.getLiquid(1000)).io(DUST.get(Netherrack, 16), DUST_TINY.get(Gold, 1)).add(1600, 120);
        INDUSTRIAL_GRINDING.RB().ii(of(Items.NETHERRACK, 8)).fi(Mercury.getLiquid(1000)).io(DUST.get(Netherrack, 8), DUST_TINY.get(Gold, 5)).add(1600, 120);
    }

    private static void addGrinderRecipe(Material ore, Material fluid, ItemStack... outputs){
        INDUSTRIAL_GRINDING.RB().ii(of(ORE.getMaterialTag(ore), 1)).fi(fluid.getLiquid(1000)).io(outputs).add(100, 120);
    }
}
