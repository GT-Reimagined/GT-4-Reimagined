package trinsdar.gt4r.block;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.dynamic.BlockDynamic;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import trinsdar.gt4r.data.GT4RData;

public class BlockCasing extends BlockDynamic {

    public BlockCasing(String domain, String id, Block.Properties properties) {
        super(domain, id, properties);
        if (this.getClass() != BlockCasing.class) AntimatterAPI.register(BlockCasing.class, this);
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

    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this == GT4RData.REINFORCED_GLASS ? 1.0F : super.getAmbientOcclusionLightValue(state, worldIn, pos);
    }

   /* public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }*/

    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
        return (this == GT4RData.REINFORCED_GLASS && adjacentBlockState.matchesBlock(this)) || super.isSideInvisible(state, adjacentBlockState, side);
    }

    @Override
    public void onItemModelBuild(IItemProvider item, AntimatterItemModelProvider prov) {
        prov.modelAndTexture(item, AntimatterBlockModelBuilder.getSimple()).tex(t -> t.putAll(AntimatterBlockModelBuilder.buildTextures(((ITextureProvider) item).getTextures())));
    }
}