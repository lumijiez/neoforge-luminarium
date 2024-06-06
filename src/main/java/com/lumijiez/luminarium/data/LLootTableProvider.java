package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.data.loottables.LBlockLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LLootTableProvider extends LootTableProvider {
    public LLootTableProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, getRequiredTables(), getSubProviders(), pRegistries);
    }

    @Override
    protected void validate(
            @NotNull WritableRegistry<LootTable> writableregistry,
            @NotNull ValidationContext validationcontext,
            ProblemReporter.@NotNull Collector problemreporter$collector) {}

    private static Set<ResourceKey<LootTable>> getRequiredTables() {
        return Set.of();
    }
    private static List<SubProviderEntry> getSubProviders() {
        List<SubProviderEntry> subProviders = new ArrayList<>();
        subProviders.add(
                new SubProviderEntry(LBlockLootProvider::new, LootContextParamSets.BLOCK)
        );
        return subProviders;
    }
}
