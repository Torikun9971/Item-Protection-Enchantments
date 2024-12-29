package com.torikun9971.itemprotectionenchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemProtectionEnchantments implements ModInitializer {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfiguration.class, JanksonConfigSerializer::new);
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, resourceManager) -> AutoConfig.getConfigHolder(ModConfiguration.class).load());
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
