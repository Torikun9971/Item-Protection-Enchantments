package com.item_protection_enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.enchantments.event.ItemProtectionEvent;
import com.item_protection_enchantments.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.util.Locale;

@Mod(ItemProtectionEnchantments.MOD_ID)
public class ItemProtectionEnchantments {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ItemProtectionEnchantments(IEventBus eventBus, ModContainer container) {
        ModEnchantments.ENCHANTMENTS.register(eventBus);

        container.registerConfig(ModConfig.Type.COMMON, ModConfiguration.COMMON_CONFIG, String.format(Locale.ROOT, "%s.toml", MOD_ID));

        eventBus.addListener(this::setup);
        eventBus.addListener(this::setupClient);
    }

    private void setup(final FMLCommonSetupEvent event) {
        IEventBus modEventBus = NeoForge.EVENT_BUS;
        modEventBus.register(new ItemProtectionEvent());
    }

    @OnlyIn(Dist.CLIENT)
    private void setupClient(final FMLClientSetupEvent event) {

    }

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            int lvl = itemStack.getEnchantmentLevel(enchantment);

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
