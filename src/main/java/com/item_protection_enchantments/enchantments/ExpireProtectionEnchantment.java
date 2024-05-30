package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class ExpireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().expireProtectionEnchantment.minimumPower;
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.getConfig().expireProtectionEnchantment.anvilCost;
    }

    @Override
    public int getWeight() {
        return ModConfiguration.getConfig().expireProtectionEnchantment.weight;
    }
}
