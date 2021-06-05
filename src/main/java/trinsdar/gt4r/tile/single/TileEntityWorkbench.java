package trinsdar.gt4r.tile.single;

import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import trinsdar.gt4r.gui.ContainerWorkbench;
import trinsdar.gt4r.machine.MaterialMachine;

public class TileEntityWorkbench extends TileEntityMaterial<TileEntityWorkbench>{
    public TileEntityWorkbench(MaterialMachine type) {
        super(type);
    }

    @Override
    public void onGuiEvent(IGuiEvent event, int... data) {
        super.onGuiEvent(event, data);
        if (event == GuiEvent.EXTRA_BUTTON && !openContainers.isEmpty()){
            if (data[0] == 0){
                int[] index = new int[1];
                index[0] = 0;
                openContainers.forEach(o -> {
                    if (index[0] == 0){
                        ((ContainerWorkbench<?>)o).clearCraftingGrid();
                    }
                    index[0]++;
                });
            } else if (data[0] == 1){
                int[] index = new int[1];
                index[0] = 0;
                openContainers.forEach(o -> {
                    if (index[0] == 0){
                        ((ContainerWorkbench<?>)o).clearCraftingGridToPlayer();
                    }
                    index[0]++;
                });
            }
        }
    }
}
