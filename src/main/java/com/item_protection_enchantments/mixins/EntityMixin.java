package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "tickInVoid", at = @At("HEAD"), cancellable = true)
    protected void protection_enchantments$tickInVoid(CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.VOID_PROTECTION_ITEM)) {
                itemEntity.setNoGravity(true);
                itemEntity.setInvulnerable(true);
                itemEntity.setPosition(itemEntity.getX(), itemEntity.getWorld().getBottomY() + 1, itemEntity.getZ());
                itemEntity.setVelocity(0, 0, 0);
                if (!itemEntity.isGlowing()) itemEntity.setGlowing(ModConfiguration.getConfig().voidProtectionEnchantment.glow);

                ci.cancel();
            }
        }
    }

    @Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$isFireImmune(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.FIRE_PROTECTION_ITEM)) {
                cir.setReturnValue(true);
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
}
