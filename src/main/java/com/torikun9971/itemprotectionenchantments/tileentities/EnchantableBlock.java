package com.torikun9971.itemprotectionenchantments.tileentities;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.ListNBT;

import javax.annotation.Nullable;
import java.util.Map;

public interface EnchantableBlock {
    void protection_enchantments$setEnchantments(@Nullable Map<Enchantment, Integer> enchantments);

    Map<Enchantment, Integer> protection_enchantments$getEnchantments();

    ListNBT protection_enchantments$getEnchantmentTag();
}
