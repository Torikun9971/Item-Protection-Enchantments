package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BlastProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinCost(int enchantmentLevel) {
        return ModConfiguration.BLAST_PROTECTION_MIN_COST.get();
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.BLAST_PROTECTION_RARITY.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        if (canEnchant(stack, ModConfiguration.BLAST_PROTECTION_ENCHANTABLE_ITEMS.get())) {
            return stack.getItem() != Items.NETHER_STAR;
        }

        return false;
    }

    @Override
    public boolean isTreasureOnly() {
        return ModConfiguration.BLAST_PROTECTION_TREASURE_ENCHANTMENT.get();
    }

    @Override
    public boolean isTradeable() {
        return ModConfiguration.BLAST_PROTECTION_TRADEABLE.get();
    }
}
