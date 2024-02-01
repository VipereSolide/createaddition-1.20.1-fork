package com.rose.gods_retribution.content.block;

import com.google.common.collect.ImmutableList;
import com.rose.gods_retribution.content.AllShapes;
import com.rose.gods_retribution.fundation.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnlitTorchBlock extends DirectionalBlock
{
    public static final List<Item> FLAMMABLE_ITEMS = new ArrayList<>();

    public UnlitTorchBlock(Properties pProperties)
    {
        super(pProperties);

        FLAMMABLE_ITEMS.add(Items.FLINT_AND_STEEL);
        FLAMMABLE_ITEMS.add(Items.FIRE_CHARGE);
        FLAMMABLE_ITEMS.add(Blocks.TORCH.asItem());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState()
                .setValue(FACING, context.getClickedFace().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        var dir = pState.getValue(FACING);

        if (dir == Direction.UP || dir == Direction.DOWN)
        {
            return AllShapes.UNLIT_TORCH_STANDING;
        }

        return AllShapes.UNLIT_TORCH_WALL.get(dir);
    }

    public boolean isFlamable(ItemStack stack)
    {
        var item = stack.getItem();
        return FLAMMABLE_ITEMS.contains(item);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        var itemInHand = pPlayer.getItemInHand(pHand);

        if (isFlamable(itemInHand))
        {
            if (Math.random() < 0.3F)
            {
                var dir = pState.getValue(FACING);

                if (dir == Direction.UP || dir == Direction.DOWN)
                {
                    pLevel.setBlock(pPos, Blocks.TORCH.defaultBlockState(), 3);
                }
                else
                {
                    pLevel.setBlock(pPos,
                            Blocks.WALL_TORCH
                                    .defaultBlockState()
                                    .setValue(WallTorchBlock.FACING, dir.getOpposite()),
                            3);
                }

                pLevel.playSound((Player) null, pPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS);
            }
            else
            {
                pLevel.playSound((Player) null, pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack,
                                @Nullable BlockGetter pLevel,
                                @NotNull List<Component> pTooltipComponents,
                                @NotNull TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        MutableComponent component = Lang.block("unlit_torch", "summary");
        component.setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY));

        pTooltipComponents.add(component);
    }
}