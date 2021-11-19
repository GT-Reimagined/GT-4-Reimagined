package trinsdar.gt4r.block;

import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.gt4r.machine.MaterialMachine;
import trinsdar.gt4r.tile.single.TileEntityDrum;

import javax.annotation.Nullable;
import java.util.List;

import static muramasa.antimatter.Data.NULL;

public class BlockMachineMaterial extends BlockMachine implements IColorHandler {
    Material material = NULL;
    public BlockMachineMaterial(Machine<?> type, Tier tier) {
        super(type, tier);
        if (type instanceof MaterialMachine){
            this.material = ((MaterialMachine)type).getMaterial();
        }
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable IBlockReader world, @Nullable BlockPos pos, int i) {
        return i == 0 ? material.getRGB() : -1;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return i == 0 ? material.getRGB() : -1;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = super.getDrops(state, builder);
        TileEntity tileentity = builder.getOptionalParameter(LootParameters.BLOCK_ENTITY);
        if (!list.isEmpty() && tileentity instanceof TileEntityDrum){
            ItemStack stack = list.get(0);
            TileEntityDrum drum = (TileEntityDrum) tileentity;
            if (!drum.getDrop().isEmpty()){
                CompoundNBT nbt = stack.getOrCreateTag();
                nbt.put("Fluid", drum.getDrop().writeToNBT(new CompoundNBT()));
            }
            if (drum.isOutput()){
                CompoundNBT nbt = stack.getOrCreateTag();
                nbt.putBoolean("Outputs", drum.isOutput());
            }
        }
        return list;
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && (nbt.contains("Fluid") || nbt.contains("Outputs"))){
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof TileEntityDrum){
                TileEntityDrum drum = (TileEntityDrum) tileEntity;
                drum.fluidHandler.ifPresent(f -> {
                    FluidStack fluid = nbt.contains("Fluid") ? FluidStack.loadFluidStackFromNBT(nbt.getCompound("Fluid")) : FluidStack.EMPTY;
                    if (fluid != null && !fluid.isEmpty()){
                        f.fill(fluid, IFluidHandler.FluidAction.EXECUTE);
                    }
                    if (nbt.contains("Outputs")){
                        ((TileEntityDrum.DrumFluidHandler)f).setOutput(nbt.getBoolean("Outputs"));
                    }
                });
            }

        }
    }
}
