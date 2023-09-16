package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import trinsdar.gt4r.blockentity.single.BlockEntitySteamMachine;

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
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public void onBlockUpdate() {
        if (handler.getTile() instanceof BlockEntityMachine){
            ((BlockEntityMachine<?>)handler.getTile()).recipeHandler.ifPresent(h -> {
                if (h instanceof BlockEntitySteamMachine.SteamMachineRecipeHandler){
                    ((BlockEntitySteamMachine.SteamMachineRecipeHandler) h).setSteamClear(handler.getTile().getLevel().isEmptyBlock(handler.getTile().getBlockPos().relative(side)));
                }
            });
        }

    }
}
