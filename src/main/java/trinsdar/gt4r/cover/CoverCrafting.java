package trinsdar.gt4r.cover;

import muramasa.antimatter.Data;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.widget.BackgroundWidget;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkHooks;
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
    public boolean openGui(PlayerEntity player, Direction side) {
        if (!hasGui()) return false;
        NetworkHooks.openGui((ServerPlayerEntity) player, this, packetBuffer -> {
            packetBuffer.writeBlockPos(handler.getTile().getBlockPos());
            packetBuffer.writeInt(side.get3DDataValue());
        });
        return true;
    }

    @Override
    public boolean onInteract(PlayerEntity player, Hand hand, Direction side, @Nullable AntimatterToolType type) {
        return openGui(player, side);
    }

    @Override
    public <T> boolean blocksCapability(Capability<T> cap, @Nullable Direction side) {
        return true;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
