package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTags.BY_PRODUCT_MULTI;
import static muramasa.antimatter.material.MaterialTags.MACERATE_INTO;
import static muramasa.antimatter.material.MaterialTags.ORE_MULTI;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.util.Utils.getConventionalMaterialType;
import static muramasa.antimatter.util.Utils.getConventionalStoneType;
import static trinsdar.gt4r.data.GT4RData.Biochaff;
import static trinsdar.gt4r.data.GT4RData.Plantball;
import static trinsdar.gt4r.data.Materials.Brick;
import static trinsdar.gt4r.data.Materials.Clay;
import static trinsdar.gt4r.data.Materials.Limestone;
import static trinsdar.gt4r.data.Materials.Scoria;
import static trinsdar.gt4r.data.RecipeMaps.EXTRUDING;
import static trinsdar.gt4r.data.RecipeMaps.INDUSTRIAL_GRINDING;
import static trinsdar.gt4r.data.RecipeMaps.MACERATING;
import static trinsdar.gt4r.data.RecipeMaps.SIFTING;

public class MaceratorLoader {
    public static void initManual(){
        MACERATING.RB().ii(RecipeIngredient.of(Items.STONE, 1)).io(new ItemStack(Items.GRAVEL)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 1)).add(25, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY_BALL, 1)).io(DUST_SMALL.get(Clay, 2)).add(16, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Plantball, 1)).io(new ItemStack(Biochaff, 1)).add(300, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Biochaff, 1)).io(new ItemStack(Items.DIRT, 1)).add(300, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY, 1)).io(DUST.get(Clay, 2)).add(30, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.TERRACOTTA, 1)).io(DUST.get(Clay, 1)).add(16, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICKS, 1)).io(DUST.get(Brick, 1)).add(100, 2);
        MACERATING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(DUST.get(Wood, 6)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.PRISMARINE, 1)).io(DUST.get(Prismarine, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.DARK_PRISMARINE, 1)).io(DUST.get(DarkPrismarine, 1)).add(400, 2);
        if (AntimatterAPI.isModLoaded(Ref.MOD_CREATE)){
            MACERATING.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getBlockFromId(new ResourceLocation(Ref.MOD_CREATE, "limestone")), 1)).io(DUST.get(Limestone, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getBlockFromId(new ResourceLocation(Ref.MOD_CREATE, "weathered_limestone")), 1)).io(DUST.get(Limestone, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getBlockFromId(new ResourceLocation(Ref.MOD_CREATE, "scoria")), 1)).io(DUST.get(Scoria, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getBlockFromId(new ResourceLocation(Ref.MOD_CREATE, "dark_scoria")), 1)).io(DUST.get(Scoria, 1)).add(400, 2);
        }
        MACERATING.RB().ii(RecipeIngredient.of(Items.NETHER_QUARTZ_ORE, 1)).io(new ItemStack(Items.QUARTZ, 2)).add(200, 2);
        if (AntimatterAPI.isModLoaded("cinderscapes")){
            MACERATING.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz_ore")), 1)).io(new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "rose_quartz_ore")), 2)).add(200, 2);
            MACERATING.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz_ore")), 1)).io(new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "smoky_quartz_ore")), 2)).add(200, 2);
            MACERATING.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz_ore")), 1)).io(new ItemStack(AntimatterPlatformUtils.getItemFromID(new ResourceLocation("cinderscapes", "sulfur_quartz_ore")), 2)).add(200, 2);
        }
    }

    public static void initAuto() {
        AntimatterAPI.all(BlockOre.class, o -> {
            if (o.getOreType() != ORE) return;
            Material m = o.getMaterial();
            Material sm = o.getStoneType().getMaterial();
            if (!m.has(DUST) || !m.has(CRUSHED)) return;
            ItemStack stoneDust = sm.has(DUST) ? DUST.get(sm, 1) : ItemStack.EMPTY;
            TagKey<Item> oreTag = TagUtils.getForgelikeItemTag(String.join("", getConventionalStoneType(o.getStoneType()), "_", getConventionalMaterialType(o.getOreType()), "/", o.getMaterial().getId()));
            RecipeIngredient ore = RecipeIngredient.of(oreTag,1);
            ItemStack crushedStack = CRUSHED.get(m, ORE_MULTI.getInt(m));
            Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : MACERATE_INTO.getMapping(m);
            RecipeMap<?> rm = MACERATING;
            if (sm == Sand || sm == RedSand || sm == Gravel){
                rm = SIFTING;
            }
            List<ItemStack> stacks = new ArrayList<>();
            stacks.add(Utils.ca((ORE_MULTI.getInt(m)) * (rm == SIFTING ? 1 : 2), crushedStack));
            if (rm == SIFTING) stacks.add(crushedStack);
            stacks.add(DUST.get(oreByProduct1, 1));
            if (!stoneDust.isEmpty()) stacks.add(stoneDust);
            ItemStack[] stackArray = stacks.toArray(new ItemStack[0]);
            List<Double> ints = new ArrayList<>();
            ints.add(1.0);
            if (rm == SIFTING) ints.add(0.5);
            ints.add(0.1 * BY_PRODUCT_MULTI.getInt(m));
            if (!stoneDust.isEmpty()) ints.add(0.5);
            double[] chances = ints.stream().mapToDouble(i -> i).toArray();
            rm.RB().ii(ore).io(stackArray).chances(chances).add(400, 2);
        });
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE) && m != NetheriteScrap) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(ORE.getMaterialTag(m),1);
            RecipeIngredient crushed = RecipeIngredient.of(CRUSHED.getMaterialTag(m), 1);
            ItemStack crushedStack = CRUSHED.get(m,1);
            ItemStack stoneDust = DUST.get(Stone, 1);

            //TODO better way to do this
            Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : MACERATE_INTO.getMapping(m);
            Material oreByProduct2 = m.getByProducts().size() > 1 ? m.getByProducts().get(1) : oreByProduct1;
            Material oreByProduct3 = m.getByProducts().size() > 2 ? m.getByProducts().get(2) : oreByProduct2;

            if (m == NetheriteScrap){
                MACERATING.RB().ii(ore).io(Utils.ca((ORE_MULTI.getInt(m) * multiplier) * 2, crushedStack), DUST.get(oreByProduct1, 1), DUST.get(Netherrack, 1)).chances(1.0, 0.1 * multiplier * BY_PRODUCT_MULTI.getInt(m), 0.5).add(400, 2);
            }
            MACERATING.RB().ii(crushed).io(DUST_IMPURE.get(MACERATE_INTO.getMapping(m), 1), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.getMaterialTag(m), 1)).io(DUST_PURE.get(MACERATE_INTO.getMapping(m), 1), DUST.get(oreByProduct2, 1)).chances(1.0, 0.1).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(CRUSHED_CENTRIFUGED.getMaterialTag(m), 1)).io(DUST.get(MACERATE_INTO.getMapping(m), 1), DUST.get(oreByProduct3, 1)).chances(1.0, 0.1).add(400, 2);
            if (m.has(RAW_ORE)){
                MACERATING.RB().ii(RecipeIngredient.of(RAW_ORE.getMaterialTag(m), 1)).io(Utils.ca((ORE_MULTI.getInt(m) * multiplier) * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1 * multiplier * BY_PRODUCT_MULTI.getInt(m)).add(400, 2);
            }
        });
        DUST.all().forEach(m -> {
            if (m.has(PLATE) && m != Wood){
                long duration = m.getMass();
                MACERATING.RB().ii(PLATE.getMaterialIngredient(m, 1)).io(DUST.get(m, 1)).add(duration, 4);
            }
            if (m.has(INGOT)){
                long duration = m.getMass();
                MACERATING.RB().ii(INGOT.getMaterialIngredient(m, 1)).io(DUST.get(m, 1)).add(duration, 4);
            }
            if (m.has(GEM)){
                long duration = m.getMass();
                MACERATING.RB().ii(GEM.getMaterialIngredient(m, 1)).io(DUST.get(m, 1)).add(duration, 4);
            }
        });
        AntimatterAPI.all(StoneType.class, s -> {
            if (s.getMaterial() == NULL || !s.getMaterial().has(DUST)) return;
            MACERATING.RB().ii(RecipeIngredient.of(s.getState().getBlock().asItem(), 1)).io(DUST.get(s.getMaterial(), 1)).add(400, 2);
            if (s instanceof CobbleStoneType){
                MACERATING.RB().ii(RecipeIngredient.of(((CobbleStoneType)s).getBlock("cobble").asItem(), 1)).io(DUST.get(s.getMaterial(), 1)).add(400, 2);
            }
        });
        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
            if (!t.getMaterial().has(DUST) || t.getMaterial() == Wood) return;
            Item pipeTiny = t.getBlockItem(PipeSize.TINY);
            Item pipeSmall = t.getBlockItem(PipeSize.SMALL);
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            MACERATING.RB().ii(RecipeIngredient.of(pipeTiny, 1)).io(DUST_SMALL.get(t.getMaterial(), 2)).add(t.getMaterial().getMass()/2,128);
            MACERATING.RB().ii(RecipeIngredient.of(pipeSmall, 1)).io(DUST.get(t.getMaterial(), 1)).add(t.getMaterial().getMass(),128);
            MACERATING.RB().ii(RecipeIngredient.of(pipeNormal, 1)).io(DUST.get(t.getMaterial(), 3)).add(t.getMaterial().getMass()*3,128);
            MACERATING.RB().ii(RecipeIngredient.of(pipeLarge, 1)).io(DUST.get(t.getMaterial(), 6)).add(t.getMaterial().getMass()*6,128);
            MACERATING.RB().ii(RecipeIngredient.of(pipeHuge, 1)).io(DUST.get(t.getMaterial(), 12)).add(t.getMaterial().getMass()*12,128);
        });

        AntimatterAPI.all(ItemPipe.class).forEach(t -> {
            if (!t.getMaterial().has(INGOT)) return;
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            MACERATING.RB().ii(RecipeIngredient.of(pipeNormal, 1)).io(DUST.get(t.getMaterial(), 3)).add(t.getMaterial().getMass()*3,128);
            MACERATING.RB().ii(RecipeIngredient.of(pipeLarge, 1)).io(DUST.get(t.getMaterial(), 6)).add(t.getMaterial().getMass()*6,128);
            MACERATING.RB().ii(RecipeIngredient.of(pipeHuge, 1)).io(DUST.get(t.getMaterial(), 12)).add(t.getMaterial().getMass()*12,128);
        });

    }
}
