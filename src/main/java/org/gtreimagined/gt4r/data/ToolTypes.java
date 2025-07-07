package org.gtreimagined.gt4r.data;

import com.google.common.collect.ImmutableMap;
import org.gtreimagined.gtlib.GTAPI;
import org.gtreimagined.gtlib.Ref;
import org.gtreimagined.gtlib.data.GTTools;
import org.gtreimagined.gtlib.item.ItemBattery;
import org.gtreimagined.gtlib.material.Material;
import org.gtreimagined.gtlib.recipe.ingredient.PropertyIngredient;
import org.gtreimagined.gtlib.recipe.material.MaterialRecipe;
import org.gtreimagined.gtlib.tool.GTToolType;
import org.gtreimagined.gtlib.tool.IGTTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tuple;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.gtreimagined.gtcore.GTCore;
import org.gtreimagined.gtcore.data.GTCoreTags;
import org.gtreimagined.gtcore.item.ItemPowerUnit;
import org.gtreimagined.tesseract.api.eu.IEnergyHandler;
import org.gtreimagined.tesseract.api.eu.IEnergyHandlerItem;
import org.gtreimagined.tesseract.api.forge.TesseractCaps;
import org.jetbrains.annotations.NotNull;
import org.gtreimagined.gt4r.GT4RRef;
import org.gtreimagined.gt4r.items.IElectricTool;
import org.gtreimagined.gt4r.items.MaterialRockCutter;

import java.util.Map;

import static org.gtreimagined.gtlib.material.Material.NULL;
import static net.minecraft.world.level.material.Material.*;
import static org.gtreimagined.gt4r.data.GT4RItems.RockCutterPowerUnit;

public class ToolTypes {
    public static final MaterialRecipe.Provider POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool", GT4RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Material m = (Material) mats.mats.get("secondary");
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            String domain = id.equals(ROCK_CUTTER.getId()) ? GT4RRef.ID : GTCore.ID;
            IGTTool type = GTAPI.get(IGTTool.class, id.replace('-', '_'), domain);
            return type.resolveStack((Material) mats.mats.get("primary"), m == null ? NULL : m, battery.getA(), battery.getB());
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            CompoundTag nbt = stack.getTag().getCompound(Ref.TAG_TOOL_DATA);
            Material primary = GTAPI.get(Material.class, nbt.getString(Ref.KEY_TOOL_DATA_PRIMARY_MATERIAL));
            Material secondary = GTAPI.get(Material.class, nbt.getString(Ref.KEY_TOOL_DATA_SECONDARY_MATERIAL));
            return ImmutableMap.of("primary", primary, "secondary", secondary, "energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });

    public static final MaterialRecipe.Provider POWERED_TOOL_BUILDER_BASIC = MaterialRecipe.registerProvider("powered-tool-basic", GT4RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            IElectricTool type = GTAPI.get(IElectricTool.class, id.replace('-', '_'), GT4RRef.ID);
            return type.resolveStack(battery.getA(), battery.getB());
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            return ImmutableMap.of("energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });

    public static final MaterialRecipe.Provider UNIT_POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool-from-unit", GT4RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Tuple<Long, Tuple<Long, Material>> t = (Tuple<Long, Tuple<Long, Material>>) mats.mats.get("secondary");
            String domain = id.contains(ROCK_CUTTER.getId()) ? GT4RRef.ID : GTCore.ID;
            IGTTool type = GTAPI.get(IGTTool.class, id.replace('-', '_'), domain);
            t.getB().getB();
            return type.resolveStack((Material) mats.mats.get("primary"), t.getB().getB(), t.getA(), t.getB().getA());
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            CompoundTag nbt = stack.getTag().getCompound(Ref.TAG_TOOL_DATA);
            Material primary = GTAPI.get(Material.class, nbt.getString(Ref.KEY_TOOL_DATA_PRIMARY_MATERIAL));
            Material secondary = GTAPI.get(Material.class, nbt.getString(Ref.KEY_TOOL_DATA_SECONDARY_MATERIAL));
            return ImmutableMap.of("primary", primary, "secondary", secondary, "energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });
    public static GTToolType ROCK_CUTTER = GTAPI.register(GTToolType.class, new GTToolType(GT4RRef.ID, "rock_cutter", 1, 1, 1, -1.0F, -3.0F, false)).setPowered(100000L, 1).setRepairable(false).setOverlayLayers(2).addEffectiveMaterials(ICE_SOLID, METAL, STONE, HEAVY_METAL, PISTON).setBrokenItems(ImmutableMap.of("rock_cutter", i -> getBrokenItem(i, RockCutterPowerUnit))).setType(GTTools.PICKAXE).setToolSpeedMultiplier(0.25f).setToolSupplier((domain, toolType, tier, properties) -> new MaterialRockCutter(domain, toolType, properties, 1));

    static {
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_LV.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_MV.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_HV.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(CustomTags.DRILL.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_LV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_MV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_HV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_SMALL.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(CustomTags.POWER_UNIT_ROCK_CUTTER.location(), ToolTypes::getEnergyAndMat);
    }

    public static void init(){
    }

    private static ItemStack getBrokenItem(ItemStack tool, ItemLike broken){
        ItemStack powerUnit = new ItemStack(broken);
        Tuple<Long, Long> tuple = getEnergy(tool);
        CompoundTag dataTag = powerUnit.getOrCreateTagElement(Ref.TAG_ITEM_ENERGY_DATA);
        IEnergyHandlerItem handler = powerUnit.getCapability(TesseractCaps.ENERGY_HANDLER_CAPABILITY_ITEM).resolve().orElse(null);
        if (handler != null){
            handler.setEnergy(tuple.getA());
            handler.setCapacity(tuple.getB());
            powerUnit = handler.getContainer().getItemStack();
        } else {
            dataTag.putLong(Ref.KEY_ITEM_ENERGY, tuple.getA());
            dataTag.putLong(Ref.KEY_ITEM_MAX_ENERGY, tuple.getB());
        }
        return powerUnit;
    }

    public static Tuple<Long, Long> getEnergy(ItemStack stack){
        if (stack.getItem() instanceof ItemBattery battery){
            long energy = stack.getCapability(TesseractCaps.ENERGY_HANDLER_CAPABILITY_ITEM).map(IEnergyHandler::getEnergy).orElse((long)0);
            long maxEnergy = stack.getCapability(TesseractCaps.ENERGY_HANDLER_CAPABILITY_ITEM).map(IEnergyHandler::getCapacity).orElse(battery.getCapacity());
            return new Tuple<>(energy, maxEnergy);
        }
        if (stack.getItem() instanceof IGTTool tool){
            if (tool.getGTToolType().isPowered()){
                long currentEnergy = tool.getCurrentEnergy(stack);
                long maxEnergy = tool.getMaxEnergy(stack);
                return new Tuple<>(currentEnergy, maxEnergy);
            }
        }
        if (stack.getItem() instanceof IElectricTool tool){
            if (tool.getGTToolType().isPowered()){
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
