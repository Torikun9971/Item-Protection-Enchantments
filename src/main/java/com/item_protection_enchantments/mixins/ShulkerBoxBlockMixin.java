package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.blockentities.EnchantableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    @Redirect(method = "playerWillDestroy", at = @At(value = "NEW", target = "(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/item/ItemEntity;", ordinal = 0))
    private ItemEntity protection_enchantments$playerWillDestroy(Level level, double x, double y, double z, ItemStack stack) {
        BlockEntity blockEntity = level.getBlockEntity(new BlockPos(x, y, z));

        if (blockEntity instanceof EnchantableBlock enchantableBlock) {
            if (enchantableBlock.getEnchantmentTag() != null) {
                stack.getOrCreateTag().put("Enchantments", enchantableBlock.getEnchantmentTag());
            }
        }

        return new ItemEntity(level, x, y, z, stack);
    }

    @Inject(method = "setPlacedBy", at = @At("HEAD"))
    public void protection_enchantments$setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack, CallbackInfo ci) {
        BlockEntity blockentity = level.getBlockEntity(pos);

        if (blockentity instanceof EnchantableBlock enchantableBlock) {
            enchantableBlock.setEnchantments(EnchantmentHelper.getEnchantments(stack));
        }
    }
}
