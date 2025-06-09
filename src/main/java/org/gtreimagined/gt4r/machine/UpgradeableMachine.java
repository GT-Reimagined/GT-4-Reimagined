package org.gtreimagined.gt4r.machine;

import org.gtreimagined.gtlib.Data;
import org.gtreimagined.gtlib.blockentity.BlockEntityMachine;
import org.gtreimagined.gtlib.blockentity.multi.BlockEntityMultiMachine;
import org.gtreimagined.gtlib.cover.CoverOutput;
import org.gtreimagined.gtlib.gui.screen.GTContainerScreen;
import org.gtreimagined.gtlib.gui.widget.IOWidget;
import org.gtreimagined.gtlib.gui.widget.MachineStateWidget;
import org.gtreimagined.gtlib.gui.widget.ProgressWidget;
import org.gtreimagined.gtlib.gui.widget.TextWidget;
import org.gtreimagined.gtlib.gui.widget.WidgetSupplier;
import org.gtreimagined.gtlib.machine.Tier;
import org.gtreimagined.gtlib.machine.types.Machine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.gtreimagined.gt4r.blockentity.single.BlockEntityUpgradeableMachine;
import org.gtreimagined.gt4r.data.CustomTags;
import org.gtreimagined.gt4r.data.Textures;

import java.util.ArrayList;
import java.util.List;

import static org.gtreimagined.gtlib.machine.MachineFlag.*;

public class UpgradeableMachine extends Machine<UpgradeableMachine> {
    List<TagKey<Item>> allowedUpgrades;

    public UpgradeableMachine(String domain, String id) {
        super(domain, id);
        setTiers(Tier.LV);
        setBaseTexture(Textures.BASE_HANDLER);
        addFlags(BASIC, EU, COVERABLE);
        setTile(BlockEntityUpgradeableMachine::new);
        setGUI(Data.BASIC_MENU_HANDLER);
        allowedUpgrades = List.of(CustomTags.OVERCLOCKER_UPGRADES, CustomTags.TRANSFORMER_UPGRADES, CustomTags.BATTERY_UPGRADES,
                CustomTags.MUFFLER_UPGRADES, CustomTags.STEAM_UPGRADES);
        this.addTooltipInfo((b, s, w, t, f) -> {
            List<String> upgrades = new ArrayList<>(); //= "O T B M S";
            if (allowedUpgrades.contains(CustomTags.OVERCLOCKER_UPGRADES)){
                upgrades.add("O");
            }
            if (allowedUpgrades.contains(CustomTags.TRANSFORMER_UPGRADES)){
                upgrades.add("T");
            }
            if (allowedUpgrades.contains(CustomTags.BATTERY_UPGRADES)){
                upgrades.add("B");
            }
            if (allowedUpgrades.contains(CustomTags.MUFFLER_UPGRADES)){
                upgrades.add("M");
            }
            if (allowedUpgrades.contains(CustomTags.STEAM_UPGRADES)){
                upgrades.add("S");
            }
            t.add(new TranslatableComponent("tooltip.gt4r.possible_upgrades", String.join(" ", upgrades)));
            CompoundTag nbt = s.getTag();
            if (nbt != null && nbt.contains("upgrades")){
                ListTag list = nbt.getList("upgrades", 10);
                for (int i = 0; i < list.size(); i++) {
                    CompoundTag upgrade = list.getCompound(i);
                    String tag = upgrade.getString("key");
                    int amount = upgrade.getInt("value");
                    tag = tag.substring(tag.indexOf(":") + 1);
                    t.add(new TranslatableComponent("tooltip.gt4r." + tag, amount));
                }
            }
        });
    }

    @Override
    protected void setupGui() {
        super.setupGui();
        addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((GTContainerScreen<?>) b).getTitle().getString(), 4210752, false).build(a, b)).setPos(9, 5).clientSide());
            if (has(RECIPE)) {
                t.addWidget(ProgressWidget.build())
                        .addWidget(MachineStateWidget.build());
            }
            if ((has(ITEM) || has(FLUID)))
                t.addWidget(IOWidget.build(9, 63).onlyIf(u -> u.handler instanceof BlockEntityMachine<?> machine &&
                        machine.getOutputFacing() != null &&
                        machine.coverHandler.map(c -> c.getOutputCover() instanceof CoverOutput).orElse(false) &&
                        !(u.handler instanceof BlockEntityMultiMachine<?>)));
        });
    }

    @SafeVarargs
    public final UpgradeableMachine setUpgrades(TagKey<Item>... upgrade) {
        allowedUpgrades = List.of(upgrade);
        return this;
    }

    public List<TagKey<Item>> getAllowedUpgrades() {
        return allowedUpgrades;
    }
}
