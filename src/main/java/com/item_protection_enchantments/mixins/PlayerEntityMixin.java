package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;vanishCursedItems()V", ordinal = 0))
    private void protection_enchantments$vanishCursedItems(PlayerEntity player) {
        for (int i = 0; i < player.inventory.size(); ++i) {
            ItemStack itemStack = player.inventory.getStack(i);
            if (itemStack.isEmpty() || !EnchantmentHelper.hasVanishingCurse(itemStack)) continue;
            if (ItemProtectionEnchantments.hasEnchantment(itemStack, true, ModEnchantments.INVENTORY_HOLDING) && ModConfiguration.getConfig().inventoryHoldingEnchantment.disableVanishingCurse) continue;
            player.inventory.removeStack(i);
        }
    }

    @Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;dropAll()V", ordinal = 0))
    private void protection_enchantments$dropAll(PlayerInventory inventory) {
        for (List list : inventory.combinedInventory) {
            for (int i = 0; i < list.size(); ++i) {
                ItemStack itemStack = (ItemStack)list.get(i);
                if (itemStack.isEmpty()) continue;
                if (ItemProtectionEnchantments.hasEnchantment(itemStack, true, ModEnchantments.INVENTORY_HOLDING)) continue;
                inventory.player.dropItem(itemStack, true, false);
                list.set(i, ItemStack.EMPTY);
            }
        }
    }
}
