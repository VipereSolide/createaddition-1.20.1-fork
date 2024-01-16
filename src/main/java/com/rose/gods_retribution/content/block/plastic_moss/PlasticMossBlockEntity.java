package com.rose.gods_retribution.content.block.plastic_moss;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PlasticMossBlockEntity extends SmartBlockEntity
{
    /**
     * Time left to live in the world measured in ticks. 20 ticks = 1 second.
     */
    public static final int MAX_LIFETIME = 100;

    private int currentLifetime;

    public PlasticMossBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        currentLifetime = MAX_LIFETIME;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours)
    {

    }

    @Override
    public void tick()
    {
        super.tick();

        currentLifetime -= 1;
        if (currentLifetime <= 0)
            destroy();
    }

    public void destroy()
    {
        if (level instanceof ServerLevel serverLevel)
        {
            double x = getBlockPos().getX();
            double y = getBlockPos().getY();
            double z = getBlockPos().getZ();

            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, getBlockState()),
                    x,
                    y,
                    z,
                    5,
                    5,
                    .5,
                    .5,
                    0);
        }

        level.destroyBlock(getBlockPos(), false);
    }

    public int getCurrentLifetime()
    {
        return currentLifetime;
    }
}
