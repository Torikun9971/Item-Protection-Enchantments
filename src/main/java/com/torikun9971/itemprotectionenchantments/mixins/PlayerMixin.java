package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.enchantment.EnchantmentUtil;
import com.torikun9971.itemprotectionenchantments.enchantment.ModEnchantments;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
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

            if (!EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP))
                continue;

            if (EnchantmentUtil.hasEnchantment(itemstack, true, ModEnchantments.INVENTORY_HOLDING) &&
                    ModConfiguration.getConfig().inventoryHolding.isVanishingCurseDisabled)
                continue;

            player.inventory.removeItemNoUpdate(i);
        }
    }

    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;dropAll()V", ordinal = 0))
    private void protection_enchantments$dropAll(Inventory inventory) {
        for(List<ItemStack> list : inventory.compartments) {
            for(int i = 0; i < list.size(); ++i) {
                ItemStack itemstack = list.get(i);

                if (itemstack.isEmpty()) continue;

                if (EnchantmentUtil.hasEnchantment(itemstack, true, ModEnchantments.INVENTORY_HOLDING))
                    continue;

                inventory.player.drop(itemstack, true, false);
                list.set(i, ItemStack.EMPTY);
            }
        }
    }
}
