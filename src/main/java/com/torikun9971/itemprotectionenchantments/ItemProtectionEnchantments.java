package com.torikun9971.itemprotectionenchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.event.ModEvents;
import com.mojang.logging.LogUtils;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import com.torikun9971.itemprotectionenchantments.init.ModLootFunctionTypes;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.util.Map;

@Mod(ItemProtectionEnchantments.MOD_ID)
public class ItemProtectionEnchantments {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ItemProtectionEnchantments(IEventBus eventBus) {
        ModEnchantments.ENCHANTMENTS.register(eventBus);
        ModLootFunctionTypes.LOOT_FUNCTION_TYPES.register(eventBus);

        eventBus.addListener(this::setup);

        if (FMLEnvironment.dist.isClient()) {
            eventBus.addListener(this::setupClient);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        IEventBus modEventBus = NeoForge.EVENT_BUS;
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

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Enchantment... enchantments) {
        Map<Enchantment, Integer> enchantmentsMap = itemStack.getAllEnchantments();

        for (Enchantment enchantment : enchantments) {
            if (mustHaveAll && !enchantmentsMap.containsKey(enchantment))
                return false;

            if (!mustHaveAll && enchantmentsMap.containsKey(enchantment))
                return true;
        }

        return mustHaveAll;
    }
}
