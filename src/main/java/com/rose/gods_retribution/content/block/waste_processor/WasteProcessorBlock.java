package com.rose.gods_retribution.content.block.waste_processor;

import com.rose.gods_retribution.content.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class WasteProcessorBlock extends HorizontalKineticBlock implements IBE<WasteProcessorBlockEntity>
{
	public WasteProcessorBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public Class<WasteProcessorBlockEntity> getBlockEntityClass()
	{
		return WasteProcessorBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends WasteProcessorBlockEntity> getBlockEntityType()
	{
		return AllBlockEntityTypes.WASTE_PROCESSOR.get();
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
	{
		return AllBlockEntityTypes.WASTE_PROCESSOR.create(blockPos, blockState);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
	{
		return box(0,0,0,16,16,16);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState)
	{
		return RenderShape.MODEL;
	}

	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving)
	{
		if (pState.getBlock() != pNewState.getBlock())
		{
			BlockEntity be = pLevel.getBlockEntity(pPos);
			if (be instanceof WasteProcessorBlockEntity)
				((WasteProcessorBlockEntity) be).drops();
		}
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
	{
		if (!pLevel.isClientSide())
		{
			BlockEntity be = pLevel.getBlockEntity(pPos);
			if (be instanceof WasteProcessorBlockEntity)
				NetworkHooks.openScreen((ServerPlayer) pPlayer, (WasteProcessorBlockEntity) be, pPos); // does not work anymore in minecraft versions newer than 1.20.1
			else
				throw new IllegalStateException("Our container provider is missing !");
		}

		return InteractionResult.sidedSuccess(pLevel.isClientSide());
	}

	/*@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType)
	{
		if (pLevel.isClientSide())
			return null;
		return createTickerHelper(pBlockEntityType,
								  AllBlockEntityTypes.WASTE_PROCESSOR.get(),
								  (level, blockPos, blockState, blockEntity) -> blockEntity.tick(level, blockPos, blockState));
	}*/

	@Override
	public Direction.Axis getRotationAxis(BlockState state)
	{
		return state.getValue(HORIZONTAL_FACING).getAxis();
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
	public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face)
	{
		return face.getAxis() == state.getValue(HORIZONTAL_FACING).getAxis();
	}
}
