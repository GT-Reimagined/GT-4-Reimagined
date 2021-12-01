package trinsdar.gt4r.tile.single;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.EnergyHandler;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import tesseract.api.capability.TesseractGTCapability;
import tesseract.api.gt.GTTransaction;

import java.util.List;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;

public class TileEntityInfiniteStorage<T extends TileEntityInfiniteStorage<T>> extends TileEntityMachine<T> implements IInfoRenderer<TileEntityInfiniteStorage.InfiniteStorageWidget> {

    public TileEntityInfiniteStorage(Machine<?> type) {
        super(type);
        energyHandler.set(() -> new MachineEnergyHandler<T>((T)this, Long.MAX_VALUE, Long.MAX_VALUE, 0, 32, 0, 4) {
            @Override
            public GTTransaction extract(GTTransaction.Mode mode) {
                return new GTTransaction(availableAmpsOutput(), this.getOutputVoltage(), this::extractEnergy);
            }

            @Override
            public boolean extractEnergy(GTTransaction.TransferData data) {
                getState().receive(false, data.getAmps(false));
                return true;
            }

            @Override
            public boolean canOutput(Direction direction) {
                return tile.getFacing() == direction;
            }
        });

    }

    @Override
    public void onGuiEvent(IGuiEvent event, PlayerEntity playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON) {
            final int[] data = ((GuiEvents.GuiEvent)event).data;
            energyHandler.ifPresent(h -> {
                int voltage = (int) h.getOutputVoltage();
                int amperage = (int) h.getOutputAmperage();
                boolean shiftHold = data[1] != 0;
                switch (data[0]) {
                    case 0:
                        voltage /= shiftHold ? 512 : 64;
                        break;
                    case 1:
                        voltage -= shiftHold ? 512 : 64;
                        break;
                    case 2:
                        amperage /= shiftHold ? 512 : 64;
                        break;
                    case 3:
                        amperage -= shiftHold ? 512 : 64;
                        break;
                    case 4:
                        voltage /= shiftHold ? 16 : 2;
                        break;
                    case 5:
                        voltage -= shiftHold ? 16 : 1;
                        break;
                    case 6:
                        amperage /= shiftHold ? 16 : 2;
                        break;
                    case 7:
                        amperage -= shiftHold ? 16 : 1;
                        break;
                    case 8:
                        voltage += shiftHold ? 512 : 64;
                        break;
                    case 9:
                        voltage *= shiftHold ? 512 : 64;
                        break;
                    case 10:
                        amperage += shiftHold ? 512 : 64;
                        break;
                    case 11:
                        amperage *= shiftHold ? 512 : 64;
                        break;
                    case 12:
                        voltage += shiftHold ? 16 : 1;
                        break;
                    case 13:
                        voltage *= shiftHold ? 16 : 2;
                        break;
                    case 14:
                        amperage += shiftHold ? 16 : 1;
                        break;
                    case 15:
                        amperage *= shiftHold ? 16 : 2;
                        break;
                }

                if (voltage < 0){
                    voltage = 0;
                }
                if (amperage < 0){
                    amperage = 0;
                }

                h.setOutputVoltage(voltage);
                h.setOutputAmperage(amperage);

                this.refreshCap(TesseractGTCapability.ENERGY_HANDLER_CAPABILITY);
            });
        }
    }

    @Override
    public List<String> getInfo() {
        List<String> info = super.getInfo();
        energyHandler.ifPresent(h -> {
            info.add("Voltage Out: " + h.getOutputVoltage());
            info.add("Amperage Out: " + h.getOutputAmperage());
        });
        return info;
    }
    //TODO
/*
    @Override
    public void drawInfo(MatrixStack stack, FontRenderer renderer, int left, int top) {
        // TODO: Replace by new TranslationTextComponent()
        energyHandler.ifPresent(h -> {
            renderer.drawString(stack,"Control Panel", left + 43, top + 21, 16448255);
            renderer.drawString(stack,"VOLT: " + h.getOutputVoltage(), left + 43, top + 40, 16448255);
            renderer.drawString(stack,"TIER: " + Tier.getTier(h.getOutputVoltage() < 0 ? -h.getOutputVoltage() : h.getOutputVoltage()).getId().toUpperCase(), left + 43, top + 48, 16448255);
            renderer.drawString(stack,"AMP: " + h.getOutputAmperage(), left + 43, top + 56, 16448255);
            renderer.drawString(stack,"SUM: " + ((long) h.getOutputAmperage() * h.getOutputVoltage()), left + 43, top + 64, 16448255);
        });

    }*/

    @Override
    public int drawInfo(InfiniteStorageWidget widget, MatrixStack stack, FontRenderer renderer, int left, int top) {
        renderer.draw(stack,"Control Panel", left + 43, top + 21, 16448255);
        renderer.draw(stack,"VOLT: " + widget.voltage, left + 43, top + 40, 16448255);
        renderer.draw(stack,"TIER: " + Tier.getTier(widget.voltage < 0 ? -widget.voltage : widget.voltage).getId().toUpperCase(), left + 43, top + 48, 16448255);
        renderer.draw(stack,"AMP: " + widget.amperage, left + 43, top + 56, 16448255);
        renderer.draw(stack,"SUM: " + (long)(widget.amperage * widget.voltage), left + 43, top + 64, 16448255);
        return 72;
    }

    @Override
    public void addWidgets(GuiInstance instance, IGuiElement parent) {
        super.addWidgets(instance, parent);
        instance.addWidget(InfiniteStorageWidget.build());
    }

    public static class InfiniteStorageWidget extends InfoRenderWidget<InfiniteStorageWidget> {
        public int amperage = 0, voltage = 0;
        protected InfiniteStorageWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<InfiniteStorageWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            TileEntityInfiniteStorage<?> m = (TileEntityInfiniteStorage<?>) gui.handler;
            gui.syncInt(() -> Math.toIntExact(m.energyHandler.map(EnergyHandler::getOutputAmperage).orElse(0L)), i -> amperage = i, SERVER_TO_CLIENT);
            gui.syncInt(() -> Math.toIntExact(m.energyHandler.map(EnergyHandler::getOutputVoltage).orElse(0L)), i -> voltage = i, SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a,b) -> new InfiniteStorageWidget(a,b, (IInfoRenderer) a.handler));
        }
    }
}
