package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import tesseract.api.capability.TesseractGTCapability;

import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.of;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.FLUID_CANNING;

public class FluidCanningLoader {
    public static void init() {
        ForgeRegistries.FLUIDS.forEach(fluid -> {
            Item bucket = fluid.getFilledBucket();
            //Only the source.
            if (fluid instanceof FlowingFluid) return;
            if (bucket != Items.AIR){
                FLUID_CANNING.RB().ii(of(bucket,1)).fo(new FluidStack(fluid,1000)).io(new ItemStack(Items.BUCKET)).add(20, 8);
                FLUID_CANNING.RB().ii(of(Items.BUCKET,1)).fi(new FluidStack(fluid,1000)).io(new ItemStack(bucket)).add(20, 8);
            }
            ItemStack cell = CellTin.fill(fluid);
            ItemStack emptyCell = new ItemStack(CellTin);
            if (cell.equals(emptyCell, false)) return;
            FLUID_CANNING.RB().ii(of(cell)).fo(new FluidStack(fluid,1000)).io(emptyCell).add(20, 8);
            FLUID_CANNING.RB().ii(of(emptyCell)).fi(new FluidStack(fluid,1000)).io(cell).add(20, 8);


        });
        FLUID_CANNING.RB().ii(of(BatteryHullSmall, 1)).fi(Mercury.getLiquid(1000)).io(getFullBattery(BatterySmallMercury)).add(16, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(getFullBattery(BatterySmallAcid)).add(16, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullMedium, 1)).fi(Mercury.getLiquid(4000)).io(getFullBattery(BatteryMediumMercury)).add(64, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(getFullBattery(BatteryMediumAcid)).add(64, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullLarge, 1)).fi(Mercury.getLiquid(16000)).io(getFullBattery(BatteryLargeMercury)).add(258, 1);
        FLUID_CANNING.RB().ii(of(BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(getFullBattery(BatteryLargeAcid)).add(258, 1);
    }

    private static ItemStack getFullBattery(IItemProvider battery){
        ItemStack stack = new ItemStack(battery);
        stack.getCapability(TesseractGTCapability.ENERGY_HANDLER_CAPABILITY).ifPresent(e -> {
            e.insert(e.getCapacity(), false);
        });
        return stack;
    }
}
