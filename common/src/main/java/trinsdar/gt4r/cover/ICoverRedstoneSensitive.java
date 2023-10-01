package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.util.AntimatterCapUtils;
import net.minecraft.core.Direction;
import trinsdar.gt4r.cover.redstone.CoverRedstoneMachineController;

import java.util.ArrayList;
import java.util.List;

public interface ICoverRedstoneSensitive extends ICover {
    default boolean isPowered(Direction side){
        return AntimatterCapUtils.getCoverHandler(source().getTile(), side).map(h -> {
            List<CoverRedstoneMachineController> list = new ArrayList<>();
            for (Direction dir : Direction.values()){
                if (h.get(dir) instanceof CoverRedstoneMachineController machineController){
                    list.add(machineController);
                }
            }
            int i = 0;
            int j = 0;
            for (CoverRedstoneMachineController coverStack : list){
                j++;
                if (coverStack.isPowered()){
                    i++;
                }
            }
            return i > 0 && i == j;
        }).orElse(false);
    }
}
