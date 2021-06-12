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
        //player.playSound(muramasa.antimatter.Ref.WRENCH, SoundCategory.BLOCKS, 1.0f, 2.0f);
        return true;
    }

    @Override
    public boolean onInteract(CoverStack<?> instance, PlayerEntity player, Hand hand, Direction side, @Nullable AntimatterToolType type) {
        return openGui(instance, player, side);
    }

    @Override
    public ResourceLocation getModel(Direction dir, Direction facing) {
        return getBasicModel();
    }
}
