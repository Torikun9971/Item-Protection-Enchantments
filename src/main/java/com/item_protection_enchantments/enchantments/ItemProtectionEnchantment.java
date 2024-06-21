package com.item_protection_enchantments.enchantments;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Predicate;

public class ItemProtectionEnchantment extends Enchantment {
    public static final int WEIGHT = 1;
    public static final int ANVIL_COST = 8;
    public static final int MIN_LEVEL = 30;
    public static final int MAX_LEVEL = 50;

    public ItemProtectionEnchantment() {
        super(Enchantment.properties(ItemTags.AXES, WEIGHT, 1, Enchantment.constantCost(MIN_LEVEL), Enchantment.constantCost(MAX_LEVEL), ANVIL_COST, EquipmentSlot.values()));
    }

    @Override
    public int getWeight() {
        return WEIGHT;
    }

    @Override
    public int getAnvilCost() {
        return ANVIL_COST;
    }

    @Override
    public int getMinPower(int level) {
        return MIN_LEVEL;
    }

    @Override
    public int getMaxPower(int level) {
        return MAX_LEVEL;
    }

    public boolean isAcceptableItem(ItemStack stack, EnchantmentPredicates predicate) {
        if (predicate.getPredicate() != null) {
            return predicate.getPredicate().test(stack.getItem());
        }

        return false;
    }

    public enum EnchantmentPredicates {
        ALL_ITEMS((item) -> true),

        ITEMS_AND_COMPATIBLE_BLOCKS((item) -> {
            if (!(item instanceof BlockItem)) {
                return true;
            }

            if (item instanceof BlockItem blockItem) {
                if (blockItem.getBlock() instanceof ShulkerBoxBlock) {
                    return true;
                }
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
