package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractCapUtils;
import tesseract.TesseractGraphWrappers;
import trinsdar.gt4r.data.RecipeMaps;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.GT4RData.Lighter;
import static trinsdar.gt4r.data.GT4RData.LighterEmpty;
import static trinsdar.gt4r.data.Materials.Mercury;
import static trinsdar.gt4r.data.Materials.Propane;
import static trinsdar.gt4r.data.Materials.SulfuricAcid;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_CANNING;

public class FluidCanningLoader {
    public static void init() {
        AntimatterPlatformUtils.getAllFluids().forEach(fluid -> {
            Item bucket = fluid.getBucket();
            if (bucket == Items.AIR) return;
            //Only the source, so we don't get duplicates.
            if (!fluid.isSource(fluid.defaultFluidState())) return;
            ResourceLocation fluidId = AntimatterPlatformUtils.getIdFromFluid(fluid);
            RecipeMaps.FLUID_CANNING.RB().ii(RecipeIngredient.of(bucket, 1)).fo(FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier)).io(Items.BUCKET.getDefaultInstance()).add(fluidId.getNamespace() + "_" + fluidId.getPath() + "_bucket",20, 8);
            RecipeMaps.FLUID_CANNING.RB().ii(RecipeIngredient.of(Items.BUCKET, 1)).fi(FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier)).io(new ItemStack(bucket, 1)).add("bucket_from_" + fluidId.getNamespace() + "_" + fluidId.getPath(),20, 8);

            /*AntimatterAPI.all(ItemFluidCell.class, emptyCell -> {
                int size = emptyCell.getCapacity();
                ItemStack filled = emptyCell.fill(fluid, size);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(filled)).fo(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(emptyCell.getDefaultInstance()).add(emptyCell.getId() + "_from_" + AntimatterPlatformUtils.getIdFromFluid(fluid).getPath(),20, 8);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(emptyCell, 1)).fi(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(filled).add(AntimatterPlatformUtils.getIdFromFluid(fluid).getPath() + "_" + emptyCell.getId(),20, 8);
            });*/
        });
        FLUID_CANNING.RB().ii(of(BatteryHullSmall, 1)).fi(Mercury.getLiquid(1000)).io(getFullBattery(BatterySmallMercury)).add("bettery_small_mercury",16, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(getFullBattery(BatterySmallAcid)).add("battery_small_acid",16, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullMedium, 1)).fi(Mercury.getLiquid(4000)).io(getFullBattery(BatteryMediumMercury)).add("battery_medium_mercury",64, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(getFullBattery(BatteryMediumAcid)).add("battery_medium_acid",64, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullLarge, 1)).fi(Mercury.getLiquid(16000)).io(getFullBattery(BatteryLargeMercury)).add("battery_large_mercury",258, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(getFullBattery(BatteryLargeAcid)).add("battery_large_acid",258, 1);
        FLUID_CANNING.RB().ii(of(LighterEmpty, 1)).fi(Propane.getGas(100)).io(new ItemStack(Lighter)).add("lighter",1, 1);
        //FLUID_CANNING.RB().ii(of(Biochaff, 1)).fi(new FluidStack(Fluids.WATER, 1000)).fo(Biomass.getLiquid(1000)).add(400, 8);
    }

    private static ItemStack getFullBattery(ItemLike battery){
        ItemStack stack = new ItemStack(battery);
        TesseractCapUtils.getEnergyHandlerItem(stack).ifPresent(e -> {
            Utils.addEnergy(e, e.getCapacity());
            stack.setTag(e.getContainer().getTag());
        });
        return stack;
    }
}
