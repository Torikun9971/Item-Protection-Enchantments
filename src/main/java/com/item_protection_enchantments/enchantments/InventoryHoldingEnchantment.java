package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.item.ItemStack;

public class InventoryHoldingEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().inventoryHoldingEnchantment.minimumPower;
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.getConfig().inventoryHoldingEnchantment.anvilCost;
    }

    @Override
    public int getWeight() {
        return ModConfiguration.getConfig().inventoryHoldingEnchantment.weight;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return isAcceptableItem(stack, ModConfiguration.getConfig().inventoryHoldingEnchantment.enchantableItems);
    }

    @Override
    public boolean isTreasure() {
        return ModConfiguration.getConfig().inventoryHoldingEnchantment.treasureEnchantment;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return ModConfiguration.getConfig().inventoryHoldingEnchantment.tradeable;
    }
}
