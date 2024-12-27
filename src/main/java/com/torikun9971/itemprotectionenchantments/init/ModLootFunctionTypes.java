package com.torikun9971.itemprotectionenchantments.init;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.lootfunctiontypes.CopyEnchantmentFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModLootFunctionTypes {
    public static LootFunctionType COPY_ENCHANTMENTS;

    public static void init() {
        COPY_ENCHANTMENTS = Registry.register(
                Registries.LOOT_FUNCTION_TYPE,
                ItemProtectionEnchantments.id("copy_enchantments"),
                new LootFunctionType(CopyEnchantmentFunction.CODEC)
        );
    }
}
