package com.torikun9971.itemprotectionenchantments.event;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;

public class ModEvents {
    @SubscribeEvent
    public void blastEnchantmentEvent(ExplosionEvent.Detonate event) {
        event.getAffectedEntities().removeIf((entity) -> {
            if (!(entity instanceof ItemEntity itemEntity))
                return false;

            return ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.BLAST_PROTECTION_ITEM.get());
        });
    }

    @SubscribeEvent
    public void expireEnchantmentEvent(ItemExpireEvent event) {
        if (ItemProtectionEnchantments.hasEnchantment(event.getEntity().getItem(), true, ModEnchantments.EXPIRE_PROTECTION_ITEM.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void loadConfigEvent(AddReloadListenerEvent event) {
        AutoConfig.getConfigHolder(ModConfiguration.class).load();
    }
}
