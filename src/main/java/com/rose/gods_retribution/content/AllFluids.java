package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Consumer;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

/**
 * Contains all the mod's fluids.
 */
public class AllFluids
{
	public static final FluidEntry<ForgeFlowingFluid.Flowing> TEST_FLUID = REGISTRATE
			.fluid("test_fluid",
				   new ResourceLocation(GodsRetribution.MOD_ID, "block/water_still"),
				   new ResourceLocation(GodsRetribution.MOD_ID, "block/water_flow"),
				   (props, still, flow) -> new FluidType(props) {
						@Override
						public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer)
						{
							consumer.accept(new IClientFluidTypeExtensions()
							{
								@Override
								public ResourceLocation getStillTexture()
								{
									return still;
								}

								@Override
								public ResourceLocation getFlowingTexture()
								{
									return flow;
								}
							});
						}
				   })
			.properties(p -> p
					.viscosity(1500)
					.density(500))
			.renderType(RenderType::translucent)
			//.source(ForgeFlowingFluid.Source::new) // if we want a fluid without fluid properties
			.source(factory -> { // if we want a fluid with fluid properties
				factory.levelDecreasePerBlock(2);
				factory.tickRate(25);
				factory.slopeFindDistance(3);
				factory.explosionResistance(100.0f);
				return new ForgeFlowingFluid.Source(factory);
			})
			.bucket()
			.model((ctx, provider) -> {
				provider.withExistingParent(ctx.getName(), "item/generated")
						.texture("layer0", "item/test_fluid_bucket");
			})
			.tab(AllCreativeTabs.MAIN.getKey())
			.build()
			//.noBucket()
			.register();

	public static final FluidEntry<ForgeFlowingFluid.Flowing> GLUE = REGISTRATE
			.fluid("glue",
				   new ResourceLocation(GodsRetribution.MOD_ID, "block/glue_still"),
				   new ResourceLocation(GodsRetribution.MOD_ID, "block/glue_flow"))
			.properties(p -> p
					.viscosity(3000)
					.density(1000))
			.source(f -> {
				f.levelDecreasePerBlock(3);
				f.tickRate(20);
				f.slopeFindDistance(1);
				f.explosionResistance(100.0f);
				return new ForgeFlowingFluid.Source(f);
			})
			.bucket()
			.model((ctx, provider) -> {
				provider.withExistingParent(ctx.getName(), "item/generated")
						.texture("layer0", "item/test_fluid_bucket");
			})
			.tab(AllCreativeTabs.MAIN.getKey())
			.build().register();

	public static final FluidEntry<ForgeFlowingFluid.Flowing> PLASTIC = REGISTRATE
			.fluid("plastic",
				   new ResourceLocation(GodsRetribution.MOD_ID, "block/plastic_still"),
				   new ResourceLocation(GodsRetribution.MOD_ID, "block/plastic_flow"))
			.properties(p -> p
					.viscosity(3000)
					.density(1000))
			.source(f -> {
				f.levelDecreasePerBlock(2);
				f.tickRate(40);
				f.slopeFindDistance(3);
				f.explosionResistance(100.0f);
				return new ForgeFlowingFluid.Source(f);
			})
			.bucket()
			.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), "item/generated").texture("layer0", "item/plastic_bucket"))
			.tab(AllCreativeTabs.MAIN.getKey())
			.build().register();

	/**
	 * Loads this class.
	 */
	public static void register()
	{}
}
