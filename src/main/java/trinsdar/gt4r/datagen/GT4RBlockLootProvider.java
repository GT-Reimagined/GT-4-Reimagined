package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import net.minecraft.data.DataGenerator;
import trinsdar.gt4r.data.GT4RData;

public class GT4RBlockLootProvider extends AntimatterBlockLootProvider {
    public GT4RBlockLootProvider(String providerDomain, String providerName, DataGenerator gen) {
        super(providerDomain, providerName, gen);
    }

    @Override
    protected void loot() {
        super.loot();
        tables.put(GT4RData.RUBBER_LEAVES, b -> droppingWithChancesAndSticks(GT4RData.RUBBER_LEAVES, GT4RData.RUBBER_SAPLING, 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F));
        this.add(GT4RData.RUBBER_LOG);
        this.add(GT4RData.RUBBER_SAPLING);
    }
}
