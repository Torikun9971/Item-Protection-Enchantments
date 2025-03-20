package com.torikun9971.itemprotectionenchantments.compat.alexscaves;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.torikun9971.itemprotectionenchantments.ItemProtectionEnchantments;
import com.torikun9971.itemprotectionenchantments.compat.Loadable;
import com.torikun9971.itemprotectionenchantments.compat.Plugin;
import com.torikun9971.itemprotectionenchantments.enchantments.AcidProtectionEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Plugin(requireMods = { AlexsCaves.MODID })
public class AlexsCavesPlugin implements Loadable {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ItemProtectionEnchantments.MOD_ID);

    public static final RegistryObject<Enchantment> ACID_PROTECTION_ITEM = ENCHANTMENTS.register("acid_protection_item", AcidProtectionEnchantment::new);

    @Override
    public void load(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
