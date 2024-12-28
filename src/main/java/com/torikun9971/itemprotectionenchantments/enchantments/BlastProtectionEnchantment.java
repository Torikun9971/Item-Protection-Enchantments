package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BlastProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return !stack.isOf(Items.NETHER_STAR) && super.isAcceptableItem(stack);
    }

    @Override
    protected ModConfiguration.IBaseProtectionConfig getConfig() {
        return ModConfiguration.getConfig().blastProtection;
    }
}
