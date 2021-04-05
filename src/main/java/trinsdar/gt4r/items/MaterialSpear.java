package trinsdar.gt4r.items;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialSword;
import muramasa.antimatter.tool.MaterialTool;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolType;

import java.util.UUID;

public class MaterialSpear extends MaterialTool {
    public UUID attUUID = UUID.fromString("0fb96bd2-8064-11ea-bc55-0242ac130003");

    public MaterialSpear(String domain, AntimatterToolType type, Properties properties) {
        super(domain, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && entityIn instanceof PlayerEntity &&  !this.hasReach((PlayerEntity) entityIn)) {
            this.extendReach((PlayerEntity) entityIn);
        }
        else if(!isSelected && entityIn instanceof PlayerEntity && this.hasReach((PlayerEntity) entityIn)) {
            this.removeReach((PlayerEntity) entityIn);
        }
    }

    public float getReach(){
        return 8.0F;
    };

    public void extendReach(PlayerEntity player) {
        AttributeModifier extended = new AttributeModifier(attUUID, "extend-reach", getReach(), AttributeModifier.Operation.ADDITION);

        if(!player.getAttribute(ForgeMod.REACH_DISTANCE.get()).hasModifier(extended))
            player.getAttribute(ForgeMod.REACH_DISTANCE.get()).applyPersistentModifier(extended);
    }

    public boolean hasReach(PlayerEntity player) {
        AttributeModifier extended = new AttributeModifier(attUUID, "extend-reach", getReach(), AttributeModifier.Operation.ADDITION);
        return player.getAttribute(ForgeMod.REACH_DISTANCE.get()).hasModifier(extended);
    }

    public void removeReach(PlayerEntity player) {
        player.getAttribute(ForgeMod.REACH_DISTANCE.get()).removeModifier(attUUID);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.type == EnchantmentType.WEAPON){
            return true;
        }
        return type.isPowered() ? enchantment != Enchantments.UNBREAKING : super.canApplyAtEnchantingTable(stack, enchantment);
    }
}
