package org.gtreimagined.gt4r.datagen;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.data.GTTools;
import org.gtreimagined.gtlib.data.GTLibMaterials;
import org.gtreimagined.gtlib.datagen.providers.GTBlockTagProvider;
import net.minecraft.tags.BlockTags;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.block.BlockCasing;
import org.gtreimagined.gt4r.block.BlockColoredWall;
import org.gtreimagined.gt4r.block.BlockFakeCasing;
import org.gtreimagined.gt4r.block.BlockNonSolidMachine;
import org.gtreimagined.gt4r.block.BlockRedstoneMachine;

import static org.gtreimagined.gt4r.data.GT4RBlocks.IRIDIUM_REINFORCED_STONE;

public class GT4RBlockTagProvider extends GTBlockTagProvider {

    public GT4RBlockTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    public void processTags(String domain) {
        super.processTags(domain);
        GTAPI.all(BlockCasing.class, GT4RRef.ID, cas -> {
            this.tag(GTTools.PICKAXE.getToolType()).add(cas);
        });
        GTAPI.all(BlockFakeCasing.class, GT4RRef.ID, cas -> {
            this.tag(GTTools.PICKAXE.getToolType()).add(cas);
        });
        GTAPI.all(BlockNonSolidMachine.class, GT4RRef.ID, cas -> {
            this.tag(GTTools.WRENCH.getToolType()).add(cas);
        });
        GTAPI.all(BlockRedstoneMachine.class, GT4RRef.ID, cas -> {
            this.tag(GTTools.WRENCH.getToolType()).add(cas);
        });
        GTAPI.all(BlockColoredWall.class, GT4RRef.ID, cas -> {
            if (cas.getMaterial() == GTLibMaterials.Wood){
                this.tag(GTTools.AXE.getToolType()).add(cas);
            } else {
                this.tag(GTTools.WRENCH.getToolType()).add(cas);
            }
        });
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(IRIDIUM_REINFORCED_STONE);
    }
}
