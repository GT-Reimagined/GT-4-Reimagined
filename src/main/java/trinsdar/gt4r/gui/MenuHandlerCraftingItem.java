package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.AntimatterCaps;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.gui.MenuHandler;
import muramasa.antimatter.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.util.LazyOptional;
import trinsdar.gt4r.data.client.ScreenFactories;

public class MenuHandlerCraftingItem extends MenuHandler<GTWorkbenchContainer> {
    public MenuHandlerCraftingItem(String domain, String id) {
        super(domain, id);
    }

    @Override
    public GTWorkbenchContainer onContainerCreate(int windowId, PlayerInventory inv, PacketBuffer data) {
        //TODO
        return menu((IGuiHandler) inv.player, inv, windowId);
    }

    @Override
    public GTWorkbenchContainer getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
        return tile instanceof PlayerEntity ? new ContainerCraftingItem(windowId, playerInv, IWorldPosCallable.of(playerInv.player.world, playerInv.player.getPosition())) : null;
    }

    @Override
    public Object screen() {
        return ScreenFactories.SCREEN_CRAFTING_TABLE;
    }
}
