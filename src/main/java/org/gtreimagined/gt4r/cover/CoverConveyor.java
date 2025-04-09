package org.gtreimagined.gt4r.cover;

import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.capability.ICoverHandler;
import org.gtreimagined.gtlib.cover.CoverFactory;
import org.gtreimagined.gtlib.machine.Tier;
import org.gtreimagined.gtlib.util.Utils;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class CoverConveyor extends CoverBasicTransport {

    public static String ID = "conveyor_module";

    public CoverConveyor(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, Direction side) {
        return cap != IItemHandler.class;
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
        onConveyorUpdate(this, side);
    }

    static void onConveyorUpdate(CoverBasicTransport cover, Direction side){
        if (cover.source().getTile().getLevel().isClientSide) return;
        if (cover.source().getTile() == null)
            return;
        boolean isMachine = cover.source().getTile() instanceof BlockEntityMachine;
        BlockState state = cover.source().getTile().getLevel().getBlockState(cover.source().getTile().getBlockPos().relative(side));
        //Drop into world.
        if (state == Blocks.AIR.defaultBlockState() && isMachine && cover.exportMode.isExport()) {
            Level world = cover.source().getTile().getLevel();
            BlockPos pos = cover.source().getTile().getBlockPos();
            ItemStack stack = cover.source().getTile().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side).map(Utils::extractAny).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addFreshEntity(new ItemEntity(world,pos.getX()+side.getStepX(), pos.getY()+side.getStepY(), pos.getZ()+side.getStepZ(),stack));
        }
        if (!(state.getBlock() instanceof EntityBlock)) return;
        BlockEntity adjTile = cover.source().getTile().getLevel().getBlockEntity(cover.source().getTile().getBlockPos().relative(side));
        if (adjTile == null) {
            return;
        }
        if (cover.canMove(side)) {
            if (cover.exportMode.isExport()) {
                if (isMachine) cover.source().getTile().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> adjTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferItems(ih, other,true)));
            } else {
                cover.source().getTile().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> adjTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferItems(other, ih,true)));
            }
        }
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
