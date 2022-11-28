package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RMaterialTags;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.material.MaterialTags.ELEC;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    static boolean breakMethod = true;
    public static void init() {
        ELEC.all().forEach(m -> {
            int power = 0;
            if (m.has(GT4RMaterialTags.ELEC30)) power = 30;
            if (m.has(GT4RMaterialTags.ELEC60)) power = 60;
            if (m.has(GT4RMaterialTags.ELEC90)) power = 90;
            if (m.has(GT4RMaterialTags.ELEC120)) power = 120;
            if (power == 0) return;
            if (!m.has(AntimatterMaterialTypes.DUST) && !m.has(AntimatterMaterialTypes.LIQUID) && !m.has(AntimatterMaterialTypes.GAS)) return;
            int count = m.getProcessInto().stream().mapToInt(t -> t.s).sum();
            if (m.has(AntimatterMaterialTypes.LIQUID) || m.has(AntimatterMaterialTypes.GAS)) count *= 4;
            add(m, power, (int) m.getMass() * count);
        });

        ELECTROLYZING.RB().fi(AntimatterMaterials.Water.getLiquid(3000)).fo(Hydrogen.getGas(2000), Oxygen.getGas(1000)).add(2000, 30);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Items.BONE_MEAL, 3))).io(AntimatterMaterialTypes.DUST.get(Calcium, 1)).add(24, 106);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Blocks.SAND, 8))).io(AntimatterMaterialTypes.DUST.get(SiliconDioxide, 1)).add(500, 25);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Blocks.RED_SAND, 8))).io(AntimatterMaterialTypes.DUST.get(SiliconDioxide, 1), AntimatterMaterialTypes.DUST_TINY.get(AntimatterMaterials.Iron, 1)).add(500, 25);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Diamond, 1))).io(AntimatterMaterialTypes.DUST.get(Carbon, 64), AntimatterMaterialTypes.DUST.get(Carbon, 64)).add(1536,60);
        add(Steel,50, 60, 2600);
        add(DarkAsh, 1, 30, 24);
        add(AntimatterMaterials.Coal, 1, 30, 24);
        if (breakMethod) return;

        add(SodiumPersulfate, 90, 432); //close
        add(Methane, 60, 80); // close: 1 sec off
        //add(Sugar, 90, 448);
        add(Glyceryl, 90, 800); // close

        //add(Apatite, 90, 288);

        //add(NitroCarbon, 60, 96);
        add(Lazurite, 120, 392); // fixed
        add(Galena, 90, 832); // slightly over
        //add(CalciumCarbonate, 90, 400);
        add(Salt, 30,320); // far under
    }

    private static void add(Material dust, long euT, int duration) {
        add(dust, dust.getProcessInto().stream().mapToInt(t -> t.s).sum(), euT, duration);
    }

    private static void add(Material dust, int count, long euT, int duration) {
        List<MaterialStack> stacks = dust.getProcessInto();
        List<FluidStack> fluidStacks = stacks.stream().filter(t -> (t.m.has(AntimatterMaterialTypes.LIQUID) || t.m.has(AntimatterMaterialTypes.GAS)) && !t.m.has(AntimatterMaterialTypes.DUST)).map(t -> {
            return t.m.has(AntimatterMaterialTypes.LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).collect(Collectors.toList());
        List<ItemStack> itemStacks = dust.getProcessInto().stream().filter(t -> t.m.has(AntimatterMaterialTypes.DUST)).map(t -> new ItemStack(AntimatterMaterialTypes.DUST.get(t.m), t.s))
                .collect(Collectors.toList());
        RecipeBuilder rb = ELECTROLYZING.RB();
        if ((dust.has(AntimatterMaterialTypes.LIQUID) || dust.has(AntimatterMaterialTypes.GAS)) && !dust.has(AntimatterMaterialTypes.DUST)){
            rb.fi(getFluid(dust,count * 1000));
        } else {
            rb.ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(dust), count));
        }
        if (!itemStacks.isEmpty()) rb.io(itemStacks.toArray(new ItemStack[0]));
        if (!fluidStacks.isEmpty()) rb.fo(fluidStacks.toArray(new FluidStack[0]));
        rb.add(duration, euT);
    }

    private static FluidStack getFluid(Material mat, int amount){
        if (mat.has(AntimatterMaterialTypes.LIQUID)){
            return mat.getLiquid(amount);
        } else if (mat.has(AntimatterMaterialTypes.GAS)){
            return mat.getGas(amount);
        } else if (mat.has(AntimatterMaterialTypes.PLASMA)){
            return mat.getPlasma(amount);
        } else {
            return mat.getLiquid(amount);
        }
    }
}
