package trinsdar.gt4r.entity.forge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.entity.SpearEntity;

public class SpearEntityForge extends SpearEntity implements IEntityAdditionalSpawnData {
    public SpearEntityForge(EntityType<SpearEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public SpearEntityForge(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(GT4RData.SPEAR_ENTITY_TYPE, world);
    }

    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeItemStack(this.weapon, false);
    }

    public void readSpawnData(FriendlyByteBuf additionalData) {
        ItemStack stack = additionalData.readItem();
        weapon = stack.copy();
    }

    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
