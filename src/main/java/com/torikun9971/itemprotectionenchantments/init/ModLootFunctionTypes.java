package com.torikun9971.itemprotectionenchantments.init;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.lootfunctiontypes.CopyEnchantmentFunction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModLootFunctionTypes {
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registry.LOOT_FUNCTION_TYPE.key(), ItemProtectionEnchantments.MOD_ID);

    public static final RegistryObject<LootItemFunctionType> COPY_ENCHANTMENTS = LOOT_FUNCTION_TYPES.register(
            "copy_enchantments", () -> new LootItemFunctionType(new CopyEnchantmentFunction.Serializer())
    );
}
