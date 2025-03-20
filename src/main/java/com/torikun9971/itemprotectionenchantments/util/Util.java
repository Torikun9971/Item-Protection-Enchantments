package com.torikun9971.itemprotectionenchantments.util;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.ModList;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;

public class Util {
    public static boolean hasEnchantment(Object itemEntity, Enchantment... enchantments) {
        if (itemEntity instanceof ItemEntity item) {
            return hasEnchantment(item, enchantments);
        }

        return false;
    }

    public static boolean hasEnchantment(ItemEntity itemEntity, Enchantment... enchantments) {
        return hasEnchantment(itemEntity.getItem(), enchantments);
    }

    public static boolean hasEnchantment(ItemStack itemStack, Enchantment... enchantments) {
        Map<Enchantment, Integer> enchantmentMap = itemStack.getAllEnchantments();

        for (Enchantment enchantment : enchantments) {
            if (!enchantmentMap.containsKey(enchantment)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isModLoaded(String... modIds) {
        ModList modList = ModList.get();

        for (String id : modIds) {
            if (!modList.isLoaded(id)) {
                return false;
            }
        }

        return true;
    }

    public static <T> boolean isSubClass(String className, Class<T> subType) {
        try {
            Class.forName(className).asSubclass(subType);
        } catch (Throwable e) {
            return false;
        }

        return true;
    }

    public static <T> Optional<? extends T> createInstance(String className, Class<T> type) {
        try {
            Class<? extends T> clazz = Class.forName(className).asSubclass(type);

            return Optional.of(clazz.getConstructor().newInstance());
        } catch (ClassNotFoundException e) {
            ItemProtectionEnchantments.LOGGER.error("Class not found: {}", className);
        } catch (InvocationTargetException e) {
            ItemProtectionEnchantments.LOGGER.error("Failed to instantiate class: {}, caused by: {}", className, e.getCause());
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            ItemProtectionEnchantments.LOGGER.error("Failed to instantiate class: {}", className, e);
        }

        return Optional.empty();
    }
}
