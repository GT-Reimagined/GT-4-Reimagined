package trinsdar.gt4r.machine;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

public interface IUpgradeProvider {
    Map<TagKey<Item>, Integer> getUpgrades();

    Map<TagKey<Item>, Integer> getAllowedUpgrades();
}
