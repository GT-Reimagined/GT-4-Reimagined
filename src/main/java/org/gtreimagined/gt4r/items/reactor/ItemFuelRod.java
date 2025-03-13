package org.gtreimagined.gt4r.items.reactor;

import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.reactor.components.ComponentRegistry;
import org.gtreimagined.gt4r.reactor.components.FuelType;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapter;
import org.gtreimagined.gt4r.reactor.components.IComponentAdapterFactory;
import org.gtreimagined.gt4r.reactor.components.IReactorGrid;
import org.gtreimagined.gt4r.reactor.components.adapters.FuelRodAdapter;
import org.gtreimagined.gt4r.reactor.item.interfaces.IBasicFuelRod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ItemFuelRod extends ItemBasic<ItemFuelRod> implements IBasicFuelRod, IColorHandler, IComponentAdapterFactory {
    final Material material;
    final RodSize rodSize;
    final FuelType fuelType;
    final Supplier<Item> depleted;
    public ItemFuelRod(Material material, RodSize rodSize, FuelType fuelType, Supplier<Item> depleted) {
        super(GT4RRef.ID, rodSize.id + "_" + material.getId() + "_rod", new Properties().durability(fuelType.durability()).tab(Ref.TAB_ITEMS));
        this.material = material;
        this.rodSize = rodSize;
        this.fuelType = fuelType;
        this.depleted = depleted;
        ComponentRegistry.registerAdapter(this,this);
    }

    @Override
    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public int getRodCount(@NotNull ItemStack itemStack) {
        return rodSize.rods;
    }

    @Override
    public int getRemainingHealth(@NotNull ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getDamageValue();
    }

    @Override
    public void applyDamage(@NotNull ItemStack itemStack, int damage) {
        itemStack.setDamageValue(itemStack.getDamageValue() + damage);
    }

    @Override
    public @Nullable ItemStack getProduct(@NotNull ItemStack itemStack) {
        return new ItemStack(depleted.get());
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GT4RRef.ID, "item/basic/nuclear_rod/rod_" + rodSize.id + "_colored"), new Texture(GT4RRef.ID, "item/basic/nuclear_rod/rod_" + rodSize.id + "_overlay")};
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return i == 0 ? material.getRGB() : -1;
    }

    @Override
    public boolean canAdaptItem(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public @NotNull IComponentAdapter getAdapter(@NotNull ItemStack itemStack, @NotNull IReactorGrid reactor, int x, int y) {
        return new FuelRodAdapter(reactor, x, y, itemStack, this);
    }

    public enum RodSize {
        SINGLE(1, "single"),
        DUAL(2, "dual"),
        QUAD(4, "quad");

        int rods;
        String id;
        RodSize(int rods, String id){
            this.rods = rods;
            this.id = id;
        }
    }
}
