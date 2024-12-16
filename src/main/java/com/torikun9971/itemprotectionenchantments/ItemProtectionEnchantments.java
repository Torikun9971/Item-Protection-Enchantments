package com.torikun9971.itemprotectionenchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.event.ModEvents;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import com.torikun9971.itemprotectionenchantments.init.ModLootFunctionTypes;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(ItemProtectionEnchantments.MOD_ID)
public class ItemProtectionEnchantments {
    public static final String MOD_ID = "protection_enchantments";
    public static final Logger LOGGER = LogManager.getLogger();

    public ItemProtectionEnchantments() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEnchantments.ENCHANTMENTS.register(modEventBus);
        modEventBus.register(ModLootFunctionTypes.LOOT_FUNCTION_TYPES);

        AutoConfig.register(ModConfiguration.class, JanksonConfigSerializer::new);

        modEventBus.addListener(this::setup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::setupClient);
        });
    }

    private void setup(final FMLCommonSetupEvent event) {
        IEventBus modEventBusEvent = MinecraftForge.EVENT_BUS;

        modEventBusEvent.register(new ModEvents());
    }

    @OnlyIn(Dist.CLIENT)
    private void setupClient(final FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () ->
                (client, parent) -> AutoConfig.getConfigScreen(ModConfiguration.class, parent).get()
        );
    }

    public static boolean hasEnchantment(ItemStack itemStack, boolean mustHaveAll, Enchantment... enchantments) {
        Map<Enchantment, Integer> enchantmentsMap = EnchantmentHelper.getEnchantments(itemStack);

        for (Enchantment enchantment : enchantments) {
            if (mustHaveAll && !enchantmentsMap.containsKey(enchantment))
                return false;

            if (!mustHaveAll && enchantmentsMap.containsKey(enchantment))
                return true;
        }

        return mustHaveAll;
    }
}
