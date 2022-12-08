package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.AntimatterCaps;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
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
import net.minecraftforge.common.capabilities.Capability;
import trinsdar.gt4r.cover.redstone.CoverRedstoneMachineController;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
import static trinsdar.gt4r.gui.ButtonOverlays.*;
import static trinsdar.gt4r.gui.ButtonOverlays.EXPORT_IMPORT;
import static trinsdar.gt4r.gui.ButtonOverlays.EXPORT_IMPORT_CONDITIONAL;
import static trinsdar.gt4r.gui.ButtonOverlays.EXPORT_IMPORT_INVERT_CONDITIONAL;

public class CoverConveyor extends CoverBasicTransport {

    public static String ID = "conveyor_module";

    public CoverConveyor(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
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
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 0 || mode == 2 || mode == 4;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 1 || mode == 3 || mode == 5;
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
        boolean isMachine = handler.getTile() instanceof TileEntityMachine;
        BlockState state = handler.getTile().getLevel().getBlockState(handler.getTile().getBlockPos().relative(side));
        //Drop into world.
        if (state == Blocks.AIR.defaultBlockState() && isMachine && coverMode.getName().startsWith("Export")) {
            Level world = handler.getTile().getLevel();
            BlockPos pos = handler.getTile().getBlockPos();
            ItemStack stack = handler.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).map(Utils::extractAny).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addFreshEntity(new ItemEntity(world,pos.getX()+side.getStepX(), pos.getY()+side.getStepY(), pos.getZ()+side.getStepZ(),stack));
        }
        if (!(state.getBlock() instanceof EntityBlock)) return;
        BlockEntity adjTile = handler.getTile().getLevel().getBlockEntity(handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) {
            return;
        }
        if (canMove(side)) {
            if (coverMode.getName().startsWith("Export")) {
                if (isMachine) handler.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> adjTile.getCapability(ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferItems(ih, other,true)));
            } else {
                handler.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> adjTile.getCapability(ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferItems(other, ih,true)));
            }
        }
    }

    protected boolean canMove(Direction side){
        String name = getCoverMode().getName();
        if (name.contains("Conditional")){
            boolean powered = handler.getTile().getCapability(AntimatterCaps.getCOVERABLE_HANDLER_CAPABILITY(), side).map(h -> {
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
    public boolean hasGui() {
        return true;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }
}
