package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.AirVentBlock;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlock;
import com.rose.gods_retribution.content.block.plastic_moss.PlasticMossBlock;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraft.world.level.material.MapColor;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;
import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;

public class AllBlocks
{
    static
    {
        REGISTRATE.setCreativeTab(AllCreativeTabs.MAIN);
    }

    public static final BlockEntry<Block> WOOD = REGISTRATE.block("wood", Block::new)
            .initialProperties(() -> Blocks.DIRT)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW).requiresCorrectToolForDrops())
            .transform(pickaxeOnly())
            .blockstate(simpleCubeAll("brass_block"))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.STORAGE_BLOCKS)
            .tag(BlockTags.BEACON_BASE_BLOCKS)
            .transform(tagBlockAndItem("storage_blocks/brass"))
            .tag(Tags.Items.STORAGE_BLOCKS)
            .tab(AllCreativeTabs.MAIN.getKey())
            .build()
            .register();

    public static final BlockEntry<PlasticMossBlock> PLASTIC_MOSS =
            REGISTRATE.block("plastic_moss", PlasticMossBlock::new)
                    .initialProperties(() -> Blocks.WHITE_WOOL)
                    .properties(p -> p.mapColor(MapColor.COLOR_YELLOW))
                    .blockstate(simpleCubeAll("plastic_moss"))
                    .item()
                    .tab(AllCreativeTabs.MAIN.getKey())
                    .build()
                    .register();

    public static final BlockEntry<LabellingMachineBlock> LABELLING_MACHINE =
            REGISTRATE.block("labelling_machine", LabellingMachineBlock::new)
                    .initialProperties(SharedProperties::softMetal)
                    .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag) // Dunno what this tag means (contraption safe?).
                    .transform(BlockStressDefaults.setImpact(4))
                    .item()
                    .tab(AllCreativeTabs.MAIN.getKey())
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<Block> AERATION_BLOCK = REGISTRATE.block("aeration_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build().register();

    public static final BlockEntry<AirVentBlock> AIR_VENT = REGISTRATE.block("air_vent", AirVentBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion())
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build().register();

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE.block("steel_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build().register();

    public static final BlockEntry<Block> TILES_BATHROOM = REGISTRATE.block("tiles_bathroom_block", Block::new)
            .initialProperties(() -> Blocks.BONE_BLOCK)
            .item()
            .tab(AllCreativeTabs.MAIN.getKey())
            .build().register();

    /**
     * Loads this class
     */
    public static void register()
    {
    }
}
