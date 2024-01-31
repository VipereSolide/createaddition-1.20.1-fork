package com.rose.gods_retribution.content.block.keyholes;

import com.rose.gods_retribution.content.AllShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class KeyholeBlock extends HorizontalDirectionalBlock
{
    public static final BooleanProperty LOCKED = BlockStateProperties.LOCKED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public KeyholeBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(LOCKED);
        builder.add(POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(LOCKED, false)
                .setValue(POWERED, false);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState blockstate,
                                        @NotNull BlockGetter world,
                                        @NotNull BlockPos position,
                                        @NotNull CollisionContext collisionContext)
    {
        return AllShapes.ofSize(0, 0, 0, 16, 16, 16);
    }

    public boolean isSignalSource(@NotNull BlockState state)
    {
        return true;
    }

    public int getSignal(@NotNull BlockState pBlockState,
                         @NotNull BlockGetter pBlockAccess,
                         @NotNull BlockPos pPos,
                         @NotNull Direction pSide)
    {

        return pBlockState.getValue(POWERED) && pSide == pBlockState.getValue(FACING) ? 15 : 0;
    }

    public static void setActive(Level world, BlockPos pos, BlockState state)
    {
        world.scheduleTick(pos, state.getBlock(), 2);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        if (pState.getValue(POWERED))
        {
            pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.FALSE), 2);
        }
        else
        {
            pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.TRUE), 2);
            pLevel.scheduleTick(pPos, this, 2);
        }

        this.updateNeighborsInFront(pLevel, pPos, pState);
    }

    protected void updateNeighborsInFront(Level pLevel, BlockPos pPos, BlockState pState)
    {
        Direction direction = pState.getValue(FACING);
        BlockPos blockpos = pPos.relative(direction.getOpposite());
        pLevel.neighborChanged(blockpos, this, pPos);
        pLevel.updateNeighborsAtExceptFromFacing(blockpos, this, direction);
    }
}
