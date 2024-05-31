package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BlastProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().blastProtectionEnchantment.minimumPower;
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.getConfig().blastProtectionEnchantment.rarity;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (super.isAcceptableItem(stack)) {
            return stack.getItem() != Items.NETHER_STAR;
        }

        return false;
    }
}
