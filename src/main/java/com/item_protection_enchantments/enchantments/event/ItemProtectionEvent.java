package com.item_protection_enchantments.enchantments.event;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.entity.item.ItemEntity;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemProtectionEvent {
    @SubscribeEvent
    public void blastEnchantmentEvent(ExplosionEvent.Detonate event) {
        event.getAffectedEntities().removeIf((entity) -> {
            if (entity instanceof ItemEntity) {
                ItemEntity itemEntity = (ItemEntity) entity;

                if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.BLAST_PROTECTION_ITEM.get())) {
                    return true;
                }
            }

            return false;
        });
    }

    @SubscribeEvent
    public void expireEnchantmentEvent(ItemExpireEvent event) {
        if (ItemProtectionEnchantments.hasEnchantment(event.getEntityItem().getItem(), true, ModEnchantments.EXPIRE_PROTECTION_ITEM.get())) {
            event.setCanceled(true);
        }
    }
}
