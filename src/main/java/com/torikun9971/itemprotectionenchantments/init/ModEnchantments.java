package com.torikun9971.itemprotectionenchantments.init;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEnchantments {
    public static Enchantment FIRE_PROTECTION_ITEM;
    public static Enchantment CACTUS_PROTECTION_ITEM;
    public static Enchantment BLAST_PROTECTION_ITEM;
    public static Enchantment VOID_PROTECTION_ITEM;
    public static Enchantment EXPIRE_PROTECTION_ITEM;
    public static Enchantment INVENTORY_HOLDING;

    public static void init() {
        FIRE_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, ItemProtectionEnchantments.id("fire_protection_item"), new FireProtectionEnchantment());
        CACTUS_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, ItemProtectionEnchantments.id("cactus_protection_item"), new CactusProtectionEnchantment());
        BLAST_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, ItemProtectionEnchantments.id("blast_protection_item"), new BlastProtectionEnchantment());
        VOID_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, ItemProtectionEnchantments.id("void_protection_item"), new VoidProtectionEnchantment());
        EXPIRE_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, ItemProtectionEnchantments.id("expire_protection_item"), new ExpireProtectionEnchantment());
        INVENTORY_HOLDING = Registry.register(Registries.ENCHANTMENT, ItemProtectionEnchantments.id("inventory_holding"), new InventoryHoldingEnchantment());
    }
}
