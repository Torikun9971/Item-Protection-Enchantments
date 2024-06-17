package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;

public class CactusProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.CACTUS_PROTECTION_MIN_COST.get();
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.CACTUS_PROTECTION_RARITY.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return canEnchant(stack, ModConfiguration.CACTUS_PROTECTION_ENCHANTABLE_ITEMS.get());
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfiguration.CACTUS_PROTECTION_TREASURE_ENCHANTMENT.get();
    }

    @Override
    public boolean isTradeable() {
        return ModConfiguration.CACTUS_PROTECTION_TRADEABLE.get();
    }
}
