package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.blocks.LBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class LBlockStateProvider extends BlockStateProvider {
    public LBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile modelRadiantOre = models().cubeAll("radiant_ore", modLoc("block/radiant_ore"));
        simpleBlockWithItem(LBlocks.RADIANT_ORE.get(), modelRadiantOre);




        // InDev
//        ModelFile modelWandWireframe =
//                models()
//                        .cubeAll("wand_wireframe", modLoc("block/wand_wireframe"))
//                        .renderType("translucent");
//        simpleBlockWithItem(LumBlockController.WAND_WIREFRAME.get(), modelWandWireframe);
    }
}
