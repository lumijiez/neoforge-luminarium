package com.lumijiez.luminarium;

import com.lumijiez.luminarium.blocks.LBlocks;
import com.lumijiez.luminarium.items.LItems;
import com.lumijiez.luminarium.tabs.LCreativeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Luminarium.MODID)
public class Luminarium
{
    public static final String MODID = "luminarium";

    public Luminarium(IEventBus modEventBus) {
        registerAll(modEventBus);
    }

    public void registerAll(IEventBus modEventBus) {
        LBlocks.register(modEventBus);
        LItems.register(modEventBus);
        LCreativeTab.register(modEventBus);
    }
}
