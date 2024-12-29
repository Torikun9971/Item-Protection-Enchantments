package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.enchantment.EnchantmentUtil;
import com.torikun9971.itemprotectionenchantments.enchantment.ModEnchantments;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    /**
     * delete items cursed with the Curse of Vanishing under specific conditions.
     */
    @Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;vanishCursedItems()V", ordinal = 0))
    private void protection_enchantments$vanishCursedItems(PlayerEntity player) {
        for (int i = 0; i < player.inventory.size(); ++i) {
            ItemStack itemStack = player.inventory.getStack(i);

            if (itemStack.isEmpty()) continue;

            if (!EnchantmentHelper.hasAnyEnchantmentsWith(itemStack, EnchantmentEffectComponentTypes.PREVENT_EQUIPMENT_DROP))
                continue;

            if (EnchantmentUtil.hasEnchantment(itemStack, true, ModEnchantments.INVENTORY_HOLDING) &&
                    ModConfiguration.getConfig().inventoryHolding.isVanishingCurseDisabled)
                continue;

            player.inventory.removeStack(i);
        }
    }

    @Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;dropAll()V", ordinal = 0))
    private void protection_enchantments$dropAll(PlayerInventory inventory) {
        for (DefaultedList<ItemStack> list : inventory.combinedInventory) {
            for (int i = 0; i < list.size(); ++i) {
                ItemStack itemStack = list.get(i);

                if (itemStack.isEmpty()) continue;

                if (EnchantmentUtil.hasEnchantment(itemStack, true, ModEnchantments.INVENTORY_HOLDING))
                    continue;

                inventory.player.dropItem(itemStack, true, false);
                list.set(i, ItemStack.EMPTY);
            }
        }
    }
}
