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

import java.util.Arrays;
import java.util.Set;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.WRENCH;
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
                ASSEMBLING.RB().ii(AntimatterIngredient.of(wireItem,1), INT_CIRCUITS.get(24)).fi(Rubber.getLiquid(size.getCableThickness()*16)).io(new ItemStack(cableItem,1)).add(size.getCableThickness()* 20L,8);
            });
        });
    }
}
