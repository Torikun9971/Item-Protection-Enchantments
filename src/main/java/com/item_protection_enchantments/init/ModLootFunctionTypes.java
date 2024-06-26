package com.item_protection_enchantments.init;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.lootfunctiontypes.CopyEnchantmentFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModLootFunctionTypes {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, ItemProtectionEnchantments.MOD_ID);

    public static final RegistryObject<LootItemFunctionType> COPY_ENCHANTMENTS = LOOT_FUNCTION_TYPES.register("copy_enchantments", () -> new LootItemFunctionType(new CopyEnchantmentFunction.Serializer()));
}
