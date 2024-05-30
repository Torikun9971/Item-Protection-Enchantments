package com.item_protection_enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemProtectionEnchantments implements ModInitializer {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModEnchantments.init();

        AutoConfig.register(ModConfiguration.class, JanksonConfigSerializer::new);
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register(((server, resourceManager) -> AutoConfig.getConfigHolder(ModConfiguration.class).load()));
    }

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            int lvl = EnchantmentHelper.getLevel(enchantment, itemStack);

            if (mustHaveAll) {
                if (lvl < 1) {
                    return false;
                }
            } else {
                if (lvl > 0) {
                    return true;
                }
            }
        }

        return mustHaveAll;
    }
}
