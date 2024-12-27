package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import net.minecraft.item.ItemStack;

public class FireProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return !stack.getItem().isFireproof() && super.isAcceptableItem(stack);
    }

    @Override
    protected ModConfiguration.IBaseProtectionConfig getConfig() {
        return ModConfiguration.getConfig().fireProtection;
    }
}
