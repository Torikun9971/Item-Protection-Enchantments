package com.item_protection_enchantments.blockentities;

import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nullable;
import java.util.Map;

public interface EnchantableBlock {
    void setEnchantments(@Nullable Map<Enchantment, Integer> enchantments);

    Map<Enchantment, Integer> getEnchantments();

    ListTag getEnchantmentTag();
}
