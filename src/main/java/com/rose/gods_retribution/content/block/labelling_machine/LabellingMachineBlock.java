package com.rose.gods_retribution.content.block.labelling_machine;

import com.rose.gods_retribution.content.AllBlockEntityTypes;
import com.rose.gods_retribution.content.AllShapes;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LabellingMachineBlock extends HorizontalKineticBlock implements IBE<LabellingMachineBlockEntity>
{
    public LabellingMachineBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        return AllShapes.LABELLING_MACHINE.get(pState.getValue(HORIZONTAL_FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Direction preferredSide = getPreferredHorizontalFacing(context);
        if (preferredSide != null)
            return defaultBlockState().setValue(HORIZONTAL_FACING, preferredSide);

        return super.getStateForPlacement(context);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state)
    {
        return state.getValue(HORIZONTAL_FACING)
                .getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face)
    {
        return face.getAxis() == state.getValue(HORIZONTAL_FACING)
                .getAxis();
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type)
    {
        return false;
    }

    @Override
    public Class<LabellingMachineBlockEntity> getBlockEntityClass()
    {
        return LabellingMachineBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends LabellingMachineBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.LABELLING_MACHINE.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockstate)
    {
        return AllBlockEntityTypes.LABELLING_MACHINE.create(blockPos, blockstate);
    }
}
