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
	 * Shortcut for the vertical slabs' blockstate.
	 *
	 * @param context
	 * @param provider
	 * @param texture
	 */
	public static void makeBlockstate(DataGenContext<Block, VerticalSlabBlock> context, RegistrateBlockstateProvider provider, String texture)
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
	 * Shortcut for the vertical slabs' crafting table recipe.
	 *
	 * @param context
	 * @param consumer
	 * @param madeFrom
	 */
	public static void makeShapedRecipe(DataGenContext<Item, BlockItem> context, RegistrateRecipeProvider consumer, ItemLike madeFrom)
	{
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, context.getEntry(), 6)
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.define('#', madeFrom)
				.unlockedBy("has_required_block", consumer.has(madeFrom))
				.save(consumer);
	}

	/**
	 * Shortcut for the vertical slabs' stonecutting recipe, if needed.
	 *
	 * @param context
	 * @param consumer
	 * @param madeFrom
	 */
	public static void makeStonecuttingRecipe(DataGenContext<Item, BlockItem> context, RegistrateRecipeProvider consumer, ItemLike madeFrom)
	{
		consumer.stonecutting(
				DataIngredient.ingredient(Ingredient.of(madeFrom::asItem), madeFrom::asItem),
				RecipeCategory.BUILDING_BLOCKS,
				context::getEntry);
	}
}
