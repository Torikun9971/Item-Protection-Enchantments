package com.item_protection_enchantments.init;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.lootfunctiontypes.CopyEnchantment;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ModLootFunctionTypes {
    public static final List<LootFunctionType> LOOT_FUNCTION_TYPES = new ArrayList<>();

    public static final LootFunctionType COPY_ENCHANTMENTS = register("copy_enchantments", new CopyEnchantment.Serializer());

    private static LootFunctionType register(String name, ILootSerializer<? extends ILootFunction> serializer) {
        LootFunctionType lootFunctionType = Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(ItemProtectionEnchantments.MOD_ID + ":" + name), new LootFunctionType(serializer));
        LOOT_FUNCTION_TYPES.add(lootFunctionType);

        return lootFunctionType;
    }
}
