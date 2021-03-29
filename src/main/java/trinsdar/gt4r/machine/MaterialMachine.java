package trinsdar.gt4r.machine;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.machine.types.TankMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tile.TileEntityTank;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import trinsdar.gt4r.block.BlockMachineMaterial;
import trinsdar.gt4r.data.Textures;
import trinsdar.gt4r.tile.single.TileEntityDrum;

import static muramasa.antimatter.machine.MachineFlag.CONFIGURABLE;
import static muramasa.antimatter.machine.MachineFlag.COVERABLE;
import static muramasa.antimatter.machine.MachineFlag.FLUID;
import static muramasa.antimatter.machine.MachineFlag.ITEM;

public class MaterialMachine extends Machine<MaterialMachine> {
    Material material;
    public MaterialMachine(String domain, String id, Material material, Object... data) {
        super(domain, id, data);
        this.material = material;
        setTile(() -> new TileEntityDrum(this));
        addFlags(FLUID, COVERABLE, CONFIGURABLE);
        covers((ICover[]) null);
        frontCovers();
        baseTexture = Textures.DRUM_HANDLER;
        overlayTextures = Textures.DRUM_OVERLAY_HANDLER;
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
