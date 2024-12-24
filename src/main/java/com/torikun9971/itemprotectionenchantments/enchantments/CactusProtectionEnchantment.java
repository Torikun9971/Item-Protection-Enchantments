package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;

public class CactusProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    protected ModConfiguration.IBaseProtectionConfig getConfig() {
        return ModConfiguration.getConfig().cactusProtection;
    }
}
