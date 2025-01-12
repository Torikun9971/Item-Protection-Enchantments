package com.torikun9971.itemprotectionenchantments.interfaces;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface EnchantableBlock {
    void protection_enchantments$setEnchantments(@Nullable Map<Enchantment, Integer> enchantments);

    Map<Enchantment, Integer> protection_enchantments$getEnchantments();

    NbtList protection_enchantments$getEnchantmentNbt();
}
