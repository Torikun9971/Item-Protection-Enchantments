package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class VoidProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().voidProtectionEnchantment.minimumPower;
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.getConfig().voidProtectionEnchantment.anvilCost;
    }

    @Override
    public int getWeight() {
        return ModConfiguration.getConfig().voidProtectionEnchantment.weight;
    }
}
