package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.engraved_blocks.EngravedLimestoneBlockEntity;
import com.rose.gods_retribution.content.block.engraved_blocks.EngravedLimestoneBlockRenderer;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlockEntity;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineRenderer;
import com.rose.gods_retribution.content.block.plastic_moss.PlasticMossBlockEntity;
import com.rose.gods_retribution.content.block.waste_processor.WasteProcessorBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftInstance;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

/**
 * Contains all the mod's block entities.
 */
public class AllBlockEntityTypes
{
    public static final BlockEntityEntry<LabellingMachineBlockEntity> LABELLING_MACHINE = REGISTRATE
            .blockEntity("labelling_machine", LabellingMachineBlockEntity::new)
            .instance(() -> ShaftInstance::new)
            .validBlocks(AllBlocks.LABELLING_MACHINE)
            .renderer(() -> LabellingMachineRenderer::new)
            .register();

    public static final BlockEntityEntry<WasteProcessorBlockEntity> WASTE_PROCESSOR = REGISTRATE
            .blockEntity("waste_processor", WasteProcessorBlockEntity::new)
            .validBlocks(AllBlocks.WASTE_PROCESSOR)
            .register();

    public static final BlockEntityEntry<PlasticMossBlockEntity> PLASTIC_MOSS = REGISTRATE
            .blockEntity("plastic_moss", PlasticMossBlockEntity::new)
            .validBlocks(AllBlocks.PLASTIC_MOSS)
            .register();

    public static final BlockEntityEntry<EngravedLimestoneBlockEntity> ENGRAVED_LIMESTONE = REGISTRATE
            .blockEntity("engraved_limestone", EngravedLimestoneBlockEntity::new)
            .validBlocks(AllBlocks.ENGRAVED_LIMESTONE)
            .renderer(() -> EngravedLimestoneBlockRenderer::new)
            .register();

    /**
     * Loads this class.
     */
    public static void register()
    {
    }
}