package org.gtreimagined.gt4r.mixin;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.gtreimagined.gt4r.items.ItemElectricTool;

@Debug(export = true)
@Mixin(ShapedRecipe.class)
public class ShapedRecipeMixin {

    @Inject(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;IIZ)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Ingredient;test(Lnet/minecraft/world/item/ItemStack;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void injectMatches(CraftingContainer craftingInventory, int width, int height, boolean mirrored, CallbackInfoReturnable<Boolean> cir, int i, int j, int k, int l, Ingredient ingredient){
        ItemStack stack = craftingInventory.getItem(i + j * craftingInventory.getWidth());
        if (stack.getItem() instanceof ItemElectricTool tool){
            if (ingredient.test(stack)){
                if (!tool.hasEnoughDurability(stack, tool.getAntimatterToolType().getCraftingDurability(), true)){
                    cir.setReturnValue(false);
                }
            }
        }

    }
}
