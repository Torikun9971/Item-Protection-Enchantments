package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.blockentities.EnchantableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    @Redirect(method = "onBreak", at = @At(value = "NEW", target = "(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/ItemEntity;", ordinal = 0))
    private ItemEntity protection_enchantments$onBreak(World world, double x, double y, double z, ItemStack stack) {
        BlockEntity blockEntity = world.getBlockEntity(new BlockPos(MathHelper.floor(x),MathHelper.floor(y),MathHelper.floor(z)));

        if (blockEntity instanceof EnchantableBlock enchantableBlock) {
            if (enchantableBlock.getEnchantmentNbt() != null) {
                stack.getOrCreateNbt().put("Enchantments", enchantableBlock.getEnchantmentNbt());
            }
        }

        return new ItemEntity(world, x, y, z, stack);
    }

    @Inject(method = "onPlaced", at = @At("HEAD"))
    public void protection_enchantments$onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo ci) {
        BlockEntity blockentity = world.getBlockEntity(pos);

        if (blockentity instanceof EnchantableBlock enchantableBlock) {
            enchantableBlock.setEnchantments(EnchantmentHelper.get(stack));
        }
    }
}
