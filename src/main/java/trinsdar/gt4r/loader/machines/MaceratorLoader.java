package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.datagen.builder.AntimatterCookingRecipeBuilder;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.Materials;
import trinsdar.gt4r.data.RecipeMaps;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.Utils.formatNumber;
import static muramasa.antimatter.util.Utils.getConventionalMaterialType;
import static muramasa.antimatter.util.Utils.getConventionalStoneType;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.MACERATING;
import static trinsdar.gt4r.data.RecipeMaps.SIFTING;

public class MaceratorLoader {
    public static void init() {
        AntimatterAPI.all(BlockOre.class, Ref.ID,o -> {
            if (o.getOreType() != ORE) return;
            Material m = o.getMaterial();
            Material sm = o.getStoneType().getMaterial();
            if (!m.has(DUST) || !m.has(CRUSHED)) return;
            ItemStack stoneDust = sm.has(DUST) ? DUST.get(sm, 1) : ItemStack.EMPTY;
            if (sm == BasaltVanilla) stoneDust = DUST.get(Basalt, 1);
            ITag.INamedTag<Item> oreTag = TagUtils.getForgeItemTag(String.join("", getConventionalStoneType(o.getStoneType()), "_", getConventionalMaterialType(o.getOreType()), "/", o.getMaterial().getId()));
            RecipeIngredient ore = RecipeIngredient.of(oreTag,1);
            ItemStack crushedStack = CRUSHED.get(m,1);
            Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : m.getMacerateInto();
            RecipeMap rm = MACERATING;
            if (sm == Sand || sm == RedSand || sm == Gravel || o.getStoneType() == SOUL_SAND || o.getStoneType() == SOUL_SOIL){
                rm = SIFTING;
            }
            if (stoneDust.isEmpty()){
                rm.RB().ii(ore).io(Utils.ca((m.getOreMulti()) * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(100, 10 * m.getByProductMulti()).add(400, 2);
            } else {
                rm.RB().ii(ore).io(Utils.ca((m.getOreMulti()) * 2, crushedStack), DUST.get(oreByProduct1, 1), stoneDust).chances(100, 10 * m.getByProductMulti(), 50).add(400, 2);
            }

        });
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE) && m != NetheriteScrap) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(ORE.getMaterialTag(m),1);
            RecipeIngredient crushed = RecipeIngredient.of(CRUSHED.getMaterialTag(m), 1);
            ItemStack crushedStack = CRUSHED.get(m,1);
            ItemStack stoneDust = DUST.get(Stone, 1);

            //TODO better way to do this
            Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : m.getMacerateInto();
            Material oreByProduct2 = m.getByProducts().size() > 1 ? m.getByProducts().get(1) : oreByProduct1;
            Material oreByProduct3 = m.getByProducts().size() > 2 ? m.getByProducts().get(2) : oreByProduct2;

            if (m == NetheriteScrap){
                MACERATING.RB().ii(ore).io(Utils.ca((m.getOreMulti() * multiplier) * 2, crushedStack), DUST.get(oreByProduct1, 1), DUST.get(Netherrack, 1)).chances(100, 10 * multiplier * m.getByProductMulti(), 50).add(400, 2);
            }
            MACERATING.RB().ii(crushed).io(DUST_IMPURE.get(m.getMacerateInto(), 1), DUST.get(oreByProduct1, 1)).chances(100, 10).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.getMaterialTag(m), 1)).io(DUST_PURE.get(m.getMacerateInto(), 1), DUST.get(oreByProduct2, 1)).chances(100, 10).add(400, 2);
            if (m.has(CRUSHED_CENTRIFUGED)) {
                MACERATING.RB().ii(RecipeIngredient.of(CRUSHED_CENTRIFUGED.getMaterialTag(m), 1)).io(DUST.get(m.getMacerateInto(), 1), DUST.get(oreByProduct3, 1)).chances(100, 10).add(400, 2);
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
        MACERATING.RB().ii(RecipeIngredient.of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.SAND)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.STONE, 1)).io(new ItemStack(Items.GRAVEL)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY_BALL, 1)).io(DUST_SMALL.get(Clay, 2)).add(16, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.CLAY, 1)).io(DUST.get(Clay, 2)).add(30, 4);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BRICKS, 1)).io(DUST.get(Brick, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(DUST.get(Wood, 6)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BASALT, 1)).io(DUST.get(Basalt, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.BLACKSTONE, 1)).io(DUST.get(Blackstone, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.GRANITE, 1)).io(DUST.get(Granite, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.DIORITE, 1)).io(DUST.get(Diorite, 1)).add(400, 2);
        MACERATING.RB().ii(RecipeIngredient.of(Items.ANDESITE, 1)).io(DUST.get(Andesite, 1)).add(400, 2);
        AntimatterAPI.all(StoneType.class, Ref.ID, s -> {
            if (s.getMaterial() == NULL || !s.getMaterial().has(DUST)) return;
            MACERATING.RB().ii(RecipeIngredient.of(s.getState().getBlock().asItem(), 1)).io(DUST.get(s.getMaterial(), 1)).add(400, 2);
        });
        if (AntimatterAPI.isModLoaded(Ref.MOD_CREATE)){
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "limestone")), 1)).io(DUST.get(Limestone, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "weathered_limestone")), 1)).io(DUST.get(Limestone, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "scoria")), 1)).io(DUST.get(Scoria, 1)).add(400, 2);
            MACERATING.RB().ii(RecipeIngredient.of(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Ref.MOD_CREATE, "dark_scoria")), 1)).io(DUST.get(Scoria, 1)).add(400, 2);
        }
    }
}
