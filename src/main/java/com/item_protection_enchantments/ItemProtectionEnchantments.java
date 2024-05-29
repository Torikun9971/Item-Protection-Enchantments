package com.item_protection_enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import com.item_protection_enchantments.init.ModLootFunctionTypes;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ItemProtectionEnchantments implements ModInitializer {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModEnchantments.init();
        ModLootFunctionTypes.init();

        AutoConfig.register(ModConfiguration.class, JanksonConfigSerializer::new);
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register(((server, resourceManager) -> AutoConfig.getConfigHolder(ModConfiguration.class).load()));
    }

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Enchantment... enchantments) {
        Map<Enchantment, Integer> enchantmentsMap = EnchantmentHelper.get(itemStack);

        for (Enchantment enchantment : enchantments) {
            if (mustHaveAll) {
                if (!enchantmentsMap.containsKey(enchantment)) {
                    return false;
                }
            } else {
                if (enchantmentsMap.containsKey(enchantment)) {
                    return true;
                }
            }
        }

        return mustHaveAll;
    }
}
