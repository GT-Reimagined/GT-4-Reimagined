package org.gtreimagined.gt4r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class CraftingHelper {
    public static CriterionTriggerInstance criterion(ItemLike item, AntimatterRecipeProvider provider){
        return provider.hasSafeItem(item);
    }

    public static CriterionTriggerInstance criterion(TagKey<Item> item, AntimatterRecipeProvider provider){
        return provider.hasSafeItem(item);
    }

    public static <K, V> ImmutableMap<K, V> of2(
            K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return ImmutableMap.<K, V>builder().put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4).put(k5, v5).put(k6, v6).build();
    }

    public static <K, V> ImmutableMap<K, V> of2(
            K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        return ImmutableMap.<K, V>builder().put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4).put(k5, v5).put(k6, v6).put(k7, v7).build();
    }

    public static <K, V> ImmutableMap<K, V> of2(
            K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        return ImmutableMap.<K, V>builder().put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4).put(k5, v5).put(k6, v6).put(k7, v7).put(k8, v8).build();
    }

    public static <K, V> ImmutableMap<K, V> of2(
            K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
        return ImmutableMap.<K, V>builder().put(k1, v1).put(k2, v2).put(k3, v3).put(k4, v4).put(k5, v5).put(k6, v6).put(k7, v7).put(k8, v8).put(k9, v9).build();
    }
}
