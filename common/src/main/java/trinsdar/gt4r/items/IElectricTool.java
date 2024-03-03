package trinsdar.gt4r.items;

import com.google.common.collect.Streams;
import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.Data;
import muramasa.antimatter.Ref;
import muramasa.antimatter.behaviour.IAddInformation;
import muramasa.antimatter.behaviour.IBehaviour;
import muramasa.antimatter.behaviour.IBlockDestroyed;
import muramasa.antimatter.behaviour.IInteractEntity;
import muramasa.antimatter.behaviour.IItemHighlight;
import muramasa.antimatter.behaviour.IItemRightClick;
import muramasa.antimatter.behaviour.IItemUse;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAbstractToolMethods;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.tool.ToolUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import tesseract.TesseractCapUtils;
import tesseract.api.context.TesseractItemContext;
import tesseract.api.gt.IEnergyHandlerItem;
import tesseract.api.gt.IEnergyItem;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IElectricTool extends IAbstractToolMethods, IEnergyItem {

    AntimatterToolType getAntimatterToolType();

    Tier getItemTier();

    default int getPoweredBarColor(ItemStack stack){
        return 0x00BFFF;
    }

    default int getPoweredBarWidth(ItemStack stack){
        long currentEnergy = getCurrentEnergy(stack);
        if (currentEnergy > 0) {
            double maxAmount = getMaxEnergy(stack);
            return (int)( 13*(currentEnergy / maxAmount));
        }
        return 0;
    }

    default boolean isPoweredBarVisible(ItemStack stack) {
        return getCurrentEnergy(stack) > 0;
    }

    default Item getItem() {
        return (Item) this;
    }

    default Set<TagKey<Block>> getActualTags() {
        return getAntimatterToolType().getToolTypes();
    }


    default long getCurrentEnergy(ItemStack stack) {
        return getEnergyTag(stack).getLong(Ref.KEY_ITEM_ENERGY);
    }

    default long getMaxEnergy(ItemStack stack) {
        return getEnergyTag(stack).getLong(Ref.KEY_ITEM_MAX_ENERGY);
    }

    @Override
    default boolean canCreate(TesseractItemContext context) {
        return getAntimatterToolType().isPowered();
    }

    int getEnergyTier();

    default CompoundTag getEnergyTag(ItemStack stack){
        CompoundTag dataTag = stack.getTagElement(Ref.TAG_ITEM_ENERGY_DATA);
        return dataTag != null ? dataTag : validateEnergyTag(stack, 0, 10000);
    }

    default boolean genericIsCorrectToolForDrops(ItemStack stack, BlockState state) {
        AntimatterToolType type = this.getAntimatterToolType();
        if (type.getEffectiveMaterials().contains(state.getMaterial())) {
            return true;
        }
        if (type.getEffectiveBlocks().contains(state.getBlock())) {
            return true;
        }
        for (TagKey<Block> effectiveBlockTag : type.getEffectiveBlockTags()) {
            if (state.is(effectiveBlockTag)){
                return true;
            }
        }
        boolean isType = false;
        for (TagKey<Block> toolType : getAntimatterToolType().getToolTypes()) {
            if (state.is(toolType)){
                isType = true;
                break;
            }
        }
        return isType && ToolUtils.isCorrectTierForDrops(getItemTier(), state);
    }

    default float getDefaultMiningSpeed(ItemStack stack){
        return getItemTier().getSpeed() * getAntimatterToolType().getMiningSpeedMultiplier();
    }

    default CompoundTag validateEnergyTag(ItemStack stack, long startingEnergy, long maxEnergy){
        IEnergyHandlerItem h = TesseractCapUtils.getEnergyHandlerItem(stack).orElse(null);
        if (h != null){
            h.setEnergy(startingEnergy);
            h.setCapacity(maxEnergy);
            stack.setTag(h.getContainer().getTag());
        }
        return stack.getOrCreateTagElement(Ref.TAG_ITEM_ENERGY_DATA);
    }

    default void onGenericFillItemGroup(CreativeModeTab group, NonNullList<ItemStack> list, long maxEnergy) {
        if (group != Ref.TAB_TOOLS) return;
        if (getAntimatterToolType().isPowered()) {
            ItemStack stack = new ItemStack(this.getItem());
            IEnergyHandlerItem h = TesseractCapUtils.getEnergyHandlerItem(stack).orElse(null);
            if (h != null){
                list.add(stack.copy());
                h.setCapacity(maxEnergy);
                h.setEnergy(maxEnergy);
                stack.setTag(h.getContainer().getTag());
                list.add(stack);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default void onGenericAddInformation(ItemStack stack, List<Component> tooltip, TooltipFlag flag) {
        //TODO change this to object %s system for other lang compat
        if (flag.isAdvanced() && getAntimatterToolType().isPowered())
            tooltip.add(Utils.translatable("antimatter.tooltip.energy").append(": " + getCurrentEnergy(stack) + " / " + getMaxEnergy(stack)));
        if (getAntimatterToolType().getTooltip().size() != 0) tooltip.addAll(getAntimatterToolType().getTooltip());
        tooltip.add(Utils.translatable("antimatter.tooltip.mining_level", getItemTier().getLevel()).withStyle(ChatFormatting.YELLOW));
        tooltip.add(Utils.translatable("antimatter.tooltip.tool_speed", Utils.literal("" + getDefaultMiningSpeed(stack)).withStyle(ChatFormatting.LIGHT_PURPLE)));
        for (Map.Entry<String, IBehaviour<IAntimatterTool>> e : getAntimatterToolType().getBehaviours().entrySet()) {
            IBehaviour<?> b = e.getValue();
            if (!(b instanceof IAddInformation addInformation)) continue;
            addInformation.onAddInformation(this, stack, tooltip, flag);
        }
    }

    default boolean onGenericHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, float volume, float pitch) {
        if (getAntimatterToolType().getUseSound() != null)
            target.getCommandSenderWorld().playSound(null, target.getX(), target.getY(), target.getZ(), getAntimatterToolType().getUseSound(), SoundSource.HOSTILE, volume, pitch);
        Utils.damageStack(getAntimatterToolType().getAttackDurability(), stack, attacker);
        if (attacker instanceof Player player) refillTool(stack, player);
        return true;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default boolean onGenericBlockDestroyed(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        if (entity instanceof Player player) {
            if (getAntimatterToolType().getUseSound() != null)
                player.playNotifySound(getAntimatterToolType().getUseSound(), SoundSource.BLOCKS, 0.84F, 0.75F);
            boolean isToolEffective = genericIsCorrectToolForDrops(stack, state);
            if (state.getDestroySpeed(world, pos) != 0.0F) {
                int damage = isToolEffective ? getAntimatterToolType().getUseDurability() : getAntimatterToolType().getUseDurability() + 1;
                Utils.damageStack(damage, stack, entity);
            }
        }
        boolean returnValue = true;
        for (Map.Entry<String, IBehaviour<IAntimatterTool>> e : getAntimatterToolType().getBehaviours().entrySet()) {
            IBehaviour<?> b = e.getValue();
            if (!(b instanceof IBlockDestroyed)) continue;
            returnValue = ((IBlockDestroyed) b).onBlockDestroyed(this, stack, world, state, pos, entity);
        }
        if (entity instanceof Player player) refillTool(stack, player);
        return returnValue;
    }

    default void refillTool(ItemStack stack, Player player){
        if (this.getAntimatterToolType().isPowered()) {
            Streams.concat(player.getInventory().items.stream(), player.getInventory().offhand.stream()).forEach(s -> {
                if (this.getCurrentEnergy(stack) < getMaxEnergy(stack)){
                    if (s.getItem() instanceof ItemBattery battery && battery.getTier().getIntegerId() == this.getEnergyTier()){
                        IEnergyHandlerItem batteryHandler = TesseractCapUtils.getEnergyHandlerItem(s).orElse(null);
                        IEnergyHandlerItem toolHandler = TesseractCapUtils.getEnergyHandlerItem(stack).orElse(null);
                        if (batteryHandler != null && toolHandler != null){
                            long extracted = batteryHandler.extractEu(battery.getCapacity(), true);
                            if (extracted > 0){
                                long inserted = toolHandler.insertEu(extracted, true);
                                if (inserted > 0){
                                    toolHandler.insertEu(batteryHandler.extractEu(inserted, false), false);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    default InteractionResult genericInteractLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand){
        InteractionResult result = InteractionResult.PASS;
        for (Map.Entry<String, IBehaviour<IAntimatterTool>> e : getAntimatterToolType().getBehaviours().entrySet()) {
            IBehaviour<?> b = e.getValue();
            if (!(b instanceof IInteractEntity interactEntity)) continue;
            InteractionResult r = interactEntity.interactLivingEntity(this, stack, player, interactionTarget, usedHand);
            if (result != InteractionResult.SUCCESS) result = r;
        }
        return result;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default InteractionResult onGenericItemUse(UseOnContext ctx) {
        InteractionResult result = InteractionResult.PASS;
        for (Map.Entry<String, IBehaviour<IAntimatterTool>> e : getAntimatterToolType().getBehaviours().entrySet()) {
            IBehaviour<?> b = e.getValue();
            if (!(b instanceof IItemUse itemUse)) continue;
            InteractionResult r = itemUse.onItemUse(this, ctx);
            if (result != InteractionResult.SUCCESS) result = r;
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    default InteractionResultHolder<ItemStack> onGenericRightclick(Level level, Player player, InteractionHand usedHand){
        for (Map.Entry<String, IBehaviour<IAntimatterTool>> e : getAntimatterToolType().getBehaviours().entrySet()) {
            IBehaviour<?> b = e.getValue();
            if (!(b instanceof IItemRightClick rightClick)) continue;
            InteractionResultHolder<ItemStack> r = rightClick.onRightClick(this, level, player, usedHand);
            if (r.getResult().shouldAwardStats()) return r;
        }
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @SuppressWarnings("rawtypes")
    default InteractionResult onGenericHighlight(Player player, LevelRenderer levelRenderer, Camera camera, HitResult target, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource) {
        InteractionResult result = InteractionResult.PASS;
        for (Map.Entry<String, IBehaviour<IAntimatterTool>> e : getAntimatterToolType().getBehaviours().entrySet()) {
            IBehaviour<?> b = e.getValue();
            if (!(b instanceof IItemHighlight)) continue;
            InteractionResult type = ((IItemHighlight) b).onDrawHighlight(player, levelRenderer, camera, target, partialTicks, poseStack, multiBufferSource);
            if (type != InteractionResult.SUCCESS) {
                result = type;
            } else {
                return InteractionResult.FAIL;
            }
        }
        return result;
    }

    default ItemStack getGenericContainerItem(final ItemStack oldStack) {
        ItemStack stack = oldStack.copy();
        damage(stack, getAntimatterToolType().getCraftingDurability());
        return stack;
    }

    default int getDefaultEnergyUse(){
        return 100;
    }

    default int damage(ItemStack stack, int amount) {
        if (!getAntimatterToolType().isPowered()) return amount;
        IEnergyHandlerItem h = TesseractCapUtils.getEnergyHandlerItem(stack).orElse(null);
        if (!(h instanceof ItemEnergyHandler)) {
            return amount;
        }
        long currentEnergy = h.getEnergy();
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
        int energyEfficiency = enchants.getOrDefault(Data.ENERGY_EFFICIENCY, 0);
        int energyUse = Math.max(1, getDefaultEnergyUse() - (int)((energyEfficiency * 0.1f) * getDefaultEnergyUse()));
        int multipliedDamage = amount * energyUse;
        if (currentEnergy >= multipliedDamage) {
            h.extractEu(multipliedDamage, false);
            stack.setTag(h.getContainer().getTag());
            //tag.putLong(Ref.KEY_TOOL_DATA_ENERGY, currentEnergy - multipliedDamage); // Otherwise take energy off of tool if energy is larger than multiplied damage
            return 0; // Nothing is taken away from main durability
        } else { // Lastly, set energy to 0 and take leftovers off of tool durability itself
            return 0;
        }
    }

    default boolean hasEnoughDurability(ItemStack stack, int damage, boolean energy) {
        return energy && getCurrentEnergy(stack) >= damage * 100;
    }

    boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker);
}
