package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.tile.single.TileEntitySteamMachine;

public class CoverSteamVent extends BaseCover {
    public CoverSteamVent(){
        register();
    }


    @Override
    protected String getRenderId() {
        return "output";
    }

    @Override
    public String getId() {
        return "cover_steam_vent";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public void onBlockUpdate(CoverStack<?> instance, Direction side) {
        if (instance.getTile() instanceof TileEntityMachine){
            ((TileEntityMachine<?>)instance.getTile()).recipeHandler.ifPresent(h -> {
                if (h instanceof TileEntitySteamMachine.SteamMachineRecipeHandler){
                    ((TileEntitySteamMachine.SteamMachineRecipeHandler) h).setSteamClear(instance.getTile().getWorld().isAirBlock(instance.getTile().getPos().offset(side)));
                }
            });
        }

    }
}
