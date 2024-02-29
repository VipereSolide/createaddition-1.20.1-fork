package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import com.rose.gods_retribution.content.block.*;
import com.rose.gods_retribution.content.block.engraved_blocks.EngravedLimestoneBlock;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlock;
import com.rose.gods_retribution.content.block.keyholes.KeyholeBlock;
import com.rose.gods_retribution.content.block.plastic_moss.PlasticMossBlock;
import com.rose.gods_retribution.content.block.waste_processor.WasteProcessorBlock;
import com.rose.gods_retribution.fundation.blocks.SmartBlock;
import com.rose.gods_retribution.fundation.blocks.VerticalSlabBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.client.model.generators.ModelFile;
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
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, Blocks.GOLD_BLOCK))
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
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, Blocks.GOLD_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<WallBlock> GOLD_WALL = REGISTRATE
            .block("gold_wall", WallBlock::new)
            .initialProperties(() -> Blocks.GOLD_BLOCK)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_IRON_TOOL,
                    BlockTags.GUARDED_BY_PIGLINS
            )
            .blockstate((ctx, provider) -> {
                //provider.wallBlock(ctx.getEntry(), "gold_wall", provider.blockTexture(Blocks.GOLD_BLOCK));
                provider.wallBlock(ctx.getEntry(), provider.blockTexture(Blocks.GOLD_BLOCK));
            })
            .item()
            .tag(
                    ItemTags.WALLS,
                    ItemTags.PIGLIN_LOVED
            )
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), new ResourceLocation("minecraft", "block/gold_block"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, Blocks.GOLD_BLOCK))
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
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, Blocks.DIAMOND_BLOCK))
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
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, Blocks.DIAMOND_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<WallBlock> DIAMOND_WALL = REGISTRATE
            .block("diamond_wall", WallBlock::new)
            .initialProperties(() -> Blocks.DIAMOND_BLOCK)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_IRON_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.wallBlock(ctx.getEntry(), provider.blockTexture(Blocks.DIAMOND_BLOCK));
            })
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), new ResourceLocation("minecraft", "block/diamond_block"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, Blocks.DIAMOND_BLOCK))
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
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, Blocks.IRON_BLOCK))
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
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, Blocks.IRON_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<WallBlock> IRON_WALL = REGISTRATE
            .block("iron_wall", WallBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_STONE_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.wallBlock(ctx.getEntry(), provider.blockTexture(Blocks.IRON_BLOCK));
            })
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), new ResourceLocation("minecraft", "block/iron_block"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, Blocks.IRON_BLOCK))
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
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, Blocks.LAPIS_BLOCK))
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
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, Blocks.LAPIS_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<WallBlock> LAPIS_WALL = REGISTRATE
            .block("lapis_wall", WallBlock::new)
            .initialProperties(() -> Blocks.LAPIS_BLOCK)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_STONE_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.wallBlock(ctx.getEntry(), provider.blockTexture(Blocks.LAPIS_BLOCK));
            })
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), new ResourceLocation("minecraft", "block/lapis_block"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, Blocks.LAPIS_BLOCK))
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
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, Blocks.EMERALD_BLOCK))
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
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, Blocks.EMERALD_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<WallBlock> EMERALD_WALL = REGISTRATE
            .block("emerald_wall", WallBlock::new)
            .initialProperties(() -> Blocks.EMERALD_BLOCK)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_IRON_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.wallBlock(ctx.getEntry(), provider.blockTexture(Blocks.EMERALD_BLOCK));
            })
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), new ResourceLocation("minecraft", "block/emerald_block"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, Blocks.EMERALD_BLOCK))
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
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, Blocks.NETHERITE_BLOCK))
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
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, Blocks.NETHERITE_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .tab(CreativeModeTabs.BUILDING_BLOCKS)
            .build().register();

    public static final BlockEntry<WallBlock> NETHERITE_WALL = REGISTRATE
            .block("netherite_wall", WallBlock::new)
            .initialProperties(() -> Blocks.NETHERITE_BLOCK)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.NEEDS_DIAMOND_TOOL
            )
            .blockstate((ctx, provider) -> {
                provider.wallBlock(ctx.getEntry(), provider.blockTexture(Blocks.NETHERITE_BLOCK));
            })
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), new ResourceLocation("minecraft", "block/netherite_block"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, Blocks.NETHERITE_BLOCK))
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
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, AllBlocks.WOOD))
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
            .recipe((ctx, c) -> makeSlabShapedRecipe(ctx, c, AllBlocks.WOOD))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> WOOD_WALL = REGISTRATE
            .block("wood_wall", WallBlock::new)
            .initialProperties(() -> AllBlocks.WOOD.get())
            .tag(
                    BlockTags.WALLS,
                    AllTags.Blocks.WOODY,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.wallBlock(ctx.getEntry(), provider.modLoc("block/wood"));
            })
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), provider.modLoc("block/wood"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, AllBlocks.WOOD))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> WOOD_VSLAB = REGISTRATE
            .block("wood_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.WOOD::get)
            .tag(
                    AllTags.Blocks.VERTICAL_SLABS,
                    AllTags.Blocks.WOODY,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((c, p) -> {
                VerticalSlabBlock.makeBlockstate(c, p, "block/wood");
            })
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> {
                VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.WOOD);
            })
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
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES, RecipeCategory.BUILDING_BLOCKS);
                makeStairsShapedRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES);
            })
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
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES, RecipeCategory.BUILDING_BLOCKS, 2);
                makeSlabShapedRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> INDUSTRIAL_IRON_SHINGLES_WALL = REGISTRATE
            .block("industrial_iron_shingles_wall", WallBlock::new)
            .initialProperties(AllBlocks.INDUSTRIAL_IRON_SHINGLES::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/industrial_iron_shingles")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/industrial_iron_shingles")))
            .recipe((ctx, cons) -> {
                makeWallShapedRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES);
                makeStonecuttingRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES, RecipeCategory.BUILDING_BLOCKS);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> INDUSTRIAL_IRON_SHINGLES_VSLAB = REGISTRATE
            .block("industrial_iron_shingles_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.INDUSTRIAL_IRON_SHINGLES::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/industrial_iron_shingles"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> {
                VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES);
                VerticalSlabBlock.makeStonecuttingRecipe(ctx, cons, AllBlocks.INDUSTRIAL_IRON_SHINGLES);
            })
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
            .blockstate((ctx, provider) -> provider
                    .stairsBlock(ctx.getEntry(), provider.modLoc("block/cracked_industrial_iron_shingles")))
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES, RecipeCategory.BUILDING_BLOCKS);
                makeStairsShapedRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> CRACKED_INDUSTRIAL_IRON_SHINGLES_SLAB = REGISTRATE
            .block("cracked_industrial_iron_shingles_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> provider.slabBlock(
                    ctx.getEntry(),
                    provider.modLoc("block/cracked_industrial_iron_shingles"),
                    provider.modLoc("block/cracked_industrial_iron_shingles")))
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES, RecipeCategory.BUILDING_BLOCKS, 2);
                makeSlabShapedRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> CRACKED_INDUSTRIAL_IRON_SHINGLES_WALL = REGISTRATE
            .block("cracked_industrial_iron_shingles_wall", WallBlock::new)
            .initialProperties(AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/cracked_industrial_iron_shingles")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/cracked_industrial_iron_shingles")))
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES, RecipeCategory.BUILDING_BLOCKS);
                makeWallShapedRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> CRACKED_INDUSTRIAL_IRON_SHINGLES_VSLAB = REGISTRATE
            .block("cracked_industrial_iron_shingles_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/cracked_industrial_iron_shingles"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> {
                VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES);
                VerticalSlabBlock.makeStonecuttingRecipe(ctx, cons, AllBlocks.CRACKED_INDUSTRIAL_IRON_SHINGLES);
            })
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

    public static final BlockEntry<WasteProcessorBlock> WASTE_PROCESSOR = REGISTRATE
            .block("waste_processor", WasteProcessorBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p
                    .noOcclusion()
                    .mapColor(MapColor.PODZOL))
            .tag(
                    com.simibubi.create.AllTags.AllBlockTags.SAFE_NBT.tag,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.MINEABLE_WITH_AXE
            )
            .transform(BlockStressDefaults.setImpact(4))
            .blockstate((ctx, provider) -> {
                provider.horizontalBlock(ctx.getEntry(), new ModelFile.UncheckedModelFile(provider.modLoc("block/waste_processor")));
            })
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build().register();

    public static final BlockEntry<Block> AERATION_BLOCK = REGISTRATE
            .block("aeration_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<StairBlock> AERATION_STAIRS = REGISTRATE
            .block("aeration_stairs", (p) -> new StairBlock(AllBlocks.AERATION_BLOCK.getDefaultState(), p))
            .initialProperties(AllBlocks.AERATION_BLOCK::get)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/aeration_block")))
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> {
                makeStairsShapedRecipe(ctx, cons, AllBlocks.AERATION_BLOCK);
                makeStonecuttingRecipe(ctx, cons, AllBlocks.AERATION_BLOCK, RecipeCategory.BUILDING_BLOCKS);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> AERATION_SLAB = REGISTRATE
            .block("aeration_slab", SlabBlock::new)
            .initialProperties(AllBlocks.AERATION_BLOCK::get)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.slabBlock(ctx.getEntry(), provider.modLoc("block/aeration_block"), provider.modLoc("block/aeration_block")))
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.AERATION_BLOCK, RecipeCategory.BUILDING_BLOCKS, 2);
                makeSlabShapedRecipe(ctx, cons, AllBlocks.AERATION_BLOCK);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> AERATION_WALL = REGISTRATE
            .block("aeration_wall", WallBlock::new)
            .initialProperties(AllBlocks.AERATION_BLOCK::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/aeration_block")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/aeration_block")))
            .recipe((ctx, cons) -> {
                makeWallShapedRecipe(ctx, cons, AllBlocks.AERATION_BLOCK);
                makeStonecuttingRecipe(ctx, cons, AllBlocks.AERATION_BLOCK, RecipeCategory.BUILDING_BLOCKS);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> AERATION_VSLAB = REGISTRATE
            .block("aeration_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(() -> AllBlocks.AERATION_BLOCK.get())
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((ctx, provider) -> VerticalSlabBlock.makeBlockstate(ctx, provider, "block/aeration_block"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, consumer) -> {
                VerticalSlabBlock.makeShapedRecipe(ctx, consumer, AllBlocks.AERATION_BLOCK);
                VerticalSlabBlock.makeStonecuttingRecipe(ctx, consumer, AllBlocks.AERATION_BLOCK);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

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
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/steel_block")))
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.STEEL_BLOCK, RecipeCategory.BUILDING_BLOCKS);
                makeStairsShapedRecipe(ctx, cons, AllBlocks.STEEL_BLOCK);
            })
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
            .blockstate((ctx, provider) -> provider.slabBlock(ctx.getEntry(), provider.modLoc("block/steel_block"), provider.modLoc("block/steel_block")))
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.STEEL_BLOCK, RecipeCategory.BUILDING_BLOCKS, 2);
                makeSlabShapedRecipe(ctx, cons, AllBlocks.STEEL_BLOCK);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> STEEL_WALL = REGISTRATE
            .block("steel_wall", WallBlock::new)
            .initialProperties(AllBlocks.STEEL_BLOCK::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/steel_block")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/steel_block")))
            .recipe((ctx, cons) -> {
                makeWallShapedRecipe(ctx, cons, AllBlocks.STEEL_BLOCK);
                makeStonecuttingRecipe(ctx, cons, AllBlocks.STEEL_BLOCK, RecipeCategory.BUILDING_BLOCKS);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> STEEL_VSLAB = REGISTRATE
            .block("steel_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.STEEL_BLOCK::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/steel_block"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> {
                VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.STEEL_BLOCK);
                VerticalSlabBlock.makeStonecuttingRecipe(ctx, cons, AllBlocks.STEEL_BLOCK);
            })
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
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/tiles_bathroom_block")))
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> {
                makeStairsShapedRecipe(ctx, cons, AllBlocks.TILES_BATHROOM);
                makeStonecuttingRecipe(ctx, cons, AllBlocks.TILES_BATHROOM, RecipeCategory.BUILDING_BLOCKS);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> TILES_BATHROOM_SLAB = REGISTRATE
            .block("tiles_bathroom_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.SANDSTONE_SLAB)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, p) -> p.slabBlock(ctx.getEntry(), p.modLoc("block/tiles_bathroom_block"), p.modLoc("block/" + ctx.getName())))
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> {
                makeSlabShapedRecipe(ctx, cons, AllBlocks.TILES_BATHROOM);
                makeStonecuttingRecipe(ctx, cons, AllBlocks.TILES_BATHROOM, RecipeCategory.BUILDING_BLOCKS, 2);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> TILES_BATHROOM_WALL = REGISTRATE
            .block("tiles_bathroom_wall", WallBlock::new)
            .initialProperties(AllBlocks.TILES_BATHROOM::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/tiles_bathroom_block")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/tiles_bathroom_block")))
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, AllBlocks.TILES_BATHROOM))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> TILES_BATHROOM_VSLAB = REGISTRATE
            .block("tiles_bathroom_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.TILES_BATHROOM::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/tiles_bathroom_block"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> {
                VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.TILES_BATHROOM);
                VerticalSlabBlock.makeStonecuttingRecipe(ctx, cons, AllBlocks.TILES_BATHROOM);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> FLINT_BLOCK = REGISTRATE
            .block("flint_block", Block::new)
            .initialProperties(() -> Blocks.GRAVEL)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .tag(
                    BlockTags.MINEABLE_WITH_SHOVEL,
                    Tags.Blocks.STORAGE_BLOCKS,
                    AllTags.Blocks.FORGE_STORAGE_BLOCKS_FLINT,
                    AllTags.Blocks.WASTE
            )
            .item()
            .tag(
                    Tags.Items.STORAGE_BLOCKS,
                    AllTags.Items.FORGE_STORAGE_BLOCKS_FLINT,
                    AllTags.Items.WASTE
            )
            .recipe((ctx, cons) -> makePackingShapedRecipe3(ctx, cons, Items.FLINT))
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
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/flint_block")))
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, AllBlocks.FLINT_BLOCK))
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
            .blockstate((ctx, provider) -> provider.slabBlock(ctx.getEntry(), provider.modLoc("block/flint_block"), provider.modLoc("block/flint_block")))
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, AllBlocks.FLINT_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> FLINT_WALL = REGISTRATE
            .block("flint_wall", WallBlock::new)
            .initialProperties(AllBlocks.FLINT_BLOCK::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_SHOVEL
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/flint_block")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/flint_block")))
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, AllBlocks.FLINT_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> FLINT_VSLAB = REGISTRATE
            .block("flint_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.FLINT_BLOCK::get)
            .tag(
                    BlockTags.MINEABLE_WITH_SHOVEL,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/flint_block"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.FLINT_BLOCK))
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
            .tag(AllTags.Items.CONCRETE)
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
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/concrete")))
            .item()
            .tag(
                    ItemTags.STAIRS,
                    AllTags.Items.CONCRETE
            )
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE, RecipeCategory.BUILDING_BLOCKS);
                makeStairsShapedRecipe(ctx, cons, AllBlocks.CONCRETE);
            })
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
            .blockstate((ctx, provider) -> provider.slabBlock(
                    ctx.getEntry(),
                    provider.modLoc("block/concrete"),
                    provider.modLoc("block/concrete")))
            .item()
            .tag(
                    ItemTags.SLABS,
                    AllTags.Items.CONCRETE
            )
            .recipe((ctx, cons) -> {
                makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE, RecipeCategory.BUILDING_BLOCKS, 2);
                makeSlabShapedRecipe(ctx, cons, AllBlocks.CONCRETE);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> CONCRETE_WALL = REGISTRATE
            .block("concrete_wall", WallBlock::new)
            .initialProperties(AllBlocks.CONCRETE::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.CONCRETE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/concrete")))
            .item()
            .tag(
                    ItemTags.WALLS,
                    AllTags.Items.CONCRETE
            )
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/concrete")))
            .recipe((ctx, cons) -> {
                makeWallShapedRecipe(ctx, cons, AllBlocks.CONCRETE);
                makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE, RecipeCategory.BUILDING_BLOCKS);
            })
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> CONCRETE_VSLAB = REGISTRATE
            .block("concrete_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.CONCRETE::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS,
                    AllTags.Blocks.CONCRETE
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/concrete"))
            .item()
            .tag(
                    AllTags.Items.VERTICAL_SLABS,
                    AllTags.Items.CONCRETE
            )
            .recipe((ctx, cons) -> {
                VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.CONCRETE);
                VerticalSlabBlock.makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE);
            })
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
                .tag(AllTags.Items.CONCRETE)
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
                .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/concrete_" + colourName)))
                .item()
                .tag(
                        ItemTags.STAIRS,
                        AllTags.Items.CONCRETE
                )
                .recipe((ctx, cons) -> {
                    makeStairsShapedRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour));
                    makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour), RecipeCategory.BUILDING_BLOCKS);
                })
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
                .blockstate((ctx, provider) -> provider.slabBlock(
                        ctx.getEntry(),
                        provider.modLoc("block/concrete_" + colourName),
                        provider.modLoc("block/concrete_" + colourName)))
                .item()
                .tag(
                        ItemTags.SLABS,
                        AllTags.Items.CONCRETE
                )
                .recipe((ctx, cons) -> {
                    makeSlabShapedRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour));
                    makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour), RecipeCategory.BUILDING_BLOCKS, 2);
                })
                .tab(AllCreativeTabs.DECORATION.getKey())
                .build().register();
    });

    public static final DyedBlockList<WallBlock> CONCRETE_COLOURS_WALLS = new DyedBlockList<>(colour -> {
        String colourName = colour.getSerializedName();
        return REGISTRATE
                .block("concrete_" + colourName + "_wall", WallBlock::new)
                .initialProperties(AllBlocks.CONCRETE_WALL::get)
                .properties(p -> p.mapColor(colour))
                .tag(
                        BlockTags.WALLS,
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        AllTags.Blocks.CONCRETE
                )
                .blockstate((ctx, provider) -> provider.wallBlock(
                        ctx.getEntry(),
                        provider.modLoc("block/concrete_" + colourName)))
                .item()
                .tag(
                        ItemTags.WALLS,
                        AllTags.Items.CONCRETE
                )
                .model((ctx, provider) -> provider.wallInventory(
                        ctx.getName(),
                        provider.modLoc("block/concrete_" + colourName)))
                .recipe((ctx, cons) -> {
                    makeWallShapedRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour));
                    makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour), RecipeCategory.BUILDING_BLOCKS);
                })
                .tab(AllCreativeTabs.DECORATION.getKey())
                .build().register();
    });

    public static final DyedBlockList<VerticalSlabBlock> CONCRETE_COLOUR_VLSABS = new DyedBlockList<>(colour -> {
        String colourName = colour.getSerializedName();
        return REGISTRATE
                .block("concrete_" + colourName + "_vertical_slab", VerticalSlabBlock::new)
                .initialProperties(AllBlocks.CONCRETE_COLOURS.get(colour)::get)
                .tag(
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        AllTags.Blocks.CONCRETE,
                        AllTags.Blocks.VERTICAL_SLABS
                )
                .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/concrete_" + colourName))
                .item()
                .tag(
                        AllTags.Items.CONCRETE,
                        AllTags.Items.VERTICAL_SLABS
                )
                .recipe((ctx, cons) -> {
                    VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour));
                    VerticalSlabBlock.makeStonecuttingRecipe(ctx, cons, AllBlocks.CONCRETE_COLOURS.get(colour));
                })
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
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/clear_glass")))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, AllBlocks.CLEAR_GLASS))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> CLEAR_GLASS_SLAB = REGISTRATE
            .block("clear_glass_slab", SlabBlock::new)
            .initialProperties(AllBlocks.CLEAR_GLASS::get)
            .tag(BlockTags.SLABS)
            .blockstate((ctx, provider) -> provider.slabBlock(
                    ctx.getEntry(),
                    provider.modLoc("block/clear_glass"),
                    provider.modLoc("block/clear_glass")))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, AllBlocks.CLEAR_GLASS))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> CLEAR_GLASS_WALL = REGISTRATE
            .block("clear_glass_wall", WallBlock::new)
            .initialProperties(AllBlocks.CLEAR_GLASS::get)
            .tag(BlockTags.WALLS)
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/clear_glass")))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/clear_glass")))
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, AllBlocks.CLEAR_GLASS))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> CLEAR_GLASS_VSLAB = REGISTRATE
            .block("clear_glass_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.CLEAR_GLASS::get)
            .tag(AllTags.Blocks.VERTICAL_SLABS)
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/clear_glass"))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.CLEAR_GLASS))
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
            .recipe((ctx, cons) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.getEntry())
                    .pattern("aaa")
                    .pattern("aba")
                    .pattern("aaa")
                    .define('a', Blocks.IRON_BARS)
                    .define('b', Items.IRON_INGOT)
                    .unlockedBy("has_iron_bars", cons.has(Blocks.IRON_BARS))
                    .unlockedBy("has_iron_ingot", cons.has(Items.IRON_INGOT))
                    .group(ctx.getName())
                    .save(cons))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<StairBlock> METAL_WIREMESH_STAIRS = REGISTRATE
            .block("metal_wiremesh_stairs", (p) -> new StairBlock(AllBlocks.METAL_WIREMESH_BLOCK.getDefaultState(), p))
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK::get)
            .tag(
                    BlockTags.STAIRS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block")))
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> METAL_WIREMESH_SLAB = REGISTRATE
            .block("metal_wiremesh_slab", SlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK::get)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.slabBlock(
                    ctx.getEntry(),
                    provider.modLoc("block/metal_wiremesh_block"),
                    provider.modLoc("block/metal_wiremesh_block")))
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> METAL_WIREMESH_WALL = REGISTRATE
            .block("metal_wiremesh_wall", WallBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/metal_wiremesh_block")))
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> METAL_WIREMESH_VSLAB = REGISTRATE
            .block("metal_wiremesh_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/metal_wiremesh_block"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK))
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
            .recipe((ctx, cons) -> makePackingShapedRecipe3(ctx, cons, Blocks.IRON_BARS))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<StairBlock> METAL_WIREMESH_STAIRS_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_stairs_transparent", (p) -> new StairBlock(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT.getDefaultState(), p))
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.STAIRS
            )
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block_transparent")))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.STAIRS)
            .recipe((ctx, cons) -> makeStairsShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> METAL_WIREMESH_SLAB_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_slab_transparent", SlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> provider.slabBlock(
                    ctx.getEntry(),
                    provider.modLoc("block/metal_wiremesh_block_transparent"),
                    provider.modLoc("block/metal_wiremesh_block_transparent")))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.SLABS)
            .recipe((ctx, cons) -> makeSlabShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<WallBlock> METAL_WIREMESH_WALL_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_wall_transparent", WallBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT::get)
            .tag(
                    BlockTags.WALLS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> provider.wallBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block_transparent")))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> provider.wallInventory(ctx.getName(), provider.modLoc("block/metal_wiremesh_block_transparent")))
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> METAL_WIREMESH_VSLAB_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_vertical_slab_transparent", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    AllTags.Blocks.VERTICAL_SLABS
            )
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/metal_wiremesh_block_transparent"))
            .addLayer(() -> RenderType::cutout)
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT))
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

    public static final BlockEntry<WallBlock> LIGHT_WALL = REGISTRATE
            .block("light_wall", WallBlock::new)
            .initialProperties(AllBlocks.LIGHT_BLOCK::get)
            .tag(BlockTags.WALLS)
            .blockstate((ctx, provider) -> {
                provider.wallBlock(ctx.getEntry(), provider.modLoc("block/light_block"));
            })
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, provider) -> {
                provider.wallInventory(ctx.getName(), provider.modLoc("block/light_block"));
            })
            .recipe((ctx, cons) -> makeWallShapedRecipe(ctx, cons, AllBlocks.LIGHT_BLOCK))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<VerticalSlabBlock> LIGHT_VSLAB = REGISTRATE
            .block("light_vertical_slab", VerticalSlabBlock::new)
            .initialProperties(AllBlocks.LIGHT_BLOCK::get)
            .tag(AllTags.Blocks.VERTICAL_SLABS)
            .blockstate((c, p) -> VerticalSlabBlock.makeBlockstate(c, p, "block/light_block"))
            .item()
            .tag(AllTags.Items.VERTICAL_SLABS)
            .recipe((ctx, cons) -> VerticalSlabBlock.makeShapedRecipe(ctx, cons, AllBlocks.LIGHT_BLOCK))
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

    public static final BlockEntry<Block> WASTE_BLOCK = REGISTRATE
            .block("waste_block", Block::new)
            .initialProperties(() -> Blocks.DIRT)
            .tag(
                    Tags.Blocks.STORAGE_BLOCKS,
                    AllTags.Blocks.FORGE_STORAGE_BLOCKS_WASTE,
                    AllTags.Blocks.WASTE
            )
            .item()
            .tag(
                    Tags.Items.STORAGE_BLOCKS,
                    AllTags.Items.FORGE_STORAGE_BLOCKS_WASTE,
                    AllTags.Items.WASTE
            )
            .recipe((ctx, cons) -> makePackingShapedRecipe3(ctx, cons, AllItems.WASTE, RecipeCategory.MISC))
            .tab(AllCreativeTabs.MAIN.getKey())
            .build().register();

    public static final BlockEntry<FallingBlock> ASHES_BLOCK = REGISTRATE
            .block("ashes_block", FallingBlock::new)
            .initialProperties(() -> Blocks.SAND)
            .tag(
                    AllTags.Blocks.WASTE,
                    AllTags.Blocks.FORGE_STORAGE_BLOCKS_WASTE,
                    AllTags.Blocks.FORGE_STORAGE_BLOCKS_ASHES,
                    Tags.Blocks.STORAGE_BLOCKS,
                    BlockTags.MINEABLE_WITH_SHOVEL
            )
            .item()
            .tag(
                    AllTags.Items.WASTE,
                    AllTags.Items.FORGE_STORAGE_BLOCKS_WASTE,
                    AllTags.Items.FORGE_STORAGE_BLOCKS_ASHES,
                    Tags.Items.STORAGE_BLOCKS
            )
            .recipe((ctx, cons) -> makePackingShapedRecipe3(ctx, cons, AllItems.ASHES))
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    // blocks depending on other mods presence. Created here, initialized in register()
    public static BlockEntry<VerticalSlabBlock> MEADOW_LIMESTONE_BRICKS_VSLAB;
    public static BlockEntry<StairBlock> MEADOW_POLISHED_LIMESTONE_STAIRS;
    public static BlockEntry<SlabBlock> MEADOW_POLISHED_LIMESTONE_SLAB;
    public static BlockEntry<WallBlock> MEADOW_POLISHED_LIMESTONE_WALL;
    public static BlockEntry<VerticalSlabBlock> MEADOW_POLISHED_LIMESTONE_VSLAB;

    /**
     * Loads this class.
     * <p>
     * The blocks depending on the optional presence of other mods will be initialized here. That way, they will be in
     * the game only if the corresponding mod is present.
     */
    public static void register()
    {
        if (GodsRetribution.MEADOW_ACTIVE)
        {
            // registering the blocks depending on Let's Do Meadow mod
            MEADOW_LIMESTONE_BRICKS_VSLAB = REGISTRATE
                    .block("meadow_limestone_bricks_vertical_slab", VerticalSlabBlock::new)
                    .initialProperties(() -> Blocks.STONE_BRICKS)
                    .tag(
                            AllTags.Blocks.VERTICAL_SLABS,
                            AllTags.Blocks.COMPATIBILITY,
                            AllTags.Blocks.COMPATIBILITY_MEADOW,
                            BlockTags.MINEABLE_WITH_PICKAXE
                    )
                    .blockstate(simpleCubeAll("waste_block")) // will be overrode by the blockstate in main. Just here for the datagen not to shoot itself in the mouth because the texture does not exists
                    .item()
                    .tag(
                            AllTags.Items.VERTICAL_SLABS,
                            AllTags.Items.COMPATIBILITY,
                            AllTags.Items.COMPATIBILITY_MEADOW
                    )
                    .tab(AllCreativeTabs.ANCIENT.getKey())
                    .build().register();

            MEADOW_POLISHED_LIMESTONE_STAIRS = REGISTRATE
                    .block("meadow_polished_limestone_stairs", (p) -> new StairBlock(AllBlocks.WOOD.getDefaultState(), p))
                    .initialProperties(() -> Blocks.STONE_BRICKS)
                    .tag(
                            BlockTags.MINEABLE_WITH_PICKAXE,
                            BlockTags.STAIRS,
                            AllTags.Blocks.COMPATIBILITY,
                            AllTags.Blocks.COMPATIBILITY_MEADOW
                    )
                    .blockstate(simpleCubeAll("waste_block"))
                    .item()
                    .tag(
                            ItemTags.STAIRS,
                            AllTags.Items.COMPATIBILITY,
                            AllTags.Items.COMPATIBILITY_MEADOW
                    )
                    .tab(AllCreativeTabs.ANCIENT.getKey())
                    .build().register();

            MEADOW_POLISHED_LIMESTONE_SLAB = REGISTRATE
                    .block("meadow_polished_limestone_slab", SlabBlock::new)
                    .initialProperties(() -> Blocks.STONE_BRICKS)
                    .tag(
                            BlockTags.SLABS,
                            BlockTags.MINEABLE_WITH_PICKAXE,
                            AllTags.Blocks.COMPATIBILITY,
                            AllTags.Blocks.COMPATIBILITY_MEADOW
                    )
                    .blockstate(simpleCubeAll("waste_block"))
                    .item()
                    .tag(
                            ItemTags.SLABS,
                            AllTags.Items.COMPATIBILITY,
                            AllTags.Items.COMPATIBILITY_MEADOW
                    )
                    .tab(AllCreativeTabs.ANCIENT.getKey())
                    .build().register();

            MEADOW_POLISHED_LIMESTONE_WALL = REGISTRATE
                    .block("meadow_polished_limestone_wall", WallBlock::new)
                    .initialProperties(() -> Blocks.STONE_BRICKS)
                    .tag(
                            BlockTags.WALLS,
                            BlockTags.MINEABLE_WITH_PICKAXE,
                            AllTags.Blocks.COMPATIBILITY,
                            AllTags.Blocks.COMPATIBILITY_MEADOW
                    )
                    .blockstate(simpleCubeAll("waste_block"))
                    .item()
                    .tag(
                            ItemTags.WALLS,
                            AllTags.Items.COMPATIBILITY,
                            AllTags.Items.COMPATIBILITY_MEADOW
                    )
                    .tab(AllCreativeTabs.ANCIENT.getKey())
                    .build().register();

            MEADOW_POLISHED_LIMESTONE_VSLAB = REGISTRATE
                    .block("meadow_polished_limestone_vertical_slab", VerticalSlabBlock::new)
                    .initialProperties(() -> Blocks.STONE_BRICKS)
                    .tag(
                            BlockTags.MINEABLE_WITH_PICKAXE,
                            AllTags.Blocks.VERTICAL_SLABS,
                            AllTags.Blocks.COMPATIBILITY,
                            AllTags.Blocks.COMPATIBILITY_MEADOW
                    )
                    .blockstate(simpleCubeAll("waste_block"))
                    .item()
                    .tag(
                            AllTags.Items.VERTICAL_SLABS,
                            AllTags.Items.COMPATIBILITY,
                            AllTags.Items.COMPATIBILITY_MEADOW
                    )
                    .tab(AllCreativeTabs.ANCIENT.getKey())
                    .build().register();
        }
    }

    /**
     * Shortcut for the datagen of the wall blocks' crafting table recipe.
     *
     * @param context
     * @param consumer
     * @param madeOf
     */
    public static void makeWallShapedRecipe(DataGenContext<Item, BlockItem> context, RegistrateRecipeProvider consumer, ItemLike madeOf)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, context.getEntry(), 6)
                .pattern("###")
                .pattern("###")
                .define('#', madeOf)
                .unlockedBy("has_" + consumer.safeName(madeOf), consumer.has(madeOf))
                .save(consumer);
    }

    /**
     * Shortcut for the datagen of the stairs' crafting table recipe.
     *
     * @param context
     * @param consumer
     * @param madeOf
     */
    public static void makeStairsShapedRecipe(DataGenContext<Item, BlockItem> context, RegistrateRecipeProvider consumer, ItemLike madeOf)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, context.getEntry(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', madeOf)
                .unlockedBy("has_" + consumer.safeName(madeOf), consumer.has(madeOf))
                .save(consumer);
    }

    /**
     * Shortcut for the datagen of the stairs' crafting table recipe.
     *
     * @param context
     * @param consumer
     * @param madeOf
     */
    public static void makeSlabShapedRecipe(DataGenContext<Item, BlockItem> context, RegistrateRecipeProvider consumer, ItemLike madeOf)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, context.getEntry(), 6)
                .pattern("###")
                .define('#', madeOf)
                .unlockedBy("has_" + consumer.safeName(madeOf), consumer.has(madeOf))
                .save(consumer);
    }

    /**
     * Shortcut for the datagen of a 3x3 packing recipe (like 9 iron ingots to 1 iron block, for instance). The recipe
     * must be used on an {@code ItemBuilder} and not on a {@code BlockBuilder}. Use it like this :
     * <p>
     * <pre>
     * REGISTRATE.block(...)
     * ...
     * .item()
     * ...
     * .recipe((ctx, consumer) -> makePackingShapedRecipe3(ctx, consumer, AllItems.AN_ITEM, RecipeCategory.SOMETHING))
     * </pre>
     * </p>
     *
     * @param context
     * @param consumer
     * @param madeOf
     * @param category
     */
    public static void makePackingShapedRecipe3(DataGenContext<Item, BlockItem> context,
                                                RegistrateRecipeProvider consumer,
                                                ItemLike madeOf,
                                                RecipeCategory category)
    {
        ShapedRecipeBuilder.shaped(category, context.getEntry())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', madeOf)
                .unlockedBy("has_" + consumer.safeName(madeOf), consumer.has(madeOf))
                .save(consumer);
    }

    /**
     * The default category is {@code BUILDING_BLOCKS}.
     *
     * @param context
     * @param consumer
     * @param madeOf
     */
    public static void makePackingShapedRecipe3(DataGenContext<Item, BlockItem> context,
                                                RegistrateRecipeProvider consumer,
                                                ItemLike madeOf)
    {
        makePackingShapedRecipe3(context, consumer, madeOf, RecipeCategory.BUILDING_BLOCKS);
    }

    /**
     * Same thing that the {@code makePackingShapedRecipe3} but in a 2x2 grid.
     *
     * @param context
     * @param consumer
     * @param madeOf
     * @param category
     */
    public static void makePackingShapedRecipe2(DataGenContext<Item, BlockItem> context,
                                                RegistrateRecipeProvider consumer,
                                                ItemLike madeOf,
                                                RecipeCategory category)
    {
        ShapedRecipeBuilder.shaped(category, context.getEntry())
                .pattern("##")
                .pattern("##")
                .define('#', madeOf)
                .unlockedBy("has_" + consumer.safeName(madeOf), consumer.has(madeOf))
                .save(consumer);
    }

    /**
     * The default category is {@code BUILDING_BLOCKS}.
     *
     * @param context
     * @param consumer
     * @param madeOf
     */
    public static void makePackingShapedRecipe2(DataGenContext<Item, BlockItem> context,
                                                RegistrateRecipeProvider consumer,
                                                ItemLike madeOf)
    {
        makePackingShapedRecipe2(context, consumer, madeOf, RecipeCategory.BUILDING_BLOCKS);
    }

    /**
     * Shortcut for the datagen, for stonecutting recipes.
     *
     * @param context
     * @param consumer
     * @param madeOf
     * @param recipeCategory
     * @param resultCount
     */
    public static void makeStonecuttingRecipe(DataGenContext<Item, BlockItem> context, RegistrateRecipeProvider consumer, ItemLike madeOf, RecipeCategory recipeCategory, int resultCount)
    {
        consumer.stonecutting(
                DataIngredient.ingredient(
                        Ingredient.of(() -> madeOf.asItem()),
                        () -> madeOf.asItem()),
                recipeCategory,
                context::getEntry,
                resultCount);
    }

    /**
     * Shortcut for the datagen, for stonecutting recipes.
     * @param context
     * @param consumer
     * @param madeOf
     * @param recipeCategory
     */
    public static void makeStonecuttingRecipe(DataGenContext<Item, BlockItem> context, RegistrateRecipeProvider consumer, ItemLike madeOf, RecipeCategory recipeCategory)
    {
        makeStonecuttingRecipe(context, consumer, madeOf, recipeCategory, 1);
    }
}
