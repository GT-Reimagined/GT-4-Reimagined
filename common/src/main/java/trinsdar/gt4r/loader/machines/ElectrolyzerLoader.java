package trinsdar.gt4r.loader.machines;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.data.GT4RMaterialTags;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static trinsdar.gt4r.data.GT4RMaterialTags.ELEC;
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
            if (!m.has(DUST) && !m.has(LIQUID) && !m.has(GAS)) return;
            int count = m.getProcessInto().stream().mapToInt(t -> t.s).sum();
            if (m.has(LIQUID) || m.has(GAS)) count *= 4;
            add(m, power, (int) m.getMass() * count);
        });

        ELECTROLYZING.RB().fi(Water.getLiquid(3000)).fo(Hydrogen.getGas(2000), Oxygen.getGas(1000)).add("water",2000, 30);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Items.BONE_MEAL, 3))).io(DUST.get(Calcium, 1)).add("bone_meal",24, 106);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Blocks.SAND, 8))).io(DUST.get(SiliconDioxide, 1)).add("sand",500, 25);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(new ItemStack(Blocks.RED_SAND, 8))).io(DUST.get(SiliconDioxide, 1), DUST_TINY.get(Iron, 1)).add("red_sand",500, 25);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(DUST.get(Diamond, 1))).io(DUST.get(Carbon, 64), DUST.get(Carbon, 64)).add("diamond_dust",1536,60);
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
        List<FluidHolder> fluidStacks = stacks.stream().filter(t -> (t.m.has(LIQUID) || t.m.has(GAS)) && !t.m.has(DUST)).map(t -> {
            return t.m.has(LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).toList();
        List<ItemStack> itemStacks = dust.getProcessInto().stream().filter(t -> t.m.has(DUST)).map(t -> new ItemStack(DUST.get(t.m), t.s)).toList();
        RecipeBuilder rb = ELECTROLYZING.RB();
        String suffix = dust.has(LIQUID) ? "_liquid" : dust.has(GAS) ? "_gas" :"_dust";
        if ((dust.has(LIQUID) || dust.has(GAS)) && !dust.has(DUST)){
            rb.fi(getFluid(dust,count * 1000));
        } else {
            rb.ii(RecipeIngredient.of(DUST.get(dust), count));
        }
        if (!itemStacks.isEmpty()) rb.io(itemStacks.toArray(new ItemStack[0]));
        if (!fluidStacks.isEmpty()) rb.fo(fluidStacks.toArray(new FluidHolder[0]));
        rb.add(dust.getId() + suffix, duration, euT);
    }

    private static FluidHolder getFluid(Material mat, int amount){
        if (mat.has(LIQUID)){
            return mat.getLiquid(amount);
        } else if (mat.has(GAS)){
            return mat.getGas(amount);
        } else if (mat.has(AntimatterMaterialTypes.PLASMA)){
            return mat.getPlasma(amount);
        } else {
            return mat.getLiquid(amount);
        }
    }
}
