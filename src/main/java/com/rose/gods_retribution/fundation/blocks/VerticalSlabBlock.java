package com.rose.gods_retribution.fundation.blocks;

import com.rose.gods_retribution.content.AllShapes;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

/**
 * The class to create vertical slabs. It also contains some helper static methods to ease the creation of vertical
 * slabs.
 */
public class VerticalSlabBlock extends HorizontalDirectionalBlock
{
	public VerticalSlabBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public @NotNull VoxelShape getShape(BlockState blockstate,
										@NotNull BlockGetter world,
										@NotNull BlockPos position,
										@NotNull CollisionContext collisionContext)
	{
		return AllShapes.VERTICAL_SLAB.get(blockstate.getValue(FACING));
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
		provider.horizontalBlock(context.getEntry(), provider
				.models()
				.withExistingParent(context.getName(), provider.modLoc("block/vertical_slab"))
				.texture("main", texture)
				.texture("all", texture)
				.texture("particle", texture)
		);
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
