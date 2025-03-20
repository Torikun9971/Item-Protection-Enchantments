package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BlastProtectionEnchantment extends BaseProtectionEnchantment {
    @Override
    public boolean canEnchant(ItemStack stack) {
        return !stack.is(Items.NETHER_STAR) && super.canEnchant(stack);
    }

    @Override
    protected ModConfiguration.BlastProtectionConfig getConfig() {
        return ModConfiguration.getConfig().blastProtection;
    }
}
