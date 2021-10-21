package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkHooks;
import trinsdar.gt4r.Ref;

import javax.annotation.Nullable;

public class CoverCrafting extends BaseCover {
    public CoverCrafting() {
        super();
        register();
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public String getId() {
        return "crafting_module";
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public boolean openGui(CoverStack<?> instance, PlayerEntity player, Direction side) {
        if (!hasGui()) return false;
        NetworkHooks.openGui((ServerPlayerEntity) player, instance, packetBuffer -> {
            packetBuffer.writeBlockPos(instance.getTile().getPos());
            packetBuffer.writeInt(side.getIndex());
        });
        return true;
    }

    @Override
    public boolean onInteract(CoverStack<?> instance, PlayerEntity player, Hand hand, Direction side, @Nullable AntimatterToolType type) {
        return openGui(instance, player, side);
    }

    @Override
    public <T> boolean blocksCapability(CoverStack<?> stack, Capability<T> cap, @Nullable Direction side) {
        return true;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
