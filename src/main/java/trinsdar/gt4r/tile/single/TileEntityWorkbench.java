package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import trinsdar.gt4r.data.SlotTypes;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.machine.MaterialMachine;

import static net.minecraft.inventory.container.Container.areItemsAndTagsEqual;

public class TileEntityWorkbench extends TileEntityMaterial<TileEntityWorkbench>{
    public TileEntityWorkbench(MaterialMachine type) {
        super(type);
    }

    @Override
    public void onGuiEvent(IGuiEvent event, PlayerEntity playerEntity, int... data) {
        super.onGuiEvent(event, playerEntity, data);
        if (event == GuiEvent.EXTRA_BUTTON && !openContainers.isEmpty()){
            if (data[0] == 0){
                openContainers.forEach(o -> {
                    if (playerEntity.getUniqueID().compareTo(o.getPlayerInv().player.getUniqueID()) == 0){
                        ((ContainerWorkbench<?>)o).clearCraftingGrid();
                    }
                });
            } else if (data[0] == 1){
                openContainers.forEach(o -> {
                    if (playerEntity.getUniqueID().compareTo(o.getPlayerInv().player.getUniqueID()) == 0){
                        ((ContainerWorkbench<?>)o).clearCraftingGridToPlayer();
                    }
                });
            }
        }
    }
}
