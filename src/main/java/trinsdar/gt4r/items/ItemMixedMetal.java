package trinsdar.gt4r.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.data.Materials;

import javax.annotation.Nullable;
import java.util.List;

import static muramasa.antimatter.Data.NULL;

public class ItemMixedMetal extends ItemBasic<ItemMixedMetal> implements IColorHandler {
    public ItemMixedMetal() {
        super(Ref.ID, "mixed_metal");
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i > 2) return -1;
        CompoundNBT stackNbt = stack.getTag();
        if (stackNbt == null) return -1;
        CompoundNBT nbt = stackNbt.getCompound(muramasa.antimatter.Ref.TAG_TOOL_DATA);
        String tagId = i == 0 ? "tm" : i == 1 ? "mm" : "bm";
        Material mat = Material.get(nbt.getString(tagId));
        if (mat == NULL) return -1;
        return mat.getRGB();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT stackNbt = stack.getTag();
        if (stackNbt == null){
            super.addInformation(stack, worldIn, tooltip, flagIn);
            return;
        }
        CompoundNBT nbt = stackNbt.getCompound(muramasa.antimatter.Ref.TAG_TOOL_DATA);
        Material t = Material.get(nbt.getString("tm"));
        Material m = Material.get(nbt.getString("mm"));
        Material b = Material.get(nbt.getString("bm"));
        tooltip.add(new StringTextComponent("Top Material: " + t.getDisplayName().getString()));
        tooltip.add(new StringTextComponent("Middle Material: " + m.getDisplayName().getString()));
        tooltip.add(new StringTextComponent("Bottom Material: " + b.getDisplayName().getString()));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            ItemStack itemStack = new ItemStack(this);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("tm", Materials.WroughtIron.getId());
            nbt.putString("mm", Materials.Brass.getId());
            nbt.putString("bm", Materials.Tin.getId());
            itemStack.getOrCreateTag().put(muramasa.antimatter.Ref.TAG_TOOL_DATA, nbt);
            items.add(itemStack);
        }
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "item/basic/" + getId() + "_top"), new Texture(getRegistryName().getNamespace(), "item/basic/" + getId() + "_middle"), new Texture(getRegistryName().getNamespace(), "item/basic/" + getId() + "_bottom")};
    }
}
