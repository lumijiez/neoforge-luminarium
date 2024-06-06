package com.lumijiez.luminarium.items.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BuilderWand extends Item  {
    public BuilderWand() {
        super(new Properties()
                .durability(100)
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .fireResistant()
                .setNoRepair());

        NeoForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES)
            return;

        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null) return;

        if (mc.hitResult instanceof BlockHitResult blockHitResult) {
            if (mc.level.getBlockState(blockHitResult.getBlockPos()).equals(Blocks.GRASS_BLOCK.defaultBlockState())) {
                ItemStack mainHandItem = mc.player != null ? mc.player.getMainHandItem() : ItemStack.EMPTY;
                if (!mainHandItem.isEmpty() && mainHandItem.getItem() instanceof BuilderWand) {
                    renderGrassBlocks(blockHitResult.getBlockPos(), event.getPoseStack(), blockHitResult.getDirection());
                }
            }
        }
    }

    private void renderGrassBlocks(BlockPos pos, PoseStack poseStack, Direction hitFace) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();

        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> visited = new HashSet<>();
        queue.add(pos);
        visited.add(pos);

        int count = 0;
        int range = 20;

        while (!queue.isEmpty() && count < 20) {
            BlockPos currentPos = queue.poll();
            BlockPos targetPos;

            switch (hitFace) {
                case UP -> targetPos = currentPos.above();
                case DOWN -> targetPos = currentPos.below();
                case NORTH -> targetPos = currentPos.north();
                case SOUTH -> targetPos = currentPos.south();
                case WEST -> targetPos = currentPos.west();
                case EAST -> targetPos = currentPos.east();
                default -> targetPos = currentPos;
            }

            if (mc.level.isEmptyBlock(targetPos) && mc.level.getBlockState(targetPos.below()).getBlock() == Blocks.GRASS_BLOCK) {
                count++;
                poseStack.pushPose();

                double renderX = targetPos.getX();
                double renderY = targetPos.getY();
                double renderZ = targetPos.getZ();

                poseStack.translate(
                        renderX - mc.gameRenderer.getMainCamera().getPosition().x(),
                        renderY - mc.gameRenderer.getMainCamera().getPosition().y(),
                        renderZ - mc.gameRenderer.getMainCamera().getPosition().z()
                );

                poseStack.scale(1, 1, 1);

                try {
                    int combinedLightIn = 10;
                    mc.getBlockRenderer().renderSingleBlock(
                            Blocks.GRASS_BLOCK.defaultBlockState(),
                            //LumBlockController.WAND_WIREFRAME.get().defaultBlockState(),
                            poseStack,
                            bufferSource,
                            combinedLightIn,
                            OverlayTexture.RED_OVERLAY_V,
                            ModelData.EMPTY,
                            RenderType.translucent()
                    );
                } finally {
                    poseStack.popPose();
                    bufferSource.endBatch();
                }
            }

            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = currentPos.relative(dir);
                if (!visited.contains(neighborPos) && neighborPos.distSqr(pos) <= range * range) {
                    queue.add(neighborPos);
                    visited.add(neighborPos);
                }
            }
        }
    }

}
