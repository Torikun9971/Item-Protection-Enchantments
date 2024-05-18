package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

import javax.annotation.Nonnull;

public class ExpireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.EXPIRE_PROTECTION_MIN_COST.get();
    }

    @Nonnull
    @Override
    public Rarity getRarity() {
        return ModConfiguration.EXPIRE_PROTECTION_RARITY.get();
    }
}
