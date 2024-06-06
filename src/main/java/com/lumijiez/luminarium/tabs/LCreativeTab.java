package com.lumijiez.luminarium.tabs;

import com.lumijiez.luminarium.Luminarium;
import com.lumijiez.luminarium.common.util.LTranslate;
import com.lumijiez.luminarium.items.LItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Luminarium.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LUMINARIUM_TAB = CREATIVE_MODE_TABS.register("luminarium_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(LTranslate.LUMINARIUM_TAB))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(LItems::icon)
            .withBackgroundLocation(new ResourceLocation(Luminarium.MODID, "textures/luminarium_tab.png"))
            .withSearchBar()
            .displayItems((parameters, output) -> LItems.addItems(output)).build());

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
