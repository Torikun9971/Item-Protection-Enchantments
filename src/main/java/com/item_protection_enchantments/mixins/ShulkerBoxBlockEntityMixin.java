package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.blockentities.EnchantableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(ShulkerBoxBlockEntity.class)
public abstract class ShulkerBoxBlockEntityMixin extends RandomizableContainerBlockEntity implements WorldlyContainer, EnchantableBlock {
    @Unique
    protected ListTag protection_enchantments$enchantmentTag = new ListTag();

    public ShulkerBoxBlockEntityMixin(@Nullable DyeColor color, BlockPos pos, BlockState state) {
        super(BlockEntityType.SHULKER_BOX, pos, state);
    }

    public ShulkerBoxBlockEntityMixin(BlockPos pos, BlockState state) {
        super(BlockEntityType.SHULKER_BOX, pos, state);
    }

    @Inject(method = "saveAdditional", at = @At("TAIL"))
    protected void protection_enchantments$saveAdditional(CompoundTag tag, CallbackInfo ci) {
        if (this.protection_enchantments$enchantmentTag != null) tag.put("Enchantments", protection_enchantments$enchantmentTag);
    }

    @Inject(method = "loadFromTag", at = @At("TAIL"))
    public void protection_enchantments$loadFromTag(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("Enchantments")) {
            this.protection_enchantments$enchantmentTag = tag.getList("Enchantments", ListTag.TAG_COMPOUND);
        }
    }

    @Override
    public void setEnchantments(@Nullable Map<Enchantment, Integer> enchantments) {
        protection_enchantments$enchantmentTag.clear();

        if (enchantments != null) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchantment = entry.getKey();

                if (enchantment != null) {
                    int lvl = entry.getValue();
                    protection_enchantments$enchantmentTag.add(EnchantmentHelper.storeEnchantment(EnchantmentHelper.getEnchantmentId(enchantment), lvl));
                }
            }
        }
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        return EnchantmentHelper.deserializeEnchantments(protection_enchantments$enchantmentTag);
    }

    @Override
    public ListTag getEnchantmentTag() {
        return protection_enchantments$enchantmentTag;
    }
}
