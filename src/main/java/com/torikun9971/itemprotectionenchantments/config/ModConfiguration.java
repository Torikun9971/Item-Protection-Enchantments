package com.torikun9971.itemprotectionenchantments.config;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.enchantments.BaseProtectionEnchantment;
import com.torikun9971.itemprotectionenchantments.enchantments.BaseProtectionEnchantment.EnchantmentPredicates;
import com.torikun9971.itemprotectionenchantments.enchantments.InventoryHoldingEnchantment;
import com.torikun9971.itemprotectionenchantments.enchantments.VoidProtectionEnchantment.ProtectionHeights;
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
    public FireProtectionConfig fireProtection = new FireProtectionConfig();

    @CollapsibleObject
    public CactusProtectionConfig cactusProtection = new CactusProtectionConfig();

    @CollapsibleObject
    public BlastProtectionConfig blastProtection = new BlastProtectionConfig();

    @CollapsibleObject
    public VoidProtectionConfig voidProtection = new VoidProtectionConfig();

    @CollapsibleObject
    public ExpireProtectionConfig expireProtection = new ExpireProtectionConfig();

    @CollapsibleObject
    public InventoryHoldingConfig inventoryHolding = new InventoryHoldingConfig();

    public interface IBaseProtectionConfig {
        EnchantmentPredicates getEnchantableItems();

        int getMinimumCost();

        int getAnvilCost();

        int getWeight();

        boolean isTreasure();

        boolean isTradeable();
    }

    public static class FireProtectionConfig implements IBaseProtectionConfig {
        @Tooltip
        @EnumHandler
        public EnchantmentPredicates enchantableItems = BaseProtectionEnchantment.PREDICATE;

        @Tooltip
        @BoundedDiscrete(min = 0, max = BaseProtectionEnchantment.MAX_LEVEL)
        public int minimumCost = BaseProtectionEnchantment.MIN_LEVEL;

        @Tooltip
        @BoundedDiscrete(min = 0, max = 128)
        public int anvilCost = BaseProtectionEnchantment.ANVIL_COST;

        @Tooltip
        @BoundedDiscrete(min = 1, max = 128)
        public int weight = BaseProtectionEnchantment.WEIGHT;

        @Tooltip
        public boolean isTreasure = false;

        @Tooltip
        public boolean isTradeable = true;

        @Tooltip
        public boolean isGlow = false;

        @Override
        public EnchantmentPredicates getEnchantableItems() {
            return enchantableItems;
        }

        @Override
        public int getMinimumCost() {
            return minimumCost;
        }

        @Override
        public int getAnvilCost() {
            return anvilCost;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public boolean isTreasure() {
            return isTreasure;
        }

        @Override
        public boolean isTradeable() {
            return isTradeable;
        }
    }

    public static class CactusProtectionConfig implements IBaseProtectionConfig {
        @Tooltip
        @EnumHandler
        public EnchantmentPredicates enchantableItems = BaseProtectionEnchantment.PREDICATE;

        @Tooltip
        @BoundedDiscrete(min = 0, max = BaseProtectionEnchantment.MAX_LEVEL)
        public int minimumCost = BaseProtectionEnchantment.MIN_LEVEL;

        @Tooltip
        @BoundedDiscrete(min = 0, max = 128)
        public int anvilCost = BaseProtectionEnchantment.ANVIL_COST;

        @Tooltip
        @BoundedDiscrete(min = 1, max = 128)
        public int weight = BaseProtectionEnchantment.WEIGHT;

        @Tooltip
        public boolean isTreasure = false;

        @Tooltip
        public boolean isTradeable = true;

        @Override
        public EnchantmentPredicates getEnchantableItems() {
            return enchantableItems;
        }

        @Override
        public int getMinimumCost() {
            return minimumCost;
        }

        @Override
        public int getAnvilCost() {
            return anvilCost;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public boolean isTreasure() {
            return isTreasure;
        }

        @Override
        public boolean isTradeable() {
            return isTradeable;
        }
    }

    public static class BlastProtectionConfig implements IBaseProtectionConfig {
        @Tooltip
        @EnumHandler
        public EnchantmentPredicates enchantableItems = BaseProtectionEnchantment.PREDICATE;

        @Tooltip
        @BoundedDiscrete(min = 0, max = BaseProtectionEnchantment.MAX_LEVEL)
        public int minimumCost = BaseProtectionEnchantment.MIN_LEVEL;

        @Tooltip
        @BoundedDiscrete(min = 0, max = 128)
        public int anvilCost = BaseProtectionEnchantment.ANVIL_COST;

        @Tooltip
        @BoundedDiscrete(min = 1, max = 128)
        public int weight = BaseProtectionEnchantment.WEIGHT;

        @Tooltip
        public boolean isTreasure = false;

        @Tooltip
        public boolean isTradeable = true;

        @Override
        public EnchantmentPredicates getEnchantableItems() {
            return enchantableItems;
        }

        @Override
        public int getMinimumCost() {
            return minimumCost;
        }

        @Override
        public int getAnvilCost() {
            return anvilCost;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public boolean isTreasure() {
            return isTreasure;
        }

        @Override
        public boolean isTradeable() {
            return isTradeable;
        }
    }

    public static class VoidProtectionConfig implements IBaseProtectionConfig {
        @Tooltip
        @EnumHandler
        public EnchantmentPredicates enchantableItems = BaseProtectionEnchantment.PREDICATE;

        @Tooltip
        @BoundedDiscrete(min = 0, max = BaseProtectionEnchantment.MAX_LEVEL)
        public int minimumCost = BaseProtectionEnchantment.MIN_LEVEL;

        @Tooltip
        @BoundedDiscrete(min = 0, max = 128)
        public int anvilCost = BaseProtectionEnchantment.ANVIL_COST;

        @Tooltip
        @BoundedDiscrete(min = 1, max = 128)
        public int weight = BaseProtectionEnchantment.WEIGHT;

        @Tooltip
        public boolean isTreasure = false;

        @Tooltip
        public boolean isTradeable = true;

        @Tooltip
        public boolean isGlow = true;

        @Tooltip
        @EnumHandler
        public ProtectionHeights protectionHeight = ProtectionHeights.HEIGHT_WHERE_ENTITY_TAKES_DAMAGE;

        @Tooltip
        @BoundedDiscrete(min = 0, max = 128)
        public int protectedHeight = 1;

        @Override
        public EnchantmentPredicates getEnchantableItems() {
            return enchantableItems;
        }

        @Override
        public int getMinimumCost() {
            return minimumCost;
        }

        @Override
        public int getAnvilCost() {
            return anvilCost;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public boolean isTreasure() {
            return isTreasure;
        }

        @Override
        public boolean isTradeable() {
            return isTradeable;
        }
    }

    public static class ExpireProtectionConfig implements IBaseProtectionConfig {
        @Tooltip
        @EnumHandler
        public EnchantmentPredicates enchantableItems = BaseProtectionEnchantment.PREDICATE;

        @Tooltip
        @BoundedDiscrete(min = 0, max = BaseProtectionEnchantment.MAX_LEVEL)
        public int minimumCost = BaseProtectionEnchantment.MIN_LEVEL;

        @Tooltip
        @BoundedDiscrete(min = 0, max = 128)
        public int anvilCost = BaseProtectionEnchantment.ANVIL_COST;

        @Tooltip
        @BoundedDiscrete(min = 1, max = 128)
        public int weight = BaseProtectionEnchantment.WEIGHT;

        @Tooltip
        public boolean isTreasure = false;

        @Tooltip
        public boolean isTradeable = true;

        @Override
        public EnchantmentPredicates getEnchantableItems() {
            return enchantableItems;
        }

        @Override
        public int getMinimumCost() {
            return minimumCost;
        }

        @Override
        public int getAnvilCost() {
            return anvilCost;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public boolean isTreasure() {
            return isTreasure;
        }

        @Override
        public boolean isTradeable() {
            return isTradeable;
        }
    }

    public static class InventoryHoldingConfig implements IBaseProtectionConfig {
        @Tooltip
        @EnumHandler
        public EnchantmentPredicates enchantableItems = BaseProtectionEnchantment.PREDICATE;

        @Tooltip
        @BoundedDiscrete(min = 0, max = BaseProtectionEnchantment.MAX_LEVEL)
        public int minimumCost = BaseProtectionEnchantment.MIN_LEVEL;

        @Tooltip
        @BoundedDiscrete(min = 0, max = 128)
        public int anvilCost = InventoryHoldingEnchantment.ANVIL_COST;

        @Tooltip
        @BoundedDiscrete(min = 1, max = 128)
        public int weight = BaseProtectionEnchantment.WEIGHT;

        @Tooltip
        public boolean isTreasure = true;

        @Tooltip
        public boolean isTradeable = true;

        @Tooltip
        public boolean isVanishingCurseDisabled = true;

        @Override
        public EnchantmentPredicates getEnchantableItems() {
            return enchantableItems;
        }

        @Override
        public int getMinimumCost() {
            return minimumCost;
        }

        @Override
        public int getAnvilCost() {
            return anvilCost;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public boolean isTreasure() {
            return isTreasure;
        }

        @Override
        public boolean isTradeable() {
            return isTradeable;
        }
    }

    public static ModConfiguration getConfig() {
        return AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();
    }
}
