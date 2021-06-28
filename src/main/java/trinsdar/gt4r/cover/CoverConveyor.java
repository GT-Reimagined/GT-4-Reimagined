package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class CoverConveyor extends CoverBasicTransport {

    public static String ID = "conveyor_module";

    public CoverConveyor() {
        super();
        register();
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
    public void onUpdate(CoverStack<?> instance, Direction side) {
        if (instance.getTile() == null)
            return;
        boolean isMachine = instance.getTile() instanceof TileEntityMachine;
        BlockState state = instance.getTile().getWorld().getBlockState(instance.getTile().getPos().offset(side));
        ImportExportMode mode = ImportExportMode.values()[instance.getNbt().getInt("coverMode")];
        //Drop into world.
        if (state == Blocks.AIR.getDefaultState() && isMachine && mode.getName().startsWith("Export")) {
            World world = instance.getTile().getWorld();
            BlockPos pos = instance.getTile().getPos();
            ItemStack stack = instance.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).map(Utils::extractAny).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addEntity(new ItemEntity(world,pos.getX()+side.getXOffset(), pos.getY()+side.getYOffset(), pos.getZ()+side.getZOffset(),stack));
        }
        if (!(state.hasTileEntity())) return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) {
            return;
        }
        if (mode.getName().startsWith("Export")) {
            if (isMachine) instance.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> adjTile.getCapability(ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferItems(ih, other,true)));
        } else {
            instance.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> adjTile.getCapability(ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferItems(other, ih,true)));
        }
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public ResourceLocation getModel(Direction dir, Direction facing) {
        return getBasicDepthModel();
    }
}
