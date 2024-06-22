package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;

public class InventoryHoldingEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.INVENTORY_HOLDING_MIN_COST.get();
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.INVENTORY_HOLDING_ANVIL_COST.get();
    }

    @Override
    public int getWeight() {
        return ModConfiguration.INVENTORY_HOLDING_WEIGHT.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return canEnchant(stack, ModConfiguration.INVENTORY_HOLDING_ENCHANTABLE_ITEMS.get());
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfiguration.INVENTORY_HOLDING_TREASURE_ENCHANTMENT.get();
    }

    @Override
    public boolean isTradeable() {
        return ModConfiguration.INVENTORY_HOLDING_TRADEABLE.get();
    }
}
