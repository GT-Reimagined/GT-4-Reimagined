package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.loader.crafting.CraftingHelper;
import trinsdar.gt4r.loader.crafting.Machines;
import trinsdar.gt4r.loader.crafting.Parts;
import trinsdar.gt4r.loader.crafting.WireCablesPlates;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static trinsdar.gt4r.loader.crafting.CraftingHelper.criterion;

public class GT4RRecipes extends AntimatterRecipeProvider {

    public GT4RRecipes(String providerDomain, String providerName, DataGenerator gen) {
        super(providerDomain, providerName, gen);
        registerCraftingLoaders();
        //Depends on certain data so TIER MAPS cannot be static {} initialized.
        //GregTechData.buildTierMaps();
    }

    protected void registerCraftingLoaders() {
        this.craftingLoaders.add(Parts::loadRecipes);
        this.craftingLoaders.add(WireCablesPlates::loadRecipes);
        this.craftingLoaders.add(Machines::loadRecipes);
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        super.registerRecipes(consumer);
        addConditionalRecipe(consumer, getStackRecipe("", "has_sulfur_dust", criterion(getForgeItemTag("dusts/sulfur"), this),
                new ItemStack(Blocks.TORCH, 6), of('D', getForgeItemTag("dusts/sulfur"), 'R', Tags.Items.RODS_WOODEN), "D", "R"), Ref.class, "sulfurTorch", Ref.ID, "sulfur_torch");

        addItemRecipe(consumer, Ref.ID, "hopper", "", "has_wrench", criterion(WRENCH.getTag(), this),
                Blocks.HOPPER, of('C', Blocks.CHEST, 'I', getForgeItemTag("plates/iron"), 'W', WRENCH.getTag()), "IWI", "ICI", " I ");


        addItemRecipe(consumer, "gears", "has_stone", criterion(Blocks.PISTON, this), Blocks.STICKY_PISTON, of('S', GT4RData.StickyResin, 'P', Blocks.PISTON), "S", "P");
    }

    @Override
    public String getName() {
        return "GregTech Crafting Recipes";
    }

    private String fixLoc(String attach) {
        return Ref.ID.concat(":").concat(attach);
    }

}
