package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.tileentities.EnchantableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin extends ContainerBlock {
    @Shadow
    @Nullable
    public abstract DyeColor getColor();

    public ShulkerBoxBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "playerWillDestroy", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof ShulkerBoxTileEntity) {
            ShulkerBoxTileEntity shulkerboxtileentity = (ShulkerBoxTileEntity)tileentity;
            if (!world.isClientSide && player.isCreative() && !shulkerboxtileentity.isEmpty()) {
                ItemStack itemstack = ShulkerBoxBlock.getColoredItemStack(this.getColor());
                CompoundNBT compoundnbt = shulkerboxtileentity.saveToTag(new CompoundNBT());
                if (!compoundnbt.isEmpty()) {
                    itemstack.addTagElement("BlockEntityTag", compoundnbt);
                }

                if (shulkerboxtileentity.hasCustomName()) {
                    itemstack.setHoverName(shulkerboxtileentity.getCustomName());
                }

                if (tileentity instanceof EnchantableBlock) {
                    EnchantableBlock enchantableBlock = (EnchantableBlock) tileentity;

                    if (enchantableBlock.getEnchantmentTag() != null) {
                        itemstack.getOrCreateTag().put("Enchantments", enchantableBlock.getEnchantmentTag());
                    }
                }

                ItemEntity itementity = new ItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                world.addFreshEntity(itementity);
            } else {
                shulkerboxtileentity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(world, pos, state, player);
        ci.cancel();
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
