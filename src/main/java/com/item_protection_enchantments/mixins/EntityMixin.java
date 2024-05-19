package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.commands.CommandSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin extends CapabilityProvider<Entity> implements Nameable, EntityAccess, CommandSource, IForgeEntity {
    public EntityMixin(EntityType<?> entityType, Level level) {
        super(Entity.class);
    }

    @Inject(method = "lavaHurt", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$lavaHurt(CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity itemEntity) {
            if (ItemProtectionEnchantments.hasEnchantment(itemEntity.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
                ci.cancel();
            }
        }
    }
}
