package com.item_protection_enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.enchantments.event.ItemProtectionEvent;
import com.item_protection_enchantments.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Locale;
import java.util.Map;

@Mod(ItemProtectionEnchantments.MOD_ID)
public class ItemProtectionEnchantments {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ItemProtectionEnchantments() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEnchantments.ENCHANTMENTS.register(modEventBus);
        ModLootFunctionTypes.LOOT_FUNCTION_TYPES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfiguration.COMMON_CONFIG, String.format(Locale.ROOT, "%s.toml", MOD_ID));

        modEventBus.addListener(this::setup);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::setupClient);
        });
    }

    private void setup(final FMLCommonSetupEvent event) {
        IEventBus modEventBus = MinecraftForge.EVENT_BUS;
        modEventBus.register(new ItemProtectionEvent());
    }

    @OnlyIn(Dist.CLIENT)
    private void setupClient(final FMLClientSetupEvent event) {

    }

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Enchantment... enchantments) {
        Map<Enchantment, Integer> enchantmentsMap = itemStack.getAllEnchantments();

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
