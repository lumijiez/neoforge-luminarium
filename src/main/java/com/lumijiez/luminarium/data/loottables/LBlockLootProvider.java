package com.lumijiez.luminarium.data.loottables;

import com.lumijiez.luminarium.blocks.LBlocks;
import com.lumijiez.luminarium.items.LItems;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class LBlockLootProvider extends BlockLootSubProvider {

    public LBlockLootProvider() {
        super(
                Set.of(),
                FeatureFlags.REGISTRY.allFlags()
        );
    }

    @Override
    protected void generate() {
        add(LBlocks.RADIANT_ORE.get(), (p) -> createOreDrop(p, LItems.RAW_LUMINARIUM_ORE.asItem()));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return LBlocks.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .collect(Collectors.toList());
    }
}
