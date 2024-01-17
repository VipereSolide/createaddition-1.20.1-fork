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

    @Override
    public InteractionResult use(BlockState pState, Level world, BlockPos pos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if (pPlayer instanceof ServerPlayer serverPlayer)
            withBlockEntityDo(world, pos, labellingMachine -> NetworkHooks.openScreen(serverPlayer, labellingMachine, labellingMachine::sendToMenu));

        return InteractionResult.SUCCESS;

        /*
        if (pLevel.isClientSide())
        {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.OpenLabellingMachineScreen(pHand));
        }
        */

        /*
        // We only want to perform inventory actions on server side.
        if (pLevel.isClientSide())
            return InteractionResult.FAIL;

        // Querying the labelling machine block entity at the given position.
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof LabellingMachineBlockEntity machine)
        {
            ItemStack inHand = pPlayer.getItemInHand(pHand);
            var inventory = machine.getInventory();

            // If the player's hand is empty, retrieve {64 item if crouching; 1 otherwise} from the inventory.
            if (inHand.isEmpty())
            {
                if (inventory.getStackInSlot(0).isEmpty())
                {
                    pPlayer.sendSystemMessage(Component.literal("No item in block entity!"));
                    return InteractionResult.SUCCESS;
                }

                var extracted = inventory.extractItem(0, pPlayer.isCrouching() ? inventory.getSlotLimit(0) : 1, false);
                pPlayer.setItemInHand(pHand, extracted);
            }
            // Otherwise, input 1 item inside the inventory.
            else
            {
                var toInsert = inHand.copy();
                toInsert.setCount(1);

                var leftover = inventory.insertItem(0, toInsert, false);
                var remainder = inHand.copy();
                remainder.setCount(remainder.getCount() - 1);
                remainder.grow(leftover.getCount());
                pPlayer.setItemInHand(pHand, remainder);
            }

            return InteractionResult.SUCCESS;
        }*/

        // return InteractionResult.FAIL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving)
    {
        // We only want to perform inventory actions on server side.
        if (!pLevel.isClientSide())
        {
            // Querying the labelling machine block entity at the given position.
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof LabellingMachineBlockEntity machine)
            {
                // Spawning the content of the machine on the floor.
                var inventory = machine.inventory;
                for (int index = 0; index < inventory.getSlots(); index++)
                {
                    var stack = inventory.getStackInSlot(index);
                    var entity = new ItemEntity(pLevel,
                            pPos.getX() + .5,
                            pPos.getY() + .5,
                            pPos.getZ() + .5,
                            stack);
                    pLevel.addFreshEntity(entity);
                }
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
}
