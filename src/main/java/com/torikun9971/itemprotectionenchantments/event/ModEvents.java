package com.torikun9971.itemprotectionenchantments.event;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.enchantment.EnchantmentUtil;
import com.torikun9971.itemprotectionenchantments.enchantment.ModEnchantments;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;

public class ModEvents {
    @SubscribeEvent
    public void blastEnchantmentEvent(ExplosionEvent.Detonate event) {
        event.getAffectedEntities().removeIf((entity) -> {
            if (!(entity instanceof ItemEntity itemEntity))
                return false;

            return EnchantmentUtil.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.BLAST_PROTECTION_ITEM);
        });
    }

    @SubscribeEvent
    public void loadConfigEvent(AddReloadListenerEvent event) {
        AutoConfig.getConfigHolder(ModConfiguration.class).load();
    }
}
