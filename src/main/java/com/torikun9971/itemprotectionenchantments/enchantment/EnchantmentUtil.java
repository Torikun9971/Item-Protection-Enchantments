package com.torikun9971.itemprotectionenchantments.enchantment;

import com.google.common.collect.Maps;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.*;

public class EnchantmentUtil {
    public static Map<Identifier, Integer> extractEnchantments(ItemStack stack) {
        ItemEnchantmentsComponent component = stack.getEnchantments();
        Map<Identifier, Integer> identifiers = Maps.newHashMap();

        for (RegistryEntry<Enchantment> enchantment : component.getEnchantments()) {
            if (enchantment.getKey().isEmpty()) continue;
            RegistryKey<Enchantment> key = enchantment.getKey().get();

            identifiers.put(key.getValue(), component.getLevel(enchantment));
        }

        return identifiers;
    }

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Identifier... enchantments) {
        Map<Identifier, Integer> identifiers = extractEnchantments(itemStack);

        for (Identifier identifier : enchantments) {
            int lvl = identifiers.getOrDefault(identifier, 0);

            if (mustHaveAll && lvl < 1)
                return false;

            if (!mustHaveAll && lvl > 0)
                return true;
        }

        return mustHaveAll;
    }
}
