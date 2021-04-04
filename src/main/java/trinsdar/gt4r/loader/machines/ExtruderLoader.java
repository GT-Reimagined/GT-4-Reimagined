package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
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
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m), getReusable(GT4RData.ShapeRod)).io(ROD.get(m, 2)).add((m.getMass() * 2), euPerTick);
            if (m == Rubber){
                EXTRUDING.RB().ii(DUST.getMaterialIngredient(m), getReusable(GT4RData.ShapeRod)).io(ROD.get(m, 2)).add(m.getMass() * 2, euPerTick);
            }
        });
        PLATE.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m), getReusable(GT4RData.ShapePlate)).io(PLATE.get(m, 1)).add(m.getMass(), euPerTick);
            if (m == Rubber){
                EXTRUDING.RB().ii(DUST.getMaterialIngredient(m), getReusable(GT4RData.ShapePlate)).io(PLATE.get(m, 1)).add(m.getMass(), euPerTick);
            }
        });
        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GT4RData.ShapeWire)).io(stack).add(t.getMaterial().getMass()*2,96);
        });

        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
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

        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
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
        EXTRUDING.RB().ii(INGOT.getMaterialIngredient(Tin), getReusable(GT4RData.ShapeCell)).io(new ItemStack(GT4RData.CellTin)).add(128, 32);
        EXTRUDING.RB().ii(DUST.getMaterialIngredient(Rubber), getReusable(GT4RData.ShapeIngot)).io(INGOT.get(Rubber, 1)).add(10, 16);
        EXTRUDING.RB().ii(INGOT.getMaterialIngredient(WroughtIron), getReusable(GT4RData.ShapeIngot)).io(new ItemStack(Items.IRON_INGOT)).add(10, 64);
        EXTRUDING.RB().ii(DUST.getMaterialIngredient(SiliconDioxide), getReusable(GT4RData.ShapeBottle)).io(new ItemStack(Items.GLASS_BOTTLE)).add(32, 16);
    }

    private static RecipeIngredient getReusable(ITag.INamedTag<Item> tag){
        return RecipeIngredient.of(tag, 1).setNoConsume();
    }

    private static RecipeIngredient getReusable(IItemProvider item){
        return RecipeIngredient.of(item, 1).setNoConsume();
    }
}
