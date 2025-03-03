package org.gtreimagined.gt4r.blockentity.multi;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.client.SoundHelper;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.data.CustomTags;
import org.gtreimagined.gt4r.machine.IUpgradeProvider;
import org.gtreimagined.gt4r.machine.UpgradeableFluidHandler;
import org.gtreimagined.gt4r.machine.UpgradeableMachineRecipeHandler;

import java.util.List;
import java.util.Map;

public class BlockEntityUpgradeableBasicMultiblock<T extends BlockEntityUpgradeableBasicMultiblock<T>> extends BlockEntityBasicMultiMachine<T> implements IUpgradeProvider {
    Map<TagKey<Item>, Integer> upgrades = new Object2ObjectArrayMap<>();
    Map<TagKey<Item>, Integer> allowedUpgrades;
    public BlockEntityUpgradeableBasicMultiblock(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new UpgradeableMachineRecipeHandler<>((T)this));
        this.fluidHandler.set(() -> new UpgradeableFluidHandler<>((T)this));
        int transformerAmount = this.getMachineTier().getVoltage() >= Tier.IV.getVoltage() ? 0 : 5 - Utils.getVoltageTier(this.getMachineTier().getVoltage());
        allowedUpgrades = ImmutableMap.of(CustomTags.OVERCLOCKER_UPGRADES, 4, CustomTags.TRANSFORMER_UPGRADES, transformerAmount, CustomTags.MUFFLER_UPGRADES, 1, CustomTags.STEAM_UPGRADES, 1);

    }

    @Override
    public Map<TagKey<Item>, Integer> getUpgrades() {
        return upgrades;
    }

    @Override
    public Map<TagKey<Item>, Integer> getAllowedUpgrades() {
        return allowedUpgrades;
    }

    @Override
    public InteractionResult onInteractBoth(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty()){
            TagKey<Item> foundUpgrade = null;
            for (TagKey<Item> allowedUpgrade : getAllowedUpgrades().keySet()) {
                if (allowedUpgrade == CustomTags.TRANSFORMER_UPGRADES){
                    long maxInput = energyHandler.map(e -> e.getInputVoltage()).orElse(0L);
                    allowedUpgrade = maxInput >= 512 ? CustomTags.HV_TRANSFORMER_UPGRADES : CustomTags.TRANSFORMER_UPGRADES;
                    if (stack.is(allowedUpgrade)){
                        foundUpgrade = CustomTags.TRANSFORMER_UPGRADES;
                        break;
                    } else {
                        continue;
                    }
                }
                if (stack.is(allowedUpgrade)){
                    foundUpgrade = allowedUpgrade;
                    break;
                }
            }
            if (foundUpgrade != null){
                int maxCount = getAllowedUpgrades().get(foundUpgrade);
                int upgrade = upgrades.getOrDefault(foundUpgrade, 0);
                if (upgrade >= maxCount) return InteractionResult.PASS;
                upgrade++;
                upgrades.put(foundUpgrade, upgrade);
                if (isServerSide()){
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    if (foundUpgrade == CustomTags.OVERCLOCKER_UPGRADES) {
                        recipeHandler.ifPresent(MachineRecipeHandler::resetProgress);
                    }
                    if (foundUpgrade == CustomTags.TRANSFORMER_UPGRADES || foundUpgrade == CustomTags.HV_TRANSFORMER_UPGRADES) {
                        energyHandler.ifPresent(e -> e.setInputVoltage(e.getInputVoltage() * 4));
                    }
                    if (foundUpgrade == CustomTags.STEAM_UPGRADES){
                        energyHandler.ifPresent(e -> {
                            e.setInputVoltage(0);
                            //e.extractEu(e.getEnergy(), false); //TODO way to drain only the internal buffer of the machine, not batteries in it
                        });
                    }
                    if (foundUpgrade == CustomTags.MUFFLER_UPGRADES) {
                        this.setMuffled(true);
                    }
                    world.playSound(null, pos, SoundEvents.UI_BUTTON_CLICK, SoundSource.BLOCKS, 1.0f, 1.0f);
                }
                return InteractionResult.sidedSuccess(isClientSide());
            }

        }
        return super.onInteractBoth(state, world, pos, player, hand, hit, type);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("upgrades")){
            ListTag list = tag.getList("upgrades", 10);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag upgrade = list.getCompound(i);
                upgrades.put(TagUtils.getItemTag(new ResourceLocation(upgrade.getString("key"))), upgrade.getInt("value"));
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ListTag list = new ListTag();
        upgrades.forEach((k, v) -> {
            CompoundTag upgrade = new CompoundTag();
            upgrade.putString("key", k.location().toString());
            upgrade.putInt("value", v);
            list.add(upgrade);
        });
        if (!list.isEmpty()) tag.put("upgrades", list);
    }

    @Override
    public void setMachineState(MachineState newState) {
        super.setMachineState(newState);
        if (level != null && level.isClientSide && upgrades.containsKey(CustomTags.MUFFLER_UPGRADES)){
            SoundHelper.clear(level, this.getBlockPos());
        }
    }

    @Override
    public void onDrop(BlockState state, LootContext.Builder builder, List<ItemStack> drops) {
        if (drops.isEmpty()) return;
        ItemStack machine = drops.get(0);
        if (!upgrades.isEmpty()){
            CompoundTag nbt = machine.getOrCreateTag();
            ListTag listTag = new ListTag();
            upgrades.forEach((k, v) -> {
                CompoundTag upgrade = new CompoundTag();
                upgrade.putString("key", k.location().toString());
                upgrade.putInt("value", v);
                listTag.add(upgrade);
            });
            if (!listTag.isEmpty()) nbt.put("upgrades", listTag);
        }
    }

    @Override
    public void onPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlacedBy(world, pos, state, placer, stack);
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("upgrades")){
            ListTag list = tag.getList("upgrades", 10);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag upgrade = list.getCompound(i);
                upgrades.put(TagUtils.getItemTag(new ResourceLocation(upgrade.getString("key"))), upgrade.getInt("value"));
            }
        }
    }
}
