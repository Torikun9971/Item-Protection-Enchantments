package com.item_protection_enchantments.blockentities;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface EnchantableBlock {
    void setEnchantments(@Nullable Map<Enchantment, Integer> enchantments);

    Map<Enchantment, Integer> getEnchantments();

    NbtList getEnchantmentNbt();
}
