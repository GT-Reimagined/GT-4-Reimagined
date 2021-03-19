package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
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

    //@Override
    public void onGuiEvent(IGuiEvent event, int... data) {
        if (event == GuiEvent.EXTRA_SWITCH){
            switch (data[0]){
                case 0: coverMode = CoverMode.IMPORT;
                case 1: coverMode = CoverMode.IMPORT_EXPORT;
                case 2: coverMode = CoverMode.IMPORT_CONDITIONAL;
                case 3: coverMode = CoverMode.IMPORT_EXPORT_CONDITIONAL;
                case 4: coverMode = CoverMode.IMPORT_INVERT_COND;
                case 5: coverMode = CoverMode.IMPORT_EXPORT_INVERT_COND;
                case 6: coverMode = EXPORT;
                case 7: coverMode = CoverMode.EXPORT_IMPORT;
                case 8: coverMode = CoverMode.EXPORT_CONDITIONAL;
                case 9: coverMode = CoverMode.EXPORT_IMPORT_CONDITIONAL;
                case 10: coverMode = CoverMode.EXPORT_INVERT_COND;
                case 11: coverMode = CoverMode.EXPORT_IMPORT_INVERT_COND;
            }

        }
    }
}
