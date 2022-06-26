package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import trinsdar.gt4r.Ref;

import javax.annotation.Nullable;

public class CoverCrafting extends BaseCover {


    public CoverCrafting(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
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
    public boolean openGui(Player player, Direction side) {
        if (!hasGui()) return false;
        AntimatterPlatformUtils.openGui((ServerPlayer) player, this, packetBuffer -> {
            packetBuffer.writeBlockPos(handler.getTile().getBlockPos());
            packetBuffer.writeInt(side.get3DDataValue());
        });
        return true;
    }

    @Override
    public boolean onInteract(Player player, InteractionHand hand, Direction side, @Nullable AntimatterToolType type) {
        return openGui(player, side);
    }

    @Override
    public <T> boolean blocksCapability(Capability<T> cap, @Nullable Direction side) {
        return true;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
