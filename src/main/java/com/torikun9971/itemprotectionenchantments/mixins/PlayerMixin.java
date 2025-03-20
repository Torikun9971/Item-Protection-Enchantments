package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import com.torikun9971.itemprotectionenchantments.util.Util;
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
    /**
     * delete items cursed with the Curse of Vanishing under specific conditions.
     */
    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;destroyVanishingCursedItems()V", ordinal = 0))
    private void protection_enchantments$destroyVanishingCursedItems(Player player) {
        for(int i = 0; i < player.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = player.inventory.getItem(i);

            if (itemstack.isEmpty()) continue;

            if (!EnchantmentHelper.hasVanishingCurse(itemstack))
                continue;

            if (Util.hasEnchantment(itemstack, ModEnchantments.INVENTORY_HOLDING.get()) &&
                    ModConfiguration.getConfig().inventoryHolding.isVanishingCurseDisabled
            ) continue;

            player.inventory.removeItemNoUpdate(i);
        }
    }

    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;dropAll()V", ordinal = 0))
    private void protection_enchantments$dropAll(Inventory inventory) {
        for(List<ItemStack> list : inventory.compartments) {
            for(int i = 0; i < list.size(); ++i) {
                ItemStack itemstack = list.get(i);

                if (itemstack.isEmpty()) continue;

                if (Util.hasEnchantment(itemstack, ModEnchantments.INVENTORY_HOLDING.get()))
                    continue;

                inventory.player.drop(itemstack, true, false);
                list.set(i, ItemStack.EMPTY);
            }
        }
    }
}
