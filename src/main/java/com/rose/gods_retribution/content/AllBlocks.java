package com.rose.gods_retribution.content;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraft.world.level.material.MapColor;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;
import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.simibubi.create.foundation.data.TagGen.tagBlockAndItem;

public class AllBlocks
{
    static
    {
        REGISTRATE.setCreativeTab(AllCreativeTabs.MAIN);
    }

    public static final BlockEntry<Block> WOOD = REGISTRATE.block("wood", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
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

    /**
     * Loads this class
     */
    public static void register()
    {
    }
}
