package com.lumijiez.lumiscope.items.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class Radar extends Item {
    public Radar(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
        return super.useOn(pContext);


    }
}
