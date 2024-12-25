package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;

public class FireProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    public boolean canEnchant(ItemStack stack) {
        return !stack.getItem().isFireResistant() && super.canEnchant(stack);
    }

    @Override
    protected ModConfiguration.IBaseProtectionConfig getConfig() {
        return ModConfiguration.getConfig().fireProtection;
    }
}
