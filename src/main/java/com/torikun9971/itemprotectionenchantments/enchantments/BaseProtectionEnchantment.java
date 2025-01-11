package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration.IBaseProtectionConfig;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public abstract class BaseProtectionEnchantment extends Enchantment {
    public static final int MIN_COST = 30;
    public static final int MAX_COST = 50;
    public static final Rarity RARITY = Rarity.VERY_RARE;
    public static final EnchantmentTypes CATEGORY = EnchantmentTypes.ITEMS_AND_COMPATIBLE_BLOCKS;

    public BaseProtectionEnchantment() {
        super(RARITY, CATEGORY.getEnchantmentType(), EquipmentSlotType.values());
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

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return canEnchant(stack);
    }

    protected abstract IBaseProtectionConfig getConfig();

    public enum EnchantmentTypes {
        ALL_ITEMS("all_items", (item) -> true),

        ITEMS_AND_COMPATIBLE_BLOCKS("items_and_compatible_blocks", (item) -> {
            if (!(item instanceof BlockItem)) {
                return true;
            }

            if (item instanceof BlockItem &&
                    ((BlockItem) item).getBlock() instanceof ShulkerBoxBlock) {
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
        private final EnchantmentType type;

        EnchantmentTypes(String name, Predicate<Item> predicate) {
            this.predicate = predicate;
            this.type = EnchantmentType.create(name, predicate);
        }

        public Predicate<Item> getPredicate() {
            return predicate;
        }

        public EnchantmentType getEnchantmentType() {
            return type;
        }
    }
}
