package org.gtreimagined.gt4r.items;

import muramasa.antimatter.block.AntimatterItemBlock;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class ReactorChamberBlockItem extends AntimatterItemBlock {
    public ReactorChamberBlockItem(Block block) {
        super(block);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        InteractionResult use = useOn(context);
        return use.consumesAction() ? use : InteractionResult.PASS;
    }
}
