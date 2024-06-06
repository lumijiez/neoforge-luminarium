package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.items.LItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class LItemModelProvider extends ItemModelProvider {
    public LItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(LItems.LUMINARIUM_PIE.asItem());
        basicItem(LItems.LUMINARIUM_INGOT.asItem());
        basicItem(LItems.RAW_LUMINARIUM_ORE.asItem());

        //basicItem(LumItemController.COBBLE_WAND.asItem());
        //basicItem(LumItemController.BUILDER_WAND.asItem());
    }
}
