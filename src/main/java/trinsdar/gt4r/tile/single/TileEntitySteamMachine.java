package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraftforge.common.util.LazyOptional;
import trinsdar.gt4r.data.Materials;

import java.util.Arrays;

import static muramasa.antimatter.machine.Tier.BRONZE;

public class TileEntitySteamMachine extends TileEntityMachine {

    public TileEntitySteamMachine(Machine<?> type) {
        super(type);
        recipeHandler = LazyOptional.of(() -> new MachineRecipeHandler<TileEntitySteamMachine>(this) {
            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return tile.fluidHandler.map(t -> t.consumeAndReturnInputs(Arrays.asList(Materials.Steam.getGas((int)activeRecipe.getPower())), simulate).size() == 0)
                        .orElse(false);
            }
            //Allow up to 16 .
            @Override
            protected boolean validateRecipe(Recipe r) {
                return r.getPower() <= Tier.LV.getVoltage()/2;
            }

            @Override
            protected boolean canRecipeContinue() {
                try {
                    return super.canRecipeContinue() && world.isAirBlock(tile.pos.offset(getOutputFacing()));
                } catch (NullPointerException e){
                    e.printStackTrace();
                    return super.canRecipeContinue();
                }
            }

            @Override
            protected int getOverclock() {
                return tile.getMachineTier() == BRONZE ? 0 : 1;
            }
        });
    }

    @Override
    public Tier getPowerLevel() {
        return Tier.LV;
    }
}
