package com.rose.gods_retribution.fundation;

import net.minecraft.world.phys.Vec2;

/**
 * Vector2Int
 */
public class Vec2i
{
    public int x;
    public int y;

    public Vec2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vec2i(int x)
    {
        this.x = x;
        this.y = 0;
    }

    public Vec2i(Vec2 vec)
    {
        this.x = (int) vec.x;
        this.y = (int) vec.y;
    }

    public Vec2i(float x, float y)
    {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Vec2i(double x, double y)
    {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Vec2i(short x, short y)
    {
        this.x = x;
        this.y = y;
    }
}