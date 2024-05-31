package com.item_protection_enchantments.enchantments;

import com.item_protection_enchantments.config.ModConfiguration;
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
        super(Rarity.VERY_RARE, EnchantmentTypes.ALL_ITEMS.getEnchantmentCategory(), EquipmentSlotType.values());
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

    @Override
    public boolean canEnchant(ItemStack stack) {
        if (ModConfiguration.ENCHANTABLE_ITEMS.get().getPredicate() != null) {
            return ModConfiguration.ENCHANTABLE_ITEMS.get().getPredicate().test(stack.getItem());
        }

        return false;
    }

    public enum EnchantmentTypes {
        ALL_ITEMS("all_items", (item) -> item instanceof Item),

        ITEMS_AND_COMPATIBLE_BLOCKS("items_and_compatible_blocks", (item) -> {
            if (item instanceof Item && !(item instanceof BlockItem)) {
                return true;
            }

            if (item instanceof BlockItem) {
                BlockItem blockItem = (BlockItem) item;

                if (blockItem.getBlock() instanceof ShulkerBoxBlock) {
                    return true;
                }
            }

            return false;
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

        public EnchantmentType getEnchantmentCategory() {
            return type;
        }
    }
}
