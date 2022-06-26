package trinsdar.gt4r.mixin;

import muramasa.antimatter.Data;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.data.ToolTypes;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerPlayNetHandlerMixin {

    @Shadow
    public ServerPlayer player;

    @ModifyConstant(
            method = "handleInteract",
            constant = {
                    @Constant(doubleValue = 36.0D)
            }
    )
    private double getExtendedAttackReachSquared(double value) {
        if (player.getMainHandItem().getItem() != ToolTypes.SPEAR.getToolStack(Data.NULL, Data.NULL).getItem()) return value;
        double extendedAttackReachValue = player.getAttributeValue(Attributes.ATTACK_REACH) * 2.0D;
        return extendedAttackReachValue * extendedAttackReachValue;
    }
}
