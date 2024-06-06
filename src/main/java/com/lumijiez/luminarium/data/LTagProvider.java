package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.Luminarium;
import com.lumijiez.luminarium.blocks.LBlocks;
import com.lumijiez.luminarium.common.util.LTags;
import com.lumijiez.luminarium.items.LItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

final class LTagProvider {
    static class Blocks extends IntrinsicHolderTagsProvider<Block> {
        Blocks(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
            super(output, Registries.BLOCK, lookupProvider, block -> block.builtInRegistryHolder().key(), Luminarium.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider lookup) {
            tag(LTags.Blocks.ORES_LUMINARIUM).add(LBlocks.RADIANT_ORE.get());

            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(LBlocks.RADIANT_ORE.get());
            tag(BlockTags.NEEDS_DIAMOND_TOOL).add(LBlocks.RADIANT_ORE.get());
        }
    }

    static class Items extends ItemTagsProvider {
        Items(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTagLookup, ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTagLookup, Luminarium.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookup) {
            copy(LTags.Blocks.ORES_LUMINARIUM, LTags.Items.ORES_LUMINARIUM);

            tag(Tags.Items.ORES).addTag(LTags.Items.ORES_LUMINARIUM);
            tag(Tags.Items.INGOTS).addTag(LTags.Items.INGOTS_LUMINARIUM);

            tag(LTags.Items.INGOTS_LUMINARIUM).add(LItems.LUMINARIUM_INGOT.get());
        }
    }
}
