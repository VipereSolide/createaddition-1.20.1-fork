package com.rose.gods_retribution.content.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemLookup
{
    public static Item ginger()
    {
        return getItemFromStringId("pamhc2crops:gingeritem");
    }

    public static Item getItemFromStringId(String id)
    {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
    }

    public static Item getItemFromStringId(String modId, String id)
    {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(modId, id));
    }
}
