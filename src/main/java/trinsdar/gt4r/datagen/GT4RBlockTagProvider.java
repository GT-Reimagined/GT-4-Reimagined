package trinsdar.gt4r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import trinsdar.gt4r.data.GT4RData;

import static trinsdar.gt4r.data.GT4RData.*;

public class GT4RBlockTagProvider extends AntimatterBlockTagProvider {

    public GT4RBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen, ExistingFileHelper helper) {
        super(providerDomain, providerName, replace, gen, helper);
    }

    @Override
    public void addTags() {
        super.addTags();
        this.tag(BlockTags.LOGS).add(GT4RData.RUBBER_LOG);
        this.tag(BlockTags.LEAVES).add(GT4RData.RUBBER_LEAVES);
        this.tag(BlockTags.SAPLINGS).add(GT4RData.RUBBER_SAPLING);
        this.tag(TagUtils.getForgeBlockTag("machine_hull/advanced")).add(ADVANCED_MACHINE_BLOCK);
        this.tag(TagUtils.getForgeBlockTag("wg_stone")).add(GRANITE_BLACK.getState().getBlock(), GRANITE_RED.getState().getBlock(), MARBLE.getState().getBlock(), BASALT.getState().getBlock(), KOMATIITE.getState().getBlock(), LIMESTONE.getState().getBlock(), GREEN_SCHIST.getState().getBlock(), BLUE_SCHIST.getState().getBlock(), KIMBERLITE.getState().getBlock(), QUARTZITE.getState().getBlock());
        this.tag(TagUtils.getBlockTag(new ResourceLocation("minecraft","base_stone_overworld"))).add(GRANITE_BLACK.getState().getBlock(), GRANITE_RED.getState().getBlock(), MARBLE.getState().getBlock(), BASALT.getState().getBlock(), KOMATIITE.getState().getBlock(), LIMESTONE.getState().getBlock(), GREEN_SCHIST.getState().getBlock(), BLUE_SCHIST.getState().getBlock(), KIMBERLITE.getState().getBlock(), QUARTZITE.getState().getBlock());
        this.tag(TagUtils.getBlockTag(new ResourceLocation("minecraft", "logs_that_burn"))).add(RUBBER_LOG);
    }
}
