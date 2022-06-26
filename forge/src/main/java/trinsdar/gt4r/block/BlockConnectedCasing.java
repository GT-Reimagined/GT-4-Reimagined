package trinsdar.gt4r.block;

import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;

import static muramasa.antimatter.client.AntimatterModelManager.LOADER_DYNAMIC;

public class BlockConnectedCasing extends BlockCasingMachine {

    private final static String SIMPLE = "antimatter:block/preset/simple";

    @Override
    protected String getTextureID() {
        return "casing";
    }

    public BlockConnectedCasing(String domain, String id) {
        super(domain, id);
    }

    @Override
    public boolean canConnect(BlockGetter world, BlockState state, @Nullable BlockEntity tile, BlockPos pos) {
        return state.getBlock() == this;
    }

    //Default code for connected textures.
    @Override
    public AntimatterBlockModelBuilder buildBlock(Block block, AntimatterBlockStateProvider prov) {
        AntimatterBlockModelBuilder builder = prov.getBuilder(block);
        Texture[] texes = ((ITextureProvider)block).getTextures();
        builder.particle(texes[0]);
        builder.config(0, (b, l) -> l.add(b.of(SIMPLE).tex(texes)));
        return builder.loader(LOADER_DYNAMIC).basicConfig(block, getConnectedTextures());
    }
}
