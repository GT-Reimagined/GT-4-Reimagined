package trinsdar.gt4r.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;
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
        String playerName = this.getDisplayName().getString();
        if (!FMLEnvironment.production) info.setReturnValue(GT4RData.CAPE_LOCATIONS[3]);
        if (orString(playerName, "GregoriusT", "OvermindDL1", "jihuayu123", "Yuesha_Kev14", "Evanvenir", "Trinsdar")) info.setReturnValue(GT4RData.CAPE_LOCATIONS[3]);
        if (playerName.equals("CrazyJ1984")) info.setReturnValue(GT4RData.CAPE_LOCATIONS[5]);
        if (playerName.equals("Mr_Brain")) info.setReturnValue(GT4RData.CAPE_LOCATIONS[2]);
        if (playerName.equals("Friedi4321")) info.setReturnValue(GT4RData.CAPE_LOCATIONS[0]);
        if (GT4RData.SupporterListGold.contains(playerName)) info.setReturnValue(GT4RData.CAPE_LOCATIONS[4]);
        if (GT4RData.SupporterListSilver.contains(playerName)) info.setReturnValue(GT4RData.CAPE_LOCATIONS[1]);
    }

    private boolean orString(String compare, String... strings){
        for (String string : strings){
            if (compare.equals(string)) return true;
        }
        return false;
    }
}
