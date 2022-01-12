package trinsdar.gt4r.config;

public class OreConfigNode {
    private boolean enable;
    private int minY, maxY, weight, size;
    private String secondary;
    private int secondaryChance;

    public OreConfigNode(boolean enable, int minY, int maxY, int weight, int size, String secondary, int secondaryChance) {
        this.enable = enable;
        this.minY = minY;
        this.maxY = maxY;
        this.weight = weight;
        this.size = size;
        this.secondary = secondary;
        this.secondaryChance = secondaryChance;
    }

    public boolean isEnabled() {
        return enable;
    }

    public void setEnabled(boolean enable) {
        this.enable = enable;
    }
}
