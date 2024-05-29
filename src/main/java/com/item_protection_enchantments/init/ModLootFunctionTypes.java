package com.item_protection_enchantments.init;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.lootfunctiontypes.CopyEnchantmentFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModLootFunctionTypes {
    public static LootFunctionType COPY_ENCHANTMENTS;

    public static void init() {
        COPY_ENCHANTMENTS = Registry.register(Registries.LOOT_FUNCTION_TYPE, new Identifier(ItemProtectionEnchantments.MOD_ID, "copy_enchantments"), new LootFunctionType(CopyEnchantmentFunction.CODEC));
    }
}
