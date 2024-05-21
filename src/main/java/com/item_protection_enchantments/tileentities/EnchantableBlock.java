package com.item_protection_enchantments.tileentities;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.ListNBT;

import javax.annotation.Nullable;
import java.util.Map;

public interface EnchantableBlock {
    void setEnchantments(@Nullable Map<Enchantment, Integer> enchantments);

    Map<Enchantment, Integer> getEnchantments();

    ListNBT getEnchantmentTag();
}
