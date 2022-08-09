package trinsdar.gt4r.machine.fabric;

import io.github.feltmc.feltapi.api.item.extensions.IsDamageableItem;
import muramasa.antimatter.Antimatter;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import trinsdar.gt4r.mixin.GTCapabilityProviderAccessor;

import java.util.Optional;

public class QuantumChestItemHandlerImpl {
    public static CompoundTag save(CompoundTag pCompoundTag, ItemStack stack) {
        ResourceLocation resourcelocation = Registry.ITEM.getKey(stack.getItem());
        pCompoundTag.putString("id", resourcelocation == null ? "minecraft:air" : resourcelocation.toString());
        pCompoundTag.putInt("Count", stack.getCount());
        if (stack.getTag() != null) {
            pCompoundTag.put("tag", stack.getTag().copy());
        }
        final CapabilityDispatcher disp = ((GTCapabilityProviderAccessor)stack.getCapabilityProvider()).getCapabilitiesGT();
        if (disp != null)
        {
            CompoundTag cnbt = disp.serializeNBT();
            if (!cnbt.isEmpty()) {
                pCompoundTag.put("ForgeCaps", cnbt);
            }
        }

        return pCompoundTag;
    }

    public static ItemStack of(CompoundTag pCompoundTag) {
        try {
            CompoundTag capNBT = pCompoundTag.contains("ForgeCaps") ? pCompoundTag.getCompound("ForgeCaps") : null;
            Item rawItem = Registry.ITEM.get(new ResourceLocation(pCompoundTag.getString("id")));
            int count = pCompoundTag.getInt("Count");
            ItemStack stack = new ItemStack(rawItem, count);
            stack.setCapNbt(capNBT);
            if (pCompoundTag.contains("tag", 10)) {
                CompoundTag tag = pCompoundTag.getCompound("tag");
                stack.setTag(tag);
                stack.getItem().verifyTagAfterLoad(pCompoundTag);
            }

            if ((stack.getItem() instanceof IsDamageableItem item && item.isDamageable(stack)) || stack.getItem().canBeDepleted()) {
                stack.setDamageValue(stack.getDamageValue());
            }
            return stack;
        } catch (RuntimeException runtimeexception) {
            Antimatter.LOGGER.debug("Tried to load invalid item: {}", pCompoundTag, runtimeexception);
            return ItemStack.EMPTY;
        }
    }
}
