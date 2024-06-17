package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.config.ModConfiguration;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.function.Predicate;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityMixin {
    @Redirect(method = "causeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getEntities(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;", ordinal = 0))
    private List<Entity> protection_enchantments$causeFallDamage(Level level, Entity entity, AABB aabb, Predicate<? super Entity> predicate) {
        DamageSource damageSource = DamageSource.FALLING_BLOCK;

        if ((Object) this instanceof FallingBlockEntity fallingBlockEntity) {
            if (fallingBlockEntity.getBlockState().getBlock() instanceof Fallable fallable) {
                damageSource = fallable.getFallDamageSource();
            }
        }

        if (ModConfiguration.ANVIL_BUG_FIX.get()) {
            if (damageSource == DamageSource.ANVIL) {
                return level.getEntities(entity, aabb, predicate.and((object) -> {
                    if (object instanceof ItemEntity) {
                        return false;
                    }

                    return true;
                }));
            }
        }

        return level.getEntities(entity, aabb, predicate);
    }
}
