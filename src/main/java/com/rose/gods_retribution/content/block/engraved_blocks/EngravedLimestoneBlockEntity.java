package com.rose.gods_retribution.content.block.engraved_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EngravedLimestoneBlockEntity extends SignBlockEntity
{
    public EngravedLimestoneBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState)
    {
        super(pType, pPos, pBlockState);
    }

    public int getTextColour()
    {
        return 0x9A9883;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag)
    {
        super.saveAdditional(pTag);
        System.out.println("Savwing uwu");
    }

    @Override
    public void load(CompoundTag pTag)
    {
        super.load(pTag);
        System.out.println("Lowdin ti hee");
    }
}