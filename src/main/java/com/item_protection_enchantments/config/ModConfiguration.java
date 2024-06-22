package com.item_protection_enchantments.config;

import com.item_protection_enchantments.enchantments.ItemProtectionEnchantment;
import com.item_protection_enchantments.enchantments.VoidProtectionEnchantment;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfiguration {
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentPredicates> FIRE_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue FIRE_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.IntValue FIRE_PROTECTION_ANVIL_COST;
    public static final ForgeConfigSpec.IntValue FIRE_PROTECTION_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue FIRE_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue FIRE_PROTECTION_TRADEABLE;
    public static final ForgeConfigSpec.BooleanValue FIRE_PROTECTION_GLOWING_TAG;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentPredicates> CACTUS_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue CACTUS_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.IntValue CACTUS_PROTECTION_ANVIL_COST;
    public static final ForgeConfigSpec.IntValue CACTUS_PROTECTION_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue CACTUS_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue CACTUS_PROTECTION_TRADEABLE;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentPredicates> BLAST_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue BLAST_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.IntValue BLAST_PROTECTION_ANVIL_COST;
    public static final ForgeConfigSpec.IntValue BLAST_PROTECTION_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue BLAST_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue BLAST_PROTECTION_TRADEABLE;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentPredicates> VOID_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue VOID_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.IntValue VOID_PROTECTION_ANVIL_COST;
    public static final ForgeConfigSpec.IntValue VOID_PROTECTION_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue VOID_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue VOID_PROTECTION_TRADEABLE;
    public static final ForgeConfigSpec.BooleanValue VOID_PROTECTION_GLOWING_TAG;
    public static final ForgeConfigSpec.EnumValue<VoidProtectionEnchantment.ProtectionHeights> VOID_PROTECTION_PROTECTION_HEIGHT;
    public static final ForgeConfigSpec.IntValue VOID_PROTECTION_PROTECTED_HEIGHT;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentPredicates> EXPIRE_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue EXPIRE_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.IntValue EXPIRE_PROTECTION_ANVIL_COST;
    public static final ForgeConfigSpec.IntValue EXPIRE_PROTECTION_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue EXPIRE_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue EXPIRE_PROTECTION_TRADEABLE;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentPredicates> INVENTORY_HOLDING_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue INVENTORY_HOLDING_MIN_COST;
    public static final ForgeConfigSpec.IntValue INVENTORY_HOLDING_ANVIL_COST;
    public static final ForgeConfigSpec.IntValue INVENTORY_HOLDING_WEIGHT;
    public static final ForgeConfigSpec.BooleanValue INVENTORY_HOLDING_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue INVENTORY_HOLDING_TRADEABLE;
    public static final ForgeConfigSpec.BooleanValue INVENTORY_HOLDING_DISABLE_VANISHING_CURSE;

    static {
        ForgeConfigSpec.Builder COMMON_CONFIG_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_CONFIG_BUILDER.comment("Game Settings");

        COMMON_CONFIG_BUILDER.push("Fire Protection Enchantments");

        FIRE_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS);

        FIRE_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_LEVEL, 0, ItemProtectionEnchantment.MAX_LEVEL);

        FIRE_PROTECTION_ANVIL_COST = COMMON_CONFIG_BUILDER.comment("Experience cost used when enchanting with an anvil")
                .defineInRange("anvilCost", ItemProtectionEnchantment.ANVIL_COST, 0, 80);

        FIRE_PROTECTION_WEIGHT = COMMON_CONFIG_BUILDER.comment("Relative chance of the enchantment being offered")
                .defineInRange("weight", ItemProtectionEnchantment.WEIGHT, 0, 10);

        FIRE_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        FIRE_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        FIRE_PROTECTION_GLOWING_TAG = COMMON_CONFIG_BUILDER.comment("Should items glow when submerged in lava?")
                .define("glow", false);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Cactus Protection Enchantments");

        CACTUS_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS);

        CACTUS_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_LEVEL, 0, ItemProtectionEnchantment.MAX_LEVEL);

        CACTUS_PROTECTION_ANVIL_COST = COMMON_CONFIG_BUILDER.comment("Experience cost used when enchanting with an anvil")
                .defineInRange("anvilCost", ItemProtectionEnchantment.ANVIL_COST, 0, 80);

        CACTUS_PROTECTION_WEIGHT = COMMON_CONFIG_BUILDER.comment("Relative chance of the enchantment being offered")
                .defineInRange("weight", ItemProtectionEnchantment.WEIGHT, 0, 10);

        CACTUS_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        CACTUS_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Blast Protection Enchantments");

        BLAST_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS);

        BLAST_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_LEVEL, 0, ItemProtectionEnchantment.MAX_LEVEL);

        BLAST_PROTECTION_ANVIL_COST = COMMON_CONFIG_BUILDER.comment("Experience cost used when enchanting with an anvil")
                .defineInRange("anvilCost", ItemProtectionEnchantment.ANVIL_COST, 0, 80);

        BLAST_PROTECTION_WEIGHT = COMMON_CONFIG_BUILDER.comment("Relative chance of the enchantment being offered")
                .defineInRange("weight", ItemProtectionEnchantment.WEIGHT, 0, 10);

        BLAST_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        BLAST_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Void Protection Enchantments");

        VOID_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS);

        VOID_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_LEVEL, 0, ItemProtectionEnchantment.MAX_LEVEL);

        VOID_PROTECTION_ANVIL_COST = COMMON_CONFIG_BUILDER.comment("Experience cost used when enchanting with an anvil")
                .defineInRange("anvilCost", ItemProtectionEnchantment.ANVIL_COST, 0, 80);

        VOID_PROTECTION_WEIGHT = COMMON_CONFIG_BUILDER.comment("Relative chance of the enchantment being offered")
                .defineInRange("weight", ItemProtectionEnchantment.WEIGHT, 0, 10);

        VOID_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        VOID_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        VOID_PROTECTION_GLOWING_TAG = COMMON_CONFIG_BUILDER.comment("Should the item glow when it falls into the void?")
                .define("glow", true);

        VOID_PROTECTION_PROTECTION_HEIGHT = COMMON_CONFIG_BUILDER.comment("Height to protect from the void")
                .defineEnum("protectionHeight", VoidProtectionEnchantment.ProtectionHeights.HEIGHT_WHERE_ENTITY_TAKES_DAMAGE);

        VOID_PROTECTION_PROTECTED_HEIGHT = COMMON_CONFIG_BUILDER.comment("Height after protection from the void (0 is the minimum build height)")
                .defineInRange("protectedHeight", 1, 0, 128);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Expire Protection Enchantments");

        EXPIRE_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS);

        EXPIRE_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_LEVEL, 0, ItemProtectionEnchantment.MAX_LEVEL);

        EXPIRE_PROTECTION_ANVIL_COST = COMMON_CONFIG_BUILDER.comment("Experience cost used when enchanting with an anvil")
                .defineInRange("anvilCost", ItemProtectionEnchantment.ANVIL_COST, 0, 80);

        EXPIRE_PROTECTION_WEIGHT = COMMON_CONFIG_BUILDER.comment("Relative chance of the enchantment being offered")
                .defineInRange("weight", ItemProtectionEnchantment.WEIGHT, 0, 10);

        EXPIRE_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        EXPIRE_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Inventory Holding Enchantments");

        INVENTORY_HOLDING_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS);

        INVENTORY_HOLDING_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_LEVEL, 0, ItemProtectionEnchantment.MAX_LEVEL);

        INVENTORY_HOLDING_ANVIL_COST = COMMON_CONFIG_BUILDER.comment("Experience cost used when enchanting with an anvil")
                .defineInRange("anvilCost", ItemProtectionEnchantment.ANVIL_COST, 0, 80);

        INVENTORY_HOLDING_WEIGHT = COMMON_CONFIG_BUILDER.comment("Relative chance of the enchantment being offered")
                .defineInRange("weight", ItemProtectionEnchantment.WEIGHT, 0, 10);

        INVENTORY_HOLDING_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", true);

        INVENTORY_HOLDING_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        INVENTORY_HOLDING_DISABLE_VANISHING_CURSE = COMMON_CONFIG_BUILDER.comment("Whether to disable the effect of Curse of Vanishing if Inventory Holding is applied")
                .define("disableVanishingCurse", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG = COMMON_CONFIG_BUILDER.build();
    }
}
