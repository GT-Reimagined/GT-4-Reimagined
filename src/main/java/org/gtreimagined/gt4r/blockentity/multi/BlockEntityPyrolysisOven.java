package org.gtreimagined.gt4r.blockentity.multi;

import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt4r.data.GT4RItems;
import org.gtreimagined.gt4r.machine.UpgradeableMachineRecipeHandler;

public class BlockEntityPyrolysisOven extends BlockEntityUpgradeableBasicMultiblock<BlockEntityPyrolysisOven> {

    public BlockEntityPyrolysisOven(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new PyrolysisRecipeHandler(this));
    }

    @Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        if (event instanceof BlockEntityIndustrialBlastFurnace.BFEvent){
            recipeHandler.ifPresent(r -> {
                PyrolysisRecipeHandler r2 = (PyrolysisRecipeHandler) r;
                r2.heatingCapacity = 0;
                ItemStack stack = (ItemStack) data[0];
                if (!stack.isEmpty()){
                    if (stack.getItem() == GT4RItems.CupronickelHeatingCoil){
                        r2.heatingCapacity += (100 * stack.getCount());
                    } else if (stack.getItem() == GT4RItems.KanthalHeatingCoil) {
                        r2.heatingCapacity += (200 * stack.getCount());
                    } else {
                        r2.heatingCapacity += (300 * stack.getCount());
                    }
                }
            });

        }
        super.onMachineEvent(event, data);
    }
    //TODO
/*
    @Override
    public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        // TODO: Replace by new TranslationTextComponent()
        this.recipeHandler.ifPresent(r -> {
            renderer.drawString(stack,"Heat: " + ((PyrolysisRecipeHandler)r).heatingCapacity + "K", 27, 62, Color.BLACK.getRGB());
        });
    }
*/
    public static class PyrolysisRecipeHandler extends UpgradeableMachineRecipeHandler<BlockEntityPyrolysisOven> {
        private int heatingCapacity;
        public PyrolysisRecipeHandler(BlockEntityPyrolysisOven tile) {
            super(tile);
        }

        @Override
        protected void activateRecipe(boolean reset) {
            //if (canOverclock)
            consumedResources = false;
            maxProgress = activeRecipe.getDuration();
            if (!generator){
                overclock = getOverclock();
                maxProgress = Math.max(1, maxProgress >>= overclock);
                float newProgress = maxProgress * ((float)heatingCapacity / 800);
                maxProgress *= Math.max(1, Math.round(newProgress));
            }
            tickTimer = 0;
            if (reset) {
                currentProgress = 0;
            }
            lastRecipe = activeRecipe;
        }

        @Override
        public CompoundTag serialize() {
            CompoundTag nbt = super.serialize();
            nbt.putInt("H", heatingCapacity);
            return nbt;
        }

        @Override
        public void deserialize(CompoundTag nbt) {
            super.deserialize(nbt);
            this.heatingCapacity = nbt.getInt("H");
        }
    }
}
