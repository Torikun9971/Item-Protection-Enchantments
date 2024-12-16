package com.torikun9971.itemprotectionenchantments.mixins;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(Enchantment.Definition.class)
public class EnchantmentDefinitionMixin {
    @Shadow
    @Final
    public static final MapCodec<Enchantment.Definition> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            RegistryCodecs.entryList(RegistryKeys.ITEM).optionalFieldOf("supported_items").forGetter(definition -> Optional.ofNullable(definition.supportedItems())),
            RegistryCodecs.entryList(RegistryKeys.ITEM).optionalFieldOf("primary_items").forGetter(Enchantment.Definition::primaryItems),
            Codecs.rangedInt(1, 1024).fieldOf("weight").forGetter(Enchantment.Definition::weight),
            Codecs.rangedInt(1, 255).fieldOf("max_level").forGetter(Enchantment.Definition::maxLevel),
            Enchantment.Cost.CODEC.fieldOf("min_cost").forGetter(Enchantment.Definition::minCost),
            Enchantment.Cost.CODEC.fieldOf("max_cost").forGetter(Enchantment.Definition::maxCost),
            Codecs.NONNEGATIVE_INT.fieldOf("anvil_cost").forGetter(Enchantment.Definition::anvilCost),
            AttributeModifierSlot.CODEC.listOf().fieldOf("slots").forGetter(Enchantment.Definition::slots)
    ).apply(instance, (supportedItemsOptional, primaryItems, weight, maxLevel, minCost, maxCost, anvilCost, slots) -> {
        RegistryEntryList<Item> supportedItems = supportedItemsOptional
                .orElse(RegistryEntryList.of(Registries.ITEM.streamEntries().toList()));

        return new Enchantment.Definition(supportedItems, primaryItems, weight, maxLevel, minCost, maxCost, anvilCost, slots);
    }));
}
