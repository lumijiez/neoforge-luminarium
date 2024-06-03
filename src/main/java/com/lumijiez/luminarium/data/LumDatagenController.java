package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.Luminarium;
import com.lumijiez.luminarium.data.lang.Lum_en_US;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Luminarium.MODID)
public class LumDatagenController {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(
                event.includeServer(),
                new LumRecipeProvider(output, lookupProvider)
        );

        generator.addProvider(
                event.includeClient(),
                new LumBlockStateProvider(output, Luminarium.MODID, existingFileHelper)
        );

        generator.addProvider(
                event.includeClient(),
                new LumItemModelProvider(output, Luminarium.MODID, existingFileHelper)
        );

        generator.addProvider(
                event.includeClient(),
                new Lum_en_US(output, Luminarium.MODID, "en_US")
        );
    }
}
