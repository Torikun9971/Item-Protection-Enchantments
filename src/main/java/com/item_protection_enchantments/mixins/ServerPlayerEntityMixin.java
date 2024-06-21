package com.item_protection_enchantments.mixins;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "copyFrom", at = @At("HEAD"))
    public void protection_enchantments$copyFrom(ServerPlayerEntity player, boolean alive, CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.getInventory().clone(player.getInventory());
        }
    }
}
