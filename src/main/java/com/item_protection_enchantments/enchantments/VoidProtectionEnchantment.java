package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class VoidProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().voidProtectionEnchantment.minimumPower;
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.getConfig().voidProtectionEnchantment.rarity;
    }
}
