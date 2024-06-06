package com.lumijiez.luminarium.items.logic;

import com.lumijiez.luminarium.items.custom.OreDetector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.model.data.ModelData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OreDetectorRender {

    private static List<Block> oreWhitelist = List.of(
            Blocks.GOLD_ORE,
            Blocks.IRON_ORE,
            Blocks.REDSTONE_ORE,
            Blocks.COAL_ORE,
            Blocks.DIAMOND_ORE,
            Blocks.ANCIENT_DEBRIS,
            Blocks.LAPIS_ORE
    );

    private static long startRenderTime = 0;
    private static final long RENDER_DURATION = 10 * 1000;
    private static final int RENDER_RADIUS = 25;
    private static final Minecraft mc = Minecraft.getInstance();
    private static final Map<BlockPos, BlockState> renderableBlocks = new HashMap<>();
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void startRender() {
        startRenderTime = System.currentTimeMillis();
        refreshBlocks();
        executor.schedule(OreDetectorRender::stopRender, RENDER_DURATION, TimeUnit.MILLISECONDS);
    }

    private static void stopRender() {
        renderableBlocks.clear();
        startRenderTime = 0;
    }

    @SubscribeEvent
    public static void onRenderWorldLast(RenderLevelStageEvent event) {
        if (startRenderTime == 0 || System.currentTimeMillis() - startRenderTime >= RENDER_DURATION)
            return;

        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL)
            return;

        if (mc.level == null) return;

        if (mc.hitResult instanceof BlockHitResult blockHitResult) {
            renderOresAroundPlayer(blockHitResult.getBlockPos(), event.getPoseStack());
        }
    }

    private static void renderOresAroundPlayer(BlockPos playerPos, PoseStack poseStack) {
        Minecraft mc = Minecraft.getInstance();
        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();

        if (renderableBlocks.isEmpty()) {
            refreshBlocks();
        }

        poseStack.pushPose();

        for (Map.Entry<BlockPos, BlockState> entry : renderableBlocks.entrySet()) {
            BlockPos blockPos = entry.getKey();
            BlockState blockState = entry.getValue();

            double renderX = blockPos.getX() - mc.gameRenderer.getMainCamera().getPosition().x();
            double renderY = blockPos.getY() - mc.gameRenderer.getMainCamera().getPosition().y();
            double renderZ = blockPos.getZ() - mc.gameRenderer.getMainCamera().getPosition().z();

            poseStack.translate(renderX, renderY, renderZ);

            renderBlockAtPos(blockState, poseStack, bufferSource);

            poseStack.translate(-renderX, -renderY, -renderZ);
        }

        poseStack.popPose();

        bufferSource.endBatch(RenderType.cutout());
    }

    private static void renderBlockAtPos(BlockState state, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource) {
        mc.getBlockRenderer().renderSingleBlock(
                state, poseStack, bufferSource, 15728880, OverlayTexture.RED_OVERLAY_V, ModelData.EMPTY, RenderType.solid()
        );
    }

    private static void refreshBlocks() {
        Minecraft mc = Minecraft.getInstance();
        BlockPos playerPos = mc.player != null ? mc.player.blockPosition() : BlockPos.ZERO;

        int minX = playerPos.getX() - RENDER_RADIUS;
        int maxX = playerPos.getX() + RENDER_RADIUS;
        int minZ = playerPos.getZ() - RENDER_RADIUS;
        int maxZ = playerPos.getZ() + RENDER_RADIUS;

        for (int x = minX; x <= maxX; x++) {
            for (int y = mc.level.getMinBuildHeight(); y <= playerPos.getY(); y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockState blockState = mc.level.getBlockState(blockPos);

                    if (oreWhitelist.contains(blockState.getBlock()))
                        renderableBlocks.put(blockPos, blockState);
                }
            }
        }
    }
}