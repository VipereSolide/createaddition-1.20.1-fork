package com.rose.gods_retribution.fundation;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.nbt.CompoundTag;

/**
 * A util class for the NBT data management.
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
}