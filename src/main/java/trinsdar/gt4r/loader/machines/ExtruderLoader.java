package trinsdar.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.LazyValue;
import trinsdar.gt4r.data.GT4RData;

import static muramasa.antimatter.Data.*;
import static trinsdar.gt4r.data.Materials.Rubber;
import static trinsdar.gt4r.data.Materials.SiliconDioxide;
import static trinsdar.gt4r.data.Materials.Tin;
import static trinsdar.gt4r.data.Materials.WroughtIron;
import static trinsdar.gt4r.data.RecipeMaps.EXTRUDING;
import static trinsdar.gt4r.data.RecipeMaps.WIRE_MILLING;

public class ExtruderLoader {
    public static void init(){
        ROD.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m), AntimatterIngredient.of(GT4RData.ShapeRod, 1)).io(ROD.get(m, 2)).add((m.getMass() * 2), 96);
        });
        PLATE.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m), AntimatterIngredient.of(GT4RData.ShapePlate, 1)).io(PLATE.get(m, 1)).add(m.getMass(), 128);
        });
        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), AntimatterIngredient.of(GT4RData.ShapeWire, 1)).io(stack).add(t.getMaterial().getMass()*2,96);
        });

        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
            Item pipeTiny = t.getBlockItem(PipeSize.TINY);
            Item pipeSmall = t.getBlockItem(PipeSize.SMALL);
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), AntimatterIngredient.of(GT4RData.ShapePipeTiny, 1)).io(new ItemStack(pipeTiny, 2)).add(t.getMaterial().getMass()*2,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), AntimatterIngredient.of(GT4RData.ShapePipeSmall, 1)).io(new ItemStack(pipeSmall, 1)).add(t.getMaterial().getMass(),128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),3), AntimatterIngredient.of(GT4RData.ShapePipeNormal, 1)).io(new ItemStack(pipeNormal, 1)).add(t.getMaterial().getMass()*3,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),6), AntimatterIngredient.of(GT4RData.ShapePipeLarge, 1)).io(new ItemStack(pipeLarge, 1)).add(t.getMaterial().getMass()*6,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),12), AntimatterIngredient.of(GT4RData.ShapePipeHuge, 1)).io(new ItemStack(pipeHuge, 1)).add(t.getMaterial().getMass()*12,128);
        });

        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),3), AntimatterIngredient.of(GT4RData.ShapePipeNormal, 1)).io(new ItemStack(pipeNormal, 1)).add(t.getMaterial().getMass()*3,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),6), AntimatterIngredient.of(GT4RData.ShapePipeLarge, 1)).io(new ItemStack(pipeLarge, 1)).add(t.getMaterial().getMass()*6,128);
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),12), AntimatterIngredient.of(GT4RData.ShapePipeHuge, 1)).io(new ItemStack(pipeHuge, 1)).add(t.getMaterial().getMass()*12,128);
        });

        BLOCK.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 9), AntimatterIngredient.of(GT4RData.ShapeBlock, 1)).io(BLOCK.get().get(m).asStack()).add(10, 128);
        });
        GEAR.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDING.RB().ii(INGOT.getMaterialIngredient(m, 4), AntimatterIngredient.of(GT4RData.ShapeGear, 1)).io(GEAR.get(m, 1)).add((m.getMass() * 5), 128);
        });
        EXTRUDING.RB().ii(INGOT.getMaterialIngredient(Tin), AntimatterIngredient.of(GT4RData.ShapeCell, 1)).io(new ItemStack(GT4RData.CellTin)).add(128, 32);
        EXTRUDING.RB().ii(DUST.getMaterialIngredient(Rubber), AntimatterIngredient.of(GT4RData.ShapeIngot, 1)).io(INGOT.get(Rubber, 1)).add(10, 16);
        EXTRUDING.RB().ii(INGOT.getMaterialIngredient(WroughtIron), AntimatterIngredient.of(GT4RData.ShapeIngot, 1)).io(new ItemStack(Items.IRON_INGOT)).add(10, 64);
        EXTRUDING.RB().ii(DUST.getMaterialIngredient(SiliconDioxide), AntimatterIngredient.of(GT4RData.ShapeBottle, 1)).io(new ItemStack(Items.GLASS_BOTTLE)).add(32, 16);
    }
}
