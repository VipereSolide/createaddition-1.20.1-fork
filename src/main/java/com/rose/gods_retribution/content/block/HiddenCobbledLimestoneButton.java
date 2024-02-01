package com.rose.gods_retribution.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class HiddenCobbledLimestoneButton extends DirectionalBlock
{
    public static final int TICKS_TO_STAY_PRESSED = 40;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public HiddenCobbledLimestoneButton(Properties p_52591_)
    {
        super(p_52591_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING, POWERED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        return super.getStateForPlacement(pContext)
                .setValue(FACING, pContext.getNearestLookingDirection().getOpposite())
                .setValue(POWERED, false);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if (pHit.getDirection() != pState.getValue(FACING))
            return InteractionResult.PASS;

        if (pState.getValue(POWERED))
        {
            return InteractionResult.CONSUME;
        }
        else
        {
            this.press(pState, pLevel, pPos);
            this.playSound(pPlayer, pLevel, pPos, true);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
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
        return pState.getValue(POWERED) ? 15 : 0;
    }

    public void press(BlockState pState, Level pLevel, BlockPos pPos)
    {
        pLevel.setBlock(pPos, pState.setValue(POWERED, true), 2);
        pLevel.updateNeighborsAt(pPos, this);

        pLevel.scheduleTick(pPos, this, TICKS_TO_STAY_PRESSED);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        if (pState.getValue(POWERED))
        {
            pLevel.setBlock(pPos, pState.setValue(POWERED, false), 2);
            pLevel.updateNeighborsAt(pPos, this);
            this.playSound(null, pLevel, pPos, false);
        }
    }

    protected void playSound(@javax.annotation.Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos, boolean on)
    {
        pLevel.playSound(pPlayer,
                pPos,
                on ? SoundEvents.BASALT_PLACE : SoundEvents.BASALT_PLACE,
                SoundSource.BLOCKS);
    }
}
