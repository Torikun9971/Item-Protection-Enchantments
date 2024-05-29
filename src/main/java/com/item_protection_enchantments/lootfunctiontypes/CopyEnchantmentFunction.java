package com.item_protection_enchantments.lootfunctiontypes;

import com.google.common.collect.ImmutableSet;
import com.item_protection_enchantments.blockentities.EnchantableBlock;
import com.item_protection_enchantments.init.ModLootFunctionTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;

import java.util.List;
import java.util.Set;

public class CopyEnchantmentFunction extends ConditionalLootFunction {
    public static final Codec<CopyEnchantmentFunction> CODEC = RecordCodecBuilder.create((instance) -> {
        return addConditionsField(instance).apply(instance, CopyEnchantmentFunction::new);
    });

    CopyEnchantmentFunction(List<LootCondition> conditions) {
        super(conditions);
    }

    @Override
    public LootFunctionType getType() {
        return ModLootFunctionTypes.COPY_ENCHANTMENTS;
    }

    @Override
    public Set<LootContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootContextParameters.BLOCK_ENTITY);
    }

    @Override
    protected ItemStack process(ItemStack stack, LootContext context) {
        BlockEntity blockEntity = context.get(LootContextParameters.BLOCK_ENTITY);
        if (blockEntity instanceof EnchantableBlock enchantableBlock) {
            if (enchantableBlock.getEnchantmentNbt() != null) {
                stack.getOrCreateNbt().put("Enchantments", enchantableBlock.getEnchantmentNbt());
            }
        }

        return stack;
    }
}
