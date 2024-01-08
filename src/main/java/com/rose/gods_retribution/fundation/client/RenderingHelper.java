package com.rose.gods_retribution.fundation.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RenderingHelper
{
    public static void renderItem(PoseStack ms, MultiBufferSource buffer, int light, int overlay, ItemStack stack)
    {
        Minecraft mc = Minecraft.getInstance();
        mc.getItemRenderer()
                .renderStatic(stack, ItemDisplayContext.GROUND, light, overlay, ms, buffer, mc.level, 0);
    }
}
