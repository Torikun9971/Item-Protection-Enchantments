package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.config.ModConfiguration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.function.Predicate;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {
    @Unique
    @Final
    private static final Predicate<Entity> DAMAGEABLE_ENTITY_PREDICATE = protection_enchantments$createDamageableEntityPredicate();

    @Redirect(method = "causeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;", ordinal = 0))
    private List<Entity> protection_enchantments$causeFallDamage(World world, Entity entity, AxisAlignedBB aabb) {
        if (ModConfiguration.getConfig().fixMC120158)
            return world.getEntities(entity, aabb, DAMAGEABLE_ENTITY_PREDICATE);
        else
            return world.getEntities(entity, aabb);
    }

    @Unique
    private static Predicate<Entity> protection_enchantments$createDamageableEntityPredicate() {
        return entity -> {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;

                if (player.isSpectator() || player.isCreative())
                    return false;
            }

            return entity instanceof LivingEntity && entity.isAlive();
        };
    }
}
