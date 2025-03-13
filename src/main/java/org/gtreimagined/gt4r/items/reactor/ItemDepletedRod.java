package org.gtreimagined.gt4r.items.reactor;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.items.reactor.ItemFuelRod.RodSize;
import org.jetbrains.annotations.Nullable;

public class ItemDepletedRod extends ItemBasic<ItemDepletedRod> implements IColorHandler {
    final Material material;
    final RodSize rodSize;
    public ItemDepletedRod(Material material, RodSize rodSize) {
        super(GT4RRef.ID, "depleted_" + rodSize.id + "_" + material.getId() + "_rod");
        this.material = material;
        this.rodSize = rodSize;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GT4RRef.ID, "item/basic/nuclear_rod/rod_" + rodSize.id + "_colored"), new Texture(GT4RRef.ID, "item/basic/nuclear_rod/rod_" + rodSize.id + "_depleted_overlay")};
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return i == 0 ? material.getRGB() : -1;
    }
}
