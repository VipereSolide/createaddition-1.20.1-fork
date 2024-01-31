package com.rose.gods_retribution.content.block.engraved_blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.rose.gods_retribution.content.AllFonts;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.List;

public class EngravedLimestoneBlockRenderer implements BlockEntityRenderer<EngravedLimestoneBlockEntity>
{
    private static final int OUTLINE_RENDER_DISTANCE = Mth.square(16);
    private static final float RENDER_SCALE = 0.666F;
    private static final Vec3 TEXT_OFFSET = new Vec3(-0.5d, 0.5, 0.0075d);
    private final Font font;

    public EngravedLimestoneBlockRenderer(BlockEntityRendererProvider.Context ctx)
    {
        this.font = ctx.getFont();
    }

    public void render(@NotNull EngravedLimestoneBlockEntity blockEntity,
                       float pPartialTick,
                       @NotNull PoseStack pPoseStack,
                       @NotNull MultiBufferSource pBuffer,
                       int pPackedLight,
                       int pPackedOverlay)
    {
        this.renderSignWithText(blockEntity, pPoseStack, pBuffer, pPackedLight);
    }

    public float getSignTextRenderScale()
    {
        return RENDER_SCALE;
    }

    void renderSignWithText(EngravedLimestoneBlockEntity blockEntity,
                            PoseStack pPoseStack,
                            MultiBufferSource pBuffer,
                            int pPackedLight
    )
    {
        pPoseStack.pushPose();

        // pPoseStack.mulPose(Axis.YP.rotationDegrees());

        this.renderSignText(blockEntity,
                blockEntity.getBlockPos(),
                blockEntity.getFrontText(),
                pPoseStack,
                pBuffer,
                pPackedLight,
                blockEntity.getTextLineHeight(),
                blockEntity.getMaxTextLineWidth(),
                true);

        this.renderSignText(blockEntity,
                blockEntity.getBlockPos(),
                blockEntity.getBackText(),
                pPoseStack,
                pBuffer,
                pPackedLight,
                blockEntity.getTextLineHeight(),
                blockEntity.getMaxTextLineWidth(),
                false);

        pPoseStack.popPose();
    }

    void renderSignText(EngravedLimestoneBlockEntity blockEntity,
                        BlockPos pPos,
                        SignText pText,
                        PoseStack pPoseStack,
                        MultiBufferSource pBuffer,
                        int pPackedLight,
                        int pLineHeight,
                        int pMaxWidth,
                        boolean pIsFrontText)
    {

        pPoseStack.pushPose();

        var facingDirection = blockEntity.getBlockState().getValue(EngravedLimestoneBlock.FACING);
        // pPoseStack.mulPose(Axis.YP.rotationDegrees(180));

        var offset = getTextOffset();

        if (facingDirection == Direction.NORTH)
        {
        }
        else if (facingDirection == Direction.SOUTH)
        {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
            offset = offset.add(new Vec3(1, 0, 1));
        }
        else if (facingDirection == Direction.EAST)
        {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(270));
            offset = offset.add(new Vec3(0, 0, 1));
        }
        else if (facingDirection == Direction.WEST)
        {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
            offset = offset.add(new Vec3(1, 0, 0));
        }
        else if (facingDirection == Direction.UP)
        {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(90));
            offset = offset.add(new Vec3(0, 0, 1));
        }
        else if (facingDirection == Direction.DOWN)
        {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(270));
            offset = offset.add(new Vec3(0, -1, 0));
        }

        this.translateSignText(pPoseStack, pIsFrontText, offset);

        int i = getDarkColor(pText);
        i = blockEntity.getTextColour();
        int j = 4 * pLineHeight / 2;
        FormattedCharSequence[] aformattedcharsequence = pText.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), component ->
        {
            MutableComponent mutable = (MutableComponent) component;
            mutable.setStyle(mutable.getStyle()
                    .withFont(AllFonts.values()[blockEntity
                            .getBlockState()
                            .getValue(EngravedLimestoneBlock.FONT_TYPE)].get()));

            List<FormattedCharSequence> list = this.font.split(component, pMaxWidth);
            return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
        });
        int k;
        boolean flag;
        int l;
        if (pText.hasGlowingText())
        {
            k = pText.getColor().getTextColor();
            flag = isOutlineVisible(pPos, k);
            l = 15728880;
        }
        else
        {
            k = i;
            flag = false;
            l = pPackedLight;
        }

        for (int i1 = 0; i1 < 4; ++i1)
        {
            FormattedCharSequence formattedcharsequence = aformattedcharsequence[i1];
            float f = (float) (-this.font.width(formattedcharsequence) / 2);
            if (flag)
            {
                this.font.drawInBatch8xOutline(formattedcharsequence, f, (float) (i1 * pLineHeight - j), k, i, pPoseStack.last().pose(), pBuffer, l);
            }
            else
            {
                this.font.drawInBatch(formattedcharsequence, f, (float) (i1 * pLineHeight - j), k, false, pPoseStack.last().pose(), pBuffer, Font.DisplayMode.POLYGON_OFFSET, 0, 15728880);
            }
        }

        pPoseStack.popPose();
    }

    private void translateSignText(PoseStack pPoseStack, boolean pIsFrontText, Vec3 pOffset)
    {
        if (!pIsFrontText)
        {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        }

        float f = 0.015625F * this.getSignTextRenderScale();
        pPoseStack.translate(pOffset.x, pOffset.y, pOffset.z);
        pPoseStack.scale(f, -f, f);
    }

    Vec3 getTextOffset()
    {
        return TEXT_OFFSET;
    }

    static boolean isOutlineVisible(BlockPos pPos, int pTextColor)
    {
        if (pTextColor == DyeColor.BLACK.getTextColor())
        {
            return true;
        }
        else
        {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer localplayer = minecraft.player;
            if (localplayer != null && minecraft.options.getCameraType().isFirstPerson() && localplayer.isScoping())
            {
                return true;
            }
            else
            {
                Entity entity = minecraft.getCameraEntity();
                return entity != null && entity.distanceToSqr(Vec3.atCenterOf(pPos)) < (double) OUTLINE_RENDER_DISTANCE;
            }
        }
    }

    static int getDarkColor(SignText pSignText)
    {
        int i = pSignText.getColor().getTextColor();
        if (i == DyeColor.BLACK.getTextColor() && pSignText.hasGlowingText())
        {
            return -988212;
        }
        else
        {
            double d0 = 0.4D;
            int j = (int) ((double) FastColor.ARGB32.red(i) * d0);
            int k = (int) ((double) FastColor.ARGB32.green(i) * d0);
            int l = (int) ((double) FastColor.ARGB32.blue(i) * d0);
            return FastColor.ARGB32.color(0, j, k, l);
        }
    }
}