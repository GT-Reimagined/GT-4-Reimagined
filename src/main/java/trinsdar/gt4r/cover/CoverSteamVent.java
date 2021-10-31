package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import trinsdar.gt4r.tile.single.TileEntitySteamMachine;

import javax.annotation.Nullable;

public class CoverSteamVent extends BaseCover {


    public CoverSteamVent(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
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
    public void onBlockUpdate() {
        if (handler.getTile() instanceof TileEntityMachine){
            ((TileEntityMachine<?>)handler.getTile()).recipeHandler.ifPresent(h -> {
                if (h instanceof TileEntitySteamMachine.SteamMachineRecipeHandler){
                    ((TileEntitySteamMachine.SteamMachineRecipeHandler) h).setSteamClear(handler.getTile().getWorld().isAirBlock(handler.getTile().getPos().offset(side)));
                }
            });
        }

    }
}
