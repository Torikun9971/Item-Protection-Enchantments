package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.blockentities.EnchantableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin extends BaseEntityBlock {
    @Shadow
    @Nullable
    public abstract DyeColor getColor();

    public ShulkerBoxBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "playerWillDestroy", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player, CallbackInfoReturnable<BlockState> cir) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ShulkerBoxBlockEntity shulkerboxblockentity) {
            if (!level.isClientSide && player.isCreative() && !shulkerboxblockentity.isEmpty()) {
                ItemStack itemstack = ShulkerBoxBlock.getColoredItemStack(this.getColor());
                blockentity.saveToItem(itemstack);
                if (shulkerboxblockentity.hasCustomName()) {
                    itemstack.setHoverName(shulkerboxblockentity.getCustomName());
                }

                if (blockentity instanceof EnchantableBlock enchantableBlock) {
                    if (enchantableBlock.getEnchantmentTag() != null) {
                        itemstack.getOrCreateTag().put("Enchantments", enchantableBlock.getEnchantmentTag());
                    }
                }

                ItemEntity itementity = new ItemEntity(level, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, itemstack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            } else {
                shulkerboxblockentity.unpackLootTable(player);
            }
        }

        cir.setReturnValue(super.playerWillDestroy(level, pos, state, player));
    }

    @Inject(method = "setPlacedBy", at = @At("HEAD"))
    public void protection_enchantments$setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack, CallbackInfo ci) {
        BlockEntity blockentity = level.getBlockEntity(pos);

        if (blockentity instanceof EnchantableBlock enchantableBlock) {
            enchantableBlock.setEnchantments(stack.getAllEnchantments());
        }
    }
}
