package org.gtreimagined.gt4r.loader.machines;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.material.MaterialTags;
import org.gtreimagined.gtlib.ore.CobbleStoneType;
import org.gtreimagined.gtlib.ore.StoneType;
import org.gtreimagined.gtlib.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gtlib.util.TagUtils;
import org.gtreimagined.gtlib.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.gtreimagined.gt4r.GT4RRef;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.*;
import static org.gtreimagined.gtlib.material.MaterialTags.*;
import static org.gtreimagined.gt4r.data.Materials.Brick;
import static org.gtreimagined.gt4r.data.RecipeMaps.FORGE_HAMMER;

public class ForgeHammerLoader {
    public static void init(){
        GTMaterialTypes.CRUSHED.all().forEach(m -> {
            if (!m.has(GTMaterialTypes.ORE) && m != GTLibMaterials.Gold && m != GTLibMaterials.Iron && m != GTLibMaterials.Diamond && m != GTLibMaterials.Emerald && m != GTLibMaterials.Lapis && m != GTLibMaterials.Redstone) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(TagUtils.getForgelikeItemTag("sandless_ores/" + m.getId()),1), crushed = GTMaterialTypes.CRUSHED.getIngredient(m, 1);
            ItemStack crushedStack = GTMaterialTypes.CRUSHED.get(m,1);

            FORGE_HAMMER.RB().ii(ore).io(Utils.ca(ORE_MULTI.getInt(m) * multiplier, crushedStack)).add(m.getId() + "_ore",16, 10);
            FORGE_HAMMER.RB().ii(crushed).io(DUST_IMPURE.get(MACERATE_INTO.getMapping(m), 1)).add(m.getId() + "_crushed_ore",16, 10);
            FORGE_HAMMER.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.get(m,1))).io(DUST_PURE.get(MACERATE_INTO.getMapping(m), 1)).add(m.getId() + "_purified_ore",16, 10);
            if (m.has(CRUSHED_REFINED)) {
                FORGE_HAMMER.RB().ii(RecipeIngredient.of(CRUSHED_REFINED.get(m,1))).io(DUST.get(MACERATE_INTO.getMapping(m), 1)).add(m.getId() + "_centrifuged_ore",16, 10);
            }
            if (m.has(RAW_ORE)){
                FORGE_HAMMER.RB().ii(RecipeIngredient.of(RAW_ORE.getMaterialTag(m), 1)).io(Utils.ca((ORE_MULTI.getInt(m) * multiplier), crushedStack)).add(m.getId() + "_raw_ore",16, 10);
            }
        });
        GTMaterialTypes.PLATE.all().stream().filter(m -> m.has(GTMaterialTypes.INGOT) && !m.has(RUBBERTOOLS)).forEach(m -> {
            int in = 2;//GTLibConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 3 : 1;
            int out = 1;//GTLibConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 2 : 1;
            FORGE_HAMMER.RB().ii(INGOT.getMaterialIngredient(m, in)).io(PLATE.get(m, out)).add(m.getId() + "_plate", m.getMass(), 16);
        });
        ROD_LONG.all().stream().filter(m -> !m.has(MaterialTags.NOSMASH)).forEach(rod -> {
            FORGE_HAMMER.RB().ii(ROD.getMaterialIngredient(rod, 2)).io(ROD_LONG.get(rod, 1)).add("rod_long_" + rod.getId(), rod.getMass(), 16);
        });
        GTAPI.all(StoneType.class, GT4RRef.ID, s -> {
            if (!(s instanceof CobbleStoneType)) return;
            FORGE_HAMMER.RB().ii(RecipeIngredient.of(((CobbleStoneType)s).getBlock(""), 1)).io(new ItemStack(((CobbleStoneType)s).getBlock("cobble"))).add(s.getId() + "_to_cobble",10, 16);
        });
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Tags.Items.COBBLESTONE, 1)).io(new ItemStack(Items.GRAVEL)).add("gravel",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Items.STONE, 1)).io(new ItemStack(Items.COBBLESTONE)).add("cobblestone",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Tags.Items.GRAVEL, 1)).io(new ItemStack(Items.SAND)).add("sand",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 1)).add("brick_dust_small",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Items.BRICKS, 1)).io(DUST.get(Brick, 1)).add("brick_dust",40, 16);
    }
}
