package com.rose.gods_retribution.content.block.labelling_machine;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rose.gods_retribution.fundation.client.RenderingHelper;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class LabellingMachineRenderer extends KineticBlockEntityRenderer
{
    public LabellingMachineRenderer(Context dispatcher)
    {
        super(dispatcher);
    }

    @Override
    protected BlockState getRenderedBlockState(KineticBlockEntity te)
    {
        return shaft(getRotationAxisOf(te));
    }

    @Override
    protected void renderSafe(KineticBlockEntity blockEntity, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay)
    {
        super.renderSafe(blockEntity, partialTicks, ms, buffer, light, overlay);

        if (Backend.canUseInstancing(blockEntity.getLevel()))
            return;

        BlockState blockState = blockEntity.getBlockState();
        BlockPos pos = blockEntity.getBlockPos();

        VertexConsumer vb = buffer.getBuffer(RenderType.solid());

        int packedLightmapCoordinates = LevelRenderer.getLightColor(blockEntity.getLevel(), pos);
        // SuperByteBuffer shaft = AllBlockPartials.SHAFT_HALF.renderOn(blockState);
        SuperByteBuffer shaft = CachedBufferer.partial(AllPartialModels.SHAFT_HALF, blockState);
        Axis axis = getRotationAxisOf(blockEntity);

        shaft
                .rotateCentered(Direction.UP, axis == Axis.Z ? 0 : 90 * (float) Math.PI / 180f)
                .translate(0, 4f / 16f, 0)
                .rotateCentered(Direction.NORTH, getAngleForTe(blockEntity, pos, axis))
                .light(packedLightmapCoordinates)
                .renderInto(ms, vb);
    }
}
