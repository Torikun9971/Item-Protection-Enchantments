package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

import javax.annotation.Nonnull;

public class VoidProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.VOID_PROTECTION_MIN_COST.get();
    }

    @Nonnull
    @Override
    public Rarity getRarity() {
        return ModConfiguration.VOID_PROTECTION_RARITY.get();
    }
}
