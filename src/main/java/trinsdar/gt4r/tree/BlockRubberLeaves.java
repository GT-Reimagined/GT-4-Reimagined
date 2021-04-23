package trinsdar.gt4r.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockRubberLeaves extends LeavesBlock implements IAntimatterObject, IModelProvider, ITextureProvider {

    protected String domain, id;

    public BlockRubberLeaves(String domain, String id) {
        super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setAllowsSpawn(BlockRubberLeaves::allowsSpawnOnLeaves).setSuffocates(BlockRubberLeaves::isntSolid).setBlocksVision(BlockRubberLeaves::isntSolid));
        this.domain = domain;
        this.id = id;
        AntimatterAPI.register(BlockRubberLeaves.class, id, this);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.get(BlockStateProperties.WATERLOGGED) ? 0 : 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.get(BlockStateProperties.WATERLOGGED) ? 0 : 30;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/tree/" + getId())};
    }

    private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    private static Boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }
}
