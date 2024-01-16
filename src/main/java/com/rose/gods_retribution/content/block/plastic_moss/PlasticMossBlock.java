package com.rose.gods_retribution.content.block.plastic_moss;

import com.rose.gods_retribution.content.AllBlockEntityTypes;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PlasticMossBlock extends Block implements IBE<PlasticMossBlockEntity>
{
    public PlasticMossBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public Class<PlasticMossBlockEntity> getBlockEntityClass()
    {
        return PlasticMossBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends PlasticMossBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.PLASTIC_MOSS.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockstate)
    {
        return AllBlockEntityTypes.PLASTIC_MOSS.create(blockPos, blockstate);
    }
}
