package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "attemptTickInVoid", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$attemptTickInVoid(CallbackInfo ci) {
        switch (ModConfiguration.getConfig().voidProtectionEnchantment.protectionHeight) {
            case MIN_BUILD_HEIGHT -> {
                if ((Object) this instanceof ItemEntity itemEntity) {
                    if (itemEntity.getY() < itemEntity.getWorld().getBottomY()) {
                        protection_enchantments$protection();
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Inject(method = "tickInVoid", at = @At("HEAD"), cancellable = true)
    protected void protection_enchantments$tickInVoid(CallbackInfo ci) {
        switch (ModConfiguration.getConfig().voidProtectionEnchantment.protectionHeight) {
            case HEIGHT_WHERE_ENTITY_TAKES_DAMAGE -> {
                protection_enchantments$protection();
                ci.cancel();
            }
        }
    }

    @Inject(method = "isImmuneToExplosion", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$isImmuneToExplosion(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.BLAST_PROTECTION_ITEM)) {
                cir.setReturnValue(true);
            }
        }
    }

    @Unique
    private void protection_enchantments$protection() {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.VOID_PROTECTION_ITEM)) {
                itemEntity.setNoGravity(true);
                itemEntity.setInvulnerable(true);
                itemEntity.setPosition(itemEntity.getX(), itemEntity.getWorld().getBottomY() + ModConfiguration.getConfig().voidProtectionEnchantment.protectedHeight, itemEntity.getZ());
                itemEntity.setVelocity(0, 0, 0);
                if (!itemEntity.isGlowing()) itemEntity.setGlowing(ModConfiguration.getConfig().voidProtectionEnchantment.glow);
            }
        }
    }
}
