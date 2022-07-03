package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.ItemLike;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.Rubber;
import static trinsdar.gt4r.data.Materials.SiliconDioxide;
import static trinsdar.gt4r.data.Materials.Tin;
import static trinsdar.gt4r.data.Materials.WroughtIron;
import static trinsdar.gt4r.data.RecipeMaps.EXTRUDING;

public class ExtruderLoader {
    public static void init(){
        ROD.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapeRod)).io(ROD.get(m, 2)).add((m.getMass() * 2), euPerTick);
            if (m == Rubber){
                EXTRUDING.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapeRod)).io(ROD.get(m, 2)).add(m.getMass() * 2, euPerTick);
            }
        });
        PLATE.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapePlate)).io(PLATE.get(m, 1)).add(m.getMass(), euPerTick);
            if (m == Rubber){
                EXTRUDING.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapePlate)).io(PLATE.get(m, 1)).add(m.getMass(), euPerTick);
            }
        });
        AntimatterAPI.all(Wire.class).forEach(t -> {
            if (!t.getMaterial().has(INGOT)) return;
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GT4RData.ShapeWire)).io(stack).add(t.getMaterial().getMass()*2,96);
        });

        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
            if (!t.getMaterial().has(INGOT)) return;
            Item pipeTiny = t.getBlockItem(PipeSize.TINY);
            Item pipeSmall = t.getBlockItem(PipeSize.SMALL);
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GT4RData.ShapePipeTiny)).io(new ItemStack(pipeTiny, 2)).add(t.getMaterial().getMass()*2,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GT4RData.ShapePipeSmall)).io(new ItemStack(pipeSmall, 1)).add(t.getMaterial().getMass(),128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),3), getReusable(GT4RData.ShapePipeNormal)).io(new ItemStack(pipeNormal, 1)).add(t.getMaterial().getMass()*3,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),6), getReusable(GT4RData.ShapePipeLarge)).io(new ItemStack(pipeLarge, 1)).add(t.getMaterial().getMass()*6,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),12), getReusable(GT4RData.ShapePipeHuge)).io(new ItemStack(pipeHuge, 1)).add(t.getMaterial().getMass()*12,128);
        });

        AntimatterAPI.all(ItemPipe.class).forEach(t -> {
            if (!t.getMaterial().has(INGOT)) return;
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),3), getReusable(GT4RData.ShapePipeNormal)).io(new ItemStack(pipeNormal, 1)).add(t.getMaterial().getMass()*3,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),6), getReusable(GT4RData.ShapePipeLarge)).io(new ItemStack(pipeLarge, 1)).add(t.getMaterial().getMass()*6,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),12), getReusable(GT4RData.ShapePipeHuge)).io(new ItemStack(pipeHuge, 1)).add(t.getMaterial().getMass()*12,128);
        });

        BLOCK.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 9), getReusable(GT4RData.ShapeBlock)).io(BLOCK.get().get(m).asStack()).add(10, 128);
        });
        GEAR.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 4), getReusable(GT4RData.ShapeGear)).io(GEAR.get(m, 1)).add((m.getMass() * 5), 128);
        });
        RING.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapeRing)).io(RING.get(m, 4)).add((m.getMass() * 4), euPerTick);
            if (m == Rubber){
                EXTRUDING.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapeRing)).io(RING.get(m, 4)).add(m.getMass() * 4, euPerTick);
            }
        });
        BOLT.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapeBolt)).io(BOLT.get(m, 8)).add((m.getMass() * 8), euPerTick);
            if (m == Rubber){
                EXTRUDING.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GT4RData.ShapeBolt)).io(BOLT.get(m, 8)).add(m.getMass() * 8, euPerTick);
            }
        });

        EXTRUDING.RB().ii(INGOT.getMaterialIngredient(Tin, 2), getReusable(GT4RData.ShapeCell)).io(new ItemStack(GT4RData.CellTin)).add(128, 32);
        EXTRUDING.RB().ii(DUST.getMaterialIngredient(Rubber, 1), getReusable(GT4RData.ShapeIngot)).io(INGOT.get(Rubber, 1)).add(10, 16);
        EXTRUDING.RB().ii(INGOT.getMaterialIngredient(WroughtIron, 1), getReusable(GT4RData.ShapeIngot)).io(new ItemStack(Items.IRON_INGOT)).add(10, 64);
        EXTRUDING.RB().ii(DUST.getMaterialIngredient(SiliconDioxide, 1), getReusable(GT4RData.ShapeBottle)).io(new ItemStack(Items.GLASS_BOTTLE)).add(32, 16);
    }

    private static RecipeIngredient getReusable(TagKey<Item> tag){
        return RecipeIngredient.of(tag, 1).setNoConsume();
    }

    private static RecipeIngredient getReusable(ItemLike item){
        return RecipeIngredient.of(item, 1).setNoConsume();
    }
}
