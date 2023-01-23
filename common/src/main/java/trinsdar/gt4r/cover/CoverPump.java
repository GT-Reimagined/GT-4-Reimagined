package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.antimatter.util.Utils;
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
        ButtonBody[][] overlays = new ButtonBody[][]{{IMPORT, IMPORT_CONDITIONAL, IMPORT_INVERT_CONDITIONAL, EXPORT, EXPORT_CONDITIONAL, EXPORT_INVERT_CONDITIONAL}, {IMPORT_EXPORT, IMPORT_EXPORT_CONDITIONAL, IMPORT_EXPORT_INVERT_CONDITIONAL, EXPORT_IMPORT, EXPORT_IMPORT_CONDITIONAL, EXPORT_IMPORT_INVERT_CONDITIONAL}};
        addGuiCallback(t -> {
            for (int x = 0; x < 6; x++){
                for (int y = 0; y < 2; y++){
                    t.addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x]);
                }
            }
        });
    }


    @Override
    public String getId() {
        return ID;
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 0 || mode == 2 || mode == 4;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 1 || mode == 3 || mode == 5;
    }


    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel().isClientSide) return;
        if (handler.getTile() == null) return;
        BlockEntity adjTile = handler.getTile().getLevel().getBlockEntity(handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) return;
        BlockEntity from = handler.getTile();
        BlockEntity to = adjTile;
        Direction fromSide = side;
        if (getCoverMode().getName().startsWith("Import")){
            from = adjTile;
            to = handler.getTile();
            fromSide = side.getOpposite();
        }
        BlockEntity finalTo = to;
        if (canMove(side)) {
            Direction finalFromSide = fromSide;
            TesseractCapUtils.getFluidHandler(from, fromSide).ifPresent(ih -> TesseractCapUtils.getFluidHandler(finalTo, finalFromSide.getOpposite()).ifPresent(other -> Utils.transferFluids(ih, other, Integer.MAX_VALUE)));
        }
    }

    protected boolean canMove(Direction side){
        String name = getCoverMode().getName();
        if (name.contains("Conditional")){
            boolean powered = AntimatterCapUtils.getCoverHandler(handler.getTile(), side).map(h -> {
                List<CoverRedstoneMachineController> list = new ArrayList<>();
                for (Direction dir : Direction.values()){
                    if (h.get(dir) instanceof CoverRedstoneMachineController){
                        list.add((CoverRedstoneMachineController) h.get(dir));
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
            return name.contains("Invert") != powered;
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
