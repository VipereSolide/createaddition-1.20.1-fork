package com.rose.gods_retribution.content.block;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.redstone.diodes.BrassDiodeBlock;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class AutomatedRedstoneSwitch extends Block
{
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty POWERING = BrassDiodeBlock.POWERING;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public AutomatedRedstoneSwitch(Properties pProperties)
    {
        super(pProperties);
        registerDefaultState(defaultBlockState()
                .setValue(POWERED, false)
                .setValue(POWERING, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Direction preferred = getPreferredFacing(context);
        if (preferred == null || (context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown()))
        {
            Direction nearestLookingDirection = context.getNearestLookingDirection();
            return defaultBlockState().setValue(FACING, context.getPlayer() != null && context.getPlayer()
                    .isShiftKeyDown() ? nearestLookingDirection : nearestLookingDirection.getOpposite());
        }
        return defaultBlockState().setValue(FACING, preferred.getOpposite());
    }

    public Direction getPreferredFacing(BlockPlaceContext context)
    {
        Direction prefferedSide = null;
        for (Direction side : Iterate.directions)
        {
            BlockState blockState = context.getLevel()
                    .getBlockState(context.getClickedPos()
                            .relative(side));
            if (blockState.getBlock() instanceof IRotate)
            {
                if (((IRotate) blockState.getBlock()).hasShaftTowards(context.getLevel(), context.getClickedPos()
                        .relative(side), blockState, side.getOpposite()))
                    if (prefferedSide != null && prefferedSide.getAxis() != side.getAxis())
                    {
                        prefferedSide = null;
                        break;
                    }
                    else
                    {
                        prefferedSide = side;
                    }
            }
        }
        return prefferedSide;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot)
    {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder.add(POWERED, POWERING, FACING));
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, SignalGetter level, BlockPos pos, Direction side)
    {
        return false;
    }

    @Override
    public boolean isSignalSource(BlockState pState)
    {
        return pState.getValue(POWERED);
    }

    @Override
    public int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection)
    {
        return pDirection == Direction.NORTH ? pState.getValue(POWERING) ? 15 : 0 : 0;
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving)
    {
        if (!pLevel.isClientSide)
        {
            boolean flag = pState.getValue(POWERED);

            if (flag != pLevel.hasNeighborSignal(pPos))
            {
                if (flag)
                {
                    pLevel.scheduleTick(pPos, this, 4);
                }
                else
                {
                    pLevel.setBlock(pPos, pState.cycle(POWERED), 2);
                }
            }

        }
    }

    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos)
    {
        pLevel.updateNeighborsAt(pPos, this);
        pLevel.updateNeighborsAt(pPos.relative(pState.getValue(FACING)), this);
    }
}
