package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.GT4RData;

import static trinsdar.gt4r.data.Materials.FishOil;
import static trinsdar.gt4r.data.Materials.Honey;
import static trinsdar.gt4r.data.Materials.SeedOil;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_EXTRACTING;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_EXTRACTOR_COILS;

public class FluidExtractorLoader {
    public static void init() {
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.CupronickelHeatingCoil, 1)).add(0, 0, 250);
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.KanthalHeatingCoil, 1)).add(0, 0, 500);
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.NichromeHeatingCoil, 1)).add(0, 0, 750);
        AntimatterMaterialTypes.ROD.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.ROD.get(r), 0.5f);
        });
        AntimatterMaterialTypes.PLATE.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.PLATE.get(r), 1f);
        });
        AntimatterMaterialTypes.INGOT.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.INGOT.get(r), 1f);
            if (r.has(AntimatterMaterialTypes.DUST)){
                add(r, AntimatterMaterialTypes.DUST.get(r), 1f);
            }
        });
        AntimatterMaterialTypes.GEAR.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.GEAR.get(r), 4f);
        });
        AntimatterMaterialTypes.PLATE_DENSE.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.PLATE_DENSE.get(r), 9f);
        });

        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(ForgeCTags.SEEDS,1))
                .fo(SeedOil.getLiquid(10))
                .add(32, 2);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(ItemTags.FISHES,1))
                .fo(FishOil.getLiquid(10))
                .add(32, 2);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(Items.HONEY_BOTTLE,1))
                .fo(Honey.getLiquid(250))
                .io(new ItemStack(Items.GLASS_BOTTLE))
                .add(32, 2);
    }

    private static void add(Material m, Item i, float ratio) {
        if (!m.has(AntimatterMaterialTypes.LIQUID)) return;
        long amount = amount(ratio);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(i,1))
                .fo(m.getLiquid(amount))
                .add((long)(m.getMass()*((float)amount/ratio())), 64, MaterialTags.MELTING_POINT.getInt(m));
    }

    private static long ratio(){
        return AntimatterPlatformUtils.isForge() ? 144L : 9000L;
    }

    private static long amount(float ingots){
        return (long) (ratio() * ingots);
    }
}
