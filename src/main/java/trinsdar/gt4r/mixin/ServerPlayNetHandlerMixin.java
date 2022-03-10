package trinsdar.gt4r.mixin;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.Data;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.ServerPlayNetHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import trinsdar.gt4r.data.Attributes;
import trinsdar.gt4r.data.ToolTypes;

@Mixin(ServerPlayNetHandler.class)
public abstract class ServerPlayNetHandlerMixin {

    @Shadow
    public ServerPlayerEntity player;

    @ModifyConstant(
            method = "handleInteract",
            constant = {
                    @Constant(doubleValue = 36.0D)
            }
    )
    private double getExtendedAttackReachSquared(double value) {
        if (player.getMainHandItem().getItem() != ToolTypes.SPEAR.getToolStack(Data.NULL, Data.NULL).getItem()) return value;
        double extendedAttackReachValue = player.getAttributeValue(Attributes.ATTACK_REACH.get()) * 2.0D;
        return extendedAttackReachValue * extendedAttackReachValue;
    }
}
