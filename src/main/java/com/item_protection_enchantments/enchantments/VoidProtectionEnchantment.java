package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;

public class VoidProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.VOID_PROTECTION_MIN_COST.get();
    }

    @Override
    public int getAnvilCost() {
        return ModConfiguration.VOID_PROTECTION_ANVIL_COST.get();
    }

    @Override
    public int getWeight() {
        return ModConfiguration.VOID_PROTECTION_WEIGHT.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return canEnchant(stack, ModConfiguration.VOID_PROTECTION_ENCHANTABLE_ITEMS.get());
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfiguration.VOID_PROTECTION_TREASURE_ENCHANTMENT.get();
    }

    @Override
    public boolean isTradeable() {
        return ModConfiguration.VOID_PROTECTION_TRADEABLE.get();
    }

    public enum ProtectionHeights { HEIGHT_WHERE_ENTITY_TAKES_DAMAGE, MIN_BUILD_HEIGHT }
}
