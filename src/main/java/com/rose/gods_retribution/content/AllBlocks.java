package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.*;
import com.rose.gods_retribution.content.block.engraved_blocks.EngravedLimestoneBlock;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlock;
import com.rose.gods_retribution.content.block.keyholes.KeyholeBlock;
import com.rose.gods_retribution.content.block.plastic_moss.PlasticMossBlock;
import com.rose.gods_retribution.fundation.blocks.SmartBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;
import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.TagGen.*;

/**
 * Contains all the mod's blocks.
 * <p>
 * The new blocks are to be registered in here, as a public static final BlockEntry
 */
public class AllBlocks
{
    // Vanilla-related blocks

    public static final BlockEntry<StairBlock> GOLD_STAIRS = REGISTRATE
            .block("gold_stairs", p -> new StairBlock(Blocks.GOLD_BLOCK.defaultBlockState(), p))
            .initialProperties(() -> Blocks.GOLD_BLOCK)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.NEEDS_IRON_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.GUARDED_BY_PIGLINS
            )
            .blockstate((ctx, provider) -> {
                provider.stairsBlock(ctx.getEntry(), provider.blockTexture(Blocks.GOLD_BLOCK));
            })
            .item()
            .tag(
                    ItemTags.PIGLIN_LOVED,
                    ItemTags.STAIRS
            )
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', Blocks.GOLD_BLOCK)
                        .unlockedBy("has_gold_block", consumer.has(Blocks.GOLD_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<SlabBlock> GOLD_SLAB = REGISTRATE
            .block("gold_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.GOLD_BLOCK)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.NEEDS_IRON_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.GUARDED_BY_PIGLINS
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.blockTexture(Blocks.GOLD_BLOCK), provider.blockTexture(Blocks.GOLD_BLOCK));
            })
            .item()
            .tag(
                    ItemTags.PIGLIN_LOVED,
                    ItemTags.SLABS
            )
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("###")
                        .define('#', Blocks.GOLD_BLOCK)
                        .unlockedBy("has_gold_block", consumer.has(Blocks.GOLD_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<StairBlock> DIAMOND_STAIRS = REGISTRATE
            .block("diamond_stairs", p -> new StairBlock(Blocks.DIAMOND_BLOCK.defaultBlockState(), p))
            .initialProperties(() -> Blocks.DIAMOND_BLOCK)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.NEEDS_IRON_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.stairsBlock(ctx.getEntry(), provider.blockTexture(Blocks.DIAMOND_BLOCK));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', Blocks.DIAMOND_BLOCK)
                        .unlockedBy("has_diamond_block", consumer.has(Blocks.DIAMOND_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<SlabBlock> DIAMOND_SLAB = REGISTRATE
            .block("diamond_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.DIAMOND_BLOCK)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.NEEDS_IRON_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.blockTexture(Blocks.DIAMOND_BLOCK), provider.blockTexture(Blocks.DIAMOND_BLOCK));
            })
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("###")
                        .define('#', Blocks.DIAMOND_BLOCK)
                        .unlockedBy("has_diamond_block", consumer.has(Blocks.DIAMOND_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<StairBlock> IRON_STAIRS = REGISTRATE
            .block("iron_stairs", p -> new StairBlock(Blocks.IRON_BLOCK.defaultBlockState(), p))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.NEEDS_STONE_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.stairsBlock(ctx.getEntry(), provider.blockTexture(Blocks.IRON_BLOCK));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', Blocks.IRON_BLOCK)
                        .unlockedBy("has_iron_block", consumer.has(Blocks.IRON_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<SlabBlock> IRON_SLAB = REGISTRATE
            .block("iron_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_STONE_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.blockTexture(Blocks.IRON_BLOCK), provider.blockTexture(Blocks.IRON_BLOCK));
            })
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("###")
                        .define('#', Blocks.IRON_BLOCK)
                        .unlockedBy("has_iron_block", consumer.has(Blocks.IRON_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<StairBlock> LAPIS_STAIRS = REGISTRATE
            .block("lapis_stairs", p -> new StairBlock(Blocks.LAPIS_BLOCK.defaultBlockState(), p))
            .initialProperties(() -> Blocks.LAPIS_BLOCK)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_STONE_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.stairsBlock(ctx.getEntry(), provider.blockTexture(Blocks.LAPIS_BLOCK));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', Blocks.LAPIS_BLOCK)
                        .unlockedBy("has_lapis_block", consumer.has(Blocks.LAPIS_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<SlabBlock> LAPIS_SLAB = REGISTRATE
            .block("lapis_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.LAPIS_BLOCK)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.NEEDS_STONE_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.blockTexture(Blocks.LAPIS_BLOCK), provider.blockTexture(Blocks.LAPIS_BLOCK));
            })
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("###")
                        .define('#', Blocks.LAPIS_BLOCK)
                        .unlockedBy("has_lapis_block", consumer.has(Blocks.LAPIS_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<StairBlock> EMERALD_STAIRS = REGISTRATE
            .block("emerald_stairs", p -> new StairBlock(Blocks.EMERALD_BLOCK.defaultBlockState(), p))
            .initialProperties(() -> Blocks.EMERALD_BLOCK)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_IRON_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.stairsBlock(ctx.getEntry(), provider.blockTexture(Blocks.EMERALD_BLOCK));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', Blocks.EMERALD_BLOCK)
                        .unlockedBy("has_emerald_block", consumer.has(Blocks.EMERALD_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<SlabBlock> EMERALD_SLAB = REGISTRATE
            .block("emerald_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.EMERALD_BLOCK)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.NEEDS_IRON_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.blockTexture(Blocks.EMERALD_BLOCK), provider.blockTexture(Blocks.EMERALD_BLOCK));
            })
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("###")
                        .define('#', Blocks.EMERALD_BLOCK)
                        .unlockedBy("has_emerald_block", consumer.has(Blocks.EMERALD_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<StairBlock> NETHERITE_STAIRS = REGISTRATE
            .block("netherite_stairs", p -> new StairBlock(Blocks.NETHERITE_BLOCK.defaultBlockState(), p))
            .initialProperties(() -> Blocks.NETHERITE_BLOCK)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_DIAMOND_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.stairsBlock(ctx.getEntry(), provider.blockTexture(Blocks.NETHERITE_BLOCK));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', Blocks.NETHERITE_BLOCK)
                        .unlockedBy("has_netherite_block", consumer.has(Blocks.NETHERITE_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<SlabBlock> NETHERITE_SLAB = REGISTRATE
            .block("netherite_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.NETHERITE_BLOCK)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.NEEDS_DIAMOND_TOOL,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.blockTexture(Blocks.NETHERITE_BLOCK), provider.blockTexture(Blocks.NETHERITE_BLOCK));
            })
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, consumer) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                        .pattern("###")
                        .define('#', Blocks.NETHERITE_BLOCK)
                        .unlockedBy("has_netherite_block", consumer.has(Blocks.NETHERITE_BLOCK))
                        .save(consumer);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    // Original blocks
    public static final BlockEntry<Block> WOOD = REGISTRATE
            .block("wood", Block::new)
            .initialProperties(() -> Blocks.DIRT)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW).requiresCorrectToolForDrops())
            .tag(AllTags.Blocks.WOODY)
            .transform(pickaxeOnly())
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> WOOD_STAIRS = REGISTRATE
            .block("wood_stairs", (p) -> new StairBlock(AllBlocks.WOOD.getDefaultState(), p))
            .initialProperties(() -> AllBlocks.WOOD.get())
            .tag(
                    BlockTags.STAIRS,
                    AllTags.Blocks.WOODY
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/wood"));
            })
            .transform(pickaxeOnly())
            .item()
            .tag(
                    ItemTags.STAIRS,
                    ItemTags.WOODEN_STAIRS
            )
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> WOOD_SLAB = REGISTRATE
            .block("wood_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.DIRT)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops())
            .tag(
                    AllTags.Blocks.WOODY,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/wood"), provider.modLoc("block/wood"));
            })
            .transform(pickaxeOnly())
            .item()
            .tag(
                    ItemTags.SLABS,
                    ItemTags.WOODEN_SLABS
            )
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> INDUSTRIAL_IRON_SHINGLES = REGISTRATE
            .block("industrial_iron_shingles", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> INDUSTRIAL_IRON_SHINGLES_STAIRS = REGISTRATE
            .block("industrial_iron_shingles_stairs", (p) -> new StairBlock(AllBlocks.INDUSTRIAL_IRON_SHINGLES.getDefaultState(), p))
            .initialProperties(() -> AllBlocks.INDUSTRIAL_IRON_SHINGLES.get())
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.STAIRS
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/industrial_iron_shingles"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> INDUSTRIAL_IRON_SHINGLES_SLAB = REGISTRATE
            .block("industrial_iron_shingles_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/industrial_iron_shingles"), provider.modLoc("block/industrial_iron_shingles"));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> CRACKED_INDUSTRIAL_IRON_SHINGLES = REGISTRATE
            .block("cracked_industrial_iron_shingles", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> CRACKED_INDUSTRIAL_IRON_SHINGLES_STAIRS = REGISTRATE
            .block("cracked_industrial_iron_shingles_stairs", (p) -> new StairBlock(AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES.getDefaultState(), p))
            .initialProperties(() -> AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES.get())
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.STAIRS
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/cracked_industrial_iron_shingles"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> CRACKED_INDUSTRIAL_IRON_SHINGLES_SLAB = REGISTRATE
            .block("cracked_industrial_iron_shingles_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/cracked_industrial_iron_shingles"), provider.modLoc("block/cracked_industrial_iron_shingles"));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<PlasticMossBlock> PLASTIC_MOSS = REGISTRATE
            .block("plastic_moss", PlasticMossBlock::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW))
            .blockstate(simpleCubeAll("plastic_moss"))
            .item()
            .build()
            .register();

    public static final BlockEntry<LabellingMachineBlock> LABELLING_MACHINE = REGISTRATE
            .block("labelling_machine", LabellingMachineBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .tag(com.simibubi.create.AllTags.AllBlockTags.SAFE_NBT.tag) // Dunno what this tag means (contraption safe?).
            .transform(BlockStressDefaults.setImpact(4))
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build()
            .register();

    public static final BlockEntry<Block> AERATION_BLOCK = REGISTRATE
            .block("aeration_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<AirVentBlock> AIR_VENT = REGISTRATE
            .block("air_vent", AirVentBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion())
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.requiresCorrectToolForDrops())
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .transform(axeOrPickaxe())
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> STEEL_STAIRS = REGISTRATE
            .block("steel_stairs", (p) -> new StairBlock(AllBlocks.STEEL_BLOCK.getDefaultState(), p))
            .initialProperties(() -> AllBlocks.STEEL_BLOCK.get())
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/steel_block"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> STEEL_SLAB = REGISTRATE
            .block("steel_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.requiresCorrectToolForDrops())
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/steel_block"), provider.modLoc("block/steel_block"));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> TILES_BATHROOM = REGISTRATE
            .block("tiles_bathroom_block", Block::new)
            .initialProperties(() -> Blocks.BONE_BLOCK)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> TILES_BATHROOM_STAIRS = REGISTRATE
            .block("tiles_bathroom_stairs", (p) -> new StairBlock(AllBlocks.TILES_BATHROOM.getDefaultState(), p))
            .initialProperties(() -> Blocks.BONE_BLOCK)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.STAIRS
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/tiles_bathroom_block"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> TILES_BATHROOM_SLAB = REGISTRATE
            .block("tiles_bathroom_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.SANDSTONE_SLAB)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, p) ->
            {
                p.slabBlock(ctx.getEntry(), p.modLoc("block/tiles_bathroom_block"), p.modLoc("block/" + ctx.getName()));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> FLINT_BLOCK = REGISTRATE
            .block("flint_block", Block::new)
            .initialProperties(() -> Blocks.GRAVEL)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .tag(
                    BlockTags.MINEABLE_WITH_SHOVEL,
                    Tags.Blocks.STORAGE_BLOCKS,
                    AllTags.Blocks.FORGE_STORAGE_BLOCKS_FLINT
            )
            .item()
            .tag(
                    Tags.Items.STORAGE_BLOCKS,
                    AllTags.Items.FORGE_STORAGE_BLOCKS_FLINT
            )
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> FLINT_STAIRS = REGISTRATE
            .block("flint_stairs", (p) -> new StairBlock(AllBlocks.FLINT_BLOCK.getDefaultState(), p))
            .initialProperties(() -> AllBlocks.FLINT_BLOCK.get())
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_SHOVEL
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/flint_block"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> FLINT_SLAB = REGISTRATE
            .block("flint_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.GRAVEL)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .tag(
                    BlockTags.MINEABLE_WITH_SHOVEL,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/flint_block"), provider.modLoc("block/flint_block"));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> CONCRETE = REGISTRATE
            .block("concrete", Block::new)
            .initialProperties(() -> Blocks.WHITE_CONCRETE)
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY))
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.CONCRETE
            )
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> CONCRETE_STAIRS = REGISTRATE
            .block("concrete_stairs", (p) -> new StairBlock(AllBlocks.CONCRETE.getDefaultState(), p))
            .initialProperties(() -> AllBlocks.CONCRETE.get())
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.STAIRS,
                    AllTags.Blocks.CONCRETE
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/concrete"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> CONCRETE_SLAB = REGISTRATE
            .block("concrete_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.WHITE_CONCRETE)
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY))
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS,
                    AllTags.Blocks.CONCRETE
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/concrete"), provider.modLoc("block/concrete"));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final DyedBlockList<Block> CONCRETE_COLOURS = new DyedBlockList<>(colour ->
    {
        String colourName = colour.getSerializedName();
        return REGISTRATE
                .block("concrete_" + colourName, Block::new)
                .initialProperties(AllBlocks.CONCRETE::get)
                .properties(p -> p.mapColor(colour))
                .tag(
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        AllTags.Blocks.CONCRETE
                )
                .item()
                .tab(AllCreativeTabs.DECORATION.getKey())
                .build()
                .register();
    });

    public static final DyedBlockList<StairBlock> CONCRETE_COLOURS_STAIRS = new DyedBlockList<>(colour ->
    {
        String colourName = colour.getSerializedName();
        return REGISTRATE
                .block("concrete_" + colourName + "_stairs", (p) -> new StairBlock(AllBlocks.CONCRETE.getDefaultState(), p))
                .initialProperties(AllBlocks.CONCRETE_STAIRS::get)
                .properties(p -> p.mapColor(colour))
                .tag(
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        BlockTags.STAIRS,
                        AllTags.Blocks.CONCRETE
                )
                .blockstate((ctx, provider) ->
                {
                    provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/concrete_" + colourName));
                })
                .recipe((ctx, consumer) ->
                {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                            .pattern("#  ")
                            .pattern("## ")
                            .pattern("###")
                            .define('#', AllBlocks.CONCRETE_COLOURS.get(colour))
                            .unlockedBy("has_concrete_" + colourName, consumer.has(AllBlocks.CONCRETE_COLOURS.get(colour)))
                            .save(consumer);
                })
                .item()
                .tag(ItemTags.STAIRS)
                .tab(AllCreativeTabs.DECORATION.getKey())
                .build().register();
    });

    public static final DyedBlockList<SlabBlock> CONCRETE_COLOURS_SLABS = new DyedBlockList<>(colour ->
    {
        String colourName = colour.getSerializedName();
        return REGISTRATE
                .block("concrete_" + colourName + "_slab", SlabBlock::new)
                .initialProperties(AllBlocks.CONCRETE_SLAB::get)
                .properties(p -> p.mapColor(colour))
                .tag(
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        BlockTags.SLABS,
                        AllTags.Blocks.CONCRETE
                )
                .blockstate((ctx, provider) ->
                {
                    provider.slabBlock(ctx.getEntry(), provider.modLoc("block/concrete_" + colourName), provider.modLoc("block/concrete_" + colourName));
                })
                .recipe((ctx, consumer) ->
                {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                            .pattern("###")
                            .define('#', AllBlocks.CONCRETE_COLOURS.get(colour))
                            .unlockedBy("has_concrete_" + colourName, consumer.has(AllBlocks.CONCRETE_COLOURS.get(colour)))
                            .save(consumer);
                })
                .item()
                .tag(ItemTags.SLABS)
                .tab(AllCreativeTabs.DECORATION.getKey())
                .build().register();
    });

    public static final BlockEntry<Block> CLEAR_GLASS = REGISTRATE
            .block("clear_glass", Block::new)
            .initialProperties(() -> Blocks.GLASS)
            .properties(properties -> properties.noOcclusion())
            .addLayer(() -> RenderType::cutout)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> CLEAR_GLASS_STAIRS = REGISTRATE
            .block("clear_glass_stairs", (p) -> new StairBlock(AllBlocks.CLEAR_GLASS.getDefaultState(), p))
            .initialProperties(AllBlocks.CLEAR_GLASS::get)
            .tag(BlockTags.STAIRS)
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/clear_glass"));
            })
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> CLEAR_GLASS_SLAB = REGISTRATE
            .block("clear_glass_slab", SlabBlock::new)
            .initialProperties(AllBlocks.CLEAR_GLASS::get)
            .tag(BlockTags.SLABS)
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/clear_glass"), provider.modLoc("block/clear_glass"));
            })
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    /*public static final BlockEntry<AutomatedRedstoneSwitch> AUTOMATED_REDSTONE_SWITCH = REGISTRATE
            .block("automated_redstone_switch", AutomatedRedstoneSwitch::new)
            .initialProperties(() -> com.simibubi.create.AllBlocks.ANDESITE_CASING.get())
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build()
            .register();*/

    public static final BlockEntry<Block> METAL_WIREMESH_BLOCK = REGISTRATE
            .block("metal_wiremesh_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .requiresCorrectToolForDrops())
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<StairBlock> METAL_WIREMESH_STAIRS = REGISTRATE
            .block("metal_wiremesh_stairs", (p) -> new StairBlock(AllBlocks.METAL_WIREMESH_BLOCK.getDefaultState(), p))
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK::get)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> METAL_WIREMESH_SLAB = REGISTRATE
            .block("metal_wiremesh_slab", SlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK::get)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block"), provider.modLoc("block/metal_wiremesh_block"));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> METAL_WIREMESH_BLOCK_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_block_transparent", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .requiresCorrectToolForDrops()
                    .noOcclusion())
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.FORGE_STORAGE_BLOCKS_IRON_BARS
            )
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(AllTags.Items.FORGE_STORAGE_BLOCKS_IRON_BARS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<StairBlock> METAL_WIREMESH_STAIRS_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_stairs_transparent", (p) -> new StairBlock(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT.getDefaultState(), p))
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.STAIRS
            )
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block_transparent"));
            })
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> METAL_WIREMESH_SLAB_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_slab_transparent", SlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block_transparent"), provider.modLoc("block/metal_wiremesh_block_transparent"));
            })
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> LIGHT_BLOCK = REGISTRATE
            .block("light_block", Block::new)
            .properties((p) -> p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.3f)
                    .explosionResistance(2.0f)
                    .destroyTime(Blocks.DIRT.defaultDestroyTime())
                    .sound(SoundType.STONE)
                    .lightLevel((i) ->
                    {
                        return 15;
                    }))
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<StairBlock> LIGHT_STAIRS = REGISTRATE
            .block("light_stairs", (p) -> new StairBlock(AllBlocks.LIGHT_BLOCK.getDefaultState(), p))
            .initialProperties(AllBlocks.LIGHT_BLOCK::get)
            .tag(BlockTags.STAIRS)
            .blockstate((ctx, provider) ->
            {
                provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/light_block"));
            })
            .item()
            .tag(ItemTags.STAIRS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> LIGHT_SLAB = REGISTRATE
            .block("light_slab", SlabBlock::new)
            .initialProperties(AllBlocks.LIGHT_BLOCK::get)
            .tag(BlockTags.SLABS)
            .blockstate((ctx, provider) ->
            {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/light_block"), provider.modLoc("block/light_block"));
            })
            .item()
            .tag(ItemTags.SLABS)
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<KeyholeBlock> LIMESTONE_KEYHOLE = REGISTRATE
            .block("limestone_keyhole", KeyholeBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .tag(Tags.Blocks.STONE)
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<KeyholeBlock> STONE_KEYHOLE = REGISTRATE
            .block("stone_keyhole", KeyholeBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .tag(Tags.Blocks.STONE)
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<EngravedLimestoneBlock> ENGRAVED_LIMESTONE = REGISTRATE
            .block("engraved_limestone", EngravedLimestoneBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .tag(Tags.Blocks.STONE)
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<SmartBlock> LIMESTONE_WALL_GUTTER = REGISTRATE
            .block("limestone_wall_gutter", properties ->
                    new SmartBlock(properties, new SmartBlock.SmartProperties()
                            .withHiddenNeighbourFaces(true, true, true, true, true, true)))
            .initialProperties(() -> Blocks.STONE)
            .blockstate(simpleCubeAll("limestone_wall_gutter"))
            .tag(Tags.Blocks.STONE)
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<UnlitTorchBlock> UNLIT_TORCH = REGISTRATE
            .block("unlit_torch", UnlitTorchBlock::new)
            .initialProperties(() -> Blocks.TORCH)
            .properties(properties -> properties
                    .lightLevel(blockstate -> 0)
                    .emissiveRendering((blockstate, blockGetter, pos) -> false))
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<Block> ENCRUSTED_CHISELED_LIMESTONE = REGISTRATE
            .block("encrusted_chiseled_limestone", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<RedstoneShifterBlock> REDSTONE_SHIFTER = REGISTRATE
            .block("redstone_shifter", RedstoneShifterBlock::new)
            .initialProperties(() -> Blocks.PISTON)
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build()
            .register();


    public static final BlockEntry<HiddenCobbledLimestoneButton> HIDDEN_COBBLED_LIMESTONE_BUTTON = REGISTRATE
            .block("hidden_cobbled_limestone_button", HiddenCobbledLimestoneButton::new)
            .initialProperties(() -> Blocks.STONE)
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<SmartBlock> LIMESTONE_RUMBLES = REGISTRATE
            .block("limestone_rumbles", p ->
                    new SmartBlock(p, new SmartBlock.SmartProperties()
                            .doNotShowNeighbourFaces()
                            .noShadow()
                            .shape(AllShapes.ofSize(0, 0, 0, 16, 4, 16))
                    ))
            .initialProperties(() -> Blocks.STONE)
            .properties(properties -> properties.noOcclusion().noCollission())
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    public static final BlockEntry<SunflowerCropBlock> SUNFLOWER_CROP = REGISTRATE
            .block("sunflower_crop", SunflowerCropBlock::new)
            .initialProperties(() -> Blocks.SUNFLOWER)
            .addLayer(() -> RenderType::cutout)
            .defaultLoot()
            .register();

    public static final BlockEntry<BalustradeBlock> LIMESTONE_BALUSTRADE = REGISTRATE
            .block("limestone_balustrade", properties ->
                    new BalustradeBlock(properties, new SmartBlock.SmartProperties()
                            .doNotShowNeighbourFaces()
                            .noShadow()
                    ))
            .initialProperties(() -> Blocks.STONE)
            .properties(properties -> properties.noOcclusion())
            .item()
            .tab(AllCreativeTabs.ANCIENT.getKey())
            .build()
            .register();

    /**
     * Loads this class
     */
    public static void register()
    {
    }
}
