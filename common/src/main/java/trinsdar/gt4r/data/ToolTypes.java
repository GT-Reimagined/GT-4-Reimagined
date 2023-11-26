package trinsdar.gt4r.data;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.item.ItemPowerUnit;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.recipe.material.MaterialRecipe;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tuple;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import tesseract.TesseractCapUtils;
import tesseract.api.gt.IEnergyHandlerItem;
import tesseract.api.gt.IGTNode;
import trinsdar.gt4r.GT4RRef;
import trinsdar.gt4r.items.MaterialRockCutter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static muramasa.antimatter.material.Material.NULL;
import static net.minecraft.world.level.material.Material.*;
import static trinsdar.gt4r.data.GT4RData.*;

public class ToolTypes {
    public static final MaterialRecipe.Provider POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool", GT4RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Material m = (Material) mats.mats.get("secondary");
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            String domain = id.equals(ROCK_CUTTER.getId()) ? GT4RRef.ID : GT4RRef.ANTIMATTER;
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'), domain);
            return type.resolveStack((Material) mats.mats.get("primary"), m == null ? NULL : m, battery.getA(), battery.getB());
        }

        @Override
        public Map<String, Object> getFromResult(@Nonnull ItemStack stack) {
            CompoundTag nbt = stack.getTag().getCompound(muramasa.antimatter.Ref.TAG_TOOL_DATA);
            Material primary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_PRIMARY_MATERIAL));
            Material secondary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_SECONDARY_MATERIAL));
            return ImmutableMap.of("primary", primary, "secondary", secondary, "energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });

    public static final MaterialRecipe.Provider UNIT_POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool-from-unit", GT4RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Tuple<Long, Tuple<Long, Material>> t = (Tuple<Long, Tuple<Long, Material>>) mats.mats.get("secondary");
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'), GT4RRef.ANTIMATTER);
            t.getB().getB();
            return type.resolveStack((Material) mats.mats.get("primary"), t.getB().getB(), t.getA(), t.getB().getA());
        }

        @Override
        public Map<String, Object> getFromResult(@Nonnull ItemStack stack) {
            return ImmutableMap.of();
        }
    });
    public static AntimatterToolType ROCK_CUTTER = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GT4RRef.ID, "rock_cutter", 1, 1, 1, -1.0F, -3.0F, false)).setPowered(100000L, 1).setRepairable(false).setOverlayLayers(2).addEffectiveMaterials(ICE_SOLID, METAL, STONE, HEAVY_METAL, PISTON).setBrokenItems(ImmutableMap.of("rock_cutter", i -> getBrokenItem(i, RockCutterPowerUnit))).setType(AntimatterDefaultTools.PICKAXE).setToolSupplier((domain, toolType, tier, properties) -> new MaterialRockCutter(domain, toolType, properties, 1));

    static {
        PropertyIngredient.addGetter(CustomTags.BATTERIES_RE.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_SMALL.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_MEDIUM.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.BATTERIES_LARGE.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_LV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_MV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_HV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_SMALL.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_ROCK_CUTTER.location(), ToolTypes::getEnergyAndMat);
    }

    public static void init(){
    }

    private static ItemStack getBrokenItem(ItemStack tool, ItemLike broken){
        ItemStack powerUnit = new ItemStack(broken);
        Tuple<Long, Long> tuple = getEnergy(tool);
        CompoundTag dataTag = powerUnit.getOrCreateTagElement(muramasa.antimatter.Ref.TAG_ITEM_ENERGY_DATA);
        IEnergyHandlerItem handler = TesseractCapUtils.getEnergyHandlerItem(powerUnit).orElse(null);
        if (handler != null){
            handler.setEnergy(tuple.getA());
            handler.setCapacity(tuple.getB());
            powerUnit = handler.getContainer().getItemStack();
        } else {
            dataTag.putLong(muramasa.antimatter.Ref.KEY_ITEM_ENERGY, tuple.getA());
            dataTag.putLong(muramasa.antimatter.Ref.KEY_ITEM_MAX_ENERGY, tuple.getB());
        }
        return powerUnit;
    }

    public static Tuple<Long, Long> getEnergy(ItemStack stack){
        if (stack.getItem() instanceof ItemBattery battery){
            long energy = TesseractCapUtils.getEnergyHandlerItem(stack).map(IGTNode::getEnergy).orElse((long)0);
            long maxEnergy = TesseractCapUtils.getEnergyHandlerItem(stack).map(IGTNode::getCapacity).orElse(battery.getCapacity());
            return new Tuple<>(energy, maxEnergy);
        }
        if (stack.getItem() instanceof IAntimatterTool tool){
            if (tool.getAntimatterToolType().isPowered()){
                long currentEnergy = tool.getCurrentEnergy(stack);
                long maxEnergy = tool.getMaxEnergy(stack);
                return new Tuple<>(currentEnergy, maxEnergy);
            }
        }
        return null;
    }

    public static Tuple<Long, Tuple<Long, Material>> getEnergyAndMat(ItemStack stack){
        if (stack.getItem() instanceof ItemPowerUnit tool){
            long currentEnergy = tool.getCurrentEnergy(stack);
            long maxEnergy = tool.getMaxEnergy(stack);
            return new Tuple<>(currentEnergy, new Tuple<>(maxEnergy, tool.getMaterial(stack)));
        }
        return null;
    }
}
