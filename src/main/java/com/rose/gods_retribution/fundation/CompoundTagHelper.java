package com.rose.gods_retribution.fundation;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

/**
 * Utility methods for managing NBT data.
 */
public class CompoundTagHelper
{
    /**
     * Fetches the mod's specific compound tag in the given global compound tag.
     *
     * @param globalCompound The global compound tag you wish to query the namespace in.
     * @return A CompoundTag which contains every mod related properties.
     */
    public static CompoundTag getMainNamespace(CompoundTag globalCompound)
    {
        return globalCompound.getCompound(GodsRetribution.MOD_ID);
    }

    /**
     * Adds the namespace compound tag to the global compound tag using the correct namespace name.
     *
     * @param namespaceCompound The compound tag in which every property from the mod will be.
     * @param globalCompound    The more global compound tag in which the mod's namespace will be added.
     * @return A CompoundTag which is the global compound to which we added the namespace compound.
     */
    public static CompoundTag createMainNamespace(CompoundTag globalCompound, CompoundTag namespaceCompound)
    {
        globalCompound.put(GodsRetribution.MOD_ID, namespaceCompound);
        return globalCompound;
    }

    /**
     * Inserts a block pos key in a given compound tag.
     *
     * @param compound Target NBT tag.
     * @param key      The name of the block pos key in your compound tag.
     * @param pos      What block position you wish to insert.
     */
    public static void putBlockPos(CompoundTag compound, String key, BlockPos pos)
    {
        compound.putIntArray(key, new int[]
                {
                        pos.getX(),
                        pos.getY(),
                        pos.getZ()
                });
    }

    /**
     * Retrieves a block pos key from a given compound tag.
     *
     * @param compound Target NBT tag.
     * @param key      The name of the block pos key you wish to retrieve from your compound tag.
     * @return A BlockPos obtained from the key in the given compound tag.
     */
    public static BlockPos getBlockPos(CompoundTag compound, String key)
    {
        var array = compound.getIntArray(key);
        return new BlockPos(array[0], array[1], array[2]);
    }
}