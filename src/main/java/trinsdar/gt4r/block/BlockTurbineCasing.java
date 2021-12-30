package trinsdar.gt4r.block;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.dynamic.ModelConfig;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.int3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.tile.multi.TileEntityLargeTurbine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static muramasa.antimatter.client.AntimatterModelManager.LOADER_DYNAMIC;

public class BlockTurbineCasing extends BlockConnectedCasing {

    public BlockTurbineCasing(String domain, String id) {
        super(domain, id);
    }

    @Override
    public ModelConfig getConfig(BlockState state, IBlockReader world, BlockPos.Mutable mut, BlockPos pos) {
        int[] conf = super.getConfig(state, world, mut, pos).getConfig();
        int[] ct = new int[conf.length+1];
        int3 mutable = new int3();
        mutable.set(pos);
        TileEntityLargeTurbine turbine = getTurbine(world, pos);
        if (turbine != null) {
            BlockPos controllerPos = turbine.getBlockPos();
            Vector3i vec = new Vector3i((pos.getX() - controllerPos.getX()), (pos.getY() - controllerPos.getY()), (pos.getZ() - controllerPos.getZ()));
            int c = getOffset(vec, turbine.getFacing()) + 10000;
            c += (turbine.getMachineState() == MachineState.ACTIVE ? 100000 : 0);
            ct[1] = c;
            ct[0] = conf[0];
            return config.set(ct);
        } else {
            return config.set(conf);
        }
    }

    protected Texture[] turbineTextures() {
        Texture[] tex = new Texture[9];
        String gas = "gas";
        if (this == GT4RData.STANDARD_MACHINE_CASING){
            gas = "steam";
        }
        for (int i = 0; i <= 8; i++) {
            tex[i] = new Texture(domain,"block/ct/turbine/large_" + gas + "_turbine_active_"+i);
        }
        return tex;
    }

    protected Texture[] turbineTexturesInactive() {
        Texture[] tex = new Texture[9];
        String gas = "gas";
        if (this == GT4RData.STANDARD_MACHINE_CASING){
            gas = "steam";
        }
        for (int i = 0; i <= 8; i++) {
            tex[i] = new Texture(domain,"block/ct/turbine/large_" + gas + "_turbine_"+i);
        }
        return tex;
    }


    protected int getOffset(Vector3i vec) {
        return vec.getX() + vec.getY()*10 + vec.getZ()*100;
    }
    
    //essentially a facing transformation
    protected int getOffset(Vector3i vec, Direction dir) {
        return getOffset(vec) + (dir.get2DDataValue() + 1)*1000;
    }

    private final static String SIDED = "antimatter:block/preset/simple_overlay";

    private final Vector3i[] VECS = new Vector3i[]{
            new Vector3i(1,-1,0),
            new Vector3i(0,-1,0),
            new Vector3i(-1,-1,0),
            new Vector3i(1,0,0),
            new Vector3i(0,0,0),
            new Vector3i(-1,0,0),
            new Vector3i(1,1,0),
            new Vector3i(0,1,0),
            new Vector3i(-1,1,0),
    };

    private final void forSides(Direction dir, BiConsumer<int3, Integer> consumer) {
        int3 pos = new int3(dir);
        int i = 0;
        pos.set(0, 0, 0);
        pos = pos.above(1);
        pos = pos.right(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
        pos = pos.right(2);
        pos = pos.below(1);
        consumer.accept(pos,i++);
        i++;
        pos = pos.left(2);
        consumer.accept(pos,i++);
        pos = pos.right(2);
        pos = pos.below(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
    }

    @Override
    public AntimatterBlockModelBuilder buildBlock(Block block, AntimatterBlockStateProvider prov) {
        Texture[] tex = turbineTextures();
        Texture[] inactive = turbineTexturesInactive();
        AntimatterBlockModelBuilder builder = super.buildBlock(block, prov);
        for (Direction dir : dirs) {
            forSides(dir, (b,c) -> {
                int offset = getOffset(b, dir);
                int offsetX1000 = offset * 10000;
                int offsetPlus10000 = offsetX1000 + 100000;
                //Antimatter.LOGGER.info(this.getId() + " Offset 1: " + offset + " Offset 2: " + offsetX1000 + " Offset 3: " + offsetPlus10000);
                builder.config(offsetX1000, SIDED, a ->
                        a.tex(t -> t.put("base", inactive[c])).rot(dir));
                builder.config(offsetPlus10000, SIDED, a ->
                        a.tex(t -> t.put("base", tex[c])).rot(dir));
            });
        }
        builder.loader(LOADER_DYNAMIC);
        return builder;
    }
    //just all horizontal dirs
    private static final List<Direction> dirs = Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    //cache.
    private static final Long2ObjectMap<TileEntityLargeTurbine> MAP = new Long2ObjectOpenHashMap<>();

    private TileEntityLargeTurbine checkTurbine(IBlockReader reader, BlockPos pos) {
        TileEntity tile = reader.getBlockEntity(pos);
        return tile instanceof TileEntityLargeTurbine && ((TileEntityLargeTurbine)tile).getCasing() == this ? (TileEntityLargeTurbine) tile : null;
    }

    protected TileEntityLargeTurbine getTurbine(IBlockReader world, BlockPos pos) {
        int3 mutable = new int3();
        mutable.set(pos);
        return MAP.compute(pos.asLong(), (a, b) -> {
            if (b != null && !(b.isRemoved())) return b;
            TileEntityLargeTurbine t;
            for (Direction d : dirs) {
                mutable.set(pos);
                mutable.relative(d,1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d,-1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(Direction.UP,1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(Direction.UP,-1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d,1);
                mutable.relative(Direction.UP,-1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d);
                mutable.relative(Direction.UP,1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d,-1);
                mutable.relative(Direction.UP,1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d,-1);
                mutable.relative(Direction.UP,-1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

            }
            return null;
        });
    }
}
