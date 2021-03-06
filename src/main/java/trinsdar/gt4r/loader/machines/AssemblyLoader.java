package trinsdar.gt4r.loader.machines;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import trinsdar.gt4r.block.BlockCasing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import trinsdar.gt4r.data.GregTechData;

import java.util.Arrays;
import java.util.Set;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.WRENCH;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.of;
import static trinsdar.gt4r.data.GregTechData.*;
import static trinsdar.gt4r.data.Materials.*;
import static trinsdar.gt4r.data.RecipeMaps.ASSEMBLING;

public class AssemblyLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                ASSEMBLING.RB().ii(of(wireItem,1), of(INGOT.get(Rubber, size.getCableThickness()))).io(new ItemStack(cableItem,1)).add(size.getCableThickness()* 20L,8);
            });
        });
        ASSEMBLING.RB().ii(of(Items.STICK, 1), of(Items.COAL, 1)).io(new ItemStack(Items.TORCH, 4)).add(400, 1);
        ASSEMBLING.RB().ii(of(Items.STRING, 1), of(Items.SLIME_BALL, 1)).io(new ItemStack(Items.LEAD, 2)).add(200, 2);
        ASSEMBLING.RB().ii(of(CircuitBoardAdv, 1), of(AdvCircuitParts, 2)).io(new ItemStack(CircuitAdv, 1)).add(1600, 2);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor, 1), of(CircuitDataStorage, 1)).io(new ItemStack(CircuitDataControl, 2)).add(3200, 4);
    }
}
