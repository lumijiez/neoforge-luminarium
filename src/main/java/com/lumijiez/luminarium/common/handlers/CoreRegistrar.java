package com.lumijiez.luminarium.common.handlers;

import com.lumijiez.luminarium.common.blocks.BlockRegistrar;
import com.lumijiez.luminarium.common.items.ItemRegistrar;
import com.lumijiez.luminarium.common.tab.LumiscopeTab;
import net.neoforged.bus.api.IEventBus;


public class CoreRegistrar {
    public static void register(IEventBus modEventBus) {
        BlockRegistrar.BLOCKS.register(modEventBus);
        ItemRegistrar.ITEMS.register(modEventBus);
        LumiscopeTab.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
