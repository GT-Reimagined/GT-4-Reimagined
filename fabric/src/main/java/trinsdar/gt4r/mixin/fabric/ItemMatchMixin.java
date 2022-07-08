package trinsdar.gt4r.mixin.fabric;

import io.github.fabricators_of_create.porting_lib.item.CustomMaxCountItem;
import org.spongepowered.asm.mixin.Mixin;
import trinsdar.gt4r.items.ItemMatch;

@Mixin(ItemMatch.class)
public abstract class ItemMatchMixin implements CustomMaxCountItem {
}
