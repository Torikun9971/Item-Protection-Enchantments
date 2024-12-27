package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;

public class InventoryHoldingEnchantment extends BaseProtectionEnchantment {
    public static final int ANVIL_COST = 6;

    @Override
    protected ModConfiguration.IBaseProtectionConfig getConfig() {
        return ModConfiguration.getConfig().inventoryHolding;
    }
}
