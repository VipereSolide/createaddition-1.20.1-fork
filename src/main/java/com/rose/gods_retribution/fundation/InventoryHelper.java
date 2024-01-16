package com.rose.gods_retribution.fundation;

import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryHelper
{
    public static int getSlotFromItem(Container container, Item item)
    {
        for (int i = 0; i < container.getContainerSize(); i++)
        {
            ItemStack stack = container.getItem(i);
            if (stack.getItem() == item)
                return i;
        }

        return 0;
    }
}
