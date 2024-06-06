package com.lumijiez.luminarium.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class CobbleWand extends Item  {
    public CobbleWand() {
        super(new Item.Properties()
                .durability(100)
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant()
                .setNoRepair());
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player == null) return InteractionResult.PASS;

        int h = 0;
        while (h++ < 5) {
            BlockPos currentPos = pos.above(h);
            if (world.getBlockState(currentPos).isAir()) {
                world.setBlock(currentPos, Blocks.COBBLESTONE.defaultBlockState(), 3);
            } else break;
        }

        if (!player.isCreative()) {
            stack.setDamageValue(stack.getDamageValue() + 1);
            if (stack.getDamageValue() >= stack.getMaxDamage()) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
