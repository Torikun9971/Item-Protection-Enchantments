package com.torikun9971.itemprotectionenchantments.lootfunctiontypes;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.torikun9971.itemprotectionenchantments.blockentities.EnchantableBlock;
import com.torikun9971.itemprotectionenchantments.init.ModLootFunctionTypes;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;

import java.util.Set;

public class CopyEnchantmentFunction extends ConditionalLootFunction {
    CopyEnchantmentFunction(LootCondition[] conditions) {
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
        if (!(blockEntity instanceof EnchantableBlock enchantableBlock))
            return stack;

        if (enchantableBlock.protection_enchantments$getEnchantmentNbt() != null) {
            stack.getOrCreateNbt().put(
                    "Enchantments", enchantableBlock.protection_enchantments$getEnchantmentNbt()
            );
        }

        return stack;
    }

    public static class Serializer extends ConditionalLootFunction.Serializer<CopyEnchantmentFunction> {
        @Override
        public CopyEnchantmentFunction fromJson(JsonObject jsonObject, JsonDeserializationContext context, LootCondition[] conditions) {
            return new CopyEnchantmentFunction(conditions);
        }
    }
}
