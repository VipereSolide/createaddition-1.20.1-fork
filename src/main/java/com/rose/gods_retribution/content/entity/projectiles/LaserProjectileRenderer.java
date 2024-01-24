package com.rose.gods_retribution.content.entity.projectiles;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.foundation.utility.AngleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class LaserProjectileRenderer extends EntityRenderer<LaserProjectileEntity>
{
    public LaserProjectileRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(LaserProjectileEntity pEntity)
    {
        return null;
    }

    @Override
    public void render(LaserProjectileEntity entity, float yaw, float pt, PoseStack ms, MultiBufferSource buffer, int light)
    {
        ms.pushPose();

        float scaleRadius = 0.15F;
        ms.translate(-scaleRadius, -scaleRadius, -scaleRadius);
        ms.scale(scaleRadius, scaleRadius, scaleRadius);

        BlockState s = Blocks.MAGMA_BLOCK.defaultBlockState();

        Minecraft.getInstance()
                .getBlockRenderer()
                .renderSingleBlock(s, ms, buffer, light, 0);

        ms.popPose();
    }
}
