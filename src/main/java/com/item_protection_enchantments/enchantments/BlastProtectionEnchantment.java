package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;

public class BlastProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().blastProtectionEnchantment.minimumPower;
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.getConfig().blastProtectionEnchantment.rarity;
    }
}
