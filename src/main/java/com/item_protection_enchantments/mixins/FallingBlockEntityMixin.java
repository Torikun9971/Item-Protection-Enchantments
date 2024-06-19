package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {
    @Redirect(method = "causeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;", ordinal = 0))
    private List<Entity> protection_enchantments$causeFallDamage(World world, Entity entity, AxisAlignedBB aabb) {
        DamageSource damageSource = DamageSource.FALLING_BLOCK;

        if ((Object) this instanceof FallingBlockEntity) {
            FallingBlockEntity fallingBlockEntity = (FallingBlockEntity) (Object) this;

            if (fallingBlockEntity.getBlockState().is(BlockTags.ANVIL)) {
                damageSource = DamageSource.ANVIL;
            }
        }

        if (ModConfiguration.ANVIL_BUG_FIX.get()) {
            if (damageSource == DamageSource.ANVIL) {
                return world.getEntities(entity, aabb).stream().filter((e) -> {
                    if (e instanceof ItemEntity) {
                        return false;
                    }

                    return true;
                }).collect(Collectors.toList());
            }
        }

        return world.getEntities(entity, aabb);
    }
}
