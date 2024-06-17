package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;

public class InventoryHoldingEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.INVENTORY_HOLDING_MIN_COST.get();
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.INVENTORY_HOLDING_RARITY.get();
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
