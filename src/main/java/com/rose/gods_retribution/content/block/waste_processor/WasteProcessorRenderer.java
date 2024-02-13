package com.rose.gods_retribution.content.block.waste_processor;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class WasteProcessorRenderer extends KineticBlockEntityRenderer
{
	public WasteProcessorRenderer(BlockEntityRendererProvider.Context context)
	{
		super(context);
	}

	@Override
	protected BlockState getRenderedBlockState(KineticBlockEntity be)
	{
		return shaft(getRotationAxisOf(be));
	}

	@Override
	protected void renderSafe(KineticBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay)
	{
		super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

		if (Backend.canUseInstancing(be.getLevel()))
			return;

		BlockState blockstate = be.getBlockState();
		BlockPos pos = be.getBlockPos();

		VertexConsumer vb = buffer.getBuffer(RenderType.solid());

		int packedLightmapCoordinates = LevelRenderer.getLightColor(be.getLevel(), pos);
		SuperByteBuffer shaft = CachedBufferer.partial(AllPartialModels.SHAFT_HALF, blockstate);
		Direction.Axis axis = getRotationAxisOf(be);

		shaft
				.rotateCentered(Direction.UP, axis == Direction.Axis.Z ? 0 : 90 * (float) Math.PI / 180f)
				.translate(0, 4f / 16f, 0)
				.rotateCentered(Direction.NORTH, getAngleForTe(be, pos, axis))
				.light(packedLightmapCoordinates)
				.renderInto(ms, vb);
	}
}
