package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;

public class VoidProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    protected ModConfiguration.VoidProtectionConfig getConfig() {
        return ModConfiguration.getConfig().voidProtection;
    }

    public enum ProtectionHeights { HEIGHT_WHERE_ENTITY_TAKES_DAMAGE, MIN_BUILD_HEIGHT }
}
