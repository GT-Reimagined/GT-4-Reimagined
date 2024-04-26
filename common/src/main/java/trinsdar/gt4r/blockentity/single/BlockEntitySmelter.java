package trinsdar.gt4r.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.gt4r.data.RecipeMaps;
import trinsdar.gt4r.blockentity.multi.BlockEntityIndustrialBlastFurnace;

public class BlockEntitySmelter extends BlockEntityMachine<BlockEntitySmelter> {
    public BlockEntitySmelter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this) {
            int heat = 0, maxHeat = 750;

            @Override
            public void onMachineEvent(IMachineEvent event, Object... data) {
                super.onMachineEvent(event, data);
                if (event instanceof BlockEntityIndustrialBlastFurnace.BFEvent) {
                    maxHeat = 750;
                    ItemStack stack = (ItemStack) data[0];
                    if (!stack.isEmpty()) {
                        IRecipe coilRecipe = RecipeMaps.SMELTER_COILS.find(new ItemStack[]{stack}, new FluidHolder[]{}, tile.tier, r -> r.getSpecialValue() > 0);
                        if (coilRecipe != null) {
                            maxHeat += (coilRecipe.getSpecialValue() * stack.getCount());
                        }
                    }
                }
            }

            @Override
            protected boolean canRecipeContinue() {
                return super.canRecipeContinue() && this.heat >= activeRecipe.getSpecialValue();
            }
        });
    }
}
