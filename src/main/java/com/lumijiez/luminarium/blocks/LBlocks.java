package com.lumijiez.luminarium.blocks;

import com.lumijiez.luminarium.Luminarium;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Luminarium.MODID);
    public static final DeferredBlock<Block> RADIANT_ORE =
            BLOCKS.registerSimpleBlock("radiant_ore",
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.5f, 30f)
                            .requiresCorrectToolForDrops()
                            .lightLevel((state) -> 15)
            );

    //public static final DeferredBlock<Block> WAND_WIREFRAME = BLOCKS.registerSimpleBlock("wand_wireframe", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
