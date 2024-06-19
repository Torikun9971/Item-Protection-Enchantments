package com.item_protection_enchantments.config;

import com.item_protection_enchantments.enchantments.ItemProtectionEnchantment;
import com.item_protection_enchantments.enchantments.VoidProtectionEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfiguration {
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentCategories> FIRE_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue FIRE_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> FIRE_PROTECTION_RARITY;
    public static final ForgeConfigSpec.BooleanValue FIRE_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue FIRE_PROTECTION_TRADEABLE;
    public static final ForgeConfigSpec.BooleanValue FIRE_PROTECTION_GLOWING_TAG;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentCategories> CACTUS_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue CACTUS_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> CACTUS_PROTECTION_RARITY;
    public static final ForgeConfigSpec.BooleanValue CACTUS_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue CACTUS_PROTECTION_TRADEABLE;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentCategories> BLAST_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue BLAST_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> BLAST_PROTECTION_RARITY;
    public static final ForgeConfigSpec.BooleanValue BLAST_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue BLAST_PROTECTION_TRADEABLE;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentCategories> VOID_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue VOID_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> VOID_PROTECTION_RARITY;
    public static final ForgeConfigSpec.BooleanValue VOID_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue VOID_PROTECTION_TRADEABLE;
    public static final ForgeConfigSpec.BooleanValue VOID_PROTECTION_GLOWING_TAG;
    public static final ForgeConfigSpec.EnumValue<VoidProtectionEnchantment.ProtectionHeights> VOID_PROTECTION_PROTECTION_HEIGHT;
    public static final ForgeConfigSpec.IntValue VOID_PROTECTION_PROTECTED_HEIGHT;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentCategories> EXPIRE_PROTECTION_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue EXPIRE_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> EXPIRE_PROTECTION_RARITY;
    public static final ForgeConfigSpec.BooleanValue EXPIRE_PROTECTION_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue EXPIRE_PROTECTION_TRADEABLE;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentCategories> INVENTORY_HOLDING_ENCHANTABLE_ITEMS;
    public static final ForgeConfigSpec.IntValue INVENTORY_HOLDING_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> INVENTORY_HOLDING_RARITY;
    public static final ForgeConfigSpec.BooleanValue INVENTORY_HOLDING_TREASURE_ENCHANTMENT;
    public static final ForgeConfigSpec.BooleanValue INVENTORY_HOLDING_TRADEABLE;
    public static final ForgeConfigSpec.BooleanValue INVENTORY_HOLDING_DISABLE_VANISHING_CURSE;

    static {
        ForgeConfigSpec.Builder COMMON_CONFIG_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_CONFIG_BUILDER.comment("Game Settings");

        COMMON_CONFIG_BUILDER.push("Fire Protection Enchantments");

        FIRE_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentCategories.ITEMS_AND_COMPATIBLE_BLOCKS);

        FIRE_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        FIRE_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        FIRE_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        FIRE_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        FIRE_PROTECTION_GLOWING_TAG = COMMON_CONFIG_BUILDER.comment("Should items glow when submerged in lava?")
                .define("glow", false);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Cactus Protection Enchantments");

        CACTUS_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentCategories.ITEMS_AND_COMPATIBLE_BLOCKS);

        CACTUS_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        CACTUS_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        CACTUS_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        CACTUS_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Blast Protection Enchantments");

        BLAST_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentCategories.ITEMS_AND_COMPATIBLE_BLOCKS);

        BLAST_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        BLAST_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        BLAST_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        BLAST_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Void Protection Enchantments");

        VOID_PROTECTION_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentCategories.ITEMS_AND_COMPATIBLE_BLOCKS);

        VOID_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        VOID_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

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
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentCategories.ITEMS_AND_COMPATIBLE_BLOCKS);

        EXPIRE_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        EXPIRE_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        EXPIRE_PROTECTION_TREASURE_ENCHANTMENT = COMMON_CONFIG_BUILDER.comment("Whether to make it a treasure enchantment")
                .define("treasureEnchantment", false);

        EXPIRE_PROTECTION_TRADEABLE = COMMON_CONFIG_BUILDER.comment("Available through villager trading?")
                .define("tradeable", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Inventory Holding Enchantments");

        INVENTORY_HOLDING_ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentCategories.ITEMS_AND_COMPATIBLE_BLOCKS);

        INVENTORY_HOLDING_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        INVENTORY_HOLDING_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

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
