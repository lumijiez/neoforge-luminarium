package com.lumijiez.luminarium.handlers;

import com.lumijiez.luminarium.blocks.BlockRegistrar;
import com.lumijiez.luminarium.items.ItemRegistrar;
import com.lumijiez.luminarium.tab.LumiscopeTab;
import net.neoforged.bus.api.IEventBus;


public class CoreRegistrar {
    public static void register(IEventBus modEventBus) {
        BlockRegistrar.BLOCKS.register(modEventBus);
        ItemRegistrar.ITEMS.register(modEventBus);
        LumiscopeTab.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
