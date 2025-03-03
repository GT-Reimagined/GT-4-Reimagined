package org.gtreimagined.gt4r.block;

import muramasa.antimatter.block.BlockFakeTile;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockFakeCasing extends BlockFakeTile {
    public BlockFakeCasing(String domain, String id, Properties properties) {
        super(domain, id, properties);
    }

    public BlockFakeCasing(String domain, String id) {
        this(domain, id, Properties.of(Material.METAL).strength(1.0f, 10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops());
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getDomain(), "block/casing/" + getRegistryName().getPath().replaceAll("_casing", ""))};
    }
}
