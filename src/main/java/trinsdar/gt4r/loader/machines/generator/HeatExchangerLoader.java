package trinsdar.gt4r.loader.machines.generator;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.system.CallbackI;
import trinsdar.gt4r.data.Materials;

import static muramasa.antimatter.Data.NUGGET;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.HOT_FUELS;
import static trinsdar.gt4r.data.RecipeMaps.THERMAL_BOILER_FUELS;

public class HeatExchangerLoader {
    public static void init(){
        HOT_FUELS.RB().fi(Lava.getLiquid(1)).fo(PahoehoeLava.getLiquid(1)).add(5, 0, 80);
        HOT_FUELS.RB().fi(HotCoolant.getLiquid(1)).fo(ColdCoolant.getLiquid(1)).add(5, 0, 20);
        THERMAL_BOILER_FUELS.RB().fi(new FluidStack(Fluids.WATER, 5), new FluidStack(Fluids.LAVA, 500)).fo(Steam.getGas(800)).io(new ItemStack(Items.OBSIDIAN), NUGGET.get(Tin, 1), NUGGET.get(Copper, 1), NUGGET.get(Electrum, 1)).chances(90, 5, 4, 1).add(1);
    }
}
