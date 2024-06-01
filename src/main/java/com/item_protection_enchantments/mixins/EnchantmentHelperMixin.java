package com.item_protection_enchantments.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(method = "generateEnchantments", at = @At("RETURN"), cancellable = true)
    private static void protection_enchantments$generateEnchantments(FeatureSet enabledFeatures, Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        List<EnchantmentLevelEntry> enchantments = cir.getReturnValue();
        boolean hasInvalidEnchantment = true;

        while (hasInvalidEnchantment) {
            hasInvalidEnchantment = enchantments.stream().anyMatch((entry) -> !entry.enchantment.isAcceptableItem(stack));

            if (hasInvalidEnchantment) {
                enchantments = EnchantmentHelper.generateEnchantments(enabledFeatures, random, stack, level, treasureAllowed);
            }
        }

        cir.setReturnValue(enchantments);
    }
}
