package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.AntimatterCaps;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.gui.MenuHandler;
import muramasa.antimatter.util.Utils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.util.LazyOptional;
import trinsdar.gt4r.data.client.ScreenFactories;

public class MenuHandlerCrafting extends MenuHandler<WorkbenchContainer> {
    public MenuHandlerCrafting(String domain, String id) {
        super(domain, id);
    }

    @Override
    public WorkbenchContainer onContainerCreate(int windowId, PlayerInventory inv, PacketBuffer data) {
        TileEntity tile = Utils.getTileFromBuf(data);
        if (tile != null) {
            Direction dir = Direction.byIndex(data.readInt());
            LazyOptional<ICoverHandler> coverHandler = tile.getCapability(AntimatterCaps.COVERABLE_HANDLER_CAPABILITY, dir);
            return getMenu(coverHandler.map(ch -> ch.get(dir)).orElse(null), inv, windowId);
        }
        return null;
    }

    @Override
    public WorkbenchContainer getMenu(Object tile, PlayerInventory playerInv, int windowId) {
        return tile instanceof CoverStack ? new ContainerCraftingCover(windowId, playerInv, IWorldPosCallable.of(((CoverStack<?>)tile).getTile().getWorld(), ((CoverStack<?>)tile).getTile().getPos()), ((CoverStack<?>) tile)) : null;
    }

    @Override
    public Object screen() {
        return ScreenFactories.SCREEN_CRAFTING_TABLE;
    }
}
