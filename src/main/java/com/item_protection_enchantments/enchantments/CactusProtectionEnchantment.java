package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class CactusProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().cactusProtectionEnchantment.minimumPower;
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.getConfig().cactusProtectionEnchantment.anvilCost;
    }

    @Override
    public int getWeight() {
        return ModConfiguration.getConfig().cactusProtectionEnchantment.weight;
    }
}
