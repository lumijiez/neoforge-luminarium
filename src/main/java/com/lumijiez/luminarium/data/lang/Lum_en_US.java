package com.lumijiez.luminarium.data.lang;

import com.lumijiez.luminarium.Luminarium;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class Lum_en_US extends LanguageProvider {
    public Lum_en_US(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(Luminarium.LUMINARIUM_PIE.asItem(), "Luminarium Pie");
        add(Luminarium.RADIANT_ORE.asItem(), "Radiant Ore");
    }
}
