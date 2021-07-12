package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.RecipeMaps;
import trinsdar.gt4r.tile.multi.TileEntityIndustrialBlastFurnace;

public class TileEntityFluidExtractor extends TileEntityMachine<TileEntityFluidExtractor> {
    public TileEntityFluidExtractor(Machine<?> type) {
        super(type);
        this.recipeHandler.set(() -> new MachineRecipeHandler<TileEntityFluidExtractor>(this){
            int heat = 0, maxHeat = 750;
            @Override
            public void onMachineEvent(IMachineEvent event, Object... data) {
                super.onMachineEvent(event, data);
                if (event instanceof TileEntityIndustrialBlastFurnace.BFEvent){
                    maxHeat = 750;
                    ItemStack stack = (ItemStack) data[0];
                    if (!stack.isEmpty()){
                        Recipe coilRecipe = RecipeMaps.FLUID_EXTRACTOR_COILS.find(new ItemStack[]{stack}, new FluidStack[]{}, r -> r.getSpecialValue() > 0);
                        if (coilRecipe != null){
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
