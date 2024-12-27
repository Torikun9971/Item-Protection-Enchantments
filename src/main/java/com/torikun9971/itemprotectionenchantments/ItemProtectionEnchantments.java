package com.torikun9971.itemprotectionenchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.event.ModEvents;
import com.mojang.logging.LogUtils;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ItemProtectionEnchantments.MOD_ID)
public class ItemProtectionEnchantments {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ItemProtectionEnchantments() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEnchantments.ENCHANTMENTS.register(modEventBus);

        modEventBus.addListener(this::setup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::setupClient);
        });
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

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            int lvl = EnchantmentHelper.getItemEnchantmentLevel(enchantment, itemStack);

            if (mustHaveAll && lvl < 1)
                return false;

            if (!mustHaveAll && lvl > 0)
                return true;
        }

        return mustHaveAll;
    }
}
