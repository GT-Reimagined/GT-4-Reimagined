package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
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
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.CupronickelHeatingCoil, 1)).add("cupronickel_heating_coil",0, 0, 250);
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.KanthalHeatingCoil, 1)).add("kanthal_heating_coil",0, 0, 500);
        FLUID_EXTRACTOR_COILS.RB().ii(RecipeIngredient.of(GT4RData.NichromeHeatingCoil, 1)).add("nichrome_heating_coil",0, 0, 750);
        ROD.all().forEach(r -> {
            add(r, ROD, 0.5f);
        });
        PLATE.all().forEach(r -> {
            add(r, PLATE, 1f);
        });
        INGOT.all().forEach(r -> {
            add(r, INGOT, 1f);
            if (r.has(DUST)){
                add(r, DUST, 1f);
            }
        });
        GEAR.all().forEach(r -> {
            add(r, GEAR, 4f);
        });
        PLATE_DENSE.all().forEach(r -> {
            add(r, PLATE_DENSE, 9f);
        });

        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(ForgeCTags.SEEDS,1))
                .fo(SeedOil.getLiquid(10))
                .add("seed_oil",32, 2);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(ItemTags.FISHES,1))
                .fo(FishOil.getLiquid(10))
                .add("fish_oil",32, 2);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(Items.HONEY_BOTTLE,1))
                .fo(Honey.getLiquid(250))
                .io(new ItemStack(Items.GLASS_BOTTLE))
                .add("honey",32, 2);
    }

    private static void add(Material m, MaterialTypeItem<?> i, float ratio) {
        if (!m.has(LIQUID)) return;
        long amount = amount(ratio);
        FLUID_EXTRACTING.RB()
                .ii(i.getMaterialIngredient(m, 1))
                .fo(m.getLiquid(amount))
                .add(m.getId() + "_" + i.getId(), (long)(m.getMass()*((float)amount/ratio())), 64, MaterialTags.MELTING_POINT.getInt(m));
    }

    private static long ratio(){
        return AntimatterPlatformUtils.isForge() ? 144L : 9000L;
    }

    private static long amount(float ingots){
        return (long) (ratio() * ingots);
    }
}
