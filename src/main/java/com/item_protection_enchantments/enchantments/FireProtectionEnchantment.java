package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;

public class FireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.FIRE_PROTECTION_MIN_COST.get();
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.FIRE_PROTECTION_RARITY.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        if (super.canEnchant(stack)) {
            return !stack.getItem().isFireResistant();
        }

        return false;
    }
}
