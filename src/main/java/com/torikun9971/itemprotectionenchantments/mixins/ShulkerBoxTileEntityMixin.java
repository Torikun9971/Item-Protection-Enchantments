package com.torikun9971.itemprotectionenchantments.mixins;

import com.torikun9971.itemprotectionenchantments.tileentities.EnchantableBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(ShulkerBoxTileEntity.class)
public abstract class ShulkerBoxTileEntityMixin implements EnchantableBlock {
    @Unique
    protected ListNBT protection_enchantments$enchantmentTag = new ListNBT();

    @Inject(method = "saveToTag", at = @At("TAIL"))
    public void protection_enchantments$saveAdditional(CompoundNBT nbt, CallbackInfoReturnable<CompoundNBT> cir) {
        if (this.protection_enchantments$enchantmentTag != null)
            nbt.put("Enchantments", protection_enchantments$enchantmentTag);
    }

    @Inject(method = "loadFromTag", at = @At("TAIL"))
    public void protection_enchantments$loadFromTag(CompoundNBT nbt, CallbackInfo ci) {
        if (nbt.contains("Enchantments")) {
            this.protection_enchantments$enchantmentTag = nbt.getList("Enchantments", 10);
        }
    }

    @Override
    public void protection_enchantments$setEnchantments(@Nullable Map<Enchantment, Integer> enchantments) {
        protection_enchantments$enchantmentTag.clear();

        if (enchantments == null)
            return;

        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            Enchantment enchantment = entry.getKey();

            if (enchantment != null) {
                CompoundNBT compoundnbt = new CompoundNBT();
                int lvl = entry.getValue();

                compoundnbt.putShort("lvl", (short) lvl);
                compoundnbt.putString(
                        "id",
                        String.valueOf(ForgeRegistries.ENCHANTMENTS.getKey(enchantment))
                );

                protection_enchantments$enchantmentTag.add(compoundnbt);
            }
        }
    }

    @Override
    public Map<Enchantment, Integer> protection_enchantments$getEnchantments() {
        return EnchantmentHelper.deserializeEnchantments(protection_enchantments$enchantmentTag);
    }

    @Override
    public ListNBT protection_enchantments$getEnchantmentTag() {
        return protection_enchantments$enchantmentTag;
    }
}
