package org.gtreimagined.gt4r.machine;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Map;

public interface IUpgradeProvider {
    Map<TagKey<Item>, Integer> getUpgrades();

    Map<TagKey<Item>, Integer> getAllowedUpgrades();
}
