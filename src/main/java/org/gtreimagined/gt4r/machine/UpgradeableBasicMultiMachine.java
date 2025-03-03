package org.gtreimagined.gt4r.machine;

import muramasa.antimatter.machine.types.BasicMultiMachine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.gtreimagined.gt4r.blockentity.multi.BlockEntityUpgradeableBasicMultiblock;
import org.gtreimagined.gt4r.data.CustomTags;

import java.util.ArrayList;
import java.util.List;

public class UpgradeableBasicMultiMachine extends BasicMultiMachine<UpgradeableBasicMultiMachine> {
    List<TagKey<Item>> allowedUpgrades;
    public UpgradeableBasicMultiMachine(String domain, String name) {
        super(domain, name);
        this.setTile(BlockEntityUpgradeableBasicMultiblock::new);
        allowedUpgrades = List.of(CustomTags.OVERCLOCKER_UPGRADES, CustomTags.TRANSFORMER_UPGRADES, CustomTags.BATTERY_UPGRADES,
                CustomTags.MUFFLER_UPGRADES, CustomTags.STEAM_UPGRADES);
        this.addTooltipInfo((b, s, w, t, f) -> {
            List<String> upgrades = new ArrayList<>(); //= "O T B M S";
            if (allowedUpgrades.contains(CustomTags.OVERCLOCKER_UPGRADES)) {
                upgrades.add("O");
            }
            if (allowedUpgrades.contains(CustomTags.TRANSFORMER_UPGRADES)) {
                upgrades.add("T");
            }
            if (allowedUpgrades.contains(CustomTags.BATTERY_UPGRADES)) {
                upgrades.add("B");
            }
            if (allowedUpgrades.contains(CustomTags.MUFFLER_UPGRADES)) {
                upgrades.add("M");
            }
            if (allowedUpgrades.contains(CustomTags.STEAM_UPGRADES)) {
                upgrades.add("S");
            }
            t.add(new TranslatableComponent("tooltip.gt4r.possible_upgrades", String.join(" ", upgrades)));
            CompoundTag nbt = s.getTag();
            if (nbt != null && nbt.contains("upgrades")) {
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

    @SafeVarargs
    public final UpgradeableBasicMultiMachine setUpgrades(TagKey<Item>... upgrade) {
        allowedUpgrades = List.of(upgrade);
        return this;
    }

    public List<TagKey<Item>> getAllowedUpgrades() {
        return allowedUpgrades;
    }
}
