package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFireBlock.class)
public abstract class AbstractFireBlockMixin extends Block {
    public AbstractFireBlockMixin(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$entityInside(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (entity instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) entity;

            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
                super.entityInside(state, world, pos, entity);
                ci.cancel();
            }
        }
    }
}
