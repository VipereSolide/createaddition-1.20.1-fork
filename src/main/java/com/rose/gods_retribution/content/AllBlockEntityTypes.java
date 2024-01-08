package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlockEntity;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineInstance;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineRenderer;
import com.simibubi.create.content.kinetics.base.HalfShaftInstance;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

public class AllBlockEntityTypes
{
    public static final BlockEntityEntry<LabellingMachineBlockEntity> LABELLING_MACHINE = REGISTRATE
            .blockEntity("labelling_machine", LabellingMachineBlockEntity::new)
            .instance(() -> LabellingMachineInstance::new)
            .validBlocks(AllBlocks.LABELLING_MACHINE)
            .renderer(() -> LabellingMachineRenderer::new)
            .register();

    public static void register()
    {
    }
}