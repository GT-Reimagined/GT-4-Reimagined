package org.gtreimagined.gt4r.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gtcore.blockentity.BlockEntitySteamMachine;
import org.gtreimagined.gtlib.item.ItemBasic;
import org.gtreimagined.gtlib.machine.Tier;

import static org.gtreimagined.gtlib.machine.Tier.BRONZE;
import static org.gtreimagined.gtlib.machine.Tier.STEEL;

public class ItemSteelUpgrade extends ItemBasic<ItemSteelUpgrade> {
    public ItemSteelUpgrade() {
        super(GT4RRef.ID, "steel_upgrade");
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        if (context.getLevel().getBlockEntity(context.getClickedPos()) instanceof BlockEntitySteamMachine steamMachine) {
            if (steamMachine.getMachineTier() == BRONZE && steamMachine.getMachineType().getDomain().equals(GT4RRef.ID)){
                if (steamMachine.getMachineType().getTiers().contains(STEEL)){
                    CompoundTag nbt = new CompoundTag();
                    steamMachine.saveAdditional(nbt);
                    context.getLevel().setBlock(context.getClickedPos(), steamMachine.getMachineType().getBlockState(Tier.STEEL).defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, steamMachine.getFacing()), 3);
                    context.getLevel().getBlockEntity(context.getClickedPos()).load(nbt);
                    if (!context.getPlayer().isCreative()){
                        context.getItemInHand().shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.onItemUseFirst(stack, context);
    }
}
