package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import trinsdar.gt4r.data.GT4RData;

public class TileEntityPyrolysisOven extends TileEntityBasicMultiMachine<TileEntityPyrolysisOven> {

    public TileEntityPyrolysisOven(Machine<?> type) {
        super(type);
        recipeHandler.set(() -> new PyrolysisRecipeHandler(this));
    }

    @Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        if (event instanceof TileEntityIndustrialBlastFurnace.BFEvent){
            recipeHandler.ifPresent(r -> {
                PyrolysisRecipeHandler r2 = (PyrolysisRecipeHandler) r;
                r2.heatingCapacity = 0;
                ItemStack stack = (ItemStack) data[0];
                if (!stack.isEmpty()){
                    if (stack.getItem() == GT4RData.CupronickelHeatingCoil){
                        r2.heatingCapacity += (100 * stack.getCount());
                    } else if (stack.getItem() == GT4RData.KanthalHeatingCoil) {
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
    public static class PyrolysisRecipeHandler extends MachineRecipeHandler<TileEntityPyrolysisOven> {
        private int heatingCapacity;
        public PyrolysisRecipeHandler(TileEntityPyrolysisOven tile) {
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
        public CompoundTag serializeNBT() {
            CompoundTag nbt = super.serializeNBT();
            nbt.putInt("H", heatingCapacity);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            super.deserializeNBT(nbt);
            this.heatingCapacity = nbt.getInt("H");
        }
    }
}
