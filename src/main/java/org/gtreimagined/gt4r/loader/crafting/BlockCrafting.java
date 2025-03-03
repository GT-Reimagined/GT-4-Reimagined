package org.gtreimagined.gt4r.loader.crafting;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.block.BlockColoredWall;
import org.gtreimagined.gt4r.data.CustomTags;
import org.gtreimagined.gt4r.data.GT4RBlocks;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static org.gtreimagined.gt4r.data.CustomTags.*;
import static org.gtreimagined.gt4r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;
import static org.gtreimagined.gt4r.data.GT4RItems.ItemSuperconductor;
import static org.gtreimagined.gt4r.data.GT4RItems.NichromeHeatingCoil;

public class BlockCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, GT4RRef.ID, "firebricks", "blocks",
                new ItemStack(GT4RBlocks.FIRE_BRICKS), of('F', GTCoreItems.FireBrick), "FF", "FF");
        provider.addStackRecipe(output, GT4RRef.ID, "standard_machine", "blocks",
                new ItemStack(GT4RBlocks.STANDARD_MACHINE_CASING, 4), of('I', PLATES_WROUGHT_ALUMINIUM, 'C', CIRCUITS_BASIC, 'M', CustomTags.MACHINE_HULLS_BASIC), "III", "CMC", "III");
        provider.addStackRecipe(output, GT4RRef.ID, "reinforced_machine", "blocks",
                new ItemStack(GT4RBlocks.REINFORCED_MACHINE_CASING, 4), of('I', PLATES_STEELS, 'C', CIRCUITS_ADVANCED, 'M', MACHINE_HULLS_ADVANCED), "III", "CMC", "III");
        provider.addStackRecipe(output, GT4RRef.ID, "advanced_machine", "blocks",
                new ItemStack(GT4RBlocks.ADVANCED_MACHINE_CASING, 4), of('I', PLATE.getMaterialTag(Chromium), 'C', CIRCUITS_ELITE, 'M', GT4RBlocks.HIGHLY_ADVANCED_MACHINE_BLOCK), "III", "CMC", "III");
        provider.addStackRecipe(output, GT4RRef.ID, "highly_advanced_machine", "blocks",
                new ItemStack(GT4RBlocks.HIGHLY_ADVANCED_MACHINE_BLOCK, 1), of('T', PLATE.getMaterialTag(Titanium), 'C', PLATE.getMaterialTag(Chromium), 'M', MACHINE_HULLS_ADVANCED), "CTC", "TMT", "CTC");
        provider.addItemRecipe(output, GT4RRef.ID, "coil_fusion", "blocks", GT4RBlocks.FUSION_COIL, of('C', CIRCUITS_MASTER, 'S', ItemSuperconductor, 'N', NichromeHeatingCoil, 'H', GT4RBlocks.HIGHLY_ADVANCED_MACHINE_BLOCK, 'I', GTCoreItems.IridiumNeutronReflector), "CSC", "NHN", "CIC");
        AntimatterAPI.all(BlockColoredWall.class, b -> {
            if (b.getMaterial() == Wood){
                provider.addItemRecipe(output, "walls", b.asItem(),
                        of('P', PLATE.getMaterialTag(Bronze), 'H', HAMMER.getTag(), 'S', SAW.getTag(), 'W', ItemTags.PLANKS), "W W", "SPH", "W W");
            } else {
                provider.addItemRecipe(output, "walls", b.asItem(),
                        of('P', PLATE.getMaterialTag(b.getMaterial()), 'H', HAMMER.getTag(), 'W', WRENCH.getTag()), "WPP", "HPP");
            }
        });
    }
}
