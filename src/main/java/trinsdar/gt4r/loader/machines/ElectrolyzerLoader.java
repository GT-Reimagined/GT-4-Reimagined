package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GAS;
import static muramasa.antimatter.Data.LIQUID;
import static trinsdar.gt4r.data.Materials.Bauxite;
import static trinsdar.gt4r.data.Materials.Calcite;
import static trinsdar.gt4r.data.Materials.Cassiterite;
import static trinsdar.gt4r.data.Materials.Grossular;
import static trinsdar.gt4r.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        add(Bauxite, 64, 400);
        add(Calcite,  64, 200);
        add(Cassiterite,  24, 250);
        add(Grossular,  64, 300);
    }

    private static void add(Material dust, long euT, int duration) {
        add(dust, dust.getProcessInto().stream().mapToInt(t -> t.s).sum(), euT, duration);
    }

    private static void add(Material dust, int count, long euT, int duration) {
        List<MaterialStack> stacks = dust.getProcessInto();
        List<FluidStack> fluidStacks = stacks.stream().filter(t -> (t.m.has(LIQUID) || t.m.has(GAS)) && !t.m.has(DUST)).map(t -> {
            return t.m.has(LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).collect(Collectors.toList());
        if (fluidStacks.isEmpty()){
            ELECTROLYZING.RB().ii(AntimatterIngredient.of(DUST.get(dust), count)).io(
                    dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                            .toArray(ItemStack[]::new)).add(duration, euT);
        } else {
            ELECTROLYZING.RB().ii(AntimatterIngredient.of(DUST.get(dust), count)).io(
                    dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                            .toArray(ItemStack[]::new)).fo(fluidStacks.toArray(new FluidStack[0])).add(duration, euT);
        }
    }
}
