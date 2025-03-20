package com.torikun9971.itemprotectionenchantments.mixins.alexscaves;

import com.github.alexmodguy.alexscaves.server.block.AcidBlock;
import com.torikun9971.itemprotectionenchantments.compat.alexscaves.AlexsCavesPlugin;
import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import com.torikun9971.itemprotectionenchantments.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(AcidBlock.class)
public abstract class AcidBlockMixin {
    @Inject(method = "entityInside", at = @At("HEAD"))
    private void protection_enchantments$entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!(entity instanceof ItemEntity itemEntity))
            return;

        if (Util.hasEnchantment(itemEntity, AlexsCavesPlugin.ACID_PROTECTION_ITEM.get())) {
            if (ModConfiguration.getConfig().acidProtection.isGlow)
                itemEntity.setGlowingTag(true);
        }
    }

    @Redirect(method = "entityInside", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"))
    private void protection_enchantments$entityInside$hurtAndBreak(ItemStack instance, int i, LivingEntity pAmount, Consumer<LivingEntity> pEntity) {
        if (Util.hasEnchantment(instance, AlexsCavesPlugin.ACID_PROTECTION_ITEM.get())) {
            return;
        }

        instance.hurtAndBreak(i, pAmount, pEntity);
    }

    @Redirect(method = "entityInside", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean protection_enchantments$entityInside$hurt(Entity instance, DamageSource pSource, float pAmount) {
        if (Util.hasEnchantment(instance, AlexsCavesPlugin.ACID_PROTECTION_ITEM.get())) {
            return false;
        }

        return instance.hurt(pSource, pAmount);
    }
}
