package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.init.ModEnchantments;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CactusBlock.class)
public abstract class CactusBlockMixin {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$entityInside(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!(entity instanceof ItemEntity))
            return;

        if (ItemProtectionEnchantments.hasEnchantment(
                ((ItemEntity) entity).getItem(), true, ModEnchantments.CACTUS_PROTECTION_ITEM.get()
        )) {
            ci.cancel();
        }
    }
}
