package trinsdar.gt4r.datagen;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.LootParameter;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import trinsdar.gt4r.Ref;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GT4RRandomDropBonus extends LootFunction {
    private static final Map<ResourceLocation, IFormulaDeserializer> DESERIALIZER_HASH_MAP = Maps.newHashMap();
    private final Enchantment enchantment;
    private final IFormula formula;

    public static final LootFunctionType RANDOM_DROP_BONUS = new LootFunctionType(new Serializer());

    public GT4RRandomDropBonus(ILootCondition[] conditionsIn, Enchantment enchantmentIn, IFormula formula) {
        super(conditionsIn);
        this.enchantment = enchantmentIn;
        this.formula = formula;
    }

    public LootFunctionType getFunctionType() {
        return RANDOM_DROP_BONUS;
    }

    public Set<LootParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootParameters.TOOL);
    }

    public ItemStack doApply(ItemStack stack, LootContext context) {
        ItemStack itemstack = context.get(LootParameters.TOOL);
        if (itemstack != null) {
            int i = EnchantmentHelper.getEnchantmentLevel(this.enchantment, itemstack);
            int j = this.formula.getValue(context.getRandom(), stack.getCount(), i);
            if (j == 0) return ItemStack.EMPTY;
            stack.setCount(j);
        }

        return stack;
    }

    public static LootFunction.Builder<?> randomDrops(Enchantment enchantment, int dividend) {
        return builder((conditions) -> {
            return new GT4RRandomDropBonus(conditions, enchantment, new RandomDropsFormula(dividend));
        });
    }

    public static LootFunction.Builder<?> uniformBonusCount(Enchantment enchantment, int multiplier) {
        return builder((conditions) -> {
            return new GT4RRandomDropBonus(conditions, enchantment, new UniformMultipliedFormula(multiplier));
        });
    }

    static {
        DESERIALIZER_HASH_MAP.put(UniformMultipliedFormula.ID, UniformMultipliedFormula::deserialize);
        DESERIALIZER_HASH_MAP.put(RandomDropsFormula.ID, RandomDropsFormula::deserialize);
    }

    interface IFormula {
        int getValue(Random random, int initialCount, int enchantmentLevel);

        void serialize(JsonObject json, JsonSerializationContext context);

        ResourceLocation getID();
    }

    interface IFormulaDeserializer {
        IFormula deserialize(JsonObject jsonObject, JsonDeserializationContext context);
    }

    static final class RandomDropsFormula implements IFormula {
        public static final ResourceLocation ID = new ResourceLocation(Ref.ID,"random_drops");

        private final int dividend;

        private RandomDropsFormula(int dividend) {
            this.dividend = dividend;
        }

        public int getValue(Random random, int initialCount, int enchantmentLevel) {
            if (random.nextInt(Math.max(1, dividend / (enchantmentLevel + 1))) == 0){
                return 1;
            }
            return 0;
        }

        public void serialize(JsonObject json, JsonSerializationContext context) {
            json.addProperty("dividend", dividend);
        }


        public static IFormula deserialize(JsonObject json, JsonDeserializationContext context) {
            return new RandomDropsFormula(json.has("dividend") ? json.getAsJsonPrimitive("dividend").getAsInt() : 4);
        }

        public ResourceLocation getID() {
            return ID;
        }
    }

    static final class UniformMultipliedFormula implements IFormula {
        public static final ResourceLocation ID = new ResourceLocation(Ref.ID,"uniform_multiplied_drops");

        private final int multiplier;

        private UniformMultipliedFormula(int multiplier) {
            this.multiplier = multiplier;
        }

        public int getValue(Random random, int initialCount, int enchantmentLevel) {
            return initialCount + (multiplier * random.nextInt(1 + enchantmentLevel));
        }

        public void serialize(JsonObject json, JsonSerializationContext context) {
            json.addProperty("multiplier", multiplier);
        }

        public static IFormula deserialize(JsonObject json, JsonDeserializationContext context) {
            return new UniformMultipliedFormula(json.has("multiplier") ? json.getAsJsonPrimitive("multiplier").getAsInt() : 3);
        }

        public ResourceLocation getID() {
            return ID;
        }
    }

    public static class Serializer extends LootFunction.Serializer<GT4RRandomDropBonus> {
        public void serialize(JsonObject json, GT4RRandomDropBonus bonus, JsonSerializationContext context) {
            super.serialize(json, bonus, context);
            json.addProperty("enchantment", Registry.ENCHANTMENT.getKey(bonus.enchantment).toString());
            json.addProperty("formula", bonus.formula.getID().toString());
            JsonObject jsonobject = new JsonObject();
            bonus.formula.serialize(jsonobject, context);
            if (jsonobject.size() > 0) {
                json.add("parameters", jsonobject);
            }

        }

        public GT4RRandomDropBonus deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
            ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getString(object, "enchantment"));
            Enchantment enchantment = Registry.ENCHANTMENT.getOptional(resourcelocation).orElseThrow(() -> {
                return new JsonParseException("Invalid enchantment id: " + resourcelocation);
            });
            ResourceLocation resourcelocation1 = new ResourceLocation(JSONUtils.getString(object, "formula"));
            IFormulaDeserializer applybonus$iformuladeserializer = GT4RRandomDropBonus.DESERIALIZER_HASH_MAP.get(resourcelocation1);
            if (applybonus$iformuladeserializer == null) {
                throw new JsonParseException("Invalid formula id: " + resourcelocation1);
            } else {
                IFormula applybonus$iformula;
                if (object.has("parameters")) {
                    applybonus$iformula = applybonus$iformuladeserializer.deserialize(JSONUtils.getJsonObject(object, "parameters"), deserializationContext);
                } else {
                    applybonus$iformula = applybonus$iformuladeserializer.deserialize(new JsonObject(), deserializationContext);
                }

                return new GT4RRandomDropBonus(conditionsIn, enchantment, applybonus$iformula);
            }
        }
    }
}
