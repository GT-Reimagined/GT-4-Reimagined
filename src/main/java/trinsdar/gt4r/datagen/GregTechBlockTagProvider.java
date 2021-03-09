package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import trinsdar.gt4r.data.GregTechData;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class GregTechBlockTagProvider extends AntimatterBlockTagProvider {

    public GregTechBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen, ExistingFileHelper helper) {
        super(providerDomain, providerName, replace, gen, helper);
    }

    @Override
    public void registerTags() {
        super.registerTags();
        this.getOrCreateBuilder(BlockTags.LOGS).add(GregTechData.RUBBER_LOG);
        this.getOrCreateBuilder(BlockTags.LEAVES).add(GregTechData.RUBBER_LEAVES);
        this.getOrCreateBuilder(BlockTags.SAPLINGS).add(GregTechData.RUBBER_SAPLING);
    }
}
