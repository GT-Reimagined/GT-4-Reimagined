package trinsdar.gt4r.integration.jei;

import io.github.gregtechintergalactical.gtutility.gui.ContainerWorkbench;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import trinsdar.gt4r.Ref;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.machine.Tier.HV;
import static muramasa.antimatter.machine.Tier.LV;
import static trinsdar.gt4r.data.GT4RMaterialTags.CHARGING_WORKBENCH;
import static trinsdar.gt4r.data.GT4RMaterialTags.WORKBENCH;

@JeiPlugin
public class GT4RJeiPlugin implements IModPlugin {
    public GT4RJeiPlugin(){
        Antimatter.LOGGER.debug("GT4RJEIPlugin created");
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Ref.ID, "jei_plugin");
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new GT4RRecipeTransferInfo());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        WORKBENCH.all().forEach(m -> {
            registration.addRecipeCatalyst(new ItemStack(Machine.get(m.getId() + "_workbench", Ref.ID).map(mch -> mch.getItem(LV)).orElse(Items.AIR)), RecipeTypes.CRAFTING);
            if (m.has(CHARGING_WORKBENCH)){
                registration.addRecipeCatalyst(new ItemStack(Machine.get(m.getId() + "_charging_workbench", Ref.ID).map(mch -> mch.getItem(HV)).orElse(Items.AIR)), RecipeTypes.CRAFTING);
            }
        });
    }

    public static class GT4RRecipeTransferInfo implements IRecipeTransferInfo<ContainerWorkbench, CraftingRecipe>{
        @Override
        public Class<ContainerWorkbench> getContainerClass() {
            return ContainerWorkbench.class;
        }

        @Override
        public RecipeType<CraftingRecipe> getRecipeType() {
            return RecipeTypes.CRAFTING;
        }

        @Override
        public boolean canHandle(ContainerWorkbench containerWorkbench, CraftingRecipe recipe) {
            return true;
        }

        @Override
        public List<Slot> getRecipeSlots(ContainerWorkbench containerWorkbench, CraftingRecipe recipe) {
            List<Slot> slots = new ArrayList<>();
            for (int i = 17; i < 26; i++) {
                slots.add(containerWorkbench.getSlot(i));
            }
            return slots;
        }

        @Override
        public List<Slot> getInventorySlots(ContainerWorkbench containerWorkbench, CraftingRecipe recipe) {
            List<Slot> slots = new ArrayList<>();
            for (int i = 1; i < 17; i++) {
                slots.add(containerWorkbench.getSlot(i));
            }
            for (int i = 26; i < 68; i++) {
                slots.add(containerWorkbench.getSlot(i));
            }
            return slots;
        }

        @Override
        public Class<CraftingRecipe> getRecipeClass() {
            return CraftingRecipe.class;
        }

        @Override
        public ResourceLocation getRecipeCategoryUid() {
            return VanillaRecipeCategoryUid.CRAFTING;
        }
    }
}
