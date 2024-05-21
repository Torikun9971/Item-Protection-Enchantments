package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "lavaHurt", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$lavaHurt(CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) (Object) this;

            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "outOfWorld", at = @At("HEAD"), cancellable = true)
    protected void protection_enchantments$outOfWorld(CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) (Object) this;

            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.VOID_PROTECTION_ITEM.get())) {
                itemEntity.setNoGravity(true);
                itemEntity.setInvulnerable(true);
                itemEntity.setPos(itemEntity.getX(), 1, itemEntity.getZ());
                itemEntity.setDeltaMovement(0, 0, 0);
                itemEntity.setGlowing(ModConfiguration.VOID_PROTECTION_GLOWING_TAG.get());

                ci.cancel();
            }
        }
    }
}
