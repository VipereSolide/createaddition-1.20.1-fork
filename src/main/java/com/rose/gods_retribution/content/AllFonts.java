package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.resources.ResourceLocation;

public enum AllFonts
{
    TIMES_NEW_PIXEL("times_new_pixel"),
    PIXEL_FRAKTUR("pixel_fraktur"),

    ;

    public String name;

    AllFonts(String name)
    {
        this.name = name;
    }

    public ResourceLocation get()
    {
        return new ResourceLocation(GodsRetribution.MOD_ID, name);
    }
}