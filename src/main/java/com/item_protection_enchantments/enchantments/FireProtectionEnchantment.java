package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.item.ItemStack;

public class FireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().fireProtectionEnchantment.minimumPower;
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.getConfig().fireProtectionEnchantment.rarity;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (super.isAcceptableItem(stack)) {
            return !stack.getItem().isFireproof();
        }

        return false;
    }
}
