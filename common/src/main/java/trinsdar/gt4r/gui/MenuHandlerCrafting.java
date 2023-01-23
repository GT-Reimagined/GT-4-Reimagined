package trinsdar.gt4r.gui;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.gui.MenuHandler;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.client.ScreenFactories;

import java.util.Optional;

public class MenuHandlerCrafting extends MenuHandler<GTWorkbenchContainer> {
    public MenuHandlerCrafting(String domain, String id) {
        super(domain, id);
    }

    @Override
    public GTWorkbenchContainer onContainerCreate(int windowId, Inventory inv, FriendlyByteBuf data) {
        BlockEntity tile = Utils.getTileFromBuf(data);
        if (tile != null) {
            Direction dir = Direction.from3DDataValue(data.readInt());
            Optional<ICoverHandler<?>> coverHandler = AntimatterCapUtils.getCoverHandler(tile, dir);
            return getMenu(coverHandler.map(ch -> ch.get(dir)).orElse(null), inv, windowId);
        }
        return null;
    }

    @Override
    protected GTWorkbenchContainer getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
        return tile instanceof ICover ? new ContainerCraftingCover(windowId, playerInv, ContainerLevelAccess.create(((ICover)tile).source().getTile().getLevel(), ((ICover)tile).source().getTile().getBlockPos()), ((ICover) tile)) : null;
    }

    @Override
    public String screenID() {
        return "crafting_table";
    }

    @Override
    public String screenDomain() {
        return Ref.ID;
    }
}
