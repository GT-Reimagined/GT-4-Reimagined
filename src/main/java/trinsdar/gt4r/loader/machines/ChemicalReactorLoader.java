package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.DUST;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.CHEMICAL_REACTING;

public class ChemicalReactorLoader {
    public static void init() {
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.MELON_SLICE, 1), NUGGET.getMaterialIngredient(Gold, 8)).io(new ItemStack(Items.GLISTERING_MELON_SLICE)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.CARROT, 1), NUGGET.getMaterialIngredient(Gold, 8)).io(new ItemStack(Items.GOLDEN_CARROT)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.APPLE, 1), INGOT.getMaterialIngredient(Gold, 8)).io(new ItemStack(Items.GOLDEN_APPLE)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.ENDER_PEARL, 1), DUST.getMaterialIngredient(Blaze, 1)).io(new ItemStack(Items.ENDER_EYE)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Tags.Items.SLIMEBALLS, 1), DUST.getMaterialIngredient(Blaze)).io(new ItemStack(Items.MAGMA_CREAM)).add(50, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Calcium, 1), DUST.getMaterialIngredient(Carbon, 1)).io(DUST.get(Calcite, 2)).add(250, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(5000)).add(3500, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Nitrogen.getGas(1000)).fo(NitroCarbon.getLiquid(2000)).add(1500, 30);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(1000), CoalFuel.getLiquid(4000)).fo(NitroCoalFuel.getLiquid(5000)).add(250,30);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(1000), Diesel.getLiquid(4000)).fo(NitroDiesel.getLiquid(5000)).add(1000,30);
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000), Oxygen.getGas(2000)).fo(NitrogenDioxide.getGas(3000)).add(1250, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000)).ii(DUST.getMaterialIngredient(SodiumSulfide, 1)).fo(SodiumPersulfate.getLiquid(3000)).add(2000, 30);
        CHEMICAL_REACTING.RB().fi(NitroCarbon.getLiquid(1000), Water.getLiquid(1000)).fo(Glyceryl.getLiquid(2000)).add(583, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Sodium, 1), DUST.getMaterialIngredient(Sulfur, 1)).io(DUST.get(SodiumSulfide, 2)).add(100, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Sulfur, 1)).fi(Water.getLiquid(2000)).fo(SulfuricAcid.getLiquid(3000)).add(1150, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(2000), Oxygen.getGas(1000)).fo(new FluidStack(Fluids.WATER, 3000)).add(10, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Sodium, 2)).fi(NitrogenDioxide.getGas(5000)).io(new ItemStack(Items.GUNPOWDER, 5)).add(34, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Potassium, 1)).fi(NitricAcid.getLiquid(1000)).io(DUST.get(Saltpeter, 1)).add(20, 30);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(3000), Water.getLiquid(1000)).ii(RecipeIngredient.of(GT4RData.CellTin, 1)).fo(NitricAcid.getLiquid(2000)).io(GT4RData.CellTin.fill(NitricOxide.getGas())).add(20, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Silicon, 1)).fi(Oxygen.getGas(2000)).io(DUST.get(SiliconDioxide, 3)).add(50,30);
        CHEMICAL_REACTING.RB().fi(NitricOxide.getGas(1000), Oxygen.getGas(1000)).fo(NitrogenDioxide.getGas(1000)).add(20, 30);
    }
}
