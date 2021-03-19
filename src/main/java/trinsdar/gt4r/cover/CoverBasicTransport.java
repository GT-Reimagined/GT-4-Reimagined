package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import net.minecraft.nbt.CompoundNBT;
import trinsdar.gt4r.Ref;

import static trinsdar.gt4r.cover.CoverMode.EXPORT;

public abstract class CoverBasicTransport extends BaseCover implements ICoverModeCover {

    CoverMode coverMode = EXPORT;

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    public ICoverMode getCoverMode() {
        return coverMode;
    }

    @Override
    public void serialize(CoverStack<?> stack, CompoundNBT nbt) {
        nbt.putInt("coverMode", coverMode.ordinal());
    }

    @Override
    public void deserialize(CoverStack<?> stack, CompoundNBT nbt) {
        super.deserialize(stack, nbt);
        coverMode = CoverMode.values()[(nbt.getInt("coverMode"))];
    }
}
