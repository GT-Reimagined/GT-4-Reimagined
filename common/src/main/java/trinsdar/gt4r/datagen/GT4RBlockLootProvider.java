package trinsdar.gt4r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.ore.BlockOre;
import trinsdar.gt4r.block.BlockCasing;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.block.BlockNonSolidMachine;
import trinsdar.gt4r.data.GT4RData;

public class GT4RBlockLootProvider extends AntimatterBlockLootProvider {
    public GT4RBlockLootProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    protected void loot() {
        super.loot();
        AntimatterAPI.all(BlockMachineMaterial.class, providerDomain, this::add);
        AntimatterAPI.all(BlockNonSolidMachine.class, providerDomain, this::add);

        AntimatterAPI.all(BlockCasing.class, providerDomain, this::add);
    }

    @Override
    protected void addToFortune(BlockOre block) {
        if (block.getOreType() == AntimatterMaterialTypes.ORE){
            if (block.getStoneType() == AntimatterStoneTypes.SAND || block.getStoneType() == AntimatterStoneTypes.SAND_RED || block.getStoneType() == AntimatterStoneTypes.GRAVEL){
                tables.put(block, this::build);
            }
        }
    }
}
