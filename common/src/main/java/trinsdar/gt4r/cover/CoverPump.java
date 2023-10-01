package trinsdar.gt4r.cover;

import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import tesseract.TesseractCapUtils;
import trinsdar.gt4r.cover.redstone.CoverRedstoneMachineController;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static trinsdar.gt4r.gui.ButtonOverlays.*;

public class CoverPump extends CoverBasicTransport {

    public static String ID = "pump_module";

    public CoverPump(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }


    @Override
    public String getId() {
        return ID;
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, Direction side) {
        return cap != FluidContainer.class;
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        return exportMode == ImportExportMode.EXPORT;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        return exportMode == ImportExportMode.IMPORT;
    }


    @Override
    public void onUpdate() {
        //Pump acts on each tick.
        if (handler.getTile().getLevel().isClientSide) return;
        if (handler.getTile() == null) return;
        BlockEntity adjTile = handler.getTile().getLevel().getBlockEntity(handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) return;
        BlockPos from = handler.getTile().getBlockPos();
        BlockPos to = handler.getTile().getBlockPos().relative(side);
        Direction fromSide = side;
        if (!exportMode.isExport()){
            from = handler.getTile().getBlockPos().relative(side);
            to = handler.getTile().getBlockPos();
            fromSide = side.getOpposite();
        }
        BlockPos finalTo = to;
        if (canMove(side)) {
            Direction finalFromSide = fromSide;
            TesseractCapUtils.getFluidHandler(handler.getTile().getLevel(), from, fromSide).ifPresent(ih -> TesseractCapUtils.getFluidHandler(handler.getTile().getLevel(), finalTo, finalFromSide.getOpposite()).ifPresent(other -> Utils.transferFluids(ih, other, Integer.MAX_VALUE)));
        }
    }

    protected boolean canMove(Direction side){
        if (redstoneMode != RedstoneMode.NO_WORK){
            boolean powered = isPowered(side);
            return (redstoneMode == RedstoneMode.INVERTED) != powered;
        }
        return true;
    }

    @Override
    protected String getRenderId() {
        return ID;
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }
}
