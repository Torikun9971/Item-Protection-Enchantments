package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.enchantment.EnchantmentUtil;
import com.torikun9971.itemprotectionenchantments.enchantment.ModEnchantments;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Shadow
    private int age;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;discard()V", ordinal = 1), cancellable = true)
    public void protection_enchantments$tick(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (EnchantmentUtil.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.EXPIRE_PROTECTION_ITEM)) {
            age = 0;
            ci.cancel();
        }
    }

    @Inject(method = "fireImmune", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$fireImmune(CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (EnchantmentUtil.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "setUnderLavaMovement", at = @At("HEAD"))
    private void protection_enchantments$setUnderLavaMovement(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (EnchantmentUtil.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM)) {
            if (ModConfiguration.getConfig().fireProtection.isGlow)
                itemEntity.setGlowingTag(true);
        }
    }
}
