package com.rose.gods_retribution.fundation;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;

import static com.rose.gods_retribution.GodsRetribution.MOD_ID;

public class Lang
{
    public static MutableComponent item(String name)
    {
        return Component.translatable("item." + MOD_ID + "." + name);
    }

    public static MutableComponent item(String name, String... other)
    {
        String s = "item." + MOD_ID + "." + name;
        for (var o : other)
            s += "." + o;

        return Component.translatable(s);
    }

    public static MutableComponent item(Item item)
    {
        return (MutableComponent) item.getDescription();
    }

    public static MutableComponent item(Item item, String... other)
    {
        String s = item.getDescriptionId();

        for (var o : other)
            s += "." + o;

        return Component.translatable(s);
    }
}
