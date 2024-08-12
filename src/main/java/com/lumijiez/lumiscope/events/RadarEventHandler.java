package com.lumijiez.lumiscope.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

public class RadarEventHandler {

    Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public void holdDiamond(RenderLevelStageEvent event) {
        if (mc.level == null) return;

        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL)
            return;

        if (mc.player == null) return;

        if (mc.player.getItemInHand(InteractionHand.MAIN_HAND).getItem().equals(Items.DIAMOND)) {
            renderCircle(event.getPoseStack(), mc.renderBuffers().bufferSource());
        }
    }

    protected void renderCircle(PoseStack poseStack, MultiBufferSource bufferSource) {
        Minecraft mc = Minecraft.getInstance();

        double offsetX = 0.0;
        double offsetY = -2.0;
        double offsetZ = 0.0;

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lineStrip());

        poseStack.pushPose();

        poseStack.translate(-offsetX, -offsetY, -offsetZ);

        float radius = 2.5f;
        int segments = 50;
        float normalX = 0.0f;
        float normalY = 0.0f;
        float normalZ = 1.0f;

        for (int i = 0; i <= segments; i++) {
            double theta = 2.0 * Math.PI * i / segments;
            float x = (float) (radius * Math.cos(theta));
            float y = (float) (radius * Math.sin(theta));

            vertexConsumer.addVertex(poseStack.last().pose(), x, 0, y)
                    .setColor(255, 255, 255, 255)
                    .setNormal(normalX, normalY, normalZ);
        }

        poseStack.popPose();
    }

}
