package trinsdar.gt4r.cover;

public enum CoverMode implements ICoverMode {
    EXPORT(88, 24),
    IMPORT(34, 24),
    EXPORT_CONDITIONAL(106, 24),
    IMPORT_CONDITIONAL(52, 24),
    EXPORT_INVERT_COND(124, 24),
    IMPORT_INVERT_COND(70, 24),
    EXPORT_IMPORT(88, 42),
    IMPORT_EXPORT(34, 42),
    EXPORT_IMPORT_CONDITIONAL(106, 42),
    IMPORT_EXPORT_CONDITIONAL(52, 42),
    EXPORT_IMPORT_INVERT_COND(124, 42),
    IMPORT_EXPORT_INVERT_COND(70, 42);

    int x, y;

    CoverMode(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
