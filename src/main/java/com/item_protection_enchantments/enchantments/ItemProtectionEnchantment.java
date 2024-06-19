package com.item_protection_enchantments.enchantments;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class ItemProtectionEnchantment extends Enchantment {
    public static final int MIN_POWER = 30;
    public static final int MAX_POWER = 50;

    public ItemProtectionEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public int getMinPower(int level) {
        return MIN_POWER;
    }

    @Override
    public int getMaxPower(int level) {
        return MAX_POWER;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.VERY_RARE;
    }

    public boolean isAcceptableItem(ItemStack stack, EnchantmentTargetPredicates predicate) {
        if (predicate.getPredicate() != null) {
            return predicate.getPredicate().test(stack.getItem());
        }

        return false;
    }

    public enum EnchantmentTargetPredicates {
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

        EnchantmentTargetPredicates(Predicate<Item> predicate) {
            this.predicate = predicate;
        }

        public Predicate<Item> getPredicate() {
            return predicate;
        }
    }
}
