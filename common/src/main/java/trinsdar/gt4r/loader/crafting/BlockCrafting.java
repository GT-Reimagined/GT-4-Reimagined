package trinsdar.gt4r.loader.crafting;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import trinsdar.gt4r.GT4RRef;
import muramasa.antimatter.data.ForgeCTags;
import trinsdar.gt4r.data.CustomTags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static trinsdar.gt4r.data.CustomTags.*;
import static trinsdar.gt4r.data.GT4RData.*;
import static trinsdar.gt4r.data.Materials.Carbon;
import static trinsdar.gt4r.data.Materials.Chromium;
import static trinsdar.gt4r.data.Materials.Titanium;

public class BlockCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, GT4RRef.ID, "firebricks", "blocks",
                new ItemStack(FIRE_BRICKS), of('F', GTCoreItems.FireBrick), "FF", "FF");
        provider.addStackRecipe(output, GT4RRef.ID, "reinforce_glass_v", "blocks",
                new ItemStack(REINFORCED_GLASS, 7), of('G', ForgeCTags.GLASS, 'A', AdvancedAlloy), "GAG", "GGG", "GAG");
        provider.addStackRecipe(output, GT4RRef.ID, "reinforce_glass_h", "blocks",
                new ItemStack(REINFORCED_GLASS, 7), of('G', ForgeCTags.GLASS, 'A', AdvancedAlloy), "GGG", "AGA", "GGG");
        provider.addStackRecipe(output, GT4RRef.ID, "standard_machine", "blocks",
                new ItemStack(STANDARD_MACHINE_CASING, 4), of('I', PLATES_WROUGHT_ALUMINIUM, 'C', CIRCUITS_BASIC, 'M', CustomTags.MACHINE_HULLS_BASIC), "III", "CMC", "III");
        provider.addStackRecipe(output, GT4RRef.ID, "reinforced_machine", "blocks",
                new ItemStack(REINFORCED_MACHINE_CASING, 4), of('I', PLATES_STEELS, 'C', CIRCUITS_ADVANCED, 'M', MACHINE_HULLS_ADVANCED), "III", "CMC", "III");
        provider.addStackRecipe(output, GT4RRef.ID, "advanced_machine", "blocks",
                new ItemStack(ADVANCED_MACHINE_CASING, 4), of('I', PLATE.getMaterialTag(Chromium), 'C', CIRCUITS_ELITE, 'M', HIGHLY_ADVANCED_MACHINE_BLOCK), "III", "CMC", "III");
        provider.addStackRecipe(output, GT4RRef.ID, "highly_advanced_machine", "blocks",
                new ItemStack(HIGHLY_ADVANCED_MACHINE_BLOCK, 1), of('T', PLATE.getMaterialTag(Titanium), 'C', PLATE.getMaterialTag(Chromium), 'M', MACHINE_HULLS_ADVANCED), "CTC", "TMT", "CTC");
        provider.addItemRecipe(output, GT4RRef.ID, "coil_fusion", "blocks", FUSION_COIL, of('C', CIRCUITS_MASTER, 'S', ItemSuperconductor, 'N', NichromeHeatingCoil, 'H', HIGHLY_ADVANCED_MACHINE_BLOCK, 'I', GTCoreItems.IridiumNeutronReflector), "CSC", "NHN", "CIC");
    }
}
