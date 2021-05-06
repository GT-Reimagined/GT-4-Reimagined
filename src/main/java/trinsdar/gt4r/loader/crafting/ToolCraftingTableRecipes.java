package trinsdar.gt4r.loader.crafting;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.pipe.PipeSize;

import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import trinsdar.gt4r.GT4RConfig;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.pipe.PipeSize.*;
import static trinsdar.gt4r.data.Materials.Rubber;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.criterion;

public class ToolCraftingTableRecipes {
    @SuppressWarnings("unchecked")
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        int wireAmount = GT4RConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 1 : 2;
        AntimatterAPI.all(Wire.class, wire -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + wire.getMaterial().getId());
            ImmutableSet<PipeSize> sizes = wire.getSizes();
            Map<PipeSize, Item> wires = sizes.stream().map(s -> new Pair<>(s, wire.getBlockItem(s))).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
            PipeSize[] val = values();
            for (int i = 1; i < val.length; i ++) {
                int offset = val[i] == HUGE ? 1 : 0;
                if (val[i] == LARGE){
                    provider.shapeless(output,"threeone","wire","has_cutter",criterion(WIRE_CUTTER.getTag(), provider),
                            new ItemStack(wires.get(val[i]),1),wires.get(SMALL),wires.get(SMALL),wires.get(SMALL));
                    provider.shapeless(output,"onethree","wire","has_cutter",criterion(WIRE_CUTTER.getTag(), provider),
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
                provider.shapeless(output, wire.getId() + "_cable_1x", "cables", "has_wire1x", provider.hasSafeItem(wire.getBlockItem(VTINY)), new ItemStack(cable.getBlockItem(VTINY)), wire.getBlockItem(VTINY), INGOT.getMaterialTag(Rubber));
                provider.shapeless(output, wire.getId() + "_cable_2x", "cables", "has_wire2x", provider.hasSafeItem(wire.getBlockItem(TINY)), new ItemStack(cable.getBlockItem(TINY)), wire.getBlockItem(TINY), INGOT.getMaterialTag(Rubber), INGOT.getMaterialTag(Rubber));
                provider.shapeless(output, wire.getId() + "_cable_4x", "cables", "has_wire4x", provider.hasSafeItem(wire.getBlockItem(SMALL)), new ItemStack(cable.getBlockItem(SMALL)), wire.getBlockItem(SMALL), INGOT.getMaterialTag(Rubber), INGOT.getMaterialTag(Rubber), INGOT.getMaterialTag(Rubber), INGOT.getMaterialTag(Rubber));
            }
            if (wire.getMaterial().has(PLATE)) {
                provider.shapeless(output, "platewire","wire","has_cutter", criterion(WIRE_CUTTER.getTag(), provider),
                        new ItemStack(wires.get(VTINY), wireAmount),
                        WIRE_CUTTER.getTag(), PLATE.getMaterialTag(wire.getMaterial()));
            }
        });

        INGOT.all().stream().filter(p -> p.has(PLATE)).forEach(p -> {
            if (GT4RConfig.GAMEPLAY.LOSSY_PART_CRAFTING){
                provider.shapeless(output, "ingothammer", "plate", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), new ItemStack(PLATE.get(p), 1),
                        HAMMER.getTag(), INGOT.getMaterialTag(p), INGOT.getMaterialTag(p));
            } else {
                provider.shapeless(output, "ingothammer", "plate", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), new ItemStack(PLATE.get(p), 1),
                        HAMMER.getTag(), INGOT.getMaterialTag(p));
            }

        });
        DUST.all().stream().filter(p -> p.has(GEM) || p.has(INGOT)).forEach(p -> {
            String gemIngot = p.has(GEM) ? "gems" : "ingots";
            provider.shapeless(output, "dust_" + p.getId() + "_from_id", "mortar_uses", "has_mortar", provider.hasSafeItem(MORTAR.getTag()),
                    DUST.get(p, 1), MORTAR.getTag(), TagUtils.getForgeItemTag(gemIngot+ "/" + p.getId()));
        });
    }

    private static void twoToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"twoone","wire","has_cutter",criterion(WIRE_CUTTER.getTag(), provider),
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from));
    }

    private static void oneToTwo(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"onetwo","wire","has_cutter",criterion(WIRE_CUTTER.getTag(), provider),
                new ItemStack(wires.get(to),2),wires.get(from));
    }

    private static void fourToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"fourone","wire","has_cutter",criterion(WIRE_CUTTER.getTag(), provider),
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from),wires.get(from),wires.get(from));
    }
}
