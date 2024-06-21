package com.item_protection_enchantments.config;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.enchantments.ItemProtectionEnchantment;
import com.item_protection_enchantments.enchantments.VoidProtectionEnchantment.ProtectionHeights;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = ItemProtectionEnchantments.MOD_ID)
public class ModConfiguration implements ConfigData {
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

    @CollapsibleObject
    public InventoryHoldingEnchantment inventoryHoldingEnchantment = new InventoryHoldingEnchantment();

    public static class FireProtectionEnchantment {
        @Comment("Items that can be enchanted\n" +
                "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS, ITEMS_ONLY")
        @EnumHandler
        public ItemProtectionEnchantment.EnchantmentPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Whether to make it a treasure enchantment")
        public boolean treasureEnchantment = false;

        @Comment("Available through villager trading?")
        public boolean tradeable = true;

        @Comment("Should items glow when submerged in lava?")
        public boolean glow = false;
    }

    public static class CactusProtectionEnchantment {
        @Comment("Items that can be enchanted\n" +
                "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS, ITEMS_ONLY")
        @EnumHandler
        public ItemProtectionEnchantment.EnchantmentPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Whether to make it a treasure enchantment")
        public boolean treasureEnchantment = false;

        @Comment("Available through villager trading?")
        public boolean tradeable = true;
    }

    public static class BlastProtectionEnchantment {
        @Comment("Items that can be enchanted\n" +
                "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS, ITEMS_ONLY")
        @EnumHandler
        public ItemProtectionEnchantment.EnchantmentPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Whether to make it a treasure enchantment")
        public boolean treasureEnchantment = false;

        @Comment("Available through villager trading?")
        public boolean tradeable = true;
    }

    public static class VoidProtectionEnchantment {
        @Comment("Items that can be enchanted\n" +
                "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS, ITEMS_ONLY")
        @EnumHandler
        public ItemProtectionEnchantment.EnchantmentPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Whether to make it a treasure enchantment")
        public boolean treasureEnchantment = false;

        @Comment("Available through villager trading?")
        public boolean tradeable = true;

        @Comment("Should the item glow when it falls into the void?")
        public boolean glow = true;

        @Comment("Height to protect from the void\n" +
                "Allowed Values: HEIGHT_WHERE_ENTITY_TAKES_DAMAGE, MIN_BUILD_HEIGHT")
        @EnumHandler
        public ProtectionHeights protectionHeight = ProtectionHeights.HEIGHT_WHERE_ENTITY_TAKES_DAMAGE;

        @Comment("Height after protection from the void (0 is the minimum build height)")
        @BoundedDiscrete(min = 0, max = 128)
        public int protectedHeight = 1;
    }

    public static class ExpireProtectionEnchantment {
        @Comment("Items that can be enchanted\n" +
                "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS, ITEMS_ONLY")
        @EnumHandler
        public ItemProtectionEnchantment.EnchantmentPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Whether to make it a treasure enchantment")
        public boolean treasureEnchantment = false;

        @Comment("Available through villager trading?")
        public boolean tradeable = true;
    }

    public static class InventoryHoldingEnchantment {
        @Comment("Items that can be enchanted\n" +
                "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS, ITEMS_ONLY")
        @EnumHandler
        public ItemProtectionEnchantment.EnchantmentPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_LEVEL)
        public int minimumPower = ItemProtectionEnchantment.MIN_LEVEL;

        @Comment("Experience cost used when enchanting with an anvil")
        @BoundedDiscrete(min = 0, max = 80)
        public int anvilCost = ItemProtectionEnchantment.ANVIL_COST;

        @Comment("Relative chance of the enchantment being offered")
        @BoundedDiscrete(min = 0, max = 10)
        public int weight = ItemProtectionEnchantment.WEIGHT;

        @Comment("Whether to make it a treasure enchantment")
        public boolean treasureEnchantment = true;

        @Comment("Available through villager trading?")
        public boolean tradeable = true;

        @Comment("Whether to disable the effect of Curse of Vanishing if Inventory Holding is applied")
        public boolean disableVanishingCurse = true;
    }

    public static ModConfiguration getConfig() {
        return AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();
    }
}
