package trinsdar.gt4r.loader.machines.generator;

import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.data.AntimatterMaterialTypes.NUGGET;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.HOT_FUELS;
import static trinsdar.gt4r.data.RecipeMaps.THERMAL_BOILER_FUELS;

public class HeatExchangerLoader {
    public static void init(){
        HOT_FUELS.RB().fi(Lava.getLiquid(1)).fo(FluidPlatformUtils.createFluidStack(GT4RData.PAHOEHOE_LAVA.getFluid(), TesseractGraphWrappers.dropletMultiplier)).add("lava",5, 0, 80);
        HOT_FUELS.RB().fi(HotCoolant.getLiquid(1)).fo(ColdCoolant.getLiquid(1)).add("coolant",5, 0, 20);
        THERMAL_BOILER_FUELS.RB().fi(Water.getLiquid(5), Lava.getLiquid(83)).fo(Steam.getGas(800)).io(new ItemStack(Items.OBSIDIAN), NUGGET.get(Tin, 1), NUGGET.get(Copper, 1), NUGGET.get(Electrum, 1)).outputChances(0.9, 0.05, 0.04, 0.01).add("lava",1);
    }
}
