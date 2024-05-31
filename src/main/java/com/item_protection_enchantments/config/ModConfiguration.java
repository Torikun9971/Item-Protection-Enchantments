package com.item_protection_enchantments.config;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.enchantments.ItemProtectionEnchantment;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = ItemProtectionEnchantments.MOD_ID)
public class ModConfiguration implements ConfigData {
    @Comment("Items that can be enchanted\n" +
            "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS")
    @EnumHandler()
    public ItemProtectionEnchantment.EnchantmentPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

    @CollapsibleObject
    public FireProtectionEnchantment fireProtectionEnchantment = new FireProtectionEnchantment();

    @CollapsibleObject
    public CactusProtectionEnchantment cactusProtectionEnchantment = new CactusProtectionEnchantment();

    @CollapsibleObject
    public BlastProtectionEnchantment blastProtectionEnchantment = new BlastProtectionEnchantment();

    @CollapsibleObject
    public VoidProtectionEnchantment voidProtectionEnchantment = new VoidProtectionEnchantment();

    @CollapsibleObject
    public ExpireProtectionEnchantment expireProtectionEnchantment = new ExpireProtectionEnchantment();

    public static class FireProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Should items glow when submerged in lava?")
        public boolean glow = false;
    }

    public static class CactusProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;
    }

    public static class BlastProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;
    }

    public static class VoidProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Should the item glow when it falls into the void?")
        public boolean glow = true;
    }

    public static class ExpireProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;
    }

    public static ModConfiguration getConfig() {
        return AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();
    }
}
