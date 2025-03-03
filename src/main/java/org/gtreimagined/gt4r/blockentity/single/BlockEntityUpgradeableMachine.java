package org.gtreimagined.gt4r.blockentity.single;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.client.SoundHelper;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.gtreimagined.gt4r.data.CustomTags;
import org.gtreimagined.gt4r.machine.IUpgradeProvider;
import org.gtreimagined.gt4r.machine.UpgradeableFluidHandler;
import org.gtreimagined.gt4r.machine.UpgradeableMachine;
import org.gtreimagined.gt4r.machine.UpgradeableMachineRecipeHandler;

import java.util.List;
import java.util.Map;

public class BlockEntityUpgradeableMachine<T extends BlockEntityUpgradeableMachine<T>> extends BlockEntityMachine<T> implements IUpgradeProvider {
    Map<TagKey<Item>, Integer> upgrades = new Object2ObjectArrayMap<>();
    Map<TagKey<Item>, Integer> allowedUpgrades;
    public BlockEntityUpgradeableMachine(UpgradeableMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new UpgradeableMachineRecipeHandler<>((T)this));
        this.fluidHandler.set(() -> new UpgradeableFluidHandler<>((T)this));
        ImmutableMap.Builder<TagKey<Item>, Integer> builder = ImmutableMap.builder();
        int transformerAmount = this.getMachineTier().getVoltage() >= Tier.IV.getVoltage() ? 0 : 5 - Utils.getVoltageTier(this.getMachineTier().getVoltage());
        if (type.getAllowedUpgrades().contains(CustomTags.OVERCLOCKER_UPGRADES)){
            builder.put(CustomTags.OVERCLOCKER_UPGRADES, 4);
        }
        if (type.getAllowedUpgrades().contains(CustomTags.TRANSFORMER_UPGRADES)){
            builder.put(CustomTags.TRANSFORMER_UPGRADES, transformerAmount);
        }
        if (type.getAllowedUpgrades().contains(CustomTags.BATTERY_UPGRADES)){
            builder.put(CustomTags.BATTERY_UPGRADES, 1);
        }
        if (type.getAllowedUpgrades().contains(CustomTags.MUFFLER_UPGRADES)){
            builder.put(CustomTags.MUFFLER_UPGRADES, 1);
        }
        if (type.getAllowedUpgrades().contains(CustomTags.STEAM_UPGRADES)){
            builder.put(CustomTags.STEAM_UPGRADES, 1);
        }
        allowedUpgrades = builder.build();
    }

    @Override
    public Map<TagKey<Item>, Integer> getUpgrades() {
        return upgrades;
    }

    @Override
    public Map<TagKey<Item>, Integer> getAllowedUpgrades() {
        return allowedUpgrades;
    }

    @NotNull
    @Override
    public Component getDisplayName() {
        return getMachineType().getDisplayName(getPowerLevel());
    }

    @Override
    public Tier getPowerLevel() {
        if (upgrades.containsKey(CustomTags.TRANSFORMER_UPGRADES)){
            long voltage = getMachineTier().getVoltage() * ((int)Math.pow(4,  upgrades.get(CustomTags.TRANSFORMER_UPGRADES)));
            return Tier.getTier(voltage);
        }
        return super.getPowerLevel();
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
                    doUpgradeFunction(foundUpgrade);
                    world.playSound(null, pos, SoundEvents.UI_BUTTON_CLICK, SoundSource.BLOCKS, 1.0f, 1.0f);
                }
                return InteractionResult.sidedSuccess(isClientSide());
            }

        }
        return super.onInteractBoth(state, world, pos, player, hand, hit, type);
    }

    protected void doUpgradeFunction(TagKey<Item> upgrade){
        if (upgrade == CustomTags.OVERCLOCKER_UPGRADES) {
            recipeHandler.ifPresent(MachineRecipeHandler::resetProgress);
        }
        if (upgrade == CustomTags.TRANSFORMER_UPGRADES || upgrade == CustomTags.HV_TRANSFORMER_UPGRADES) {
            energyHandler.ifPresent(e -> {
                if (e.getInputVoltage() > 0){
                    e.setInputVoltage(e.getInputVoltage() * 4);
                }
                if (e.getOutputVoltage() > 0){
                    e.setOutputVoltage(e.getOutputVoltage() * 4);
                }
            });
        }
        if (upgrade == CustomTags.STEAM_UPGRADES){
            energyHandler.ifPresent(e -> {
                e.setInputVoltage(0);
                //e.extractEu(e.getEnergy(), false); //TODO way to drain only the internal buffer of the machine, not batteries in it
            });
        }
        if (upgrade == CustomTags.MUFFLER_UPGRADES) {
            this.setMuffled(true);
        }
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
                TagKey<Item> upgradeTag = TagUtils.getItemTag(new ResourceLocation(upgrade.getString("key")));
                if (upgradeTag == CustomTags.TRANSFORMER_UPGRADES){
                    for (int j = 0; j < upgrade.getInt("value"); j++) {
                        doUpgradeFunction(upgradeTag);
                    }
                } else {
                    doUpgradeFunction(upgradeTag);
                }
                upgrades.put(upgradeTag, upgrade.getInt("value"));
            }
        }
    }
}
