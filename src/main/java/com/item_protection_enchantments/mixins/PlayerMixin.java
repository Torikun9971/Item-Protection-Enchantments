package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;destroyVanishingCursedItems()V", ordinal = 0))
    private void protection_enchantments$destroyVanishingCursedItems(Player player) {
        for(int i = 0; i < player.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = player.inventory.getItem(i);
            if (!itemstack.isEmpty() && EnchantmentHelper.hasVanishingCurse(itemstack)) {
                if (ItemProtectionEnchantments.hasEnchantment(itemstack, true, ModEnchantments.INVENTORY_HOLDING.get())) {
                    if (ModConfiguration.INVENTORY_HOLDING_DISABLE_VANISHING_CURSE.get()) {
                        continue;
                    }
                }

                player.inventory.removeItemNoUpdate(i);
            }
        }
    }

    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;dropAll()V", ordinal = 0))
    private void protection_enchantments$dropAll(Inventory inventory) {
        for(List<ItemStack> list : inventory.compartments) {
            for(int i = 0; i < list.size(); ++i) {
                ItemStack itemstack = list.get(i);
                if (!itemstack.isEmpty()) {
                    if (!ItemProtectionEnchantments.hasEnchantment(itemstack, true, ModEnchantments.INVENTORY_HOLDING.get())) {
                        inventory.player.drop(itemstack, true, false);
                        list.set(i, ItemStack.EMPTY);
                    }
                }
            }
        }
    }
}
