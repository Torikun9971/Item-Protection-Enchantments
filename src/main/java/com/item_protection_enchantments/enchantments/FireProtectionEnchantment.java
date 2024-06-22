package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class FireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.FIRE_PROTECTION_MIN_COST.get();
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.FIRE_PROTECTION_ANVIL_COST.get();
    }

    @Override
    public int getWeight() {
        return ModConfiguration.FIRE_PROTECTION_WEIGHT.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        if (canEnchant(stack, ModConfiguration.FIRE_PROTECTION_ENCHANTABLE_ITEMS.get())) {
            return !stack.getComponents().has(DataComponents.FIRE_RESISTANT);
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
