package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.cover.ICoverMode;
import muramasa.antimatter.cover.ICoverModeHandler;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.event.IGuiEvent;
import net.minecraft.entity.player.PlayerEntity;
import trinsdar.gt4r.Ref;

public abstract class CoverBasicTransport extends BaseCover implements ICoverModeHandler {

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public ICoverMode getCoverMode(CoverStack<?> stack) {
        return ImportExportMode.values()[stack.getNbt().getInt("coverMode")];
    }

    @Override
    public void onGuiEvent(CoverStack<?> stack, IGuiEvent event, PlayerEntity playerEntity, int... data) {
        if (event == GuiEvent.EXTRA_BUTTON){
            stack.getNbt().putInt("coverMode", getCoverMode(data[0]));
        }
    }

    public int getCoverMode(int buttonID){
        switch (buttonID){
            case 0: return ImportExportMode.IMPORT.ordinal();
            case 1: return ImportExportMode.IMPORT_EXPORT.ordinal();
            case 2: return ImportExportMode.IMPORT_CONDITIONAL.ordinal();
            case 3: return ImportExportMode.IMPORT_EXPORT_CONDITIONAL.ordinal();
            case 4: return ImportExportMode.IMPORT_INVERT_COND.ordinal();
            case 5: return ImportExportMode.IMPORT_EXPORT_INVERT_COND.ordinal();
            case 7: return ImportExportMode.EXPORT_IMPORT.ordinal();
            case 8: return ImportExportMode.EXPORT_CONDITIONAL.ordinal();
            case 9: return ImportExportMode.EXPORT_IMPORT_CONDITIONAL.ordinal();
            case 10: return ImportExportMode.EXPORT_INVERT_COND.ordinal();
            case 11: return ImportExportMode.EXPORT_IMPORT_INVERT_COND.ordinal();
            default: return ImportExportMode.EXPORT.ordinal();
        }
    }
}
