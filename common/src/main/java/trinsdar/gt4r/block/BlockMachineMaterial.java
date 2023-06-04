package trinsdar.gt4r.block;

import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.gt4r.machine.MaterialMachine;
import trinsdar.gt4r.tile.single.TileEntityDrum;

import javax.annotation.Nullable;
import java.util.List;

import static muramasa.antimatter.material.Material.NULL;
import static muramasa.antimatter.Data.WRENCH_MATERIAL;

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
}
