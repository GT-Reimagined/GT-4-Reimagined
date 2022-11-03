package trinsdar.gt4r.cover;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICoverMode;
import muramasa.antimatter.cover.ICoverModeHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import trinsdar.gt4r.Ref;

import javax.annotation.Nullable;

public abstract class CoverBasicTransport extends BaseCover implements ICoverModeHandler {

    protected ImportExportMode coverMode;
    int coverModeInt;

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
            Antimatter.LOGGER.info(handler.getTile().getLevel().isClientSide);
            coverMode = getCoverModeFromButton(ev.data[1]);
            coverModeInt = coverMode.ordinal();
        }
    }

    public ImportExportMode getCoverModeFromButton(int buttonID){
        return switch (buttonID) {
            case 0 -> ImportExportMode.IMPORT;
            case 1 -> ImportExportMode.IMPORT_EXPORT;
            case 2 -> ImportExportMode.IMPORT_CONDITIONAL;
            case 3 -> ImportExportMode.IMPORT_EXPORT_CONDITIONAL;
            case 4 -> ImportExportMode.IMPORT_INVERT_COND;
            case 5 -> ImportExportMode.IMPORT_EXPORT_INVERT_COND;
            case 7 -> ImportExportMode.EXPORT_IMPORT;
            case 8 -> ImportExportMode.EXPORT_CONDITIONAL;
            case 9 -> ImportExportMode.EXPORT_IMPORT_CONDITIONAL;
            case 10 -> ImportExportMode.EXPORT_INVERT_COND;
            case 11 -> ImportExportMode.EXPORT_IMPORT_INVERT_COND;
            default -> ImportExportMode.EXPORT;
        };
    }

    @Override
    public int coverModeToInt() {
        return coverMode.ordinal();
    }

    public ICoverMode getCoverMode(int index) {
        return ImportExportMode.values()[index];
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putInt("coverMode", coverModeInt);
        return nbt;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("coverMode")) {
            coverModeInt = nbt.getInt("coverMode");
            coverMode = (ImportExportMode) getCoverMode(coverModeInt);
        }
    }
}
