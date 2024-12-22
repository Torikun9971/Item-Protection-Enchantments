package com.torikun9971.itemprotectionenchantments.blockentities;

import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nullable;
import java.util.Map;

public interface EnchantableBlock {
    void protection_enchantments$setEnchantments(@Nullable Map<Enchantment, Integer> enchantments);

    Map<Enchantment, Integer> protection_enchantments$getEnchantments();

    ListTag protection_enchantments$getEnchantmentTag();
}
