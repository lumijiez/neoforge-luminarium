package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.Luminarium;
import com.lumijiez.luminarium.data.lang.L_en_US;
import com.mojang.datafixers.util.Function4;
import com.mojang.datafixers.util.Function5;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.commons.lang3.function.TriFunction;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Luminarium.MODID)
public class LDatagenController {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator.PackGenerator common = generator.getVanillaPack(includeClient || includeServer);
        DataGenerator.PackGenerator server = generator.getVanillaPack(includeServer);
        DataGenerator.PackGenerator client = generator.getVanillaPack(includeClient);

        generator.addProvider(
                event.includeServer(),
                new LRecipeProvider(
                        output,
                        lookupProvider
                )
        );

        generator.addProvider(
                event.includeClient(),
                new LBlockStateProvider(
                        output,
                        Luminarium.MODID,
                        existingFileHelper
                )
        );

        generator.addProvider(
                event.includeClient(),
                new LItemModelProvider(
                        output,
                        Luminarium.MODID,
                        existingFileHelper
                )
        );

        generator.addProvider(
                event.includeServer(),
                new LLootTableProvider(
                        output,
                        lookupProvider
                )
        );

        generator.addProvider(
                event.includeClient(),
                new L_en_US(
                        output,
                        Luminarium.MODID,
                        "en_US")
        );

        generator.addProvider(
                event.includeServer(),
                new LTagProvider.Blocks(
                        output,
                        lookupProvider,
                        existingFileHelper
                )
        );

        LTagProvider.Blocks blocks = server.addProvider(wrapWith(LTagProvider.Blocks::new, lookupProvider, existingFileHelper));

        generator.addProvider(
                event.includeServer(),
                new LTagProvider.Items(
                        output,
                        lookupProvider,
                        blocks.contentsGetter(),
                        existingFileHelper
                )
        );
    }

    private static <T extends DataProvider, S> DataProvider.Factory<T> wrapWith(BiFunction<PackOutput, S, T> factory, S s) {
        return (output) -> factory.apply(output, s);
    }

    private static <T extends DataProvider, S, U> DataProvider.Factory<T> wrapWith(TriFunction<PackOutput, S, U, T> factory, S s, U u) {
        return (output) -> factory.apply(output, s, u);
    }

    private static <T extends DataProvider, S, U, V> DataProvider.Factory<T> wrapWith(Function4<PackOutput, S, U, V, T> factory, S s, U u, V v) {
        return (output) -> factory.apply(output, s, u, v);
    }

    private static <T extends DataProvider, S, U, V, W> DataProvider.Factory<T> wrapWith(Function5<PackOutput, S, U, V, W, T> factory, S s, U u, V v, W w) {
        return (output) -> factory.apply(output, s, u, v, w);
    }
}
