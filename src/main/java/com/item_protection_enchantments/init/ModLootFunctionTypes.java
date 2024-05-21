package com.item_protection_enchantments.init;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.lootfunctiontypes.CopyEnchantmentFunction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.*;

public class ModLootFunctionTypes {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(ResourceKey.createRegistryKey(new ResourceLocation("loot_function_type")), ItemProtectionEnchantments.MOD_ID);

    public static final RegistryObject<LootItemFunctionType> COPY_ENCHANTMENTS = LOOT_FUNCTION_TYPES.register("copy_enchantments", () -> new LootItemFunctionType(new CopyEnchantmentFunction.Serializer()));
}
