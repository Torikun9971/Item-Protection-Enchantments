package com.item_protection_enchantments.lootfunctiontypes;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.item_protection_enchantments.blockentities.EnchantableBlock;
import com.item_protection_enchantments.init.ModLootFunctionTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Set;

public class CopyEnchantmentFunction extends LootItemConditionalFunction {
    CopyEnchantmentFunction(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public LootItemFunctionType getType() {
        return ModLootFunctionTypes.COPY_ENCHANTMENTS.get();
    }

    @Override
    public Set<LootContextParam<?>> getReferencedContextParams() {
        return ImmutableSet.of(LootContextParams.BLOCK_ENTITY);
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        BlockEntity blockEntity = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof EnchantableBlock enchantableBlock) {
            if (enchantableBlock.getEnchantmentTag() != null) {
                stack.getOrCreateTag().put("Enchantments", enchantableBlock.getEnchantmentTag());
            }
        }

        return stack;
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<CopyEnchantmentFunction> {
        @Override
        public CopyEnchantmentFunction deserialize(JsonObject jsonObject, JsonDeserializationContext context, LootItemCondition[] conditions) {
            return new CopyEnchantmentFunction(conditions);
        }
    }
}
