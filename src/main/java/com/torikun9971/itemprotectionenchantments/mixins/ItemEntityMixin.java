package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Shadow
    private int itemAge;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;discard()V", ordinal = 1), cancellable = true)
    public void protection_enchantments$tick(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.EXPIRE_PROTECTION_ITEM)) {
            itemAge = 0;
            ci.cancel();
        }
    }

    @Inject(method = "applyLavaBuoyancy", at = @At("HEAD"))
    private void protection_enchantments$applyLavaBuoyancy(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.FIRE_PROTECTION_ITEM)) {
            if (ModConfiguration.getConfig().fireProtection.isGlow)
                itemEntity.setGlowing(true);
        }
    }

    @Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$isFireImmune(CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.FIRE_PROTECTION_ITEM)) {
            cir.setReturnValue(true);
        }
    }
}
