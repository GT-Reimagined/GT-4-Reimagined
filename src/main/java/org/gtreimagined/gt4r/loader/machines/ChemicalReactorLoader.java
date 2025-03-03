package org.gtreimagined.gt4r.loader.machines;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_TINY;
import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.CHEMICAL_REACTOR;

public class ChemicalReactorLoader {
    public static void init() {
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.MELON_SLICE, 1), NUGGET.getMaterialIngredient(Gold, 8)).io(new ItemStack(Items.GLISTERING_MELON_SLICE)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.CARROT, 1), NUGGET.getMaterialIngredient(Gold, 8)).io(new ItemStack(Items.GOLDEN_CARROT)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.APPLE, 1), INGOT.getMaterialIngredient(Gold, 8)).io(new ItemStack(Items.GOLDEN_APPLE)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.ENDER_PEARL, 1), DUST.getMaterialIngredient(Blaze, 1)).io(new ItemStack(Items.ENDER_EYE)).add(50, 30);
        //CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Tags.Items.SLIMEBALLS, 1), DUST.getMaterialIngredient(Blaze)).io(new ItemStack(Items.MAGMA_CREAM)).add(50, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Calcium, 1), DUST.getMaterialIngredient(Carbon, 1)).io(DUST.get(Calcite, 2)).add("calcite_dust",250, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(5000)).add("methane",3500, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Nitrogen.getGas(1000)).fo(NitroCarbon.getLiquid(2000)).add("nitro_carbon",1500, 30);
        CHEMICAL_REACTOR.RB().fi(Glyceryl.getLiquid(1000), CoalFuel.getLiquid(4000)).fo(NitroCoalFuel.getLiquid(5000)).add("nitro_coal_fuel",250,30);
        CHEMICAL_REACTOR.RB().fi(Glyceryl.getLiquid(1000), Diesel.getLiquid(4000)).fo(NitroDiesel.getLiquid(5000)).add("nitro_diesel",1000,30);
        CHEMICAL_REACTOR.RB().fi(Nitrogen.getGas(1000), Oxygen.getGas(2000)).fo(NitrogenDioxide.getGas(3000)).add("nitrogen_dioxide",1250, 30);
        CHEMICAL_REACTOR.RB().fi(Oxygen.getGas(2000)).ii(DUST.getMaterialIngredient(SodiumSulfide, 1)).fo(SodiumPersulfate.getLiquid(3000)).add("sodium_peroxide",2000, 30);
        CHEMICAL_REACTOR.RB().fi(NitroCarbon.getLiquid(1000), Water.getLiquid(1000)).fo(Glyceryl.getLiquid(2000)).add("glyceryl",583, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Sodium, 1), DUST.getMaterialIngredient(Sulfur, 1)).io(DUST.get(SodiumSulfide, 2)).add("sodium_sulfide",100, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Sulfur, 1)).fi(Water.getLiquid(2000)).fo(SulfuricAcid.getLiquid(3000)).add("sulfuric_acid",1150, 30);
        CHEMICAL_REACTOR.RB().fi(Hydrogen.getGas(2000), Oxygen.getGas(1000)).ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(1)).fo(Water.getLiquid(3000)).add("water",10, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Sodium, 2)).fi(NitrogenDioxide.getGas(5000)).io(new ItemStack(Items.GUNPOWDER, 5)).add("gunpowder",34, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Potassium, 1)).fi(NitricAcid.getLiquid(1000)).io(DUST.get(Saltpeter, 1)).add("saltpeter",20, 30);
        CHEMICAL_REACTOR.RB().fi(NitrogenDioxide.getGas(3000), Water.getLiquid(1000)).fo(NitricAcid.getLiquid(2000), NitricOxide.getGas(1000)).add("nitric_acid",20, 30);
        CHEMICAL_REACTOR.RB().ii(DUST.getMaterialIngredient(Silicon, 1)).fi(Oxygen.getGas(2000)).io(DUST.get(SiliconDioxide, 3)).add("silicon_dioxide",50,30);
        CHEMICAL_REACTOR.RB().fi(NitricOxide.getGas(1000), Oxygen.getGas(1000)).fo(NitrogenDioxide.getGas(1000)).add("nitrogen_dioxide_2",20, 30);
        CHEMICAL_REACTOR.RB().fi(Oxygen.getGas(500), Hydrogen.getGas(1000)).fo(DistilledWater.getLiquid(1500)).add("distilled_water",5, 30);
        CHEMICAL_REACTOR.RB().fi(FishOil.getLiquid(6000), Ethanol.getLiquid(1000)).ii(DUST_TINY.getMaterialIngredient(SodiumHydroxide, 1)).fo(BioDiesel.getLiquid(6000), Glycerol.getLiquid(1000)).add("biodiesel",600, 30);
        CHEMICAL_REACTOR.RB().fi(SeedOil.getLiquid(6000), Ethanol.getLiquid(1000)).ii(DUST_TINY.getMaterialIngredient(SodiumHydroxide, 1)).fo(BioDiesel.getLiquid(6000), Glycerol.getLiquid(1000)).add("biodiesel_1",600, 30);
        CHEMICAL_REACTOR.RB().fi(Water.getLiquid(3000)).ii(DUST.getMaterialIngredient(Sodium, 1)).fo(Hydrogen.getGas(1000)).io(DUST.get(SodiumHydroxide, 3)).add("sodium_hydroxide",600, 30);
    }
}
