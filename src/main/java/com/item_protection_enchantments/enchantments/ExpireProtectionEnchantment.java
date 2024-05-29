package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class ExpireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().expireProtectionEnchantment.minimumPower;
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.getConfig().expireProtectionEnchantment.rarity;
    }
}
