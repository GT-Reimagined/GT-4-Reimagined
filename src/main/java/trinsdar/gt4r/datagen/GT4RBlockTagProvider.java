package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import trinsdar.gt4r.data.GT4RData;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

import static trinsdar.gt4r.data.GT4RData.ADVANCED_MACHINE_BLOCK;
import static trinsdar.gt4r.data.Materials.HULL;
import static trinsdar.gt4r.data.Materials.Titanium;
import static trinsdar.gt4r.data.Materials.Tungsten;

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
        this.getOrCreateBuilder(TagUtils.getForgeBlockTag("machine_hull/advanced")).add(ADVANCED_MACHINE_BLOCK);
    }
}
