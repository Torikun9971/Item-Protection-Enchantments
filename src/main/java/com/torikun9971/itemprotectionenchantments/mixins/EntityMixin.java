package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.enchantments.VoidProtectionEnchantment.ProtectionHeights;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import com.torikun9971.itemprotectionenchantments.util.Util;
import net.minecraft.world.entity.*;
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
        if (ModConfiguration.getConfig().voidProtection.protectionHeight != ProtectionHeights.MIN_BUILD_HEIGHT)
            return;

        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (itemEntity.getY() < itemEntity.level().getMinBuildHeight()) {
            protection_enchantments$protection(ci);
        }
    }

    @Inject(method = "onBelowWorld", at = @At("HEAD"), cancellable = true)
    protected void protection_enchantments$onBelowWorld(CallbackInfo ci) {
        if (ModConfiguration.getConfig().voidProtection.protectionHeight == ProtectionHeights.HEIGHT_WHERE_ENTITY_TAKES_DAMAGE) {
            protection_enchantments$protection(ci);
        }
    }

    /**
     * Protect if it is a target ItemEntity.
     */
    @Unique
    private void protection_enchantments$protection(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (Util.hasEnchantment(itemEntity.getItem(), ModEnchantments.VOID_PROTECTION_ITEM.get())) {
            itemEntity.setNoGravity(true);
            itemEntity.setInvulnerable(true);
            itemEntity.setDeltaMovement(0, 0, 0);
            itemEntity.setPos(
                    itemEntity.getX(),
                    itemEntity.level().getMinBuildHeight() + ModConfiguration.getConfig().voidProtection.protectedHeight,
                    itemEntity.getZ()
            );

            if (ModConfiguration.getConfig().voidProtection.isGlow)
                itemEntity.setGlowingTag(true);

            ci.cancel();
        }
    }
}
