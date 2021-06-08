package trinsdar.gt4r.items;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import tesseract.api.capability.TesseractGTCapability;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static muramasa.antimatter.Data.NULL;

public class ItemRockCutter extends ToolItem implements IAntimatterObject, ITextureProvider, IModelProvider {
    private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD);
    public ItemRockCutter() {
        super(1.0F, -3.0F, ItemTier.DIAMOND, EFFECTIVE_ON, new Properties().maxStackSize(1).group(Ref.TAB_ITEMS).addToolType(ToolType.PICKAXE, 3).setNoRepair());
        AntimatterAPI.register(ItemRockCutter.class, getId(), this);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (!isInGroup(group)) {
            return;
        }
        ItemStack full = new ItemStack(this, 1);
        CompoundNBT nbt = getDataTag(full);
        nbt.putLong(Ref.KEY_TOOL_DATA_ENERGY, 10000);
        full.addEnchantment(Enchantments.SILK_TOUCH, 1);
        items.add(full);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (flagIn.isAdvanced()) tooltip.add(new StringTextComponent("Energy: " + getCurrentEnergy(stack) + " / " + getMaxEnergy(stack)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return Items.DIAMOND_PICKAXE.canHarvestBlock(stack, state) && getTier().getHarvestLevel() >= state.getHarvestLevel();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (getCurrentEnergy(stack) >= 500) {
            return 2.0F;
        } else {
            return 0.0F;
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        CompoundNBT tag = getDataTag(stack);
        long currentEnergy = tag.getLong(Ref.KEY_TOOL_DATA_ENERGY);
        int multipliedDamage = amount * 500;
        if (currentEnergy >= multipliedDamage) {
            tag.putLong(Ref.KEY_TOOL_DATA_ENERGY, currentEnergy - multipliedDamage);
        }
        return 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        long currentEnergy = getCurrentEnergy(stack);
        if (currentEnergy > 0) {
            double maxAmount = getMaxEnergy(stack), difference = maxAmount - currentEnergy;
            return difference / maxAmount;
        }
        return 1.0;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0x00BFFF;
    }

    public long getCurrentEnergy(ItemStack stack) {
        return getDataTag(stack).getLong(Ref.KEY_TOOL_DATA_ENERGY);
    }

    public CompoundNBT getDataTag(ItemStack stack) {
        CompoundNBT dataTag = stack.getChildTag(Ref.TAG_TOOL_DATA);
        return dataTag != null ? dataTag : validateTag(stack, 0, 10000);
    }

    public CompoundNBT validateTag(ItemStack stack, long startingEnergy, long maxEnergy) {
        CompoundNBT dataTag = stack.getOrCreateChildTag(Ref.TAG_TOOL_DATA);
        dataTag.putLong(Ref.KEY_TOOL_DATA_ENERGY, startingEnergy);
        dataTag.putLong(Ref.KEY_TOOL_DATA_MAX_ENERGY, maxEnergy);
        return dataTag;
    }

    public long getMaxEnergy(ItemStack stack) {
        return getDataTag(stack).getLong(Ref.KEY_TOOL_DATA_MAX_ENERGY);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new ItemEnergyHandler.Provider(() -> new ItemEnergyHandler(10000, 32, 32, 1, 1));
    }
    private LazyOptional<ItemEnergyHandler> getCastedHandler(ItemStack stack) {
        return stack.getCapability(TesseractGTCapability.ENERGY_HANDLER_CAPABILITY).cast();
    }

    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CompoundNBT nbt = super.getShareTag(stack);
        CompoundNBT inner = getCastedHandler(stack).map(ItemEnergyHandler::serializeNBT).orElse(null);
        if (inner != null) {
            if (nbt == null) nbt = new CompoundNBT();
            nbt.put("E", inner);
        }
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
        super.readShareTag(stack, nbt);
        if (nbt != null) {
            getCastedHandler(stack).ifPresent(t -> t.deserializeNBT(nbt.getCompound("E")));
        }
    }

    @Override
    public String getDomain() {
        return "gt4r";
    }

    @Override
    public String getId() {
        return "rock_cutter";
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "item/basic/" + getId())};
    }
}
