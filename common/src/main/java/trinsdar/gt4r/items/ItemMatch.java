package trinsdar.gt4r.items;

import muramasa.antimatter.datagen.builder.AntimatterItemModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.texture.Texture;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import trinsdar.gt4r.Ref;

import static trinsdar.gt4r.data.GT4RData.Lighter;
import static trinsdar.gt4r.data.GT4RData.LighterEmpty;
import static trinsdar.gt4r.data.GT4RData.MatchBook;

public class ItemMatch extends ItemBasic<ItemMatch> {
    public ItemMatch(String domain, String id, Properties properties) {
        super(domain, id, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player playerentity = context.getPlayer();
        Level world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);
        ItemStack stack = context.getItemInHand();
        if ((this.canBeDepleted() && stack.getCount() == 1) || !this.canBeDepleted()){
            if (CampfireBlock.canLight(blockstate)) {
                world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, context.getLevel().random.nextFloat() * 0.4F + 0.8F);
                world.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, true), 11);
                if (playerentity != null) {
                    if (this.canBeDepleted()){
                        stack.hurtAndBreak(1, playerentity, (player) -> {
                            player.broadcastBreakEvent(context.getHand());
                            if (this == Lighter) {
                                if (!player.addItem(new ItemStack(LighterEmpty))) player.drop(new ItemStack(LighterEmpty), true);
                            }
                        });
                    } else {
                        stack.shrink(1);
                    }
                }

                return InteractionResult.sidedSuccess(world.isClientSide());
            } else {
                BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
                if (BaseFireBlock.canBePlacedAt(world, blockpos1, context.getHorizontalDirection())) {
                    world.playSound(playerentity, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, context.getLevel().random.nextFloat() * 0.4F + 0.8F);
                    BlockState blockstate1 = BaseFireBlock.getState(world, blockpos1);
                    world.setBlock(blockpos1, blockstate1, 11);
                    if (playerentity instanceof ServerPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)playerentity, blockpos1, stack);
                        if (this.canBeDepleted()){
                            stack.hurtAndBreak(1, playerentity, (player) -> {
                                player.broadcastBreakEvent(context.getHand());
                                if (this == Lighter) {
                                    if (!player.addItem(new ItemStack(LighterEmpty))) player.drop(new ItemStack(LighterEmpty), true);
                                }
                            });
                        } else {
                            stack.shrink(1);
                        }
                    }

                    return InteractionResult.sidedSuccess(world.isClientSide());
                }
            }
        }
        return InteractionResult.FAIL;
    }

    //@Override
    public int getItemStackLimit(ItemStack stack) {
        return stack.getDamageValue() == 0 ? 64 : 1;
    }

    @Override
    public void onItemModelBuild(ItemLike item, AntimatterItemModelProvider prov) {
        if (this != MatchBook && this != Lighter) {
            super.onItemModelBuild(item, prov);
            return;
        }
        String id = this.getId();
        AntimatterItemModelBuilder builder = prov.getBuilder(id +"_lit");
        builder.parent(new ResourceLocation("minecraft:item/generated"));
        builder.texture("layer0", new Texture(Ref.ID, "item/basic/" + id +"_lit"));
        prov.tex(item, new ResourceLocation(Ref.ID, "item/basic/" + id)).override().predicate(new ResourceLocation("damaged"), 1).predicate(new ResourceLocation("damage"), 0).model(new ResourceLocation(Ref.ID, "item/" + id +"_lit"));
    }

    @Override
    public Texture[] getTextures() {
        return super.getTextures();
    }
}
