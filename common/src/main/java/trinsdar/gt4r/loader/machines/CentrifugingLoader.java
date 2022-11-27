package trinsdar.gt4r.loader.machines;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.gregtechintergalactical.gtrubber.GTRubberData.StickyResin;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.item.Items.DIRT;
import static net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
import static trinsdar.gt4r.data.GT4RData.Plantball;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.CENTRIFUGING;

public class CentrifugingLoader {
    public static void init() {
        AntimatterMaterialTypes.DUST_IMPURE.all().forEach(dust -> {
            Material oreByProduct1 = dust.getByProducts().size() > 0 ? dust.getByProducts().get(0) : dust;
            CENTRIFUGING.RB().ii(of(AntimatterMaterialTypes.DUST_IMPURE.get(dust),1)).io(new ItemStack(AntimatterMaterialTypes.DUST.get(dust), 1), AntimatterMaterialTypes.DUST_TINY.get(oreByProduct1, 1)).add(400, 2);
        });

        AntimatterMaterialTypes.DUST_PURE.all().forEach(dust -> {
            Material oreByProduct = dust.getByProducts().size() > 1 ? dust.getByProducts().get(1) : dust.getByProducts().size() > 0 ? dust.getByProducts().get(0) : dust;
            CENTRIFUGING.RB().ii(of(AntimatterMaterialTypes.DUST_PURE.get(dust),1)).io(new ItemStack(AntimatterMaterialTypes.DUST.get(dust), 1), AntimatterMaterialTypes.DUST_TINY.get(oreByProduct, 1)).add(400, 2);
        });
        ItemStack[] itemStacks = AntimatterMaterials.Lava.getProcessInto().stream().filter(t -> t.m.has(AntimatterMaterialTypes.DUST_TINY)).map(t -> new ItemStack(AntimatterMaterialTypes.DUST_TINY.get(t.m), t.s))
                .toArray(ItemStack[]::new);
        CENTRIFUGING.RB().fi(new FluidStack(Fluids.LAVA, 100)).io(itemStacks).chances(0.2, 0.1, 0.025, 0.025, 0.01).add(200, 16);
        CENTRIFUGING.RB().fi(PahoehoeLava.getLiquid(100)).io(itemStacks).chances(0.2, 0.1, 0.025, 0.025, 0.01).add(200, 8);
        add(AntimatterMaterials.EnderEye, 10, 792);
        CENTRIFUGING.RB().ii(of(MAGMA_CREAM, 1)).io(new ItemStack(SLIME_BALL), new ItemStack(BLAZE_POWDER)).add(156,16);
        CENTRIFUGING.RB().ii(of(DIRT, 64)).io(new ItemStack(Items.SAND, 32), new ItemStack(CLAY_BALL, 2), new ItemStack(Plantball, 4)).add(3125, 16);
        CENTRIFUGING.RB().ii(of(GRASS_BLOCK, 64)).io(new ItemStack(Items.SAND, 32), new ItemStack(CLAY_BALL, 2), new ItemStack(Plantball, 6)).add(3125, 16);
        CENTRIFUGING.RB().ii(of(MYCELIUM, 64)).io(new ItemStack(Items.SAND, 32), new ItemStack(CLAY_BALL, 8), new ItemStack(BROWN_MUSHROOM, 16), new ItemStack(RED_MUSHROOM, 16)).add(3906, 16);
        add(AntimatterMaterials.Lapis,20, 1792);
        CENTRIFUGING.RB().ii(of(StickyResin, 8)).io(AntimatterMaterialTypes.DUST.get(Rubber, 21), AntimatterMaterialTypes.DUST.get(Plastic, 7), new ItemStack(Plantball, 4)).add(1250,10);
        CENTRIFUGING.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Netherrack), 64)).io(new ItemStack(GOLD_NUGGET, 4), new ItemStack(REDSTONE, 4), new ItemStack(GUNPOWDER, 8), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Coal, 4), AntimatterMaterialTypes.DUST.get(Sulfur, 4), AntimatterMaterialTypes.DUST.get(Phosphor, 2)).add(3125,16);
        add(AntimatterMaterials.Granite, 16, 1500);
        add(AntimatterMaterials.Diorite, 16,2250, 16);
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
        CENTRIFUGING.RB().ii(of(Items.SOUL_SAND, 16)).io(new ItemStack(Items.SAND, 1), AntimatterMaterialTypes.DUST.get(Saltpeter,4), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Coal, 1)).fo(Oil.getLiquid(1000)).add(750, 16);
        CENTRIFUGING.RB().ii(of(Items.SOUL_SOIL, 16)).io(new ItemStack(DIRT, 1), AntimatterMaterialTypes.DUST.get(Saltpeter,4), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Coal, 1)).fo(Oil.getLiquid(1000)).add(750, 16);
        CENTRIFUGING.RB().ii(of(new ItemStack(GTRubberData.RUBBER_LOG, 16))).io(AntimatterMaterialTypes.DUST.get(Carbon, 8), new ItemStack(StickyResin, 8), new ItemStack(Plantball, 6)).fo(Methane.getGas(4000)).add(1562, 16);
        CENTRIFUGING.RB().fi(Hydrogen.getGas(4000)).fo(Deuterium.getGas(1000)).add(300, 20);
        CENTRIFUGING.RB().fi(Deuterium.getGas(4000)).fo(Tritium.getGas(1000)).add(300, 20);
        CENTRIFUGING.RB().fi(Helium.getGas(16000)).fo(Helium3.getGas(1000)).add(900, 20);
        add(AntimatterMaterials.Glowstone, 16,1562, 16);
        add(AntimatterMaterials.Redstone, 20, 1750);
        CENTRIFUGING.RB().ii(of(MAGMA_BLOCK, 64)).fo(new FluidStack(Fluids.LAVA, 64000)).add(187, 16);
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
        //addMethaneRecipe(POTATO, 16);
        //addMethaneRecipe(CARROT, 16);
        addMethaneRecipe(PUMPKIN, 16);
        addMethaneRecipe(COOKIE, 64);
        addMethaneRecipe(BREAD, 64);
        addMethaneRecipe(MELON, 64);
        addMethaneRecipe(SPIDER_EYE, 32);
        addMethaneRecipe(POISONOUS_POTATO, 12);
        addMethaneRecipe(BAKED_POTATO, 24);
        addMethaneRecipe(BROWN_MUSHROOM_BLOCK, 12);
        addMethaneRecipe(RED_MUSHROOM_BLOCK, 12);
        //addMethaneRecipe(BROWN_MUSHROOM, 32);
        //addMethaneRecipe(RED_MUSHROOM, 32);
        //addMethaneRecipe(NETHER_WART, 32);
        // For when either I add my own terra wart or when ic2 comes out for 1,15, probably the latter
        //addMethaneRecipe(TerraWart, 16);
        addMethaneRecipe(GOLDEN_APPLE, 1, new ItemStack(GOLD_INGOT, 6));
        addMethaneRecipe(ENCHANTED_GOLDEN_APPLE, 1, new ItemStack(GOLD_INGOT, 64));
        addMethaneRecipe(GOLDEN_CARROT, 1, new ItemStack(GOLD_NUGGET, 6));
        addMethaneRecipe(GLISTERING_MELON_SLICE, 8, new ItemStack(GOLD_NUGGET, 6));
        CENTRIFUGING.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(AntimatterMaterials.Endstone), 64)).io(new ItemStack(Items.SAND, 48), AntimatterMaterialTypes.DUST.get(Tungsten, 1)).fo(Helium.getGas(4000), Helium3.getGas(4000)).add(19500,16);
        add(RedGarnet, 16, 937);
        add(YellowGarnet, 16, 1093);
        CENTRIFUGING.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(DarkAsh), 2)).io(AntimatterMaterialTypes.DUST.get(Ash, 2)).add(78, 16);
        //add(RedRock, 16, 2400); // 8 calcite, 4 flint, 4 clay
        add(Marble, 16, 329);
        add(AntimatterMaterials.Basalt, 16, 1500);
        add(Cinnabar, 16, 1840);
        add(Tetrahedrite, 16, 3640);
        add(BlackGranite, 5, 800);
        add(Komatiite, 16, 340);
        add(Limestone, 16, 400);
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

    private static void add(Material dust, int count, long euT, int duration) {
        List<MaterialStack> stacks = dust.getProcessInto();
        List<FluidStack> fluidStacks = stacks.stream().filter(t -> (t.m.has(AntimatterMaterialTypes.LIQUID) || t.m.has(AntimatterMaterialTypes.GAS)) && !t.m.has(AntimatterMaterialTypes.DUST)).map(t -> {
            return t.m.has(AntimatterMaterialTypes.LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).collect(Collectors.toList());
        List<ItemStack> itemStacks = dust.getProcessInto().stream().filter(t -> t.m.has(AntimatterMaterialTypes.DUST)).map(t -> new ItemStack(AntimatterMaterialTypes.DUST.get(t.m), t.s))
                .collect(Collectors.toList());
        RecipeBuilder rb = CENTRIFUGING.RB();
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
