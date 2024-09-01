package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.data.MenuHandlers;

public class ItemCraftingModule extends ItemCover implements MenuProvider {
    public ItemCraftingModule() {
        super(GT4RRef.ID, "crafting_module");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (openGui(playerIn)) return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
        return super.use(worldIn, playerIn, handIn);
    }

    public boolean openGui(Player player) {
        if (player.level.isClientSide) return false;
        AntimatterPlatformUtils.openGui((ServerPlayer) player, this, packetBuffer -> {
            packetBuffer.writeBlockPos(player.blockPosition());
        });
        return true;
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent(this.getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
        return MenuHandlers.ITEM_CRAFTING_HANDLER.getMenu(null, playerInv, windowId);
    }
}
