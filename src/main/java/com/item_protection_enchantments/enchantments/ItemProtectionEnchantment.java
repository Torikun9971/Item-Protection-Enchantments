package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.ShulkerBoxBlock;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class ItemProtectionEnchantment extends Enchantment {
    public static final int MIN_COST = 30;
    public static final int MAX_COST = 50;

    public ItemProtectionEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategories.ALL_ITEMS.getEnchantmentCategory(), EquipmentSlot.values());
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return MIN_COST;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return MAX_COST;
    }

    @Nonnull
    @Override
    public Rarity getRarity() {
        return Rarity.VERY_RARE;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        if (ModConfiguration.ENCHANTABLE_ITEMS.get().getPredicate() != null) {
            return ModConfiguration.ENCHANTABLE_ITEMS.get().getPredicate().test(stack.getItem());
        }

        return false;
    }

    public enum EnchantmentCategories {
        ALL_ITEMS("all_items", (item) -> item instanceof Item),

        ITEMS_AND_COMPATIBLE_BLOCKS("items_and_compatible_blocks", (item) -> {
            if (item instanceof Item && !(item instanceof BlockItem)) {
                return true;
            }

            if (item instanceof BlockItem blockItem) {
                if (blockItem.getBlock() instanceof ShulkerBoxBlock) {
                    return true;
                }
            }

            return false;
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
