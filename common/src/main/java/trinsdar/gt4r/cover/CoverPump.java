package trinsdar.gt4r.cover;

import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import tesseract.TesseractCapUtils;
import trinsdar.gt4r.cover.redstone.CoverRedstoneMachineController;

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
        onPumpUpdate(this, side);
    }

    static void onPumpUpdate(CoverBasicTransport cover, Direction side){
        //Pump acts on each tick.
        if (cover.source().getTile().getLevel().isClientSide) return;
        if (cover.source().getTile() == null) return;
        BlockEntity adjTile = cover.source().getTile().getLevel().getBlockEntity(cover.source().getTile().getBlockPos().relative(side));
        if (adjTile == null) return;
        BlockPos from = cover.source().getTile().getBlockPos();
        BlockPos to = cover.source().getTile().getBlockPos().relative(side);
        Direction fromSide = side;
        if (!cover.exportMode.isExport()){
            from = cover.source().getTile().getBlockPos().relative(side);
            to = cover.source().getTile().getBlockPos();
            fromSide = side.getOpposite();
        }
        BlockPos finalTo = to;
        if (cover.canMove(side)) {
            Direction finalFromSide = fromSide;
            TesseractCapUtils.INSTANCE.getFluidHandler(cover.source().getTile().getLevel(), from, fromSide).ifPresent(ih -> TesseractCapUtils.INSTANCE.getFluidHandler(cover.source().getTile().getLevel(), finalTo, finalFromSide.getOpposite()).ifPresent(other -> Utils.transferFluids(ih, other, Integer.MAX_VALUE)));
        }
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
