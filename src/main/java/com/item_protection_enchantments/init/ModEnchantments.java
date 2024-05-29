package com.item_protection_enchantments.init;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static Enchantment FIRE_PROTECTION_ITEM;
    public static Enchantment CACTUS_PROTECTION_ITEM;
    public static Enchantment BLAST_PROTECTION_ITEM;
    public static Enchantment VOID_PROTECTION_ITEM;
    public static Enchantment EXPIRE_PROTECTION_ITEM;

    public static void init() {
        FIRE_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, new Identifier(ItemProtectionEnchantments.MOD_ID, "fire_protection_item"), new FireProtectionEnchantment());
        CACTUS_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, new Identifier(ItemProtectionEnchantments.MOD_ID, "cactus_protection_item"), new CactusProtectionEnchantment());
        BLAST_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, new Identifier(ItemProtectionEnchantments.MOD_ID, "blast_protection_item"), new BlastProtectionEnchantment());
        VOID_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, new Identifier(ItemProtectionEnchantments.MOD_ID, "void_protection_item"), new VoidProtectionEnchantment());
        EXPIRE_PROTECTION_ITEM = Registry.register(Registries.ENCHANTMENT, new Identifier(ItemProtectionEnchantments.MOD_ID, "expire_protection_item"), new ExpireProtectionEnchantment());
    }
}
