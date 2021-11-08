package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.gt4r.Ref;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.Utils.getConventionalMaterialType;
import static muramasa.antimatter.util.Utils.getConventionalStoneType;
import static trinsdar.gt4r.data.GT4RData.Biochaff;
import static trinsdar.gt4r.data.GT4RData.Plantball;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.MACERATING;
import static trinsdar.gt4r.data.RecipeMaps.SIFTING;

public class MaceratorAutoLoader {
    public static void init() {
        AntimatterAPI.all(BlockOre.class, o -> {
            if (o.getOreType() != ORE) return;
            Material m = o.getMaterial();
            Material sm = o.getStoneType().getMaterial();
            if (!m.has(DUST) || !m.has(CRUSHED)) return;
            ItemStack stoneDust = sm.has(DUST) ? DUST.get(sm, 1) : ItemStack.EMPTY;
            ITag.INamedTag<Item> oreTag = TagUtils.getForgeItemTag(String.join("", getConventionalStoneType(o.getStoneType()), "_", getConventionalMaterialType(o.getOreType()), "/", o.getMaterial().getId()));
            RecipeIngredient ore = RecipeIngredient.of(oreTag,1);
            ItemStack crushedStack = CRUSHED.get(m,m.getOreMulti());
            Material oreByProduct1 = m.getByProducts().size() > 0 ? m.getByProducts().get(0) : m.getMacerateInto();
            RecipeMap rm = MACERATING;
            if (sm == Sand || sm == RedSand || sm == Gravel){
                rm = SIFTING;
            }
            List<ItemStack> stacks = new ArrayList<>();
            stacks.add(Utils.ca((m.getOreMulti()) * (rm == SIFTING ? 1 : 2), crushedStack));
            if (rm == SIFTING) stacks.add(crushedStack);
            stacks.add(DUST.get(oreByProduct1, 1));
            if (!stoneDust.isEmpty()) stacks.add(stoneDust);
            ItemStack[] stackArray = stacks.toArray(new ItemStack[0]);
            List<Integer> ints = new ArrayList<>();
            ints.add(100);
            if (rm == SIFTING) ints.add(50);
            ints.add(10 * m.getByProductMulti());
            if (!stoneDust.isEmpty()) ints.add(50);
            int[] chances = ints.stream().mapToInt(i -> i).toArray();
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
            if (m.has(RAW_ORE)){
                MACERATING.RB().ii(RecipeIngredient.of(RAW_ORE.getMaterialTag(m), 1)).io(Utils.ca((m.getOreMulti() * multiplier) * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(100, 10 * multiplier * m.getByProductMulti()).add(400, 2);
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
        });

    }
}
