package com.rose.gods_retribution.fundation;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import static com.rose.gods_retribution.GodsRetribution.MOD_ID;

/**
 * An util class used to get an item's name in the language files' format.
 */
public class Lang
{
    /**
     * @param name the item's ID
     * @return
     */
    public static MutableComponent item(String name)
    {
        return Component.translatable("item." + MOD_ID + "." + name);
    }

    /**
     * For the "sub-folders" of an item.
     *
     * @param name the item's ID
     * @param other the "sub-folders" names
     * @return
     */
    public static MutableComponent item(String name, String... other)
    {
        String s = "item." + MOD_ID + "." + name;
        for (var o : other)
            s += "." + o;

        return Component.translatable(s);
    }
}
