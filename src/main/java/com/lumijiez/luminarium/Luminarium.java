package com.lumijiez.luminarium;

import com.lumijiez.luminarium.events.RadarEventHandler;
import com.lumijiez.luminarium.handlers.CoreRegistrar;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Luminarium.MODID)
public class Luminarium {
    public static final String MODID = "luminarium";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Luminarium(IEventBus modEventBus, ModContainer modContainer) {
        CoreRegistrar.register(modEventBus);
        NeoForge.EVENT_BUS.register(new RadarEventHandler());
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
