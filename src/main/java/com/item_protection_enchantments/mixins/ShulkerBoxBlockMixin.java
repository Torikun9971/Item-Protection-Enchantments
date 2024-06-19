package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.tileentities.EnchantableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    @Redirect(method = "playerWillDestroy", at = @At(value = "NEW", target = "(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/item/ItemEntity;", ordinal = 0))
    private ItemEntity protection_enchantments$playerWillDestroy(World world, double x, double y, double z, ItemStack stack) {
        TileEntity tileEntity = world.getBlockEntity(new BlockPos(x, y, z));

        if (tileEntity instanceof EnchantableBlock) {
            EnchantableBlock enchantableBlock = (EnchantableBlock) tileEntity;

            if (enchantableBlock.getEnchantmentTag() != null) {
                stack.getOrCreateTag().put("Enchantments", enchantableBlock.getEnchantmentTag());
            }
        }

        return new ItemEntity(world, x, y, z, stack);
    }

    @Inject(method = "setPlacedBy", at = @At("HEAD"))
    public void protection_enchantments$setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack, CallbackInfo ci) {
        TileEntity tileentity = world.getBlockEntity(pos);

        if (tileentity instanceof EnchantableBlock) {
            EnchantableBlock enchantableBlock = (EnchantableBlock) tileentity;

            enchantableBlock.setEnchantments(EnchantmentHelper.getEnchantments(stack));
        }
    }
}
