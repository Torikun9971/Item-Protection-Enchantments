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
import net.minecraft.enchantment.Enchantment;

@Config(name = ItemProtectionEnchantments.MOD_ID)
public class ModConfiguration implements ConfigData {
    @Comment("Items that can be enchanted\n" +
            "Allowed Values: ALL_ITEMS, ITEMS_AND_COMPATIBLE_BLOCKS")
    @EnumHandler()
    public ItemProtectionEnchantment.EnchantmentTargetPredicates enchantableItems = ItemProtectionEnchantment.EnchantmentTargetPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

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
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_POWER)
        public int minimumPower = ItemProtectionEnchantment.MIN_POWER;

        @Comment("Enchantment Rarity (likelihood of obtaining the enchantment)\n" +
                "Allowed Values: COMMON, UNCOMMON, RARE, VERY_RARE")
        @EnumHandler
        public Enchantment.Rarity rarity = Enchantment.Rarity.VERY_RARE;

        @Comment("Should items glow when submerged in lava?")
        public boolean glow = false;
    }

    public static class CactusProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_POWER)
        public int minimumPower = ItemProtectionEnchantment.MIN_POWER;

        @Comment("Enchantment Rarity (likelihood of obtaining the enchantment)\n" +
                "Allowed Values: COMMON, UNCOMMON, RARE, VERY_RARE")
        @EnumHandler
        public Enchantment.Rarity rarity = Enchantment.Rarity.VERY_RARE;
    }

    public static class BlastProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_POWER)
        public int minimumPower = ItemProtectionEnchantment.MIN_POWER;

        @Comment("Enchantment Rarity (likelihood of obtaining the enchantment)\n" +
                "Allowed Values: COMMON, UNCOMMON, RARE, VERY_RARE")
        @EnumHandler
        public Enchantment.Rarity rarity = Enchantment.Rarity.VERY_RARE;
    }

    public static class VoidProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_POWER)
        public int minimumPower = ItemProtectionEnchantment.MIN_POWER;

        @Comment("Enchantment Rarity (likelihood of obtaining the enchantment)\n" +
                "Allowed Values: COMMON, UNCOMMON, RARE, VERY_RARE")
        @EnumHandler
        public Enchantment.Rarity rarity = Enchantment.Rarity.VERY_RARE;

        @Comment("Should the item glow when it falls into the void?")
        public boolean glow = true;
    }

    public static class ExpireProtectionEnchantment {
        @Comment("Minimum experience cost required to apply the enchantment")
        @BoundedDiscrete(min = 0, max = ItemProtectionEnchantment.MAX_POWER)
        public int minimumPower = ItemProtectionEnchantment.MIN_POWER;

        @Comment("Enchantment Rarity (likelihood of obtaining the enchantment)\n" +
                "Allowed Values: COMMON, UNCOMMON, RARE, VERY_RARE")
        @EnumHandler
        public Enchantment.Rarity rarity = Enchantment.Rarity.VERY_RARE;
    }

    public static ModConfiguration getConfig() {
        return AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();
    }
}
