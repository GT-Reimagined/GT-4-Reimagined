package trinsdar.gt4r.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.gui.ContainerWorkbench;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.machine.Tier.HV;
import static muramasa.antimatter.machine.Tier.LV;
import static trinsdar.gt4r.data.Materials.CHARGING_WORKBENCH;
import static trinsdar.gt4r.data.Materials.WORKBENCH;

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
            registration.addRecipeCatalyst(new ItemStack(Machine.get(m.getId() + "_workbench").getItem(LV)), VanillaRecipeCategoryUid.CRAFTING);
            if (m.has(CHARGING_WORKBENCH)){
                registration.addRecipeCatalyst(new ItemStack(Machine.get(m.getId() + "_charging_workbench").getItem(HV)), VanillaRecipeCategoryUid.CRAFTING);
            }
        });
    }

    public static class GT4RRecipeTransferInfo implements IRecipeTransferInfo<ContainerWorkbench>{
        @Override
        public Class<ContainerWorkbench> getContainerClass() {
            return ContainerWorkbench.class;
        }

        @Override
        public ResourceLocation getRecipeCategoryUid() {
            return VanillaRecipeCategoryUid.CRAFTING;
        }

        @Override
        public boolean canHandle(ContainerWorkbench containerWorkbench) {
            return true;
        }

        @Override
        public List<Slot> getRecipeSlots(ContainerWorkbench containerWorkbench) {
            List<Slot> slots = new ArrayList<>();
            for (int i = 17; i < 26; i++) {
                slots.add(containerWorkbench.getSlot(i));
            }
            return slots;
        }

        @Override
        public List<Slot> getInventorySlots(ContainerWorkbench containerWorkbench) {
            List<Slot> slots = new ArrayList<>();
            for (int i = 1; i < 17; i++) {
                slots.add(containerWorkbench.getSlot(i));
            }
            for (int i = 26; i < 68; i++) {
                slots.add(containerWorkbench.getSlot(i));
            }
            return slots;
        }
    }
}
