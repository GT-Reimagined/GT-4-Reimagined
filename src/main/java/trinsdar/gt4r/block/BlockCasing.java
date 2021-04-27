package trinsdar.gt4r.block;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.dynamic.BlockDynamic;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.TileEntityTypes;

import javax.annotation.Nullable;

public class BlockCasing extends BlockDynamic {

    public BlockCasing(String domain, String id, Block.Properties properties) {
        super(domain, id, properties);
        if (this.getClass() != BlockCasing.class) AntimatterAPI.register(BlockCasing.class, getId(), this);
    }

    public BlockCasing(String domain, String id) {
        this(domain, id, Block.Properties.create(Material.IRON).hardnessAndResistance(1.0f, 10.0f).sound(SoundType.METAL).setRequiresTool());
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/casing/" + getRegistryName().getPath().replaceAll("_casing", ""))};
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        AntimatterBlockModelBuilder builder = buildBlock(block,prov);
        if (builder != null) {
            prov.state(block, builder);
        } else {
            super.onBlockModelBuild(block, prov);
        }
    }
    //Hierarchial block builder.
    protected AntimatterBlockModelBuilder buildBlock(Block block, AntimatterBlockStateProvider prov) {
        return null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return this == GT4RData.FIRE_BRICKS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.SLAVE_CONTROLLER_TYPE.create();
    }
}