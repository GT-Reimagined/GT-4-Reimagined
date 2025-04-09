package org.gtreimagined.gt4r.blockentity.single;

import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.capability.machine.MachineFluidHandler;
import org.gtreimagined.gtlib.capability.machine.MachineRecipeHandler;
import org.gtreimagined.gtlib.machine.MachineFlag;
import org.gtreimagined.gtlib.machine.MachineState;
import org.gtreimagined.gtlib.machine.Tier;
import org.gtreimagined.gtlib.machine.types.Machine;
import org.gtreimagined.gtlib.recipe.IRecipe;
import org.gtreimagined.gtlib.tool.GTToolType;
import org.gtreimagined.gtlib.util.TagUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.data.GT4RItems;

import static org.gtreimagined.gtlib.machine.Tier.BRONZE;
import static org.gtreimagined.gt4r.data.Machines.STEAM_FORGE_HAMMER;

public class BlockEntitySteamMachine extends BlockEntityMachine<BlockEntitySteamMachine> {

    public static final TagKey<Fluid> STEAM = TagUtils.getForgelikeFluidTag("steam");

    public BlockEntitySteamMachine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        fluidHandler.set(() -> new MachineFluidHandler<>(this, 64000));
        recipeHandler.set(() -> new SteamMachineRecipeHandler(this));
    }

    @Override
    public Tier getPowerLevel() {
        return Tier.LV;
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable GTToolType type) {
        if (player.getItemInHand(hand).getItem() == GT4RItems.SteelUpgrade){
            CompoundTag nbt = new CompoundTag();
            this.saveAdditional(nbt);
            world.setBlock(pos, this.getMachineType().getBlockState(Tier.STEEL).defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, this.getFacing()), 3);
            world.getBlockEntity(pos).load(nbt);
            if (!player.isCreative()){
                player.getItemInHand(hand).shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }

    public static class SteamMachineRecipeHandler extends MachineRecipeHandler<BlockEntitySteamMachine>{
        protected boolean isSteamClear = false;
        protected boolean firstBlockedRun = false;

        public SteamMachineRecipeHandler(BlockEntitySteamMachine tile) {
            super(tile);
        }

        @Override
        public boolean consumeResourceForRecipe(boolean simulate) {
            return tile.fluidHandler.map(t -> t.consumeTaggedInput(STEAM, (int) getPower(), simulate).getAmount() > 0)
                    .orElse(false);
        }
        //Allow up to 16 .
        @Override
        protected boolean validateRecipe(IRecipe r) {
            return r.getPower() <= Tier.LV.getVoltage();
        }

        public void setSteamClear(boolean steamClear) {
            isSteamClear = steamClear;
            if (steamClear){
                firstBlockedRun = false;
                checkRecipe();
            }
        }

        @Override
        protected boolean canRecipeContinue() {
            isSteamClear = tile.level.isEmptyBlock(tile.worldPosition.relative(tile.getOutputFacing()));
            return super.canRecipeContinue() && (isSteamClear || !firstBlockedRun);
        }

        @Override
        protected MachineState recipeFinish() {
            if (!firstBlockedRun) firstBlockedRun = true;
            return super.recipeFinish();
        }

        @Override
        public long getPower() {
            if (activeRecipe == null) return 0;
            if (overclock == 0 || tile.has(MachineFlag.FE)) return activeRecipe.getPower();
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
        public boolean accepts(FluidStack stack) {
            return super.accepts(stack) || stack.getFluid().builtInRegistryHolder().is(STEAM);
        }

        @Override
        protected boolean consumeGeneratorResources(boolean simulate) {
            return isSteamClear && super.consumeGeneratorResources(simulate);
        }

        @Override
        public CompoundTag serialize() {
            CompoundTag tag = super.serialize();
            tag.putBoolean("firstBlockedRun", firstBlockedRun);
            return tag;
        }

        @Override
        public void deserialize(CompoundTag nbt) {
            super.deserialize(nbt);
            firstBlockedRun = nbt.getBoolean("firstBlockedRun");
        }
    }
}
