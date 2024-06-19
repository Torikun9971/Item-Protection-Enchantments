package com.item_protection_enchantments.enchantments;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class ItemProtectionEnchantment extends Enchantment {
    public static final int MIN_COST = 30;
    public static final int MAX_COST = 50;

    public ItemProtectionEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTypes.ALL_ITEMS.getEnchantmentType(), EquipmentSlotType.values());
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return MIN_COST;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return MAX_COST;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.VERY_RARE;
    }

    public boolean canEnchant(ItemStack stack, EnchantmentTypes category) {
        if (category.getPredicate() != null) {
            return category.getPredicate().test(stack.getItem());
        }

        return false;
    }

    public enum EnchantmentTypes {
        ALL_ITEMS("all_items", (item) -> true),

        ITEMS_AND_COMPATIBLE_BLOCKS("items_and_compatible_blocks", (item) -> {
            if (!(item instanceof BlockItem)) {
                return true;
            }

            if (item instanceof BlockItem) {
                BlockItem blockItem = (BlockItem) item;

                if (blockItem.getBlock() instanceof ShulkerBoxBlock) {
                    return true;
                }
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
