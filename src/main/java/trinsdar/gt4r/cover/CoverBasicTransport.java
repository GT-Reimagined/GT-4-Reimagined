package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import net.minecraft.nbt.CompoundNBT;
import trinsdar.gt4r.Ref;

import static trinsdar.gt4r.cover.CoverMode.EXPORT;

public abstract class CoverBasicTransport extends BaseCover implements ICoverModeCover {

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public ICoverMode getCoverMode(CoverStack<?> stack) {
        return CoverMode.values()[stack.getNbt().getInt("coverMode")];
    }

    @Override
    public void onGuiEvent(CoverStack<?> stack, IGuiEvent event, int... data) {
        if (event == GuiEvent.EXTRA_BUTTON){
            stack.getNbt().putInt("coverMode", getCoverMode(data[0]));
        }
    }

    public int getCoverMode(int buttonID){
        switch (buttonID){
            case 0: return CoverMode.IMPORT.ordinal();
            case 1: return CoverMode.IMPORT_EXPORT.ordinal();
            case 2: return CoverMode.IMPORT_CONDITIONAL.ordinal();
            case 3: return CoverMode.IMPORT_EXPORT_CONDITIONAL.ordinal();
            case 4: return CoverMode.IMPORT_INVERT_COND.ordinal();
            case 5: return CoverMode.IMPORT_EXPORT_INVERT_COND.ordinal();
            case 7: return CoverMode.EXPORT_IMPORT.ordinal();
            case 8: return CoverMode.EXPORT_CONDITIONAL.ordinal();
            case 9: return CoverMode.EXPORT_IMPORT_CONDITIONAL.ordinal();
            case 10: return CoverMode.EXPORT_INVERT_COND.ordinal();
            case 11: return CoverMode.EXPORT_IMPORT_INVERT_COND.ordinal();
            default: return CoverMode.EXPORT.ordinal();
        }
    }
}
