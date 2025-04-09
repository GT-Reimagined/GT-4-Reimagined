package org.gtreimagined.gt4r.cover;

import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.capability.ICoverHandler;
import org.gtreimagined.gtlib.cover.BaseCover;
import org.gtreimagined.gtlib.cover.CoverFactory;
import org.gtreimagined.gtlib.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.blockentity.single.BlockEntitySteamMachine;

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
