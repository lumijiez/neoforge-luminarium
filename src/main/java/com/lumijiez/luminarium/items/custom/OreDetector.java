package com.lumijiez.luminarium.items.custom;

import com.lumijiez.luminarium.items.logic.OreDetectorRender;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class OreDetector extends Item {
    public OreDetector() {
        super(new Properties()
                .durability(100)
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant()
                .setNoRepair());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (pUsedHand == InteractionHand.MAIN_HAND) {
            OreDetectorRender.startRender();
            pPlayer.getCooldowns().addCooldown(this, 500);
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }
}
