package com.torikun9971.itemprotectionenchantments.enchantment;

import com.google.common.collect.Maps;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Map;

public class EnchantmentUtil {
    public static Map<ResourceLocation, Integer> extractEnchantments(ItemStack stack) {
        ItemEnchantments enchantments = stack.getTagEnchantments();
        Map<ResourceLocation, Integer> locations = Maps.newHashMap();

        for (Holder<Enchantment> enchantment : enchantments.keySet()) {
            if (!enchantment.isBound() || enchantment.unwrapKey().isEmpty()) continue;
            ResourceKey<Enchantment> key = enchantment.unwrapKey().get();

            locations.put(key.location(), enchantments.getLevel(enchantment));
        }

        return locations;
    }

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, ResourceLocation... enchantments) {
        Map<ResourceLocation, Integer> locations = extractEnchantments(itemStack);

        for (ResourceLocation location : enchantments) {
            int lvl = locations.getOrDefault(location, 0);

            if (mustHaveAll && lvl < 1)
                return false;

            if (!mustHaveAll && lvl > 0)
                return true;
        }

        return mustHaveAll;
    }
}
