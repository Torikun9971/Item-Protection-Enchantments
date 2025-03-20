package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;

public class AcidProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    protected ModConfiguration.AcidProtectionConfig getConfig() {
        return ModConfiguration.getConfig().acidProtection;
    }
}