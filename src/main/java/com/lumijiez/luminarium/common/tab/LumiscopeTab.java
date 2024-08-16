package com.lumijiez.luminarium.common.tab;

import com.lumijiez.luminarium.Luminarium;
import com.lumijiez.luminarium.common.items.ItemRegistrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LumiscopeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Luminarium.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LUMINARIUM_TAB = CREATIVE_MODE_TABS.register("luminarium_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.luminarium"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemRegistrar.EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> ItemRegistrar.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
            .build());
}
