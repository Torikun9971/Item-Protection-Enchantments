package com.item_protection_enchantments.mixins;

import net.minecraft.entity.player.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "restoreFrom", at = @At("HEAD"))
    public void protection_enchantments$restoreFrom(ServerPlayerEntity player, boolean alive, CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) (Object) this;

            serverPlayer.inventory.replaceWith(player.inventory);
        }
    }
}
