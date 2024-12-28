package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration.IBaseProtectionConfig;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Predicate;

public abstract class BaseProtectionEnchantment extends Enchantment {
    public static final EnchantmentPredicates PREDICATE = EnchantmentPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;
    public static final int MIN_LEVEL = 30;
    public static final int MAX_LEVEL = 50;
    public static final int ANVIL_COST = 4;
    public static final int WEIGHT = 1;

    public BaseProtectionEnchantment() {
        super(
                Enchantment.properties(
                        ItemTags.AXES,
                        WEIGHT,
                        1,
                        Enchantment.constantCost(MIN_LEVEL),
                        Enchantment.constantCost(MAX_LEVEL),
                        ANVIL_COST,
                        EquipmentSlot.values()
                )
        );
    }

    @Override
    public int getMinPower(int level) {
        return getConfig().getMinimumCost();
    }

    @Override
    public int getMaxPower(int level) {
        return MAX_LEVEL;
    }

    @Override
    public int getAnvilCost() {
        return getConfig().getAnvilCost();
    }

    @Override
    public int getWeight() {
        return getConfig().getWeight();
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return getConfig().getEnchantableItems().getPredicate().test(stack.getItem());
    }

    @Override
    public boolean isTreasure() {
        return getConfig().isTreasure();
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return getConfig().isTradeable();
    }

    protected abstract IBaseProtectionConfig getConfig();

    public enum EnchantmentPredicates {
        ALL_ITEMS((item) -> true),

        ITEMS_AND_COMPATIBLE_BLOCKS((item) -> {
            if (!(item instanceof BlockItem)) {
                return true;
            }

            if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {
                return true;
            }

            return false;
        }),

        ITEMS_ONLY((item) -> {
            if (item instanceof BlockItem) {
                return false;
            }

            return true;
        });

        private final Predicate<Item> predicate;

        EnchantmentPredicates(Predicate<Item> predicate) {
            this.predicate = predicate;
        }

        public Predicate<Item> getPredicate() {
            return predicate;
        }
    }
}
