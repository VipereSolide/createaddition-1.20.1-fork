package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraft.world.level.material.MapColor;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;
import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.simibubi.create.foundation.data.TagGen.tagBlockAndItem;
import static com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours.assignDataBehaviour;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOnly;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.simibubi.create.foundation.data.TagGen.tagBlockAndItem;

import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;

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

    public static final BlockEntry<LabellingMachineBlock> LABELLING_MACHINE_BLOCK =
            REGISTRATE.block("labelling_machine", LabellingMachineBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.noOcclusion().mapColor(MapColor.STONE))
                    .transform(axeOrPickaxe())
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(BlockStressDefaults.setImpact(4.0))
                    .item(AssemblyOperatorBlockItem::new)
                    .tab(AllCreativeTabs.MAIN.getKey())
                    .transform(customItemModel())
                    .register();

    /**
     * Loads this class
     */
    public static void register()
    {
    }
}
