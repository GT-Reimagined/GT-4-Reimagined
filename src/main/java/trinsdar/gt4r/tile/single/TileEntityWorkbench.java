package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineCoverHandler;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nonnull;

public class TileEntityWorkbench extends TileEntityMaterial<TileEntityWorkbench>{
    public TileEntityWorkbench(MaterialMachine type) {
        super(type);
        coverHandler.set(() -> new MachineCoverHandler<TileEntityWorkbench>(this){
            @Override
            public boolean placeCover(PlayerEntity player, Direction side, ItemStack stack, ICover cover) {
                return false;
            }
        });
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return super.getGuiTexture();
    }

    @Override
    public void onGuiEvent(IGuiEvent event, PlayerEntity playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON && !openContainers.isEmpty()){
            final int[] data = ((GuiEvents.GuiEvent)event).data;
            if (data[1] == 0){
                openContainers.forEach(o -> {
                    if (playerEntity.getUUID().compareTo(o.getPlayerInv().player.getUUID()) == 0){
                        ((ContainerWorkbench<?>)o).clearCraftingGrid();
                    }
                });
            } else if (data[1] == 1){
                openContainers.forEach(o -> {
                    if (playerEntity.getUUID().compareTo(o.getPlayerInv().player.getUUID()) == 0){
                        ((ContainerWorkbench<?>)o).clearCraftingGridToPlayer();
                    }
                });
            }
        }
    }

    @Override
    public <V> boolean blocksCapability(@Nonnull Capability<V> cap, Direction side) {
        return super.blocksCapability(cap, side) || cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }
}
