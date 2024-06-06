package com.lumijiez.luminarium.common.util;

import com.lumijiez.luminarium.Luminarium;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LTags {
    private static final String FORGE = "forge";

    public static final class Items {
        public static final TagKey<Item> ORES_LUMINARIUM = forgeTag("ores/luminarium");
        public static final TagKey<Item> INGOTS_LUMINARIUM = forgeTag("ingots/luminarium");

        private static TagKey<Item> forgeTag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(FORGE, name));
        }

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(Luminarium.MODID, name));
        }
    }


    public static final class Blocks {
        public static final TagKey<Block> ORES_LUMINARIUM = forgeTag("ores/luminarium");

        private static TagKey<Block> forgeTag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(FORGE, name));
        }

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(Luminarium.MODID, name));
        }
    }
}
