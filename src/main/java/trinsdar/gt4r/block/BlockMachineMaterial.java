package trinsdar.gt4r.block;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import trinsdar.gt4r.machine.MaterialMachine;

import javax.annotation.Nullable;

import static muramasa.antimatter.Data.NULL;

public class BlockMachineMaterial extends BlockMachine {
    Material material = NULL;
    public BlockMachineMaterial(Machine<?> type, Tier tier) {
        super(type, tier);
        if (type instanceof MaterialMachine){
            this.material = ((MaterialMachine)type).getMaterial();
        }
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable IBlockReader world, @Nullable BlockPos pos, int i) {
        return i == 0 && material != NULL ? material.getRGB() : -1;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return material.getRGB();
    }
}
