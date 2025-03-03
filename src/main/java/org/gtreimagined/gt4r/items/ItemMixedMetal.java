package org.gtreimagined.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.data.Materials;

import java.util.List;

import static muramasa.antimatter.material.Material.NULL;

public class ItemMixedMetal extends ItemBasic<ItemMixedMetal> implements IColorHandler {
    public ItemMixedMetal() {
        super(GT4RRef.ID, "mixed_metal");
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i > 2) return -1;
        CompoundTag stackNbt = stack.getTag();
        if (stackNbt == null) return -1;
        CompoundTag nbt = stackNbt.getCompound(muramasa.antimatter.Ref.TAG_TOOL_DATA);
        String tagId = i == 0 ? "tm" : i == 1 ? "mm" : "bm";
        Material mat = Material.get(nbt.getString(tagId));
        if (mat == NULL) return -1;
        return mat.getRGB();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        CompoundTag stackNbt = stack.getTag();
        if (stackNbt == null){
            super.appendHoverText(stack, worldIn, tooltip, flagIn);
            return;
        }
        CompoundTag nbt = stackNbt.getCompound(muramasa.antimatter.Ref.TAG_TOOL_DATA);
        Material t = Material.get(nbt.getString("tm"));
        Material m = Material.get(nbt.getString("mm"));
        Material b = Material.get(nbt.getString("bm"));
        tooltip.add(new TextComponent("Top Material: " + t.getDisplayName().getString()));
        tooltip.add(new TextComponent("Middle Material: " + m.getDisplayName().getString()));
        tooltip.add(new TextComponent("Bottom Material: " + b.getDisplayName().getString()));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            ItemStack itemStack = new ItemStack(this);
            CompoundTag nbt = new CompoundTag();
            nbt.putString("tm", Materials.WroughtIron.getId());
            nbt.putString("mm", Materials.Brass.getId());
            nbt.putString("bm", Materials.Tin.getId());
            itemStack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
            items.add(itemStack);
        }
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getDomain(), "item/basic/" + getId() + "_top"), new Texture(getDomain(), "item/basic/" + getId() + "_middle"), new Texture(getDomain(), "item/basic/" + getId() + "_bottom")};
    }
}
