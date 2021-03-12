package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import trinsdar.gt4r.data.GT4RData;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class GT4RBlockTagProvider extends AntimatterBlockTagProvider {

    public GT4RBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen) {
        super(providerDomain, providerName, replace, gen);
    }

    @Override
    public void registerTags() {
        super.registerTags();
        this.getBuilder(BlockTags.LOGS).add(GT4RData.RUBBER_LOG);
        this.getBuilder(BlockTags.LEAVES).add(GT4RData.RUBBER_LEAVES);
        this.getBuilder(BlockTags.SAPLINGS).add(GT4RData.RUBBER_SAPLING);
    }
}
