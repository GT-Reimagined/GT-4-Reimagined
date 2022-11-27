package trinsdar.gt4r.loader.machines.generator;

import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Lava;
import static muramasa.antimatter.Data.NUGGET;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.HOT_FUELS;
import static trinsdar.gt4r.data.RecipeMaps.THERMAL_BOILER_FUELS;

public class HeatExchangerLoader {
    public static void init(){
        HOT_FUELS.RB().fi(Lava.getLiquid(1)).fo(PahoehoeLava.getLiquid(1)).add(5, 0, 80);
        HOT_FUELS.RB().fi(HotCoolant.getLiquid(1)).fo(ColdCoolant.getLiquid(1)).add(5, 0, 20);
        THERMAL_BOILER_FUELS.RB().fi(new FluidStack(Fluids.WATER, 5), new FluidStack(Fluids.LAVA, 83)).fo(Steam.getGas(800)).io(new ItemStack(Items.OBSIDIAN), NUGGET.get(Tin, 1), NUGGET.get(Copper, 1), NUGGET.get(Electrum, 1)).chances(0.9, 0.05, 0.04, 0.01).add(1);
    }
}
