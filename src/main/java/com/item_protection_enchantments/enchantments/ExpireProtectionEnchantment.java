package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.item.ItemStack;

public class ExpireProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().expireProtectionEnchantment.minimumPower;
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.getConfig().expireProtectionEnchantment.rarity;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return isAcceptableItem(stack, ModConfiguration.getConfig().expireProtectionEnchantment.enchantableItems);
    }

    @Override
    public boolean isTreasure() {
        return ModConfiguration.getConfig().expireProtectionEnchantment.treasureEnchantment;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return ModConfiguration.getConfig().expireProtectionEnchantment.tradeable;
    }
}
