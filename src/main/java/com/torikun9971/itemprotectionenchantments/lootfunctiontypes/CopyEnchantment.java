package com.torikun9971.itemprotectionenchantments.lootfunctiontypes;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.torikun9971.itemprotectionenchantments.tileentities.EnchantableBlock;
import com.torikun9971.itemprotectionenchantments.init.ModLootFunctionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.tileentity.TileEntity;

import java.util.Set;

public class CopyEnchantment extends LootFunction {
    CopyEnchantment(ILootCondition[] conditions) {
        super(conditions);
    }

    @Override
    public LootFunctionType getType() {
        return ModLootFunctionTypes.COPY_ENCHANTMENTS;
    }

    @Override
    public Set<LootParameter<?>> getReferencedContextParams() {
        return ImmutableSet.of(LootParameters.BLOCK_ENTITY);
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        TileEntity tileEntity = context.getParamOrNull(LootParameters.BLOCK_ENTITY);
        if (!(tileEntity instanceof EnchantableBlock))
            return stack;

        EnchantableBlock enchantableBlock = (EnchantableBlock) tileEntity;
        if (enchantableBlock.protection_enchantments$getEnchantmentTag() != null) {
            stack.getOrCreateTag().put(
                    "Enchantments", enchantableBlock.protection_enchantments$getEnchantmentTag()
            );
        }

        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<CopyEnchantment> {
        @Override
        public CopyEnchantment deserialize(JsonObject jsonObject, JsonDeserializationContext context, ILootCondition[] conditions) {
            return new CopyEnchantment(conditions);
        }
    }
}
