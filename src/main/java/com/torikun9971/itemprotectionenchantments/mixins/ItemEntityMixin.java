package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Inject(method = "fireImmune", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$fireImmune(CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "setUnderLavaMovement", at = @At("HEAD"))
    private void protection_enchantments$setUnderLavaMovement(CallbackInfo ci) {
        if (!((Object) this instanceof ItemEntity itemEntity))
            return;

        if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
            if (ModConfiguration.getConfig().fireProtection.isGlow)
                itemEntity.setGlowingTag(true);
        }
    }
}
