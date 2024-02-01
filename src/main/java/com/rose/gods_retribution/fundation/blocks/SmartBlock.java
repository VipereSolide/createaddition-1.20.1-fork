package com.rose.gods_retribution.fundation.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmartBlock extends Block
{
    public final SmartProperties smartProperties;

    public SmartBlock(Properties pProperties, SmartProperties smartProperties)
    {
        super(pProperties);

        this.smartProperties = smartProperties;
    }

    @Override
    public boolean hidesNeighborFace(BlockGetter level, BlockPos pos, BlockState state, BlockState neighborState, Direction dir)
    {
        return smartProperties.hiddenNeighbourFaces[dir.ordinal()];
    }

    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        if (smartProperties.noShadow)
            return Shapes.empty();

        return super.getVisualShape(pState, pLevel, pPos, pContext);
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos)
    {
        if (smartProperties.noShadow)
            return 1F;

        return super.getShadeBrightness(pState, pLevel, pPos);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos)
    {
        if (smartProperties.noShadow)
            return true;

        return super.propagatesSkylightDown(pState, pLevel, pPos);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        if (smartProperties.usesCustomShape)
            return smartProperties.voxelShape;

        return super.getShape(pState, pLevel, pPos, pContext);
    }

    public static class SmartProperties
    {
        public boolean[] hiddenNeighbourFaces;
        public boolean noShadow = false;

        public boolean usesCustomShape;
        public VoxelShape voxelShape;

        public SmartProperties showNeighbourFaces(boolean north, boolean south, boolean east, boolean west, boolean up, boolean down)
        {
            this.hiddenNeighbourFaces = new boolean[]
                    {
                            down,
                            up,
                            north,
                            south,
                            west,
                            east
                    };

            return this;
        }

        public SmartProperties doNotShowNeighbourFaces()
        {
            this.hiddenNeighbourFaces = new boolean[]
                    {
                            false,
                            false,
                            false,
                            false,
                            false,
                            false
                    };

            return this;
        }

        public SmartProperties noShadow()
        {
            this.noShadow = true;

            return this;
        }

        public SmartProperties shape(VoxelShape shape)
        {
            this.voxelShape = shape;
            this.usesCustomShape = true;

            return this;
        }
    }
}