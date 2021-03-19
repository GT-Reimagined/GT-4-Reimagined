package trinsdar.gt4r.gui;

import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.gui.ButtonOverlay;
import org.lwjgl.system.CallbackI;

public class ButtonOverlays {
    public static ButtonOverlay EXPORT_CONDITIONAL = new ButtonOverlay("export_conditional", 32, 80, 16, 16);
    public static ButtonOverlay IMPORT_CONDITIONAL = new ButtonOverlay("import_conditional", 48, 80, 16, 16);
    public static ButtonOverlay EXPORT_INVERT_CONDITIONAL = new ButtonOverlay("export_invert_conditional", 0, 80, 16, 16);
    public static ButtonOverlay IMPORT_INVERT_CONDITIONAL = new ButtonOverlay("import_invert_conditional", 16, 80, 16, 16);
    public static ButtonOverlay EXPORT_IMPORT = new ButtonOverlay("export_import", 64, 80, 16, 16);
    public static ButtonOverlay IMPORT_EXPORT = new ButtonOverlay("import_export", 80, 80, 16, 16);
    public static ButtonOverlay EXPORT_IMPORT_CONDITIONAL = new ButtonOverlay("export_import_conditional", 128, 80, 16, 16);
    public static ButtonOverlay IMPORT_EXPORT_CONDITIONAL = new ButtonOverlay("import_export_conditional", 144, 80, 16, 16);
    public static ButtonOverlay EXPORT_IMPORT_INVERT_CONDITIONAL = new ButtonOverlay("export_import_invert_conditional", 96, 80, 16, 16);
    public static ButtonOverlay IMPORT_EXPORT_INVERT_CONDITIONAL = new ButtonOverlay("import_export_invert_conditional", 112, 80, 16, 16);
}
