package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import com.torikun9971.itemprotectionenchantments.enchantments.VoidProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getY()D"), cancellable = true)
    public void protection_enchantments$checkOutOfWorld(CallbackInfo ci) {
        if (ModConfiguration.getConfig().voidProtection.protectionHeight != VoidProtectionEnchantment.ProtectionHeights.MIN_BUILD_HEIGHT)
            return;

        if (!((Object) this instanceof ItemEntity))
            return;

        if (((ItemEntity) (Object) this).getY() < 0) {
            protection_enchantments$protection(ci);
        }
    }

    @Inject(method = "outOfWorld", at = @At("HEAD"), cancellable = true)
    protected void protection_enchantments$outOfWorld(CallbackInfo ci) {
        if (ModConfiguration.getConfig().voidProtection.protectionHeight == VoidProtectionEnchantment.ProtectionHeights.HEIGHT_WHERE_ENTITY_TAKES_DAMAGE) {
            protection_enchantments$protection(ci);
        }
    }

    /**
     * Protect if it is a target ItemEntity.
     */
    @Unique
    private void protection_enchantments$protection(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity))
            return;

        ItemEntity itemEntity = (ItemEntity) (Object) this;
        if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.VOID_PROTECTION_ITEM.get())) {
            itemEntity.setNoGravity(true);
            itemEntity.setInvulnerable(true);
            itemEntity.setDeltaMovement(0, 0, 0);
            itemEntity.setPos(
                    itemEntity.getX(),
                    ModConfiguration.getConfig().voidProtection.protectedHeight,
                    itemEntity.getZ()
            );

            if (ModConfiguration.getConfig().voidProtection.isGlow)
                itemEntity.setGlowing(true);

            ci.cancel();
        }
    }
}
