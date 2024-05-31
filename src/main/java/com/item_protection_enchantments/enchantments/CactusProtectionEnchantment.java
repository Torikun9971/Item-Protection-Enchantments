package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class CactusProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.CACTUS_PROTECTION_MIN_COST.get();
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.CACTUS_PROTECTION_RARITY.get();
    }
}
