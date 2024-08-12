package com.lumijiez.lumiscope.handlers;

import com.lumijiez.lumiscope.blocks.BlockRegistrar;
import com.lumijiez.lumiscope.items.ItemRegistrar;
import com.lumijiez.lumiscope.tab.LumiscopeTab;
import net.neoforged.bus.api.IEventBus;


public class CoreRegistrar {
    public static void register(IEventBus modEventBus) {
        BlockRegistrar.BLOCKS.register(modEventBus);
        ItemRegistrar.ITEMS.register(modEventBus);
        LumiscopeTab.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
