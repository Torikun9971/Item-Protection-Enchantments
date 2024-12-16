package com.torikun9971.itemprotectionenchantments.config;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;

@Config(name = ItemProtectionEnchantments.MOD_ID)
public class ModConfiguration implements ConfigData {
    @CollapsibleObject
    public FireProtectionEnchantment fireProtection = new FireProtectionEnchantment();

    @CollapsibleObject
    public VoidProtectionEnchantment voidProtection = new VoidProtectionEnchantment();

    @CollapsibleObject
    public InventoryHoldingEnchantment inventoryHolding = new InventoryHoldingEnchantment();

    public static class FireProtectionEnchantment {
        @Tooltip
        public boolean isGlow = false;
    }

    public static class VoidProtectionEnchantment {
        @Tooltip
        public boolean isGlow = true;

        @EnumHandler
        @Tooltip
        public ProtectionHeights protectionHeight = ProtectionHeights.HEIGHT_WHERE_ENTITY_TAKES_DAMAGE;

        @BoundedDiscrete(min = 0, max = 128)
        @Tooltip
        public int protectedHeight = 1;

        public enum ProtectionHeights {
            HEIGHT_WHERE_ENTITY_TAKES_DAMAGE,
            MIN_BUILD_HEIGHT
        }
    }

    public static class InventoryHoldingEnchantment {
        @Tooltip
        public boolean isVanishingCurseDisabled = true;
    }

    public static ModConfiguration getConfig() {
        return AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();
    }
}
