package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlockEntity;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineRenderer;
import com.rose.gods_retribution.content.block.plastic_moss.PlasticMossBlockEntity;
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

    public static final BlockEntityEntry<PlasticMossBlockEntity> PLASTIC_MOSS = REGISTRATE
            .blockEntity("plastic_moss", PlasticMossBlockEntity::new)
            .validBlocks(AllBlocks.PLASTIC_MOSS)
            .register();

    /**
     * Loads this class.
     */
    public static void register()
    {
    }
}