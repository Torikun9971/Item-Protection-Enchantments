package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.item.ItemStack;

public class VoidProtectionEnchantment extends ItemProtectionEnchantment {
    @Override
    public int getMinPower(int level) {
        return ModConfiguration.getConfig().voidProtectionEnchantment.minimumPower;
    }

    @Override
    public Rarity getRarity() {
        return ModConfiguration.getConfig().voidProtectionEnchantment.rarity;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return isAcceptableItem(stack, ModConfiguration.getConfig().voidProtectionEnchantment.enchantableItems);
    }

    @Override
    public boolean isTreasure() {
        return ModConfiguration.getConfig().voidProtectionEnchantment.treasureEnchantment;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return ModConfiguration.getConfig().voidProtectionEnchantment.tradeable;
    }

    public enum ProtectionHeights { HEIGHT_WHERE_ENTITY_TAKES_DAMAGE, MIN_BUILD_HEIGHT }
}
