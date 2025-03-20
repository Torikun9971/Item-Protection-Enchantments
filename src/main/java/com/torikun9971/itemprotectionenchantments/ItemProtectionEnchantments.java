package com.torikun9971.itemprotectionenchantments;

import com.torikun9971.itemprotectionenchantments.compat.Loadable;
import com.torikun9971.itemprotectionenchantments.compat.Plugin;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.event.ModEvents;
import com.mojang.logging.LogUtils;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import com.torikun9971.itemprotectionenchantments.init.ModLootFunctionTypes;
import com.torikun9971.itemprotectionenchantments.util.Util;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.objectweb.asm.Type;
import org.slf4j.Logger;

import java.util.*;

@Mod(ItemProtectionEnchantments.MOD_ID)
public class ItemProtectionEnchantments {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ItemProtectionEnchantments() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEnchantments.ENCHANTMENTS.register(modEventBus);
        ModLootFunctionTypes.LOOT_FUNCTION_TYPES.register(modEventBus);

        loadPlugins(modEventBus);

        modEventBus.addListener(this::setup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::setupClient);
        });
    }

    private List<String> getPluginNames() {
        return ModList.get()
                .getAllScanData()
                .stream()
                .flatMap(scanData -> scanData.getAnnotations().stream())
                .filter(annotation -> Objects.equals(annotation.annotationType(), Type.getType(Plugin.class)))
                .map(ModFileScanData.AnnotationData::memberName)
                .toList();
    }

    private void loadPlugins(IEventBus eventBus) {
        for (String name : getPluginNames()) {
            if (!Util.isSubClass(name, Loadable.class)) continue;
            Optional<? extends Loadable> loadable = Util.createInstance(name, Loadable.class);

            if (loadable.isEmpty()) continue;
            Loadable plugin = loadable.get();

            Plugin annotation = plugin.getClass().getAnnotation(Plugin.class);
            if (annotation == null) {
                LOGGER.error("Plugin annotation missing on class: {}", name);
                continue;
            }

            if (!Util.isModLoaded(annotation.requireMods())) {
                LOGGER.info("Required mods missing for plugin: {}", name);
                continue;
            }

            plugin.load(eventBus);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        IEventBus modEventBus = MinecraftForge.EVENT_BUS;
        modEventBus.register(new ModEvents());

        AutoConfig.register(ModConfiguration.class, JanksonConfigSerializer::new);
    }

    @OnlyIn(Dist.CLIENT)
    private void setupClient(final FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> AutoConfig.getConfigScreen(ModConfiguration.class, parent).get()
                )
        );
    }
}
