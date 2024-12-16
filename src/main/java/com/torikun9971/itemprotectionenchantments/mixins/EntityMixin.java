package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration.VoidProtectionEnchantment.ProtectionHeights;
import com.torikun9971.itemprotectionenchantments.enchantment.EnchantmentUtil;
import com.torikun9971.itemprotectionenchantments.enchantment.ModEnchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.explosion.Explosion;
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
        if (ModConfiguration.getConfig().voidProtection.protectionHeight != ProtectionHeights.MIN_BUILD_HEIGHT)
            return;

        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (itemEntity.getY() < itemEntity.getWorld().getBottomY()) {
            protection_enchantments$protection(ci);
        }
    }

    @Inject(method = "tickInVoid", at = @At("HEAD"), cancellable = true)
    protected void protection_enchantments$tickInVoid(CallbackInfo ci) {
        if (ModConfiguration.getConfig().voidProtection.protectionHeight == ProtectionHeights.HEIGHT_WHERE_ENTITY_TAKES_DAMAGE) {
            protection_enchantments$protection(ci);
        }
    }

    @Inject(method = "isImmuneToExplosion", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$isImmuneToExplosion(Explosion explosion, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (EnchantmentUtil.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.BLAST_PROTECTION_ITEM)) {
            cir.setReturnValue(true);
        }
    }

    /**
     * Protect if it is a target ItemEntity.
     */
    @Unique
    private void protection_enchantments$protection(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (EnchantmentUtil.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.VOID_PROTECTION_ITEM)) {
            itemEntity.setNoGravity(true);
            itemEntity.setInvulnerable(true);
            itemEntity.setVelocity(0, 0, 0);
            itemEntity.setPosition(
                    itemEntity.getX(),
                    itemEntity.getWorld().getBottomY() + ModConfiguration.getConfig().voidProtection.protectedHeight,
                    itemEntity.getZ()
            );

            if (ModConfiguration.getConfig().voidProtection.isGlow)
                itemEntity.setGlowing(true);

            ci.cancel();
        }
    }
}
