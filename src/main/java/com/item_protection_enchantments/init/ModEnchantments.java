package com.item_protection_enchantments.init;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.enchantments.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ItemProtectionEnchantments.MOD_ID);

    public static final RegistryObject<Enchantment> FIRE_PROTECTION_ITEM = ENCHANTMENTS.register("fire_protection_item", FireProtectionEnchantment::new);
    public static final RegistryObject<Enchantment> CACTUS_PROTECTION_ITEM = ENCHANTMENTS.register("cactus_protection_item", CactusProtectionEnchantment::new);
    public static final RegistryObject<Enchantment> BLAST_PROTECTION_ITEM = ENCHANTMENTS.register("blast_protection_item", BlastProtectionEnchantment::new);
    public static final RegistryObject<Enchantment> VOID_PROTECTION_ITEM = ENCHANTMENTS.register("void_protection_item", VoidProtectionEnchantment::new);
    public static final RegistryObject<Enchantment> EXPIRE_PROTECTION_ITEM = ENCHANTMENTS.register("expire_protection_item", ExpireProtectionEnchantment::new);
    public static final RegistryObject<Enchantment> INVENTORY_HOLDING = ENCHANTMENTS.register("inventory_holding", InventoryHoldingEnchantment::new);
}
