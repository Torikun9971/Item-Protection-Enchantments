package com.torikun9971.itemprotectionenchantments.enchantments;

import net.minecraft.item.ItemStack;

public interface EnchantmentCondition {
    boolean condition(ItemStack stack);
}
