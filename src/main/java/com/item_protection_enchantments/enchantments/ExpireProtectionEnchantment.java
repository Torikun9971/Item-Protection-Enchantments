package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;

public class ExpireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.EXPIRE_PROTECTION_MIN_COST.get();
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.EXPIRE_PROTECTION_ANVIL_COST.get();
    }

    @Override
    public int getWeight() {
        return ModConfiguration.EXPIRE_PROTECTION_WEIGHT.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return canEnchant(stack, ModConfiguration.EXPIRE_PROTECTION_ENCHANTABLE_ITEMS.get());
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfiguration.EXPIRE_PROTECTION_TREASURE_ENCHANTMENT.get();
    }

    @Override
    public boolean isTradeable() {
        return ModConfiguration.EXPIRE_PROTECTION_TRADEABLE.get();
    }
}
