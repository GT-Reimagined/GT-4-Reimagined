package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractCapUtils;
import tesseract.api.item.ExtendedItemContainer;
import trinsdar.gt4r.cover.redstone.CoverRedstoneMachineController;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static trinsdar.gt4r.gui.ButtonOverlays.*;

public class CoverConveyor extends CoverBasicTransport {

    public static String ID = "conveyor_module";

    public CoverConveyor(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, Direction side) {
        return cap != ExtendedItemContainer.class;
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        return exportMode == ImportExportMode.EXPORT;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        return exportMode == ImportExportMode.IMPORT;
    }

    //Useful for using the same model for multiple tiers where id is dependent on tier.
    @Override
    protected String getRenderId() {
        return ID;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel().isClientSide) return;
        if (handler.getTile() == null)
            return;
        boolean isMachine = handler.getTile() instanceof BlockEntityMachine;
        BlockState state = handler.getTile().getLevel().getBlockState(handler.getTile().getBlockPos().relative(side));
        //Drop into world.
        if (state == Blocks.AIR.defaultBlockState() && isMachine && exportMode.isExport()) {
            Level world = handler.getTile().getLevel();
            BlockPos pos = handler.getTile().getBlockPos();
            ItemStack stack = TesseractCapUtils.getItemHandler(handler.getTile(), side).map(Utils::extractAny).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addFreshEntity(new ItemEntity(world,pos.getX()+side.getStepX(), pos.getY()+side.getStepY(), pos.getZ()+side.getStepZ(),stack));
        }
        if (!(state.getBlock() instanceof EntityBlock)) return;
        BlockEntity adjTile = handler.getTile().getLevel().getBlockEntity(handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) {
            return;
        }
        if (canMove(side)) {
            if (exportMode.isExport()) {
                if (isMachine) TesseractCapUtils.getItemHandler(handler.getTile(), side).ifPresent(ih -> TesseractCapUtils.getItemHandler(adjTile, side.getOpposite()).ifPresent(other -> Utils.transferItems(ih, other,true)));
            } else {
                TesseractCapUtils.getItemHandler(handler.getTile(), side).ifPresent(ih -> TesseractCapUtils.getItemHandler(adjTile, side.getOpposite()).ifPresent(other -> Utils.transferItems(other, ih,true)));
            }
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
    public boolean hasGui() {
        return true;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }
}
