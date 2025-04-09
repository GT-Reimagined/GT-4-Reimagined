package org.gtreimagined.gt4r.loader.crafting;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTMaterialTypes;
import org.gtreimagined.gtlib.data.GTTools;
import org.gtreimagined.gtlib.datagen.providers.GTRecipeProvider;
import org.gtreimagined.gtlib.pipe.PipeSize;
import org.gtreimagined.gtlib.pipe.types.Cable;
import org.gtreimagined.gtlib.pipe.types.Wire;
import org.gtreimagined.gtlib.util.RegistryUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gtcore.block.RedstoneWire;
import org.gtreimagined.gt4r.GT4RRef;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.gtreimagined.gtlib.data.GTMaterialTypes.PLATE;
import static org.gtreimagined.gtlib.pipe.PipeSize.*;
import static org.gtreimagined.gt4r.data.Materials.Rubber;

public class ToolCraftingTableRecipes {
    @SuppressWarnings("unchecked")
    public static void loadRecipes(Consumer<FinishedRecipe> output, GTRecipeProvider provider) {
        int wireAmount = 2;//GTLibConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 1 : 2;
        GTAPI.all(Wire.class, wire -> {
            Cable<?> cable = GTAPI.get(Cable.class, "cable" + "_" + wire.getMaterial().getId());
            ImmutableSet<PipeSize> sizes = wire.getSizes();
            Map<PipeSize, Item> wires = sizes.stream().map(s -> new Pair<>(s, wire.getBlockItem(s))).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
            PipeSize[] val = VALUES;
            for (int i = 1; i < val.length; i ++) {
                int offset = val[i] == HUGE ? 1 : 0;
                if (val[i] == LARGE){
                    provider.shapeless(output,"three_to_one_" + RegistryUtils.getIdFromItem(wires.get(LARGE)).getPath(),"wire",
                            new ItemStack(wires.get(val[i]),1),wires.get(SMALL),wires.get(SMALL),wires.get(SMALL));
                    provider.shapeless(output,"one_to_three_" + RegistryUtils.getIdFromItem(wires.get(NORMAL)).getPath(),"wire",
                            new ItemStack(wires.get(SMALL),3),wires.get(val[i]));
                    continue;
                }
                twoToOne(wires, val[i-1 - offset], val[i], output,provider);
                oneToTwo(wires, val[i], val[i-1 - offset], output, provider);
                if (i > 1) {
                    fourToOne(wires, val[i-2 - offset], val[i], output, provider);
                }
            }
            if (cable != null){
                provider.shapeless(output, wire.getId() + "_cable_1x", "cables", new ItemStack(cable.getBlockItem(VTINY)), wire.getBlockItem(VTINY), GTMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, wire.getId() + "_cable_2x", "cables", new ItemStack(cable.getBlockItem(TINY)), wire.getBlockItem(TINY), GTMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, wire.getId() + "_cable_4x", "cables", new ItemStack(cable.getBlockItem(SMALL)), wire.getBlockItem(SMALL), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, wire.getId() + "_cable_8x", "cables", new ItemStack(cable.getBlockItem(NORMAL)), wire.getBlockItem(NORMAL), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, wire.getId() + "_cable_12x", "cables", new ItemStack(cable.getBlockItem(LARGE)), wire.getBlockItem(LARGE), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, wire.getId() + "_cable_16x", "cables", new ItemStack(cable.getBlockItem(HUGE)), wire.getBlockItem(HUGE), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber), GTMaterialTypes.PLATE.getMaterialTag(Rubber));
            }
            if (wire.getMaterial().has(GTMaterialTypes.PLATE)) {
                provider.shapeless(output, GT4RRef.ID, wire.getMaterial().getId() + "_plate_to_wire","wire",
                        new ItemStack(wires.get(VTINY), wireAmount),
                        GTTools.WIRE_CUTTER.getTag(), GTMaterialTypes.PLATE.getMaterialTag(wire.getMaterial()));
            }
        });
        GTAPI.all(RedstoneWire.class, wire -> {
            if (wire.getMaterial().has(PLATE)) {
                provider.shapeless(output,  wire.getMaterial().getId() + "_plate_to_wire","wire",
                        new ItemStack(wire.getBlockItem(VTINY), wireAmount),
                        GTTools.WIRE_CUTTER.getTag(), PLATE.get(wire.getMaterial()));
            }
        });
    }

    private static void twoToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, GTRecipeProvider provider) {
        provider.shapeless(output,"two_to_one_" + RegistryUtils.getIdFromItem(wires.get(to)).getPath(),"wire",
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from));
    }

    private static void oneToTwo(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, GTRecipeProvider provider) {
        provider.shapeless(output,"one_to_two_" + RegistryUtils.getIdFromItem(wires.get(to)).getPath(),"wire",
                new ItemStack(wires.get(to),2),wires.get(from));
    }

    private static void fourToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, GTRecipeProvider provider) {
        provider.shapeless(output,"four_to_one_" + RegistryUtils.getIdFromItem(wires.get(to)).getPath(),"wire",
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from),wires.get(from),wires.get(from));
    }
}
