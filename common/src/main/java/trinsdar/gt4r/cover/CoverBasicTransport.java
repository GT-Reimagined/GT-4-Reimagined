package trinsdar.gt4r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICoverMode;
import muramasa.antimatter.cover.ICoverModeHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import trinsdar.gt4r.Ref;

import javax.annotation.Nullable;

public abstract class CoverBasicTransport extends BaseCover implements ICoverModeHandler {

    protected ImportExportMode coverMode;

    public CoverBasicTransport(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        this.coverMode = ImportExportMode.EXPORT;
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public ICoverMode getCoverMode() {
        return coverMode;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            coverMode = getCoverMode(ev.data[0]);
        }
    }

    public ImportExportMode getCoverMode(int buttonID){
        switch (buttonID){
            case 0: return ImportExportMode.IMPORT;
            case 1: return ImportExportMode.IMPORT_EXPORT;
            case 2: return ImportExportMode.IMPORT_CONDITIONAL;
            case 3: return ImportExportMode.IMPORT_EXPORT_CONDITIONAL;
            case 4: return ImportExportMode.IMPORT_INVERT_COND;
            case 5: return ImportExportMode.IMPORT_EXPORT_INVERT_COND;
            case 7: return ImportExportMode.EXPORT_IMPORT;
            case 8: return ImportExportMode.EXPORT_CONDITIONAL;
            case 9: return ImportExportMode.EXPORT_IMPORT_CONDITIONAL;
            case 10: return ImportExportMode.EXPORT_INVERT_COND;
            case 11: return ImportExportMode.EXPORT_IMPORT_INVERT_COND;
            default: return ImportExportMode.EXPORT;
        }
    }
}
