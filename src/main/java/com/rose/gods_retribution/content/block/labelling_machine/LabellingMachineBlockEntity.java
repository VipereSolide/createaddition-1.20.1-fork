package com.rose.gods_retribution.content.block.labelling_machine;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LabellingMachineBlockEntity extends KineticBlockEntity
{
    public LabellingMachineBlockEntity(BlockEntityType<? extends LabellingMachineBlockEntity> typeIn, BlockPos pos, BlockState state)
    {
        super(typeIn, pos, state);
    }

    @Override
    public void tick()
    {
        super.tick();

        System.out.println("Hehydehde");
    }
}
