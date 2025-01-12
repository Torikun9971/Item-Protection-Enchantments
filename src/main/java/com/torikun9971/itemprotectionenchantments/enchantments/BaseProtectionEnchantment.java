package com.torikun9971.itemprotectionenchantments.enchantments;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration.IBaseProtectionConfig;
import com.torikun9971.itemprotectionenchantments.interfaces.EnchantmentCondition;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public abstract class BaseProtectionEnchantment extends Enchantment implements EnchantmentCondition {
    public static final int MIN_POWER = 30;
    public static final int MAX_POWER = 50;
    public static final Rarity RARITY = Rarity.VERY_RARE;
    public static final EnchantmentTargetPredicates TARGET_PREDICATE = EnchantmentTargetPredicates.ITEMS_AND_COMPATIBLE_BLOCKS;

    public BaseProtectionEnchantment() {
        super(RARITY, EnchantmentTarget.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public int getMinPower(int level) {
        return getConfig().getMinimumCost();
    }

    @Override
    public int getMaxPower(int level) {
        return MAX_POWER;
    }

    @Override
    public Rarity getRarity() {
        return getConfig().getRarity();
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

    @Override
    public boolean condition(ItemStack stack) {
        return isAcceptableItem(stack);
    }

    protected abstract IBaseProtectionConfig getConfig();

    public enum EnchantmentTargetPredicates {
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

        EnchantmentTargetPredicates(Predicate<Item> predicate) {
            this.predicate = predicate;
        }

        public Predicate<Item> getPredicate() {
            return predicate;
        }
    }
}
