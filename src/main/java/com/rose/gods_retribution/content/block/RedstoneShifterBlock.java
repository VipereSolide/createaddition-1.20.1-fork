package com.rose.gods_retribution.content.block;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedstoneShifterBlock extends DirectionalBlock
{
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, POWER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState()
                .setValue(FACING, context.getNearestLookingDirection())
                .setValue(POWER, 0);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving)
    {
        pLevel.updateNeighborsAt(pPos, this);
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    public RedstoneShifterBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isSignalSource(BlockState pState)
    {
        return true;
    }

    @Override
    public int getDirectSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection)
    {
        return getSignal(pState, pLevel, pPos, pDirection);
    }

    @Override
    public int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection)
    {
        if (pDirection == pState.getValue(FACING))
            return pState.getValue(POWER);

        return 0;
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving)
    {
        if (getInputSignal(pLevel, pPos, pState) > 0)
        {
            if (pState.getValue(POWER) > 0)
                pLevel.setBlock(pPos, pState.setValue(POWER, 0), 2);
        }
        else
        {
            if (pState.getValue(POWER) == 0)
                pLevel.setBlock(pPos, pState.setValue(POWER, 15), 2);
        }

        pLevel.updateNeighborsAt(pPos, this);
    }

    public int getInputSignal(Level pLevel, BlockPos pPos, BlockState pState)
    {
        Direction direction = pState.getValue(FACING);
        BlockPos blockpos = pPos.relative(direction);
        int i = pLevel.getSignal(blockpos, direction);
        if (i >= 15)
        {
            return i;
        }
        else
        {
            BlockState blockstate = pLevel.getBlockState(blockpos);
            return Math.max(i, blockstate.is(Blocks.REDSTONE_WIRE) ? blockstate.getValue(RedStoneWireBlock.POWER) : 0);
        }
    }
}
