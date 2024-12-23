package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
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
    /**
     * delete items cursed with the Curse of Vanishing under specific conditions.
     */
    @Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;vanishCursedItems()V", ordinal = 0))
    private void protection_enchantments$vanishCursedItems(PlayerEntity player) {
        for (int i = 0; i < player.inventory.size(); ++i) {
            ItemStack itemstack = player.inventory.getStack(i);

            if (itemstack.isEmpty()) return;

            if (!EnchantmentHelper.hasVanishingCurse(itemstack))
                return;

            if (ItemProtectionEnchantments.hasEnchantment(itemstack, true, ModEnchantments.INVENTORY_HOLDING) &&
                    ModConfiguration.getConfig().inventoryHolding.isVanishingCurseDisabled)
                continue;

            player.inventory.removeStack(i);
        }
    }

    @Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;dropAll()V", ordinal = 0))
    private void protection_enchantments$dropAll(PlayerInventory inventory) {
        for (List<ItemStack> list : inventory.combinedInventory) {
            for (int i = 0; i < list.size(); ++i) {
                ItemStack itemstack = list.get(i);

                if (itemstack.isEmpty()) continue;

                if (ItemProtectionEnchantments.hasEnchantment(itemstack, true, ModEnchantments.INVENTORY_HOLDING))
                    continue;

                inventory.player.dropItem(itemstack, true, false);
                list.set(i, ItemStack.EMPTY);
            }
        }
    }
}
