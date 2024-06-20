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
        if (isAcceptableItem(stack, ModConfiguration.getConfig().fireProtectionEnchantment.enchantableItems)) {
            return !stack.getItem().isFireproof();
        }

        return false;
    }

    @Override
    public boolean isTreasure() {
        return ModConfiguration.getConfig().fireProtectionEnchantment.treasureEnchantment;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return ModConfiguration.getConfig().fireProtectionEnchantment.tradeable;
    }
}
