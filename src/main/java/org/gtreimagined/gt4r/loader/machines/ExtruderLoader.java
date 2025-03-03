package org.gtreimagined.gt4r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.data.GT4RItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gt4r.data.RecipeMaps.EXTRUDER;

public class ExtruderLoader {
    public static void init(){
        AntimatterMaterialTypes.ROD.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDER.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapeRod)).io(ROD.get(m, 2)).add(m.getId() + "_rod", (m.getMass() * 2), euPerTick);
            if (m.has(RUBBERTOOLS) && m.has(DUST)){
                EXTRUDER.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapeRod)).io(ROD.get(m, 2)).add(m.getId() + "_rod_1", m.getMass() * 2, euPerTick);
            }
        });
        AntimatterMaterialTypes.PLATE.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDER.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapePlate)).io(PLATE.get(m, 1)).add(m.getId() + "_plate", m.getMass(), euPerTick);
            if (m.has(RUBBERTOOLS) && m.has(DUST)){
                EXTRUDER.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapePlate)).io(PLATE.get(m, 1)).add(m.getId() + "_plate_1", m.getMass(), euPerTick);
            }
        });
        AntimatterAPI.all(Wire.class).forEach(t -> {
            if (!t.getMaterial().has(AntimatterMaterialTypes.INGOT)) return;
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GTCoreItems.ShapeWire)).io(stack).add(t.getId() + "_wire", t.getMaterial().getMass()*2,96);
        });

        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
            if (!t.getMaterial().has(AntimatterMaterialTypes.INGOT)) return;
            Item pipeTiny = t.getBlockItem(PipeSize.TINY);
            Item pipeSmall = t.getBlockItem(PipeSize.SMALL);
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            if (t.getSizes().contains(PipeSize.TINY)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GTCoreItems.ShapePipeTiny)).io(new ItemStack(pipeTiny, 2)).add(t.getMaterial().getId() + "_fluid_pipe_tiny",t.getMaterial().getMass()*2,128);
            }
            if (t.getSizes().contains(PipeSize.SMALL)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GTCoreItems.ShapePipeSmall)).io(new ItemStack(pipeSmall, 1)).add(t.getMaterial().getId() + "_fluid_pipe_small",t.getMaterial().getMass(),128);
            }
            if (t.getSizes().contains(PipeSize.NORMAL)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),3), getReusable(GTCoreItems.ShapePipeNormal)).io(new ItemStack(pipeNormal, 1)).add(t.getMaterial().getId() + "_fluid_pipe_normal",t.getMaterial().getMass()*3,128);
            }
            if (t.getSizes().contains(PipeSize.LARGE)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),6), getReusable(GTCoreItems.ShapePipeLarge)).io(new ItemStack(pipeLarge, 1)).add(t.getMaterial().getId() + "_fluid_pipe_large",t.getMaterial().getMass()*6,128);
            }
            if (t.getSizes().contains(PipeSize.HUGE)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),12), getReusable(GTCoreItems.ShapePipeHuge)).io(new ItemStack(pipeHuge, 1)).add(t.getMaterial().getId() + "_fluid_pipe_huge",t.getMaterial().getMass()*12,128);
            }
        });

        AntimatterAPI.all(ItemPipe.class).forEach(t -> {
            if (!t.getMaterial().has(INGOT)) return;
            Item pipeTiny = t.getBlockItem(PipeSize.TINY);
            Item pipeSmall = t.getBlockItem(PipeSize.SMALL);
            Item pipeNormal = t.getBlockItem(PipeSize.NORMAL);
            Item pipeLarge = t.getBlockItem(PipeSize.LARGE);
            Item pipeHuge = t.getBlockItem(PipeSize.HUGE);
            if (t.getSizes().contains(PipeSize.TINY)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GTCoreItems.ShapePipeTiny)).io(new ItemStack(pipeTiny, 2)).add(t.getMaterial().getId() + "_item_pipe_tiny",t.getMaterial().getMass()*2,128);
            }
            if (t.getSizes().contains(PipeSize.SMALL)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1), getReusable(GTCoreItems.ShapePipeSmall)).io(new ItemStack(pipeSmall, 1)).add(t.getMaterial().getId() + "_item_pipe_small",t.getMaterial().getMass(),128);
            }
            if (t.getSizes().contains(PipeSize.NORMAL)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),3), getReusable(GTCoreItems.ShapePipeNormal)).io(new ItemStack(pipeNormal, 1)).add(t.getMaterial().getId() + "_item_pipe_normal",t.getMaterial().getMass()*3,128);
            }
            if (t.getSizes().contains(PipeSize.LARGE)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),6), getReusable(GTCoreItems.ShapePipeLarge)).io(new ItemStack(pipeLarge, 1)).add(t.getMaterial().getId() + "_item_pipe_large",t.getMaterial().getMass()*6,128);
            }
            if (t.getSizes().contains(PipeSize.HUGE)) {
                EXTRUDER.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),12), getReusable(GTCoreItems.ShapePipeHuge)).io(new ItemStack(pipeHuge, 1)).add(t.getMaterial().getId() + "_item_pipe_huge",t.getMaterial().getMass()*12,128);
            }
        });

        BLOCK.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDER.RB().ii(INGOT.getMaterialIngredient(m, 9), getReusable(GTCoreItems.ShapeBlock)).io(BLOCK.get().get(m).asStack()).add(m.getId() + "_block",10, 128);
        });
        GEAR.all().forEach(m -> {
            if (!m.has(INGOT)) return;
            EXTRUDER.RB().ii(INGOT.getMaterialIngredient(m, 4), getReusable(GTCoreItems.ShapeGear)).io(GEAR.get(m, 1)).add(m.getId() + "_gear", (m.getMass() * 5), 128);
        });
        AntimatterMaterialTypes.RING.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDER.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapeRing)).io(RING.get(m, 4)).add(m.getId() + "_ring", (m.getMass() * 4), euPerTick);
            if (m.has(RUBBERTOOLS) && m.has(DUST)){
                EXTRUDER.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapeRing)).io(RING.get(m, 4)).add(m.getId() + "_ring_1", m.getMass() * 4, euPerTick);
            }
        });
        AntimatterMaterialTypes.BOLT.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.INGOT)) return;
            int euPerTick = m == Rubber ? 32 : 128;
            EXTRUDER.RB().ii(INGOT.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapeBolt)).io(BOLT.get(m, 8)).add(m.getId() + "_bolt", (m.getMass() * 8), euPerTick);
            if (m.has(RUBBERTOOLS) && m.has(DUST)){
                EXTRUDER.RB().ii(DUST.getMaterialIngredient(m, 1), getReusable(GTCoreItems.ShapeBolt)).io(BOLT.get(m, 8)).add(m.getId() + "_bolt_1", m.getMass() * 8, euPerTick);
            }
        });

        EXTRUDER.RB().ii(INGOT.getMaterialIngredient(Tin, 2), getReusable(GTCoreItems.ShapeCell)).io(new ItemStack(GT4RItems.CellTin)).add("tin_cell",128, 32);
        EXTRUDER.RB().ii(DUST.getMaterialIngredient(Rubber, 1), getReusable(GTCoreItems.ShapeIngot)).io(INGOT.get(Rubber, 1)).add("rubber_ingot",10, 16);
        EXTRUDER.RB().ii(INGOT.getMaterialIngredient(WroughtIron, 1), getReusable(GTCoreItems.ShapeIngot)).io(new ItemStack(Items.IRON_INGOT)).add("iron_ingot",10, 64);
        //EXTRUDING.RB().ii(DUST.getMaterialIngredient(SiliconDioxide, 1), getReusable(GTCoreItems.ShapeBottle)).io(new ItemStack(Items.GLASS_BOTTLE)).add("glass_bottle",32, 16);
    }

    private static RecipeIngredient getReusable(TagKey<Item> tag){
        return RecipeIngredient.of(tag, 1).setNoConsume();
    }

    private static RecipeIngredient getReusable(ItemLike item){
        return RecipeIngredient.of(item, 1).setNoConsume();
    }
}
