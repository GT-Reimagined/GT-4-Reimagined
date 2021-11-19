package trinsdar.gt4r.items;

import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.texture.Texture;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import trinsdar.gt4r.Ref;

import static trinsdar.gt4r.data.GT4RData.Lighter;
import static trinsdar.gt4r.data.GT4RData.LighterEmpty;
import static trinsdar.gt4r.data.GT4RData.MatchBook;

import net.minecraft.item.Item.Properties;

public class ItemMatch extends ItemBasic<ItemMatch> {
    public ItemMatch(String domain, String id, Properties properties) {
        super(domain, id, properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);
        ItemStack stack = context.getItemInHand();
        if ((this.canBeDepleted() && stack.getCount() == 1) || !this.canBeDepleted()){
            if (CampfireBlock.canLight(blockstate)) {
                world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
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

                return ActionResultType.sidedSuccess(world.isClientSide());
            } else {
                BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
                if (AbstractFireBlock.canBePlacedAt(world, blockpos1, context.getHorizontalDirection())) {
                    world.playSound(playerentity, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                    BlockState blockstate1 = AbstractFireBlock.getState(world, blockpos1);
                    world.setBlock(blockpos1, blockstate1, 11);
                    if (playerentity instanceof ServerPlayerEntity) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos1, stack);
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

                    return ActionResultType.sidedSuccess(world.isClientSide());
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return this.getDamage(stack) == 0 ? 64 : 1;
    }

    @Override
    public void onItemModelBuild(IItemProvider item, AntimatterItemModelProvider prov) {
        if (this != MatchBook && this != Lighter) {
            super.onItemModelBuild(item, prov);
            return;
        }
        String id = this.getId();
        ItemModelBuilder builder = prov.getBuilder(id +"_lit");
        builder.parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft:item/generated")));
        builder.texture("layer0", new Texture(Ref.ID, "item/basic/" + id +"_lit"));
        prov.tex(item, new ResourceLocation(Ref.ID, "item/basic/" + id)).override().predicate(new ResourceLocation("damaged"), 1).predicate(new ResourceLocation("damage"), 0).model(new ModelFile.UncheckedModelFile(new ResourceLocation(Ref.ID, "item/" + id +"_lit")));
    }

    @Override
    public Texture[] getTextures() {
        return super.getTextures();
    }
}
