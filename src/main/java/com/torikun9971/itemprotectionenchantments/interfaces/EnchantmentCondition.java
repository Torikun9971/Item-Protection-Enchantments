package com.torikun9971.itemprotectionenchantments.interfaces;

import net.minecraft.item.ItemStack;

public interface EnchantmentCondition {
    boolean condition(ItemStack stack);
}
