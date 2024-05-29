package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.blockentities.EnchantableBlock;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ShulkerBoxBlockEntity.class)
public abstract class ShulkerBoxBlockEntityMixin implements EnchantableBlock {
    @Unique
    protected NbtList protection_enchantments$enchantmentNbt = new NbtList();

    @Inject(method = "readNbt", at = @At("TAIL"))
    public void protection_enchantments$readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Enchantments")) {
            this.protection_enchantments$enchantmentNbt = nbt.getList("Enchantments", NbtElement.COMPOUND_TYPE);
        }
    }

    @Inject(method = "writeNbt", at = @At("TAIL"))
    protected void protection_enchantments$writeNbt(NbtCompound nbt, CallbackInfo ci) {
        if (this.protection_enchantments$enchantmentNbt != null) nbt.put("Enchantments", protection_enchantments$enchantmentNbt);
    }

    @Override
    public void setEnchantments(@Nullable Map<Enchantment, Integer> enchantments) {
        protection_enchantments$enchantmentNbt.clear();

        if (enchantments != null) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchantment = entry.getKey();

                if (enchantment != null) {
                    int lvl = entry.getValue();
                    protection_enchantments$enchantmentNbt.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(enchantment), lvl));
                }
            }
        }
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        return EnchantmentHelper.fromNbt(protection_enchantments$enchantmentNbt);
    }

    @Override
    public NbtList getEnchantmentNbt() {
        return protection_enchantments$enchantmentNbt;
    }
}
