package trinsdar.gt4r.machine;

import muramasa.antimatter.machine.types.BasicMultiMachine;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class UpgradeableBasicMultiMachine extends BasicMultiMachine<UpgradeableBasicMultiMachine> {
    public UpgradeableBasicMultiMachine(String domain, String name) {
        super(domain, name);
        this.setTooltipInfo((s, w, t, f) -> {
            String upgrades = "O T B M S";
            t.add(new TranslatableComponent("tooltip.gt4r.possible_upgrades", upgrades));
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
}
