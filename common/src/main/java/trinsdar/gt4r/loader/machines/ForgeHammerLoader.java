package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.Ref;
import muramasa.antimatter.data.ForgeCTags;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTags.MACERATE_INTO;
import static muramasa.antimatter.material.MaterialTags.ORE_MULTI;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.HAMMERING;

public class ForgeHammerLoader {
    public static void init(){
        CRUSHED.all().forEach(m -> {
            if (!m.has(ORE) && m != Gold && m != Iron && m != Diamond && m != Emerald && m != Lapis && m != Redstone) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(TagUtils.getForgelikeItemTag("sandless_ores/" + m.getId()),1), crushed = CRUSHED.getIngredient(m, 1);
            ItemStack crushedStack = CRUSHED.get(m,1);

            HAMMERING.RB().ii(ore).io(Utils.ca(ORE_MULTI.getInt(m) * multiplier, crushedStack)).add(16, 10);
            HAMMERING.RB().ii(crushed).io(DUST_IMPURE.get(MACERATE_INTO.getMapping(m), 1)).add(16, 10);
            HAMMERING.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.get(m,1))).io(DUST_PURE.get(MACERATE_INTO.getMapping(m), 1)).add(16, 10);
            if (m.has(CRUSHED_CENTRIFUGED)) {
                HAMMERING.RB().ii(RecipeIngredient.of(CRUSHED_CENTRIFUGED.get(m,1))).io(DUST.get(MACERATE_INTO.getMapping(m), 1)).add(16, 10);
            }
            if (m.has(RAW_ORE)){
                HAMMERING.RB().ii(RecipeIngredient.of(RAW_ORE.getMaterialTag(m), 1)).io(Utils.ca((ORE_MULTI.getInt(m) * multiplier), crushedStack)).add(16, 10);
            }
        });
        PLATE.all().stream().filter(m -> m.has(INGOT) && !m.has(RUBBERTOOLS)).forEach(m -> {
            int in = AntimatterConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 3 : 1;
            int out = AntimatterConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 2 : 1;
            HAMMERING.RB().ii(INGOT.getMaterialIngredient(m, in)).io(PLATE.get(m, out)).add(m.getMass(), 16);
        });
        AntimatterAPI.all(StoneType.class, Ref.ID, s -> {
            if (!(s instanceof CobbleStoneType)) return;
            HAMMERING.RB().ii(RecipeIngredient.of(((CobbleStoneType)s).getBlock(""), 1)).io(new ItemStack(((CobbleStoneType)s).getBlock("cobble"))).add(10, 16);
        });
        HAMMERING.RB().ii(RecipeIngredient.of(ForgeCTags.COBBLESTONE, 1)).io(new ItemStack(Items.GRAVEL)).add(10, 16);
        HAMMERING.RB().ii(RecipeIngredient.of(Items.STONE, 1)).io(new ItemStack(Items.COBBLESTONE)).add(10, 16);
        HAMMERING.RB().ii(RecipeIngredient.of(ForgeCTags.GRAVEL, 1)).io(new ItemStack(Items.SAND)).add(10, 16);
        HAMMERING.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 1)).add(10, 16);
        HAMMERING.RB().ii(RecipeIngredient.of(Items.BRICKS, 1)).io(DUST.get(Brick, 1)).add(40, 16);
    }
}
