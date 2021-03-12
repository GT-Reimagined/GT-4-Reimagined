package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import trinsdar.gt4r.data.GT4RData;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class GT4RBlockTagProvider extends AntimatterBlockTagProvider {

    public GT4RBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen, ExistingFileHelper helper) {
        super(providerDomain, providerName, replace, gen, helper);
    }

    @Override
    public void registerTags() {
        super.registerTags();
        this.getOrCreateBuilder(BlockTags.LOGS).add(GT4RData.RUBBER_LOG);
        this.getOrCreateBuilder(BlockTags.LEAVES).add(GT4RData.RUBBER_LEAVES);
        this.getOrCreateBuilder(BlockTags.SAPLINGS).add(GT4RData.RUBBER_SAPLING);
    }
}
