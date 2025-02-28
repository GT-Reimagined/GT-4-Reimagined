package org.gtreimagined.gt4r.loader.machines.generator;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import tesseract.TesseractGraphWrappers;
import org.gtreimagined.gt4r.data.GT4RBlocks;

import static muramasa.antimatter.data.AntimatterMaterialTypes.NUGGET;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.HOT_FUELS;
import static org.gtreimagined.gt4r.data.RecipeMaps.THERMAL_BOILER_FUELS;

public class HeatExchangerLoader {
    public static void init(){
        HOT_FUELS.RB().fi(Lava.getLiquid(1)).fo(new FluidStack(GT4RBlocks.PAHOEHOE_LAVA.getFluid(), 1)).add("lava",5, 0, 80);
        HOT_FUELS.RB().fi(HotCoolant.getLiquid(1)).fo(ColdCoolant.getLiquid(1)).add("coolant",5, 0, 20);
        THERMAL_BOILER_FUELS.RB().fi(Water.getLiquid(5), Lava.getLiquid(83)).fo(Steam.getGas(800)).io(new ItemStack(Items.OBSIDIAN), NUGGET.get(Tin, 1), NUGGET.get(Copper, 1), NUGGET.get(Electrum, 1)).outputChances(0.9, 0.05, 0.04, 0.01).add("lava",1);
    }
}
