package com.rose.gods_retribution.content;

import com.mojang.datafixers.TypeRewriteRule;
import com.rose.gods_retribution.content.block.AirVentBlock;
import com.rose.gods_retribution.content.block.AutomatedRedstoneSwitch;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlock;
import com.rose.gods_retribution.content.block.plastic_moss.PlasticMossBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.Tags;
import net.minecraft.world.level.material.MapColor;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;
import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.TagGen.*;

/**
 * Contains all the mod's blocks.
 *
 * The new blocks are to be registered in here, as a public static final BlockEntry
 */
public class AllBlocks
{
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
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/wood"), provider.modLoc("block/wood"));
            })
            .transform(pickaxeOnly())
            .item()
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

    public static final BlockEntry<SlabBlock> INDUSTRIAL_IRON_SHINGLES_SLAB = REGISTRATE
            .block("industrial_iron_shingles_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/industrial_iron_shingles"), provider.modLoc("block/industrial_iron_shingles"));
            })
            .item()
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
    public static final BlockEntry<SlabBlock> CRACKED_INDUSTRIAL_IRON_SHINGLES_SLAB = REGISTRATE
            .block("cracked_industrial_iron_shingles_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/cracked_industrial_iron_shingles"), provider.modLoc("block/cracked_industrial_iron_shingles"));
            })
            .item()
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

    public static final BlockEntry<SlabBlock> STEEL_SLAB = REGISTRATE
            .block("steel_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.requiresCorrectToolForDrops())
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/steel_block"), provider.modLoc("block/steel_block"));
            })
            .item()
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

    public static final BlockEntry<SlabBlock> TILES_BATHROOM_SLAB = REGISTRATE
            .block("tiles_bathroom_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.SANDSTONE_SLAB)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, p) -> {
                p.slabBlock(ctx.getEntry(), p.modLoc("block/tiles_bathroom_block"), p.modLoc("block/" + ctx.getName()));
            })
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> FLINT_BLOCK = REGISTRATE
            .block("flint_block", Block::new)
            .initialProperties(() -> Blocks.GRAVEL)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .tag(BlockTags.MINEABLE_WITH_SHOVEL)
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build()
            .register();

    public static final BlockEntry<SlabBlock> FLINT_SLAB = REGISTRATE
            .block("flint_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.GRAVEL)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .tag(
                    BlockTags.MINEABLE_WITH_SHOVEL,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/flint_block"), provider.modLoc("block/flint_block"));
            })
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> CONCRETE = REGISTRATE
            .block("concrete", Block::new)
            .initialProperties(() -> Blocks.WHITE_CONCRETE)
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build()
            .register();

    public static final BlockEntry<SlabBlock> CONCRETE_SLAB = REGISTRATE
            .block("concrete_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.WHITE_CONCRETE)
            .properties(p -> p.mapColor(MapColor.COLOR_LIGHT_GRAY))
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/concrete"), provider.modLoc("block/concrete"));
            })
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final DyedBlockList<Block> CONCRETE_COLOURS = new DyedBlockList<>(colour ->
    {
        String colourName = colour.getSerializedName();
        return REGISTRATE
                .block("concrete_" + colourName, Block::new)
                .initialProperties(AllBlocks.CONCRETE::get)
                .properties(p -> p.mapColor(colour))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item()
                .tab(AllCreativeTabs.DECORATION.getKey())
                .build()
                .register();
    });

    public static final DyedBlockList<SlabBlock> CONCRETE_COLOURS_SLABS = new DyedBlockList<>(colour -> {
        String colourName = colour.getSerializedName();
        return REGISTRATE
                .block("concrete_" + colourName + "_slab", SlabBlock::new)
                .initialProperties(AllBlocks.CONCRETE_SLAB::get)
                .properties(p -> p.mapColor(colour))
                .tag(
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        BlockTags.SLABS
                )
                .blockstate((ctx, provider) -> {
                    provider.slabBlock(ctx.getEntry(), provider.modLoc("block/concrete_" + colourName), provider.modLoc("block/concrete_" + colourName));
                })
                .item()
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

    public static final BlockEntry<AutomatedRedstoneSwitch> AUTOMATED_REDSTONE_SWITCH = REGISTRATE
            .block("automated_redstone_switch", AutomatedRedstoneSwitch::new)
            .initialProperties(() -> com.simibubi.create.AllBlocks.ANDESITE_CASING.get())
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build()
            .register();

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

    public static final BlockEntry<SlabBlock> METAL_WIREMESH_SLAB = REGISTRATE
            .block("metal_wiremesh_slab", SlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK::get)
            .tag(
                    BlockTags.SLABS,
                    BlockTags.MINEABLE_WITH_PICKAXE
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block"), provider.modLoc("block/metal_wiremesh_block"));
            })
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<Block> METAL_WIREMESH_BLOCK_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_block_transparent", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .requiresCorrectToolForDrops()
                    .noOcclusion())
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .addLayer(() -> RenderType::cutout)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    public static final BlockEntry<SlabBlock> METAL_WIREMESH_SLAB_TRANSPARENT = REGISTRATE
            .block("metal_wiremesh_slab_transparent", SlabBlock::new)
            .initialProperties(AllBlocks.METAL_WIREMESH_BLOCK_TRANSPARENT::get)
            .tag(
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.SLABS
            )
            .blockstate((ctx, provider) -> {
                provider.slabBlock(ctx.getEntry(), provider.modLoc("block/metal_wiremesh_block_transparent"), provider.modLoc("block/metal_wiremesh_block_transparent"));
            })
            .addLayer(() -> RenderType::cutout)
            .item()
            .tab(AllCreativeTabs.DECORATION.getKey())
            .build().register();

    /**
     * Loads this class
     */
    public static void register()
    {
    }
}
