package com.lumijiez.luminarium.data.lang;

import com.lumijiez.luminarium.blocks.LBlocks;
import com.lumijiez.luminarium.common.util.LTranslate;
import com.lumijiez.luminarium.items.LItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class L_en_US extends LanguageProvider {
    public L_en_US(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(LItems.LUMINARIUM_PIE.asItem(), "Luminarium Pie");
        add(LItems.RAW_LUMINARIUM_ORE.asItem(), "Raw Luminarium Ore");
        add(LItems.LUMINARIUM_INGOT.asItem(), "Luminarium Ingot");

        add(LBlocks.RADIANT_ORE.asItem(), "Radiant Ore");

        add(LTranslate.LUMINARIUM_TAB, "Luminarium");

        //add(LumItemController.COBBLE_WAND.asItem(), "Cobblestone Wand");
        //add(LumItemController.BUILDER_WAND.asItem(), "Luminarium Builder's Wand");
    }
}
