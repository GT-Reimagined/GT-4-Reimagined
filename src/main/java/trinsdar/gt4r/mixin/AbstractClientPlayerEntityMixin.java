package trinsdar.gt4r.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.data.GT4RData;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    @Shadow
    protected abstract NetworkPlayerInfo getPlayerInfo();

    public AbstractClientPlayerEntityMixin(World p_i241920_1_, BlockPos p_i241920_2_, float p_i241920_3_, GameProfile p_i241920_4_) {
        super(p_i241920_1_, p_i241920_2_, p_i241920_3_, p_i241920_4_);
    }

    @Inject(method = "getLocationCape()Lnet/minecraft/util/ResourceLocation;", at = @At(value = "HEAD"), cancellable = true)
    private void getLocationGTCape(CallbackInfoReturnable<ResourceLocation> info){
        if (this.getDisplayName().getString().equals("Dev")) info.setReturnValue(GT4RData.CAPE_LOCATIONS[3]);
        if (this.getDisplayName().getString().equals("Trinsdar")){
            NetworkPlayerInfo networkplayerinfo = getPlayerInfo();
            if (networkplayerinfo != null){
                GT4Reimagined.LOGGER.info(networkplayerinfo.getLocationCape().toString());
                GT4Reimagined.LOGGER.info(networkplayerinfo.getLocationElytra().toString());
            }
        }
    }

    private boolean checkPlayerAgainstUUID(){
        return false;
    }
}
