package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        ELECTROLYZING.RB().fi(Water.getLiquid(6000)).fo(Hydrogen.getLiquid(4000), Oxygen.getLiquid(2000)).add(100, 30);
        ELECTROLYZING.RB().ii(AntimatterIngredient.of(new ItemStack(Items.BONE_MEAL, 3))).io(DUST.get(Calcium, 1)).add(20, 106);
        ELECTROLYZING.RB().ii(AntimatterIngredient.of(new ItemStack(Blocks.SAND, 8))).io(DUST.get(SiliconDioxide, 1)).add(500, 25);

        add(Calcite,  64, 200);
        add(Cassiterite,  24, 250);
        add(Grossular,  64, 300);
        add(SodiumSulfide, 60, 200);
        add(EnderPearl, 90,220);
        add(Steel, 60, 2600);
        add(SulfuricAcid, 90, 380);
        add(RedGranite, 90, 120);
        add(Sapphire, 60, 100);
        add(Saltpeter, 90, 100);
        add(Clay, 120, 140);
        //add(RockSalt, 60, 60);
        add(StainlessSteel, 120, 440);
        add(Chromite, 90, 200);
        add(DarkAsh, 30, 20);
        add(Tungstate, 90, 220);
        add(SiliconDioxide, 60, 60);
        add(Sodalite, 120, 260);
        add(Diamond, 60, 1520);
        add(Bauxite, 64, 400);
        add(SodiumPersulfate, 90, 420);
        add(Methane, 60, 80);
        add(Pyrite, 60, 100);
        //add(Sugar, 90, 440);
        add(Glyceryl, 90, 800);
        //add(Charcoal, 30, 12);
        add(Sphalerite, 60, 80);
        



    }

    private static void add(Material dust, long euT, int duration) {
        add(dust, dust.getProcessInto().stream().mapToInt(t -> t.s).sum(), euT, duration);
    }

    private static void add(Material dust, int count, long euT, int duration) {
        List<MaterialStack> stacks = dust.getProcessInto();
        List<FluidStack> fluidStacks = stacks.stream().filter(t -> (t.m.has(LIQUID) || t.m.has(GAS)) && !t.m.has(DUST)).map(t -> {
            return t.m.has(LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).collect(Collectors.toList());
        if (dust.has(LIQUID) && !dust.has(DUST)){
            if (fluidStacks.isEmpty()){
                ELECTROLYZING.RB().fi(dust.getLiquid(count * 1000)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).add(duration, euT);
            } else {
                ELECTROLYZING.RB().fi(dust.getLiquid(count * 1000)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).fo(fluidStacks.toArray(new FluidStack[0])).add(duration, euT);
            }
        } else {
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
}
