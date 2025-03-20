package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;

public class ExpireProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    protected ModConfiguration.ExpireProtectionConfig getConfig() {
        return ModConfiguration.getConfig().expireProtection;
    }
}
