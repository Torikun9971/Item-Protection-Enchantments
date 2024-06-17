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
        if (canEnchant(stack, ModConfiguration.FIRE_PROTECTION_ENCHANTABLE_ITEMS.get())) {
            return !stack.getItem().isFireResistant();
        }

        return false;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfiguration.FIRE_PROTECTION_TREASURE_ENCHANTMENT.get();
    }

    @Override
    public boolean isTradeable() {
        return ModConfiguration.FIRE_PROTECTION_TRADEABLE.get();
    }
}
