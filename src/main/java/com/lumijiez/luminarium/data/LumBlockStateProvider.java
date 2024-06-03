package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.Luminarium;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class LumBlockStateProvider extends BlockStateProvider {
    public LumBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile model = models().cubeAll("radiant_ore", modLoc("block/radiant_ore"));
        simpleBlockWithItem(Luminarium.RADIANT_ORE.get(), model);
    }
}
