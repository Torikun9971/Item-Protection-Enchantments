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
    public int getAnvilCost() {
        return ModConfiguration.getConfig().blastProtectionEnchantment.anvilCost;
    }

    @Override
    public int getWeight() {
        return ModConfiguration.getConfig().blastProtectionEnchantment.weight;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (isAcceptableItem(stack, ModConfiguration.getConfig().blastProtectionEnchantment.enchantableItems)) {
            return stack.getItem() != Items.NETHER_STAR;
        }

        return false;
    }

    @Override
    public boolean isTreasure() {
        return ModConfiguration.getConfig().blastProtectionEnchantment.treasureEnchantment;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return ModConfiguration.getConfig().blastProtectionEnchantment.tradeable;
    }
}
