package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gtlib.util.RegistryUtils;
import org.gtreimagined.gtlib.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.gtreimagined.gt4r.data.RecipeMaps;
import org.gtreimagined.tesseract.api.forge.TesseractCaps;

import static org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreItems.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.FLUID_CANNER;

public class FluidCanningLoader {
    public static void init() {
        ForgeRegistries.FLUIDS.getValues().forEach(fluid -> {
            Item bucket = fluid.getBucket();
            if (bucket == Items.AIR) return;
            //Only the source, so we don't get duplicates.
            if (!fluid.isSource(fluid.defaultFluidState())) return;
            ResourceLocation fluidId = RegistryUtils.getIdFromFluid(fluid);
            RecipeMaps.FLUID_CANNER.RB().ii(RecipeIngredient.of(bucket, 1)).fo(new FluidStack(fluid, 1000)).io(Items.BUCKET.getDefaultInstance()).add(fluidId.getNamespace() + "_" + fluidId.getPath() + "_bucket",20, 8);
            RecipeMaps.FLUID_CANNER.RB().ii(RecipeIngredient.of(Items.BUCKET, 1)).fi(new FluidStack(fluid, 1000)).io(new ItemStack(bucket, 1)).add("bucket_from_" + fluidId.getNamespace() + "_" + fluidId.getPath(),20, 8);

            /*GTAPI.all(ItemFluidCell.class, emptyCell -> {
                int size = emptyCell.getCapacity();
                ItemStack filled = emptyCell.fill(fluid, size);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(filled)).fo(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(emptyCell.getDefaultInstance()).add(emptyCell.getId() + "_from_" + AntimatterPlatformUtils.INSTANCE.getIdFromFluid(fluid).getPath(),20, 8);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(emptyCell, 1)).fi(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(filled).add(AntimatterPlatformUtils.INSTANCE.getIdFromFluid(fluid).getPath() + "_" + emptyCell.getId(),20, 8);
            });*/
        });
        /*FLUID_CANNER.RB().ii(of(BatteryHullSmall, 1)).fi(Mercury.getLiquid(1000)).io(getFullBattery(BatterySmallMercury)).add("bettery_small_mercury",16, 1);
        FLUID_CANNER.RB().ii(of(BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(getFullBattery(BatterySmallAcid)).add("battery_small_acid",16, 1);
        FLUID_CANNER.RB().ii(of(BatteryHullMedium, 1)).fi(Mercury.getLiquid(4000)).io(getFullBattery(BatteryMediumMercury)).add("battery_medium_mercury",64, 1);
        FLUID_CANNER.RB().ii(of(BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(getFullBattery(BatteryMediumAcid)).add("battery_medium_acid",64, 1);
        FLUID_CANNER.RB().ii(of(BatteryHullLarge, 1)).fi(Mercury.getLiquid(16000)).io(getFullBattery(BatteryLargeMercury)).add("battery_large_mercury",258, 1);
        FLUID_CANNER.RB().ii(of(BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(getFullBattery(BatteryLargeAcid)).add("battery_large_acid",258, 1);*/
        FLUID_CANNER.RB().ii(of(LighterEmpty, 1)).fi(Propane.getGas(100)).io(new ItemStack(Lighter)).add("lighter",1, 1);
        //FLUID_CANNING.RB().ii(of(Biochaff, 1)).fi(new FluidStack(Fluids.WATER, 1000)).fo(Biomass.getLiquid(1000)).add(400, 8);
    }

    private static ItemStack getFullBattery(ItemLike battery){
        ItemStack stack = new ItemStack(battery);
        stack.getCapability(TesseractCaps.ENERGY_HANDLER_CAPABILITY_ITEM).ifPresent(e -> {
            Utils.addEnergy(e, e.getCapacity());
            stack.setTag(e.getContainer().getTag());
        });
        return stack;
    }
}
