package com.rose.gods_retribution.content.item.fluid_vacuum;

import com.rose.gods_retribution.fundation.items.SmartItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class FluidVacuumItem extends SmartItem
{
    public static final int MAX_QUERY_ITERATION_TOKENS = 5;

    public FluidVacuumItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity)
    {
        List<BlockPos> allPositions = new ArrayList<>();
        allPositions.add(pPos);

        int iterationTokens = MAX_QUERY_ITERATION_TOKENS;
        while (iterationTokens > 0)
        {
            // looping through a copy of the all positions list, so we don't modify the original while reading it.
            List<BlockPos> allPositionsSafe = new ArrayList<>(allPositions);
            for (BlockPos positionIterator : allPositionsSafe)
                allPositions.addAll(getAdjacentBlockPositions(pLevel, positionIterator, pState.getBlock()));

            iterationTokens--;
        }

        for (BlockPos position : allPositions)
            pLevel.destroyBlock(position, true);

        return super.mineBlock(pStack, pLevel, pState, pPos, pMiningEntity);
    }

    public List<BlockPos> getAdjacentBlockPositions(Level level, BlockPos pos, Block target)
    {
        List<BlockPos> result = new ArrayList<>();

        final List<BlockPos> availablePositions = List.of(pos.above(), pos.below(), pos.west(), pos.east(), pos.north(), pos.south());
        for (BlockPos availablePosition : availablePositions)
            if (level.getBlockState(availablePosition).getBlock() == target)
                result.add(availablePosition);

        return result;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState pBlock)
    {
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer)
    {
        return true;
    }
}
