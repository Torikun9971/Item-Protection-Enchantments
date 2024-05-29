package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.config.ModConfiguration;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Shadow
    private int itemAge;

    @Inject(method = "tick", at = @At("HEAD"))
    public void protection_enchantments$tick(CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.EXPIRE_PROTECTION_ITEM)) {
                if (itemEntity.getItemAge() >= 5999) {
                    itemAge = -1;
                }
            }
        }
    }

    @Inject(method = "applyLavaBuoyancy", at = @At("HEAD"))
    private void protection_enchantments$applyLavaBuoyancy(CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getStack(), true, ModEnchantments.FIRE_PROTECTION_ITEM)) {
                if (!itemEntity.isGlowing()) itemEntity.setGlowing(ModConfiguration.getConfig().fireProtectionEnchantment.glow);
            }
        }
    }
}
