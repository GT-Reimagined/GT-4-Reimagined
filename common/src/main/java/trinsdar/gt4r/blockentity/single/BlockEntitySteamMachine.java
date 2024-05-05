package trinsdar.gt4r.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.machine.Tier.BRONZE;
import static trinsdar.gt4r.data.Machines.STEAM_FORGE_HAMMER;

public class BlockEntitySteamMachine extends BlockEntityMachine<BlockEntitySteamMachine> {

    public static final TagKey<Fluid> STEAM = TagUtils.getForgelikeFluidTag("steam");

    public BlockEntitySteamMachine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new SteamMachineRecipeHandler(this));
    }

    @Override
    public Tier getPowerLevel() {
        return Tier.LV;
    }

    public static class SteamMachineRecipeHandler extends MachineRecipeHandler<BlockEntitySteamMachine>{
        protected boolean isSteamClear = false;

        public SteamMachineRecipeHandler(BlockEntitySteamMachine tile) {
            super(tile);
        }

        @Override
        public boolean consumeResourceForRecipe(boolean simulate) {
            return tile.fluidHandler.map(t -> t.consumeTaggedInput(STEAM, getPower() * TesseractGraphWrappers.dropletMultiplier, simulate).getFluidAmount() > 0)
                    .orElse(false);
        }
        //Allow up to 16 .
        @Override
        protected boolean validateRecipe(IRecipe r) {
            return r.getPower() <= Tier.LV.getVoltage();
        }

        public void setSteamClear(boolean steamClear) {
            isSteamClear = steamClear;
        }

        @Override
        protected boolean canRecipeContinue() {
            isSteamClear = tile.level.isEmptyBlock(tile.worldPosition.relative(tile.getOutputFacing()));
            return super.canRecipeContinue() && isSteamClear;
        }

        @Override
        public long getPower() {
            if (activeRecipe == null) return 0;
            if (overclock == 0 || tile.has(MachineFlag.RF)) return activeRecipe.getPower();
            return (activeRecipe.getPower() * (1L << overclock));
        }

        @Override
        public float getClientProgress() {
            if (tile.getMachineType() == STEAM_FORGE_HAMMER){
                float percent = (float) currentProgress / ((float) maxProgress / 3);
                if (percent > 2){
                    percent -= 2;
                } else if (percent > 1){
                    percent -=1;
                }
                return percent;
            }
            return super.getClientProgress();
        }

        @Override
        public int getOverclock() {
            return tile.getMachineTier() == BRONZE ? 0 : 1;
        }

        @Override
        public boolean accepts(FluidHolder stack) {
            return super.accepts(stack) || stack.getFluid().builtInRegistryHolder().is(STEAM);
        }

        @Override
        protected boolean consumeGeneratorResources(boolean simulate) {
            return isSteamClear && super.consumeGeneratorResources(simulate);
        }
    }
}
