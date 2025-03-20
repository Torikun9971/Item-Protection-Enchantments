package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;

public class InventoryHoldingEnchantment extends BaseProtectionEnchantment {
    @Override
    protected ModConfiguration.InventoryHoldingConfig getConfig() {
        return ModConfiguration.getConfig().inventoryHolding;
    }
}
