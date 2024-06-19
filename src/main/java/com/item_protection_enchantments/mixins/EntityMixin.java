package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "checkBelowWorld", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$checkBelowWorld(CallbackInfo ci) {
        switch (ModConfiguration.VOID_PROTECTION_PROTECTION_HEIGHT.get()) {
            case MIN_BUILD_HEIGHT -> {
                if ((Object) this instanceof ItemEntity itemEntity) {
                    if (itemEntity.getY() < itemEntity.level().getMinBuildHeight()) {
                        protection_enchantments$protection();
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Inject(method = "onBelowWorld", at = @At("HEAD"), cancellable = true)
    protected void protection_enchantments$onBelowWorld(CallbackInfo ci) {
        switch (ModConfiguration.VOID_PROTECTION_PROTECTION_HEIGHT.get()) {
            case HEIGHT_WHERE_ENTITY_TAKES_DAMAGE -> {
                protection_enchantments$protection();
                ci.cancel();
            }
        }
    }

    @Unique
    private void protection_enchantments$protection() {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.VOID_PROTECTION_ITEM.get())) {
                itemEntity.setNoGravity(true);
                itemEntity.setInvulnerable(true);
                itemEntity.setPos(itemEntity.getX(), itemEntity.level().getMinBuildHeight() + ModConfiguration.VOID_PROTECTION_PROTECTED_HEIGHT.get(), itemEntity.getZ());
                itemEntity.setDeltaMovement(0, 0, 0);
                if (!itemEntity.hasGlowingTag()) itemEntity.setGlowingTag(ModConfiguration.VOID_PROTECTION_GLOWING_TAG.get());
            }
        }
    }
}
