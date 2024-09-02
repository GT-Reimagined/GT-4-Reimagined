package trinsdar.gt4r.loader.machines;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;
import trinsdar.gt4r.data.GT4RData;

import java.util.List;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.Plantball;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.StickyResin;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.item.Items.DIRT;
import static net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.CENTRIFUGE;

public class CentrifugingLoader {
    public static void init() {
        AntimatterMaterialTypes.DUST_IMPURE.all().forEach(dust -> {
            Material oreByProduct1 = dust.getByProducts().size() > 0 ? dust.getByProducts().get(0) : dust;
            CENTRIFUGE.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(oreByProduct1, 1)).add(dust.getId() + "_impure_dust",400, 2);
        });

        AntimatterMaterialTypes.DUST_PURE.all().forEach(dust -> {
            Material oreByProduct = dust.getByProducts().size() > 1 ? dust.getByProducts().get(1) : dust.getByProducts().size() > 0 ? dust.getByProducts().get(0) : dust;
            CENTRIFUGE.RB().ii(of(DUST_PURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(oreByProduct, 1)).add(dust.getId() + "_pure_dust",400, 2);
        });
        ItemStack[] itemStacks = AntimatterMaterials.Lava.getProcessInto().stream().filter(t -> t.m.has(AntimatterMaterialTypes.DUST_TINY)).map(t -> new ItemStack(AntimatterMaterialTypes.DUST_TINY.get(t.m), t.s))
                .toArray(ItemStack[]::new);
        CENTRIFUGE.RB().fi(Lava.getLiquid(100)).io(itemStacks).outputChances(0.2, 0.1, 0.025, 0.025, 0.01).add("lava",200, 16);
        CENTRIFUGE.RB().fi(FluidPlatformUtils.createFluidStack(GT4RData.PAHOEHOE_LAVA.getFluid(), 100 * TesseractGraphWrappers.dropletMultiplier)).io(itemStacks).outputChances(0.2, 0.1, 0.025, 0.025, 0.01).add("pahoehoe_lava", 200, 8);
        add(EnderEye, 10, 792);
        CENTRIFUGE.RB().ii(of(MAGMA_CREAM, 1)).io(new ItemStack(SLIME_BALL), new ItemStack(BLAZE_POWDER)).add("magma_cream",156,16);
        CENTRIFUGE.RB().ii(of(DIRT, 64)).io(new ItemStack(Items.SAND, 32), new ItemStack(CLAY_BALL, 2), new ItemStack(Plantball, 4)).add("dirt",3125, 16);
        CENTRIFUGE.RB().ii(of(GRASS_BLOCK, 64)).io(new ItemStack(Items.SAND, 32), new ItemStack(CLAY_BALL, 2), new ItemStack(Plantball, 6)).add("grass",3125, 16);
        CENTRIFUGE.RB().ii(of(MYCELIUM, 64)).io(new ItemStack(Items.SAND, 32), new ItemStack(CLAY_BALL, 8), new ItemStack(BROWN_MUSHROOM, 16), new ItemStack(RED_MUSHROOM, 16)).add("mycelium",3906, 16);
        add(Lapis,20, 1792);
        CENTRIFUGE.RB().ii(of(StickyResin, 8)).io(DUST.get(Rubber, 21), DUST.get(Plastic, 7), new ItemStack(Plantball, 4)).add("sticky_resin",1250,10);
        CENTRIFUGE.RB().ii(of(DUST.getMaterialTag(Netherrack), 64)).io(new ItemStack(GOLD_NUGGET, 4), new ItemStack(REDSTONE, 4), new ItemStack(GUNPOWDER, 8), DUST.get(Coal, 4), DUST.get(Sulfur, 4), DUST.get(Phosphor, 2)).add("netherrack",3125,16);
        add(Granite, 16, 1500);
        add(Diorite, 16,2250, 16);
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
        CENTRIFUGE.RB().ii(of(Items.SOUL_SAND, 16)).io(new ItemStack(Items.SAND, 1), DUST.get(Saltpeter,4), DUST.get(Coal, 1)).fo(Oil.getLiquid(1000)).add("soul_sand",750, 16);
        CENTRIFUGE.RB().ii(of(Items.SOUL_SOIL, 16)).io(new ItemStack(DIRT, 1), DUST.get(Saltpeter,4), DUST.get(Coal, 1)).fo(Oil.getLiquid(1000)).add("soul_soil",750, 16);
        CENTRIFUGE.RB().ii(of(GTCoreTags.RUBBER_LOGS, 16)).io(DUST.get(Carbon, 8), new ItemStack(StickyResin, 8), new ItemStack(Plantball, 6)).fo(Methane.getGas(4000)).add("rubber_log",1562, 16);
        CENTRIFUGE.RB().fi(Hydrogen.getGas(4000)).fo(Deuterium.getGas(1000)).add("hydrogen",300, 20);
        CENTRIFUGE.RB().fi(Deuterium.getGas(4000)).fo(Tritium.getGas(1000)).add("deuterium",300, 20);
        CENTRIFUGE.RB().fi(Helium.getGas(16000)).fo(Helium3.getGas(1000)).add("helium",900, 20);
        add(Glowstone, 16,1562, 16);
        add(Redstone, 20, 1750);
        CENTRIFUGE.RB().ii(of(MAGMA_BLOCK, 64)).fo(Lava.getLiquid(64000)).add("magma_block",187, 16);
        CENTRIFUGE.RB().ii(of(ROTTEN_FLESH, 16)).io(new ItemStack(LEATHER, 4), new ItemStack(SLIME_BALL)).fo(Methane.getGas(4000)).add("rotten_flesh",375, 16);
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
        addMethaneRecipe(GOLDEN_APPLE, 1, new ItemStack(GOLD_INGOT, 6));
        addMethaneRecipe(ENCHANTED_GOLDEN_APPLE, 1, new ItemStack(GOLD_INGOT, 64));
        addMethaneRecipe(GOLDEN_CARROT, 1, new ItemStack(GOLD_NUGGET, 6));
        addMethaneRecipe(GLISTERING_MELON_SLICE, 8, new ItemStack(GOLD_NUGGET, 6));
        CENTRIFUGE.RB().ii(of(DUST.getMaterialTag(Endstone), 64)).io(new ItemStack(Items.SAND, 48), DUST.get(Tungsten, 1)).fo(Helium.getGas(4000), Helium3.getGas(4000)).add("endstone_dust",19500,16);
        add(RedGarnet, 16, 937);
        add(YellowGarnet, 16, 1093);
        CENTRIFUGE.RB().ii(of(DUST.getMaterialTag(DarkAsh), 2)).io(DUST.get(Ash, 2)).add("dark_ash",78, 16);
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
        CENTRIFUGE.RB().ii(of(input, inputAmount)).fo(Methane.getGas(1000)).add(AntimatterPlatformUtils.getIdFromItem(input).getPath() + "_into_methane",2250, 16);
    }

