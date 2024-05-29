package com.item_protection_enchantments.config;

import com.item_protection_enchantments.enchantments.ItemProtectionEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfiguration {
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.EnumValue<ItemProtectionEnchantment.EnchantmentTypes> ENCHANTABLE_ITEMS;

    public static final ForgeConfigSpec.IntValue FIRE_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> FIRE_PROTECTION_RARITY;
    public static final ForgeConfigSpec.BooleanValue FIRE_PROTECTION_GLOWING_TAG;

    public static final ForgeConfigSpec.IntValue CACTUS_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> CACTUS_PROTECTION_RARITY;

    public static final ForgeConfigSpec.IntValue BLAST_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> BLAST_PROTECTION_RARITY;

    public static final ForgeConfigSpec.IntValue VOID_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> VOID_PROTECTION_RARITY;
    public static final ForgeConfigSpec.BooleanValue VOID_PROTECTION_GLOWING_TAG;

    public static final ForgeConfigSpec.IntValue EXPIRE_PROTECTION_MIN_COST;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> EXPIRE_PROTECTION_RARITY;

    static {
        ForgeConfigSpec.Builder COMMON_CONFIG_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_CONFIG_BUILDER.comment("Game Settings\n");

        ENCHANTABLE_ITEMS = COMMON_CONFIG_BUILDER.comment("Items that can be enchanted")
                .defineEnum("enchantableItems", ItemProtectionEnchantment.EnchantmentTypes.ITEMS_AND_COMPATIBLE_BLOCKS);

        COMMON_CONFIG_BUILDER.push("Fire Protection Enchantments");

        FIRE_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        FIRE_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        FIRE_PROTECTION_GLOWING_TAG = COMMON_CONFIG_BUILDER.comment("Should items glow when submerged in lava?")
                .define("glow", false);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Cactus Protection Enchantments");

        CACTUS_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        CACTUS_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Blast Protection Enchantments");

        BLAST_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        BLAST_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Void Protection Enchantments");

        VOID_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        VOID_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        VOID_PROTECTION_GLOWING_TAG = COMMON_CONFIG_BUILDER.comment("Should the item glow when it falls into the void?")
                .define("glow", true);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG_BUILDER.push("Expire Protection Enchantments");

        EXPIRE_PROTECTION_MIN_COST = COMMON_CONFIG_BUILDER.comment("Minimum experience cost required to apply the enchantment")
                .defineInRange("minimumCost", ItemProtectionEnchantment.MIN_COST, 0, ItemProtectionEnchantment.MAX_COST);

        EXPIRE_PROTECTION_RARITY = COMMON_CONFIG_BUILDER.comment("Enchantment Rarity (likelihood of obtaining the enchantment)")
                .defineEnum("rarity", Enchantment.Rarity.VERY_RARE);

        COMMON_CONFIG_BUILDER.pop();

        COMMON_CONFIG = COMMON_CONFIG_BUILDER.build();
    }
}
