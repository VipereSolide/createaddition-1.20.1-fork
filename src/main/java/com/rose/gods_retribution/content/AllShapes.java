package com.rose.gods_retribution.content;

import java.util.function.BiFunction;

import com.simibubi.create.foundation.utility.VoxelShaper;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Contains all the mod's collision boxes for the 3D-models blocks.
 */
public class AllShapes
{
    public static final VoxelShaper LABELLING_MACHINE =
            shape(ofSize(0, 0, 1, 16, 15, 14))
                    .add(ofSize(3, 15, 3, 10, 1, 10))
                    .forDirectional(Direction.NORTH);

    public static final VoxelShaper AIR_VENT = shape(ofSize(3, 3, 14, 10, 10, 2)).forDirectional(Direction.NORTH);

    // From create:AllShapes
    public static Builder shape(VoxelShape shape)
    {
        return new Builder(shape);
    }

    public static Builder shape(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        return shape(cuboid(x1, y1, z1, x2, y2, z2));
    }

    public static VoxelShape cuboid(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        return Block.box(x1, y1, z1, x2, y2, z2);
    }

    public static VoxelShape ofSize(double posX, double posY, double posZ, double sizeX, double sizeY, double sizeZ)
    {
        return Block.box(posX, posY, posZ, sizeX + posX, sizeY + posY, sizeZ + posZ);
    }

    /**
     * Used to create a collision box.
     */
    public static class Builder
    {
        VoxelShape shape;

        public Builder(VoxelShape shape)
        {
            this.shape = shape;
        }

        /**
         * Add a new shape in the collision box.
         *
         * @param shape
         * @return itself
         */
        public Builder add(VoxelShape shape)
        {
            this.shape = Shapes.or(this.shape, shape);
            return this;
        }

        /**
         * Add a new shape in the collision box.
         *
         * @param y1 The Y-origin point
         * @param z1 The Z-origin point
         * @param x1 The X-origin point
         * @param x2 The X-end point
         * @param y2 The Y-end point
         * @param z2 The Z-end point
         * @return itself
         */
        public Builder add(double x1, double y1, double z1, double x2, double y2, double z2)
        {
            return add(cuboid(x1, y1, z1, x2, y2, z2));
        }

        /**
         * Make a hole in the collision box.
         *
         * @param x1 The X-origin point
         * @param y1 The Y-origin point
         * @param z1 The Z-origin point
         * @param x2 The X-end point
         * @param y2 The Y-end point
         * @param z2 The Z-end point
         * @return itself
         */
        public Builder erase(double x1, double y1, double z1, double x2, double y2, double z2)
        {
            this.shape =
                    Shapes.join(shape, cuboid(x1, y1, z1, x2, y2, z2), BooleanOp.ONLY_FIRST);
            return this;
        }

        /**
         * Achieve the construction of the collision box and returns the shape.
         *
         * @return the built shape ready to be used as a collision box.
         */
        public VoxelShape build()
        {
            return shape;
        }

        /**
         * Achieve the construction of the collision box and returns the shape.
         *
         * @param factory
         * @param direction
         * @return the built shape ready to be used as a collision box.
         */
        public VoxelShaper build(BiFunction<VoxelShape, Direction, VoxelShaper> factory, Direction direction)
        {
            return factory.apply(shape, direction);
        }

        /**
         * Achieve the construction of the collision box and returns the shape.
         *
         * @param factory
         * @param axis
         * @return the built shape ready to be used as a collision box.
         */
        public VoxelShaper build(BiFunction<VoxelShape, Axis, VoxelShaper> factory, Axis axis)
        {
            return factory.apply(shape, axis);
        }

        /**
         * Returns the shape oriented in the direction of our choice.
         *
         * @param direction
         * @return the built shape ready to be used as a collision box.
         */
        public VoxelShaper forDirectional(Direction direction)
        {
            return build(VoxelShaper::forDirectional, direction);
        }

        public VoxelShaper forAxis()
        {
            return build(VoxelShaper::forAxis, Axis.Y);
        }

        public VoxelShaper forHorizontalAxis()
        {
            return build(VoxelShaper::forHorizontalAxis, Axis.Z);
        }

        public VoxelShaper forHorizontal(Direction direction)
        {
            return build(VoxelShaper::forHorizontal, direction);
        }

        public VoxelShaper forDirectional()
        {
            return forDirectional(Direction.UP);
        }

    }
}
