package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import org.lwjgl.system.CallbackI;
import trinsdar.gt4r.data.GT4RData;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static net.minecraft.item.Items.*;
import static trinsdar.gt4r.data.GT4RData.Plantball;
import static trinsdar.gt4r.data.GT4RData.RUBBER_LOG;
import static trinsdar.gt4r.data.GT4RData.StickyResin;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.CENTRIFUGING;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.*;

public class CentrifugingLoader {
    public static void init() {
        DUST_IMPURE.all().forEach(dust -> {
            if (dust.hasByProducts()) CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(0), 1)).add(400, 2);
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.getMaterialTag(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(100, 10).add(400, 2);
        });

        DUST_PURE.all().forEach(dust -> {
            if (dust.hasByProducts()) CENTRIFUGING.RB().ii(of(DUST_PURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get((dust.getByProducts().size() > 1 ? dust.getByProducts().get(1) : dust.getByProducts().get(0)), 1)).add(400, 2);
            else CENTRIFUGING.RB().ii(of(DUST_PURE.getMaterialTag(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(100, 10).add(400, 2);
        });
        add(Lava, 16, 16, 4687);
        add(EnderEye, 10, 792);
        CENTRIFUGING.RB().ii(of(MAGMA_CREAM, 1)).io(new ItemStack(SLIME_BALL), new ItemStack(BLAZE_POWDER)).add(156,16);
        CENTRIFUGING.RB().ii(of(DIRT, 64)).io(new ItemStack(SAND, 32), new ItemStack(CLAY_BALL, 2), new ItemStack(Plantball, 4)).add(3125, 16);
        CENTRIFUGING.RB().ii(of(GRASS_BLOCK, 64)).io(new ItemStack(SAND, 32), new ItemStack(CLAY_BALL, 2), new ItemStack(Plantball, 6)).add(3125, 16);
        CENTRIFUGING.RB().ii(of(MYCELIUM, 64)).io(new ItemStack(SAND, 32), new ItemStack(CLAY_BALL, 8), new ItemStack(BROWN_MUSHROOM, 16), new ItemStack(RED_MUSHROOM, 16)).add(3906, 16);
        add(Lapis,20, 1792);
        CENTRIFUGING.RB().ii(of(StickyResin, 8)).io(DUST.get(Rubber, 28), new ItemStack(Plantball, 4)).add(1250,10);
        CENTRIFUGING.RB().ii(of(DUST.getMaterialTag(Netherrack), 64)).io(new ItemStack(GOLD_NUGGET, 4), new ItemStack(REDSTONE, 4), new ItemStack(GUNPOWDER, 8), DUST.get(Coal, 4), DUST.get(Sulfur, 4), DUST.get(Phosphor, 2)).add(3125,16);
        add(Granite, 16, 1500);
        CENTRIFUGING.RB().ii(of(DUST.getMaterialTag(Diorite), 16)).io(DUST.get(Nickel, 1)).add(2250, 16);
        add(Electrum, 16, 312);
        add(Invar, 16, 312);
        add(Bronze, 16, 312);
        add(Brass, 16, 468);
        add(Cupronickel, 16, 468);
        add(BatteryAlloy, 16, 468);
        add(Magnalium, 16, 600);
        add(Kanthal, 16, 1040);
        add(TungstenSteel, 16, 1900);
        add(Nichrome, 16, 2240);
        CENTRIFUGING.RB().ii(of(SOUL_SAND, 16)).io(new ItemStack(SAND, 1), DUST.get(Saltpeter,4), DUST.get(Coal, 1)).fo(Oil.getLiquid(1000)).add(750, 16);
        CENTRIFUGING.RB().ii(of(new ItemStack(RUBBER_LOG, 16))).io(DUST.get(Carbon, 8), new ItemStack(StickyResin, 8), new ItemStack(Plantball, 6)).fo(Methane.getGas(4000)).add(1562, 16);
        CENTRIFUGING.RB().fi(Hydrogen.getGas(4000)).fo(Deuterium.getGas(1000)).add(300, 20);
        CENTRIFUGING.RB().fi(Deuterium.getGas(4000)).fo(Tritium.getGas(1000)).add(300, 20);
        CENTRIFUGING.RB().fi(Helium.getGas(16000)).fo(Helium3.getGas(1000)).add(900, 20);
        CENTRIFUGING.RB().ii(of(DUST.getMaterialTag(Glowstone), 16)).io(new ItemStack(REDSTONE, 8), DUST.get(Gold, 8)).fo(Helium.getGas(1000)).add(1562, 16);
        add(Redstone, 20, 1750);
        CENTRIFUGING.RB().ii(of(MAGMA_BLOCK, 64)).fo(new FluidStack(Fluids.LAVA.getFluid(), 64000)).add(187, 16);
        CENTRIFUGING.RB().ii(of(ROTTEN_FLESH, 16)).io(new ItemStack(LEATHER, 4), new ItemStack(SLIME_BALL)).fo(Methane.getGas(4000)).add(375, 16);
        addMethaneRecipe(PORKCHOP, 12);
        addMethaneRecipe(BEEF, 12);
        addMethaneRecipe(CHICKEN, 12);
        addMethaneRecipe(MUTTON, 12);
        addMethaneRecipe(RABBIT, 12);
        addMethaneRecipe(COOKED_PORKCHOP, 12);
        addMethaneRecipe(COOKED_BEEF, 12);
        addMethaneRecipe(COOKED_CHICKEN, 12);
        addMethaneRecipe(COOKED_MUTTON, 12);
        addMethaneRecipe(COOKED_RABBIT, 12);
        addMethaneRecipe(COD, 12);
        addMethaneRecipe(SALMON, 12);
        addMethaneRecipe(COOKED_COD, 12);
        addMethaneRecipe(COOKED_SALMON, 12);
        addMethaneRecipe(APPLE, 32);
        addMethaneRecipe(POTATO, 16);
        addMethaneRecipe(CARROT, 16);
        addMethaneRecipe(PUMPKIN, 16);
        addMethaneRecipe(COOKIE, 64);
        addMethaneRecipe(BREAD, 64);
        addMethaneRecipe(MELON, 64);
        addMethaneRecipe(SPIDER_EYE, 32);
        addMethaneRecipe(POISONOUS_POTATO, 12);
        addMethaneRecipe(BAKED_POTATO, 24);
        addMethaneRecipe(BROWN_MUSHROOM_BLOCK, 12);
        addMethaneRecipe(RED_MUSHROOM_BLOCK, 12);
        addMethaneRecipe(BROWN_MUSHROOM, 32);
        addMethaneRecipe(RED_MUSHROOM, 32);
        addMethaneRecipe(NETHER_WART, 32);
        // For when either I add my own terra wart or when ic2 comes out for 1,15, probably the latter
        //addMethaneRecipe(TerraWart, 16);
        addMethaneRecipe(Plantball, 6);
        addMethaneRecipe(GOLDEN_APPLE, 1, new ItemStack(GOLD_INGOT, 6));
        addMethaneRecipe(ENCHANTED_GOLDEN_APPLE, 1, new ItemStack(GOLD_INGOT, 64));
        addMethaneRecipe(GOLDEN_CARROT, 1, new ItemStack(GOLD_NUGGET, 6));
        addMethaneRecipe(GLISTERING_MELON_SLICE, 8, new ItemStack(GOLD_NUGGET, 6));
        CENTRIFUGING.RB().ii(of(DUST.getMaterialTag(Endstone), 64)).io(new ItemStack(SAND, 48), DUST.get(Tungsten, 1)).fo(Helium.getGas(4000), Helium3.getGas(4000)).add(19500,16);
        add(RedGarnet, 16, 937);
        add(YellowGarnet, 16, 1093);
        CENTRIFUGING.RB().ii(of(DUST.getMaterialTag(DarkAsh), 2)).io(DUST.get(Ash, 2)).add(78, 16);
        //add(RedRock, 16, 2400); // 8 calcite, 4 flint, 4 clay
        add(Marble, 16, 329);
        add(Basalt, 16, 1500);
        add(Cinnabar, 16, 1840);
        add(Tetrahedrite, 16, 3640);
    }

    private static void addMethaneRecipe(Item input, int inputAmount){
        CENTRIFUGING.RB().ii(of(input, inputAmount)).fo(Methane.getGas(1000)).add(2250, 16);
    }

    private static void addMethaneRecipe(Item input, int inputAmount, ItemStack extra){
        CENTRIFUGING.RB().ii(of(input, inputAmount)).io(extra).fo(Methane.getGas(1000)).add(2250, 16);
    }

    private static void add(Material mat, long euT, int duration) {
        add(mat, mat.getProcessInto().stream().mapToInt(t -> t.s).sum(), euT, duration);
    }

    private static void add(Material mat, int count, long euT, int duration) {
        List<MaterialStack> stacks = mat.getProcessInto();
        List<FluidStack> fluidStacks = stacks.stream().filter(t -> (t.m.has(LIQUID) || t.m.has(GAS)) && !t.m.has(DUST)).map(t -> {
            return t.m.has(LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).collect(Collectors.toList());
        if ((mat.has(LIQUID) || mat.has(GAS)) && !mat.has(DUST)){
            if (fluidStacks.isEmpty()){
                CENTRIFUGING.RB().fi(getFluid(mat,count * 1000)).io(
                        mat.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).add(duration, euT);
            } else {
                CENTRIFUGING.RB().fi(getFluid(mat,count * 1000)).io(
                        mat.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).fo(fluidStacks.toArray(new FluidStack[0])).add(duration, euT);
            }
        } else {
            if (fluidStacks.isEmpty()){
                CENTRIFUGING.RB().ii(AntimatterIngredient.of(DUST.getMaterialTag(mat), count)).io(
                        mat.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).add(duration, euT);
            } else {
                CENTRIFUGING.RB().ii(AntimatterIngredient.of(DUST.getMaterialTag(mat), count)).io(
                        mat.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
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
