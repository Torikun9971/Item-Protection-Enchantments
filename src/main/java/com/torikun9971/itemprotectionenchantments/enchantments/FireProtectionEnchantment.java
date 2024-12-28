package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class FireProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    public boolean canEnchant(ItemStack stack) {
        return !stack.getComponents().has(DataComponents.FIRE_RESISTANT) && super.canEnchant(stack);
    }

    @Override
    protected ModConfiguration.IBaseProtectionConfig getConfig() {
        return ModConfiguration.getConfig().fireProtection;
    }
}
