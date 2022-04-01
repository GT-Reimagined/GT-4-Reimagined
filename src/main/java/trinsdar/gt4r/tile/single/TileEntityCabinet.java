package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.item.ITrackedHandler;
import muramasa.antimatter.gui.SlotType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import trinsdar.gt4r.machine.MaterialMachine;

public class TileEntityCabinet extends TileEntityMaterial<TileEntityCabinet>{
    final int ySize;
    public TileEntityCabinet(MaterialMachine type, int ySize) {
        super(type);
        this.ySize = ySize;
    }

    @Override
    public int guiHeight() {
        return ySize;
    }

    @Override
    public int guiSize() {
        return 184;
    }

    public Container getContents(){
        return this.itemHandler.map(i -> new HandlerWrapper(i.getHandler(SlotType.STORAGE))).orElse(null);
    }

    public static class HandlerWrapper implements Container {
        final ITrackedHandler trackedHandler;

        public HandlerWrapper(ITrackedHandler trackedHandler){
            this.trackedHandler = trackedHandler;
        }

        @Override
        public int getContainerSize() {
            return trackedHandler.getSlots();
        }

        @Override
        public boolean isEmpty() {
            int fill = 0;
            for (int slot = 0; slot < trackedHandler.getSlots(); slot ++){
                if (!trackedHandler.getStackInSlot(slot).isEmpty()){
                    fill ++;
                }
            }
            return fill == 0;
        }

        @Override
        public ItemStack getItem(int pIndex) {
            return trackedHandler.getStackInSlot(pIndex);
        }

        @Override
        public ItemStack removeItem(int pIndex, int pCount) {
            return trackedHandler.extractItem(pIndex, pCount, false);
        }

        @Override
        public ItemStack removeItemNoUpdate(int pIndex) {
            return trackedHandler.extractItem(pIndex, 1, true);
        }

        @Override
        public void setItem(int pIndex, ItemStack pStack) {
            trackedHandler.setStackInSlot(pIndex, pStack);
        }

        @Override
        public void setChanged() {

        }

        @Override
        public boolean stillValid(Player pPlayer) {
            return true;
        }

        @Override
        public void clearContent() {
        }
    }
}