    private static void addMethaneRecipe(Item input, int inputAmount, ItemStack extra){
        CENTRIFUGE.RB().ii(of(input, inputAmount)).io(extra).fo(Methane.getGas(1000)).add(AntimatterPlatformUtils.getIdFromItem(input).getPath() + "_into_methane",2250, 16);
    }

    private static void add(Material mat, long euT, int duration) {
        add(mat, mat.getProcessInto().stream().mapToInt(t -> t.s).sum(), euT, duration);
    }

    private static void add(Material dust, int count, long euT, int duration) {
        List<MaterialStack> stacks = dust.getProcessInto();
        List<FluidHolder> fluidStacks = stacks.stream().filter(t -> (t.m.has(AntimatterMaterialTypes.LIQUID) || t.m.has(AntimatterMaterialTypes.GAS)) && !t.m.has(AntimatterMaterialTypes.DUST)).map(t -> {
            return t.m.has(AntimatterMaterialTypes.LIQUID) ? t.m.getLiquid(t.s * 1000) : t.m.getGas(t.s * 1000);
        }).toList();
        List<ItemStack> itemStacks = dust.getProcessInto().stream().filter(t -> t.m.has(AntimatterMaterialTypes.DUST)).map(t -> new ItemStack(AntimatterMaterialTypes.DUST.get(t.m), t.s))
                .toList();
        RecipeBuilder rb = CENTRIFUGE.RB();
        if ((dust.has(AntimatterMaterialTypes.LIQUID) || dust.has(AntimatterMaterialTypes.GAS)) && !dust.has(AntimatterMaterialTypes.DUST)){
            rb.fi(getFluid(dust,count * 1000));
        } else {
            rb.ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(dust), count));
        }
        if (!itemStacks.isEmpty()) rb.io(itemStacks.toArray(new ItemStack[0]));
        if (!fluidStacks.isEmpty()) rb.fo(fluidStacks.toArray(new FluidHolder[0]));
        rb.add(dust.getId() + "_dust", duration, euT);
    }

    private static FluidHolder getFluid(Material mat, int amount){
        if (mat.has(AntimatterMaterialTypes.LIQUID)){
            return mat.getLiquid(amount);
        } else if (mat.has(AntimatterMaterialTypes.GAS)){
            return mat.getGas(amount);
        } else {
            return mat.getLiquid(amount);
        }
    }
}
