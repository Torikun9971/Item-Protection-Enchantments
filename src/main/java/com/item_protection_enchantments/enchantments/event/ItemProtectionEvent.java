package com.item_protection_enchantments.enchantments.event;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ItemProtectionEvent {
    private static final Set<ItemEntity> ITEM_ENTITIES = ConcurrentHashMap.newKeySet();

    @SubscribeEvent
    public void addItemEntity(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide && event.getEntity() instanceof ItemEntity itemEntity) {
            ITEM_ENTITIES.add(itemEntity);
        }
    }

    @SubscribeEvent
    public void removeItemEntity(EntityLeaveLevelEvent event) {
        if (!event.getLevel().isClientSide && event.getEntity() instanceof ItemEntity itemEntity) {
            ITEM_ENTITIES.remove(itemEntity);
        }
    }

    @SubscribeEvent
    public void blastEnchantmentEvent(ExplosionEvent.Detonate event) {
        event.getAffectedEntities().removeIf((entity) -> {
            if (entity instanceof ItemEntity itemEntity && ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.BLAST_PROTECTION_ITEM.get())) {
                return true;
            }

            return false;
        });
    }

    @SubscribeEvent
    public void voidEnchantmentEvent(TickEvent.ServerTickEvent event) {
        ITEM_ENTITIES.stream().filter((itemEntity) -> itemEntity.getY() < itemEntity.getLevel() .getMinBuildHeight()).filter((itemEntity) -> ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.VOID_PROTECTION_ITEM.get())).forEach((itemEntity) -> {
            itemEntity.setNoGravity(true);
            itemEntity.setInvulnerable(true);
            itemEntity.setPos(itemEntity.getX(), itemEntity.getLevel().getMinBuildHeight() + 1, itemEntity.getZ());
            itemEntity.setDeltaMovement(0, 0, 0);
            itemEntity.setGlowingTag(ModConfiguration.VOID_PROTECTION_GLOWING_TAG.get());
        });
    }

    @SubscribeEvent
    public void expireEnchantmentEvent(ItemExpireEvent event) {
        if (ItemProtectionEnchantments.hasEnchantment(event.getEntity().getItem(), true, ModEnchantments.EXPIRE_PROTECTION_ITEM.get())) {
            event.setCanceled(true);
        }
    }
}
