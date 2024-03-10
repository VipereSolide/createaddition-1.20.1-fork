package com.rose.gods_retribution.fundation.blocks;

import com.rose.gods_retribution.GodsRetribution;
import com.rose.gods_retribution.content.AllShapes;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

/**
 * The class to create vertical slabs. It also contains some helper static methods to ease the creation of vertical
 * slabs.
 */
public class VerticalSlabBlock extends HorizontalDirectionalBlock
{
	public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;

	public VerticalSlabBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
		builder.add(SHAPE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getClickedFace();
		BlockPos blockPos = context.getClickedPos();

		BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
		return blockstate.setValue(SHAPE, getSlabShape(blockstate, context.getLevel(), blockPos));
	}

	@Override
	public @NotNull VoxelShape getShape(BlockState blockstate,
										@NotNull BlockGetter world,
										@NotNull BlockPos position,
										@NotNull CollisionContext collisionContext)
	{
		return switch (blockstate.getValue(SHAPE))
		{
			case INNER_LEFT -> AllShapes.TEST_VERTICAL_SLAB_INNER.get(blockstate.getValue(FACING));
			case INNER_RIGHT -> AllShapes.TEST_VERTICAL_SLAB_INNER.get(blockstate.getValue(FACING).getClockWise());
			case OUTER_LEFT -> AllShapes.TEST_VERTICAL_SLAB_OUTER.get(blockstate.getValue(FACING));
			case OUTER_RIGHT -> AllShapes.TEST_VERTICAL_SLAB_OUTER.get(blockstate.getValue(FACING).getClockWise());
			case STRAIGHT -> AllShapes.TEST_VERTICAL_SLAB.get(blockstate.getValue(FACING));
		};
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror)
	{
		Direction dir = state.getValue(FACING);
		StairsShape shape = state.getValue(SHAPE);
		switch (mirror)
		{
			case LEFT_RIGHT:
				if (dir.getAxis() == Direction.Axis.Z)
				{
					switch (shape)
					{
						case INNER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
						case INNER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
						case OUTER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
						case OUTER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
						default:
							return state.rotate(Rotation.CLOCKWISE_180);
					}
				}
				break;
			case FRONT_BACK:
				if (dir.getAxis() == Direction.Axis.X)
				{
					switch (shape)
					{
						case INNER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
						case INNER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
						case OUTER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT); // outer_left ?
						case OUTER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
						case STRAIGHT:
							return state.rotate(Rotation.CLOCKWISE_180);
					}
				}
		}
		return super.mirror(state, mirror);
	}

	private static StairsShape getSlabShape(BlockState state, BlockGetter level, BlockPos pos)
	{
		Direction direction = state.getValue(FACING);
		BlockState blockstate = level.getBlockState(pos.relative(direction));
		if (isVerticalSlab(blockstate))
		{
			Direction direction2 = blockstate.getValue(FACING);
			if (direction2.getAxis() != direction.getAxis() && canTakeShape(state, level, pos, direction2.getOpposite()))
			{
				if (direction2 == direction.getCounterClockWise())
					return StairsShape.OUTER_LEFT;
				else
					return StairsShape.OUTER_RIGHT;
			}
		}

		BlockState blockstate2 = level.getBlockState(pos.relative(direction.getOpposite()));
		if (isVerticalSlab(blockstate2))
		{
			Direction direction3 = blockstate2.getValue(FACING);
			if (direction3.getAxis() != direction.getAxis() && canTakeShape(state, level, pos, direction3))
			{
				if (direction3 == direction.getCounterClockWise())
					return StairsShape.INNER_LEFT;
				else
					return StairsShape.INNER_RIGHT;
			}
		}

		return StairsShape.STRAIGHT;
	}

	private static boolean isVerticalSlab(BlockState state)
	{
		return state.getBlock() instanceof VerticalSlabBlock;
	}

	private static boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
	{
		BlockState blockstate = level.getBlockState(pos.relative(direction));
		return !isVerticalSlab(blockstate) || blockstate.getValue(FACING) != state.getValue(FACING);
	}

	/**
	 * Shortcut for the datagen of the vertical slabs' blockstate. Use it like this :
	 * <p>
	 * <pre>
	 * REGISTRATE.block(...)
	 * ...
	 * .blockstate((ctx, provider) -> VerticalSlabBlock.makeBlockstate(ctx, provider, "block/texture"))
	 * ...
	 * </pre>
	 * </p>
	 *
	 * @param context
	 * @param provider
	 * @param texture
	 */
	public static void makeBlockstate(DataGenContext<Block, VerticalSlabBlock> context,
									  RegistrateBlockstateProvider provider,
									  String texture)
	{
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.EAST)
				.with(SHAPE, StairsShape.STRAIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName())))
				.rotationY(270)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.WEST)
				.with(SHAPE, StairsShape.STRAIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName())))
				.rotationY(90)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.SOUTH)
				.with(SHAPE, StairsShape.STRAIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName())))
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.NORTH)
				.with(SHAPE, StairsShape.STRAIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName())))
				.rotationY(180)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.EAST)
				.with(SHAPE, StairsShape.OUTER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.rotationY(270)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.WEST)
				.with(SHAPE, StairsShape.OUTER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.rotationY(90)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.SOUTH)
				.with(SHAPE, StairsShape.OUTER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.NORTH)
				.with(SHAPE, StairsShape.OUTER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.rotationY(180)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.EAST)
				.with(SHAPE, StairsShape.OUTER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.rotationY(180)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.WEST)
				.with(SHAPE, StairsShape.OUTER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.SOUTH)
				.with(SHAPE, StairsShape.OUTER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.rotationY(270)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.NORTH)
				.with(SHAPE, StairsShape.OUTER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_outer")))
				.rotationY(90)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.EAST)
				.with(SHAPE, StairsShape.INNER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.rotationY(270)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.WEST)
				.with(SHAPE, StairsShape.INNER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.rotationY(90)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.SOUTH)
				.with(SHAPE, StairsShape.INNER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.NORTH)
				.with(SHAPE, StairsShape.INNER_RIGHT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.rotationY(180)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.EAST)
				.with(SHAPE, StairsShape.INNER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.rotationY(180)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.WEST)
				.with(SHAPE, StairsShape.INNER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.SOUTH)
				.with(SHAPE, StairsShape.INNER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.rotationY(270)
				.uvLock(true)
				.addModel();
		provider.getVariantBuilder(context.getEntry())
				.partialState()
				.with(FACING, Direction.NORTH)
				.with(SHAPE, StairsShape.INNER_LEFT)
				.modelForState()
				.modelFile(new ModelFile.UncheckedModelFile(new ResourceLocation(GodsRetribution.MOD_ID, "block/" + context.getName() + "_inner")))
				.rotationY(90)
				.uvLock(true)
				.addModel();

		provider
				.models()
				.withExistingParent(context.getName(), provider.modLoc("block/vslab"))
				.texture("side", texture)
				.texture("top", texture)
				.texture("particle", texture);
		provider
				.models()
				.withExistingParent(context.getName() + "_inner", provider.modLoc("block/vslab_inner"))
				.texture("side", texture)
				.texture("top", texture)
				.texture("particle", texture);
		provider
				.models()
				.withExistingParent(context.getName() + "_outer", provider.modLoc("block/vslab_outer"))
				.texture("side", texture)
				.texture("top", texture)
				.texture("particle", texture);
	}

	/**
	 * Shortcut for the vertical slabs' crafting table recipe. Use it like this :
	 * <p>
	 * <pre>
	 * REGISTRATE.block(...)
	 * ...
	 * .item()
	 * ...
	 * .makeShapedRecipe((ctx, consumer) -> VerticalSlabBlock.makeShapedRecipe(ctx, consumer, AllBlocks.BLOCK_OF_JOE))
	 * ...
	 * </pre>
	 * </p>
	 *
	 * @param context
	 * @param consumer
	 * @param madeOf
	 */
	public static void makeShapedRecipe(DataGenContext<Item, BlockItem> context,
										RegistrateRecipeProvider consumer,
										ItemLike madeOf)
	{
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, context.getEntry(), 6)
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.define('#', madeOf)
				.unlockedBy("has_required_block", consumer.has(madeOf))
				.save(consumer);
	}

	/**
	 * Shortcut for the vertical slabs' stonecutting recipe, if needed. For the usage, see the documentation for the
	 * {@code makeShapedRecipe} method.
	 *
	 * @param context
	 * @param consumer
	 * @param madeOf
	 */
	public static void makeStonecuttingRecipe(DataGenContext<Item, BlockItem> context,
											  RegistrateRecipeProvider consumer,
											  ItemLike madeOf)
	{
		consumer.stonecutting(
				DataIngredient.ingredient(Ingredient.of(madeOf::asItem), madeOf::asItem),
				RecipeCategory.BUILDING_BLOCKS,
				context::getEntry,
				2);
	}
}
