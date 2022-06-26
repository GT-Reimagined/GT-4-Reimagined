package trinsdar.gt4r.block;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.gt4r.machine.MaterialMachine;
import trinsdar.gt4r.tile.single.TileEntityDrum;

import javax.annotation.Nullable;
import java.util.List;

import static muramasa.antimatter.Data.NULL;
import static muramasa.antimatter.Data.WRENCH_MATERIAL;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlockMachineMaterial extends BlockMachine implements IColorHandler {
    Material material = NULL;
    public BlockMachineMaterial(Machine<?> type, Tier tier) {
        this(type, tier, Properties.of(WRENCH_MATERIAL).strength(1.0f, 10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops());
    }

    public BlockMachineMaterial(Machine<?> type, Tier tier, Properties properties) {
        super(type, tier, properties);
        if (type instanceof MaterialMachine){
            this.material = ((MaterialMachine)type).getMaterial();
        }
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable BlockGetter world, @Nullable BlockPos pos, int i) {
        return i == 0 ? material.getRGB() : -1;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return i == 0 ? material.getRGB() : -1;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = super.getDrops(state, builder);
        BlockEntity tileentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (!list.isEmpty() && tileentity instanceof TileEntityDrum){
            ItemStack stack = list.get(0);
            TileEntityDrum drum = (TileEntityDrum) tileentity;
            if (!drum.getDrop().isEmpty()){
                CompoundTag nbt = stack.getOrCreateTag();
                nbt.put("Fluid", drum.getDrop().writeToNBT(new CompoundTag()));
            }
            if (drum.isOutput()){
                CompoundTag nbt = stack.getOrCreateTag();
                nbt.putBoolean("Outputs", drum.isOutput());
            }
        }
        return list;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        CompoundTag nbt = stack.getTag();
        if (nbt != null && (nbt.contains("Fluid") || nbt.contains("Outputs"))){
            BlockEntity tileEntity = world.getBlockEntity(pos);
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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (this.getId().contains("drum")){
            CompoundTag nbt = stack.getTag();
            if (nbt != null && (nbt.contains("Fluid") || nbt.contains("Outputs"))){
                FluidStack fluid = nbt.contains("Fluid") ? FluidStack.loadFluidStackFromNBT(nbt.getCompound("Fluid")) : FluidStack.EMPTY;
                if (fluid != null && !fluid.isEmpty()){
                    tooltip.add(new TranslatableComponent("machine.drum.fluid", fluid.getAmount(), fluid.getDisplayName()));
                }
                if (nbt.contains("Outputs")){
                    tooltip.add(new TranslatableComponent("machine.drum.output"));
                }
            }
        }
    }
}
