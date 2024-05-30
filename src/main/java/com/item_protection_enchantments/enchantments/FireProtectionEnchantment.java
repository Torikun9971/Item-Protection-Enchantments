package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class FireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().fireProtectionEnchantment.minimumPower;
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.getConfig().fireProtectionEnchantment.anvilCost;
    }

    @Override
    public int getWeight() {
        return ModConfiguration.getConfig().fireProtectionEnchantment.weight;
    }
}
