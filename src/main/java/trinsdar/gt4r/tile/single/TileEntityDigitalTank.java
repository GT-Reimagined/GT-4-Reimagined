package trinsdar.gt4r.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityTank;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import trinsdar.gt4r.data.GT4RData;
import trinsdar.gt4r.data.SlotTypes;

import javax.annotation.Nullable;

public class TileEntityDigitalTank extends TileEntityTank<TileEntityDigitalTank> {
    public TileEntityDigitalTank(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new MachineFluidHandler<TileEntityDigitalTank>(this, 256000, 251000){
            @Nullable
            @Override
            public FluidTanks getOutputTanks() {
                return super.getInputTanks();
            }

            @Override
            protected FluidTank getTank(int tank) {
                return getInputTanks().getTank(tank);
            }

            @Override
            public FluidTanks getTanks(int tank) {
                return getInputTanks();
            }
        });
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            final int[] data = ((GuiEvents.GuiEvent)event).data;
            if (data[1] == 0){
                fluidHandler.ifPresent(f -> {
                    ItemStack orb = itemHandler.map(i -> i.getHandler(SlotTypes.DATA).getStackInSlot(0)).orElse(ItemStack.EMPTY);
                    if (orb.getItem() == GT4RData.StorageDataOrb){
                        CompoundTag tag = orb.getTag();
                        if (tag != null && tag.contains("Data")) {
                            CompoundTag dataTag = tag.getCompound("Data");
                            if (dataTag.contains("Fluid")) {
                                CompoundTag nbt = dataTag.getCompound("Fluid");
                                FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(nbt);
                                int fill = f.fill(fluidStack, IFluidHandler.FluidAction.SIMULATE);
                                if (fill != fluidStack.getAmount()) {
                                    playerEntity.sendMessage(new TranslatableComponent("message.gt4r.digital_tank_inventory"), playerEntity.getUUID());
                                    return;
                                }
                                ItemStack newStack = new ItemStack(GT4RData.CircuitDataOrb);


                                f.fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);
                                itemHandler.ifPresent(i -> i.getHandler(SlotTypes.DATA).setStackInSlot(0, newStack));
                            }

                        }
                    }
                });
            } else if (data[1] == 1){
                fluidHandler.ifPresent(f -> {
                    ItemStack orb = itemHandler.map(i -> i.getHandler(SlotTypes.DATA).getStackInSlot(0)).orElse(ItemStack.EMPTY);
                    if (orb.getItem() == GT4RData.CircuitDataOrb){
                        if (f.getInputTanks().getTank(0).getFluidAmount() > 0){
                            ItemStack newStack = new ItemStack(GT4RData.StorageDataOrb);
                            CompoundTag nbt = f.getInputTanks().getTank(0).getFluid().writeToNBT(new CompoundTag());
                            CompoundTag dataTag = new CompoundTag();
                            dataTag.put("Fluid", nbt);
                            newStack.getOrCreateTag().put("Data", dataTag);
                            f.drainInput(f.getInputTanks().getTank(0).getFluidAmount(), IFluidHandler.FluidAction.EXECUTE);
                            itemHandler.ifPresent(i -> i.getHandler(SlotTypes.DATA).setStackInSlot(0, newStack));
                        }
                    }
                });
            }
        }
    }
}
