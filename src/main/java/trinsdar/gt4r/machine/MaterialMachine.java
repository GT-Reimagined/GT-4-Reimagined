package trinsdar.gt4r.machine;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.tile.single.TileEntityMaterial;

import static muramasa.antimatter.machine.MachineFlag.COVERABLE;

public class MaterialMachine extends Machine<MaterialMachine> {
    Material material;
    public MaterialMachine(String domain, String id, Material material) {
        super(domain, id);
        this.material = material;
        setTile(() -> new TileEntityMaterial<>(this));
        addFlags(COVERABLE);
        covers((ICover[]) null);
        frontCovers();
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    protected Block getBlock(Machine<MaterialMachine> type, Tier tier) {
        return new BlockMachineMaterial(type, tier);
    }

    @Override
    public Item getItem(Tier tier) {
        return BlockItem.BLOCK_TO_ITEM.get(AntimatterAPI.get(BlockMachineMaterial.class,this.getId() + "_" + tier.getId()));
    }
}
