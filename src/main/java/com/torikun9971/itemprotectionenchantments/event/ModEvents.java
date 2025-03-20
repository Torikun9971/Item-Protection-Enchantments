package com.torikun9971.itemprotectionenchantments.event;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import com.torikun9971.itemprotectionenchantments.util.Util;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEvents {
    @SubscribeEvent
    public void blastEnchantmentEvent(ExplosionEvent.Detonate event) {
        event.getAffectedEntities().removeIf((entity) -> {
            if (!(entity instanceof ItemEntity itemEntity))
                return false;

            return Util.hasEnchantment(itemEntity.getItem(), ModEnchantments.BLAST_PROTECTION_ITEM.get());
        });
    }

    @SubscribeEvent
    public void expireEnchantmentEvent(ItemExpireEvent event) {
        if (Util.hasEnchantment(event.getEntity().getItem(), ModEnchantments.EXPIRE_PROTECTION_ITEM.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void loadConfigEvent(AddReloadListenerEvent event) {
        AutoConfig.getConfigHolder(ModConfiguration.class).load();
    }
}
