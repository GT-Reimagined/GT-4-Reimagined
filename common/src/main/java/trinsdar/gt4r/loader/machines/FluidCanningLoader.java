package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.fluid.AntimatterFluidUtils;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fluids.FluidStack;
import tesseract.FluidPlatformUtils;
import tesseract.api.TesseractCaps;

import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.Mercury;
import static trinsdar.gt4r.data.Materials.Propane;
import static trinsdar.gt4r.data.Materials.SulfuricAcid;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_CANNING;

public class FluidCanningLoader {
    public static void init() {
        AntimatterPlatformUtils.getAllFluids().forEach(fluid -> {
            Item bucket = fluid.getBucket();
            //Only the source. might not work as well on fabric
            if (!AntimatterFluidUtils.isSource(fluid)) return;
            String fluidid = FluidPlatformUtils.getFluidId(fluid).getPath();
            if (bucket != Items.AIR){
                FLUID_CANNING.RB().ii(of(bucket,1)).fo(new FluidStack(fluid,1000)).io(new ItemStack(Items.BUCKET)).add("empty_bucket_and_" + fluidid,20, 8);
                FLUID_CANNING.RB().ii(of(Items.BUCKET,1)).fi(new FluidStack(fluid,1000)).io(new ItemStack(bucket)).add("full_bucket_of_" + fluidid, 20, 8);
            }
            ItemStack cell = CellTin.fill(fluid);
            ItemStack emptyCell = new ItemStack(CellTin);
            if (cell.equals(emptyCell)) return;
            FLUID_CANNING.RB().ii(of(cell)).fo(new FluidStack(fluid,1000)).io(emptyCell).add("empty_cell_and_" + fluidid,20, 8);
            FLUID_CANNING.RB().ii(of(emptyCell)).fi(new FluidStack(fluid,1000)).io(cell).add("full_cell_of_" + fluidid, 20, 8);


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
