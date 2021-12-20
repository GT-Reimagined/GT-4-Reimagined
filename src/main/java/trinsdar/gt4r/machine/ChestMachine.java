package trinsdar.gt4r.machine;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.block.BlockMaterialChest;

public class ChestMachine extends MaterialMachine{
    public ChestMachine(String domain, String id, Material material) {
        super(domain, id, material);
    }

    @Override
    protected Block getBlock(Machine<MaterialMachine> type, Tier tier) {
        return new BlockMaterialChest(type, tier);
    }

    @Override
    public Item getItem(Tier tier) {
        return BlockItem.BY_BLOCK.get(AntimatterAPI.get(BlockMaterialChest.class,this.getId() + "_" + tier.getId(), getDomain()));
    }
}
