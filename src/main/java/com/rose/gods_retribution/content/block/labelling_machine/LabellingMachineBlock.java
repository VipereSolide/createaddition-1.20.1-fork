package com.rose.gods_retribution.content.block.labelling_machine;

import com.rose.gods_retribution.content.AllBlockEntityTypes;
import com.rose.gods_retribution.content.AllShapes;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class LabellingMachineBlock extends HorizontalKineticBlock implements IBE<LabellingMachineBlockEntity>
{
    public LabellingMachineBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState blockstate,
                                        @NotNull BlockGetter world,
                                        @NotNull BlockPos position,
                                        @NotNull CollisionContext collisionContext)
    {
        return AllShapes.LABELLING_MACHINE.get(blockstate.getValue(HORIZONTAL_FACING));
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
    public boolean isPathfindable(@NotNull BlockState state,
                                  @NotNull BlockGetter reader,
                                  @NotNull BlockPos pos,
                                  @NotNull PathComputationType type)
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

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockstate,
                                          @NotNull Level world,
                                          @NotNull BlockPos position,
                                          @NotNull Player player,
                                          @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult hitResult)
    {
        if (player instanceof ServerPlayer serverPlayer)
            withBlockEntityDo(world, position, labellingMachine ->
                    NetworkHooks.openScreen(serverPlayer, labellingMachine, labellingMachine::sendToMenu));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState blockstate,
                         Level world,
                         BlockPos position,
                         BlockState newBlockstate,
                         boolean isMoving)
    {
        // We only want to perform inventory actions on server side.
        if (!world.isClientSide())
        {
            // Querying the labelling machine block entity at the given position.
            BlockEntity blockEntity = world.getBlockEntity(position);
            if (blockEntity instanceof LabellingMachineBlockEntity machine)
            {
                // Spawning the content of the machine on the floor.
                var inventory = machine.inventory;
                for (int index = 0; index < inventory.getSlots(); index++)
                {
                    var stack = inventory.getStackInSlot(index);
                    var entity = new ItemEntity(world,
                            position.getX() + .5,
                            position.getY() + .5,
                            position.getZ() + .5,
                            stack);
                    world.addFreshEntity(entity);
                }
            }
        }

        super.onRemove(blockstate, world, position, newBlockstate, isMoving);
    }
}
