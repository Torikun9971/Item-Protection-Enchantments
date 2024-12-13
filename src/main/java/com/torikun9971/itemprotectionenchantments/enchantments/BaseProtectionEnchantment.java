package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration.IBaseProtectionConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.ShulkerBoxBlock;

import java.util.function.Predicate;

public abstract class BaseProtectionEnchantment extends Enchantment {
    public static final int MIN_COST = 30;
    public static final int MAX_COST = 50;
    public static final Rarity RARITY = Rarity.VERY_RARE;
    public static final EnchantmentCategories CATEGORY = EnchantmentCategories.ITEMS_AND_COMPATIBLE_BLOCKS;

    public BaseProtectionEnchantment() {
        super(RARITY, CATEGORY.getEnchantmentCategory(), EquipmentSlot.values());
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return getConfig().getMinimumCost();
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return MAX_COST;
    }

    @Override
    public Rarity getRarity() {
        return getConfig().getRarity();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return getConfig().getEnchantableItems().getPredicate().test(stack.getItem());
    }

    @Override
    public boolean isTreasureOnly() {
        return getConfig().isTreasure();
    }

    @Override
    public boolean isTradeable() {
        return getConfig().isTradeable();
    }

    protected abstract IBaseProtectionConfig getConfig();

    public enum EnchantmentCategories {
        ALL_ITEMS("all_items", (item) -> true),

        ITEMS_AND_COMPATIBLE_BLOCKS("items_and_compatible_blocks", (item) -> {
            if (!(item instanceof BlockItem)) {
                return true;
            }

            if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {
                return true;
            }

            return false;
        }),

        ITEMS_ONLY("items_only", (item) -> {
            if (item instanceof BlockItem) {
                return false;
            }

            return true;
        });

        private final Predicate<Item> predicate;
        private final EnchantmentCategory category;

        EnchantmentCategories(String name, Predicate<Item> predicate) {
            this.predicate = predicate;
            this.category = EnchantmentCategory.create(name, predicate);
        }

        public Predicate<Item> getPredicate() {
            return predicate;
        }

        public EnchantmentCategory getEnchantmentCategory() {
            return category;
        }
    }
}
