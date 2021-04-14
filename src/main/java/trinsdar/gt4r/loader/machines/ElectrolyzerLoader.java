package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GAS;
import static muramasa.antimatter.Data.LIQUID;
import static muramasa.antimatter.Data.PLASMA;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        ELECTROLYZING.RB().fi(Water.getLiquid(6000)).fo(Hydrogen.getGas(4000), Oxygen.getGas(2000)).add(100, 30);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Items.BONE_MEAL, 3))).io(DUST.get(Calcium, 1)).add(24, 106);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Blocks.SAND, 8))).io(DUST.get(SiliconDioxide, 1)).add(500, 25);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(DUST.get(Diamond, 1))).io(DUST.get(Carbon, 64), DUST.get(Carbon, 64)).add(1536,60);
        add(Emerald, 120, 522);
        add(Calcite,  90, 100);
        add(Cassiterite,  60, 132);
        add(Grossular,  120, 440);
        add(SodiumSulfide, 60, 208);
        add(EnderPearl, 90,220);
        add(Steel,50, 60, 2600);
        add(SulfuricAcid, 90, 392);
        add(RedGranite, 90, 120);
        add(Sapphire, 60, 100);
        add(Saltpeter, 90, 100);
        add(Clay, 120, 154);
        add(StainlessSteel, 120, 450);
        add(Chromite, 90, 210);
        add(DarkAsh, 1, 30, 24);
        add(SiliconDioxide, 60, 60);
        add(Sodalite, 120, 264);
        add(Bauxite, 120, 624);
        add(SodiumPersulfate, 90, 432);
        add(Methane, 60, 80);
        add(Pyrite, 60, 114);
        //add(Sugar, 90, 448);
        add(Glyceryl, 90, 800);
        add(Charcoal, 30, 12);
        add(Sphalerite, 60, 92);
        add(Obsidian, 120, 240);
        add(Pyrope, 120, 400);
        add(Uvarovite, 120, 480);
        add(Almandine, 120, 480);
        add(Coal, 30, 24);
        //add(Apatite, 90, 288);
        add(NitrogenDioxide, 60, 168);
        add(Andradite, 120, 480);
        add(Ruby, 90, 144);
        add(Olivine, 90, 140);
        //add(NitroCarbon, 60, 96);
        add(Lazurite, 120, 392);
        add(Phosphate, 60, 90);
        add(Galena, 90, 832);
        add(Tungstate, 90, 224);
        add(Spessartine, 120, 440);
        //add(CalciumCarbonate, 90, 400);
        add(Prismarine, 30, 800);
        add(Salt, 30,320);
        add(RockSalt, 30,72);
    }

    private static void add(Material dust, long euT, int duration) {
        add(dust, dust.getProcessInto().stream().mapToInt(t -> t.s).sum(), euT, duration);
    }

    private static void add(Material dust, int count, long euT, int duration) {
        List<MaterialStack> stacks = dust.getProcessInto();
        List<FluidStack> fluidStacks = stacks.stream().filter(t -> (t.m.has(LIQUID) || t.m.has(GAS)) && !t.m.has(DUST)).map(t -> {
            return t.m.has(LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).collect(Collectors.toList());
        if ((dust.has(LIQUID) || dust.has(GAS)) && !dust.has(DUST)){
            if (fluidStacks.isEmpty()){
                ELECTROLYZING.RB().fi(getFluid(dust,count * 1000)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).add(duration, euT);
            } else {
                ELECTROLYZING.RB().fi(getFluid(dust,count * 1000)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).fo(fluidStacks.toArray(new FluidStack[0])).add(duration, euT);
            }
        } else {
            if (fluidStacks.isEmpty()){
                ELECTROLYZING.RB().ii(RecipeIngredient.of(DUST.get(dust), count)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).add(duration, euT);
            } else {
                ELECTROLYZING.RB().ii(RecipeIngredient.of(DUST.get(dust), count)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).fo(fluidStacks.toArray(new FluidStack[0])).add(duration, euT);
            }
        }

    }

    private static FluidStack getFluid(Material mat, int amount){
        if (mat.has(LIQUID)){
            return mat.getLiquid(amount);
        } else if (mat.has(GAS)){
            return mat.getGas(amount);
        } else if (mat.has(PLASMA)){
            return mat.getPlasma(amount);
        } else {
            return mat.getLiquid(amount);
        }
    }
}
