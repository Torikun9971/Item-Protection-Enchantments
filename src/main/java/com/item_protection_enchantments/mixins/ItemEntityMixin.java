package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Inject(method = "fireImmune", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$fireImmune(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) (Object) this;

            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "setUnderLavaMovement", at = @At("HEAD"))
    private void protection_enchantments$setUnderLavaMovement(CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) (Object) this;

            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
                if (!itemEntity.isGlowing()) itemEntity.setGlowing(ModConfiguration.FIRE_PROTECTION_GLOWING_TAG.get());
            }
        }
    }
}
