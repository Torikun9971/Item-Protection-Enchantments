package com.item_protection_enchantments.mixins;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(method = "selectEnchantment", at = @At("RETURN"), cancellable = true)
    private static void protection_enchantments$selectEnchantment(RandomSource random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {
        List<EnchantmentInstance> enchantments = cir.getReturnValue();
        boolean hasInvalidEnchantment = true;

        while (hasInvalidEnchantment) {
            hasInvalidEnchantment = enchantments.stream().anyMatch((entry) -> !entry.enchantment.canEnchant(stack));

            if (hasInvalidEnchantment) {
                enchantments = EnchantmentHelper.selectEnchantment(random, stack, level, treasureAllowed);
            }
        }

        cir.setReturnValue(enchantments);
    }
}
