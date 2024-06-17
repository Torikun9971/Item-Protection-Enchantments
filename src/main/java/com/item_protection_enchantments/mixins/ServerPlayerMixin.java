package com.item_protection_enchantments.mixins;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "restoreFrom", at = @At("HEAD"))
    public void protection_enchantments$restoreFrom(ServerPlayer player, boolean alive, CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayer serverPlayer) {
            serverPlayer.getInventory().replaceWith(player.getInventory());
        }
    }
}
