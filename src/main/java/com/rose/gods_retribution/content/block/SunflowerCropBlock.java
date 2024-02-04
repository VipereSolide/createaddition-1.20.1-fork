package com.rose.gods_retribution.content.block;

import com.rose.gods_retribution.content.AllItems;
import com.rose.gods_retribution.content.AllShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SunflowerCropBlock extends CropBlock
{
    public static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]
            {
                    AllShapes.ofSize(4, 0, 4, 12, 4, 12),
                    AllShapes.ofSize(4, 0, 4, 12, 4, 12),
                    AllShapes.ofSize(4, 0, 4, 12, 7, 12),
                    AllShapes.ofSize(2, 0, 2, 14, 9, 14),
                    AllShapes.ofSize(2, 0, 2, 14, 12, 14),
                    AllShapes.ofSize(2, 0, 2, 14, 15, 14),
                    AllShapes.ofSize(2, 0, 2, 14, 16, 14),
                    AllShapes.ofSize(2, 0, 2, 14, 16, 14),
            };

    public SunflowerCropBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId()
    {
        return AllItems.SUNFLOWER_SEEDS.get();
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        if (!pLevel.isAreaLoaded(pPos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light

        if (pLevel.getRawBrightness(pPos, 0) >= 9)
        {
            int i = this.getAge(pState);
            if (i < this.getMaxAge())
            {
                float f = getGrowthSpeed(this, pLevel, pPos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((int) (25.0F / f) + 1) == 0))
                {
                    int age = i + 1;
                    if (age == getMaxAge())
                    {
                        if (pLevel.getBlockState(pPos.above()).getBlock() == Blocks.AIR)
                        {
                            pLevel.setBlock(
                                    pPos,
                                    Blocks.SUNFLOWER.defaultBlockState().setValue(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER),
                                    3);

                            pLevel.setBlock(
                                    pPos.above(),
                                    Blocks.SUNFLOWER.defaultBlockState().setValue(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER),
                                    3);
                        }
                    }
                    else
                    {
                        pLevel.setBlock(pPos, this.getStateForAge(age), 2);
                    }

                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
                }
            }
        }
    }

    @Override
    public float getMaxHorizontalOffset()
    {
        return 0.1F;
    }
}
