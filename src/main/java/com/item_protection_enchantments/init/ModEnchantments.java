package com.item_protection_enchantments.init;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.enchantments.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, ItemProtectionEnchantments.MOD_ID);

    public static final DeferredHolder<Enchantment, FireProtectionEnchantment> FIRE_PROTECTION_ITEM = ENCHANTMENTS.register("fire_protection_item", FireProtectionEnchantment::new);
    public static final DeferredHolder<Enchantment, CactusProtectionEnchantment> CACTUS_PROTECTION_ITEM = ENCHANTMENTS.register("cactus_protection_item", CactusProtectionEnchantment::new);
    public static final DeferredHolder<Enchantment, BlastProtectionEnchantment> BLAST_PROTECTION_ITEM = ENCHANTMENTS.register("blast_protection_item", BlastProtectionEnchantment::new);
    public static final DeferredHolder<Enchantment, VoidProtectionEnchantment> VOID_PROTECTION_ITEM = ENCHANTMENTS.register("void_protection_item", VoidProtectionEnchantment::new);
    public static final DeferredHolder<Enchantment, ExpireProtectionEnchantment> EXPIRE_PROTECTION_ITEM = ENCHANTMENTS.register("expire_protection_item", ExpireProtectionEnchantment::new);
    public static final DeferredHolder<Enchantment, InventoryHoldingEnchantment> INVENTORY_HOLDING = ENCHANTMENTS.register("inventory_holding", InventoryHoldingEnchantment::new);
}
