package org.gtreimagined.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import net.minecraft.tags.BlockTags;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.block.BlockCasing;
import org.gtreimagined.gt4r.block.BlockColoredWall;
import org.gtreimagined.gt4r.block.BlockFakeCasing;
import org.gtreimagined.gt4r.block.BlockNonSolidMachine;
import org.gtreimagined.gt4r.block.BlockRedstoneMachine;

import static org.gtreimagined.gt4r.data.GT4RBlocks.IRIDIUM_REINFORCED_STONE;

public class GT4RBlockTagProvider extends AntimatterBlockTagProvider {

    public GT4RBlockTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    public void processTags(String domain) {
        super.processTags(domain);
        AntimatterAPI.all(BlockCasing.class, GT4RRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockFakeCasing.class, GT4RRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockNonSolidMachine.class, GT4RRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockRedstoneMachine.class, GT4RRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockColoredWall.class, GT4RRef.ID, cas -> {
            if (cas.getMaterial() == AntimatterMaterials.Wood){
                this.tag(AntimatterDefaultTools.AXE.getToolType()).add(cas);
            } else {
                this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
            }
        });
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(IRIDIUM_REINFORCED_STONE);
    }
}
