package org.gtreimagined.gt4r.block;

import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.datagen.builder.GTBlockModelBuilder;
import org.gtreimagined.gtlib.datagen.providers.GTBlockStateProvider;
import org.gtreimagined.gtlib.datagen.providers.GTItemModelProvider;
import org.gtreimagined.gtlib.dynamic.BlockDynamic;
import org.gtreimagined.gtlib.registration.ITextureProvider;
import org.gtreimagined.gtlib.texture.Texture;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockCasing extends BlockDynamic {

    public BlockCasing(String domain, String id, Properties properties) {
        super(domain, id, properties);
        if (this.getClass() != BlockCasing.class) GTAPI.register(BlockCasing.class, this);
    }

    public BlockCasing(String domain, String id) {
        this(domain, id, Properties.of(Material.METAL).strength(1.0f, 10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops());
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getDomain(), "block/casing/" + getId().replaceAll("_casing", ""))};
    }

    @Override
    public void onBlockModelBuild(Block block, GTBlockStateProvider prov) {
        GTBlockModelBuilder builder = buildBlock(block,prov);
        if (builder != null) {
            prov.state(block, builder);
        } else {
            super.onBlockModelBuild(block, prov);
        }
    }
    //Hierarchial block builder.
    protected GTBlockModelBuilder buildBlock(Block block, GTBlockStateProvider prov) {
        return null;
    }

    @Override
    public void onItemModelBuild(ItemLike item, GTItemModelProvider prov) {
        prov.modelAndTexture(item, GTBlockModelBuilder.getSimple()).tex(t -> t.putAll(GTBlockModelBuilder.buildTextures(((ITextureProvider) item).getTextures())));
    }
}