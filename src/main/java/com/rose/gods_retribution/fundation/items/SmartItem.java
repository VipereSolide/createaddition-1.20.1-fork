package com.rose.gods_retribution.fundation.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * An extension of the default minecraft item containing a range of handy new utility methods such as
 * {@code onInitializeStack}.
 */
public abstract class SmartItem extends Item
{
    public static final String INITIALIZED_TAG = "is_initialized";

    public SmartItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected)
    {
        // If the item has already been initialized in the past, we don't want to call the onInitializeStack.
        if (itemstack.hasTag() && itemstack.getOrCreateTag().contains(INITIALIZED_TAG))
            return;

        onInitializeStack(itemstack, world, entity, slot, selected);
        setInitialized(itemstack, true);

        super.inventoryTick(itemstack, world, entity, slot, selected);
    }

    private void setInitialized(ItemStack stack, boolean initialized)
    {
        stack.getOrCreateTag().putBoolean(INITIALIZED_TAG, initialized);

    }

    /**
     * Called whenever an itemstack of this item is contained in the inventory of a player for the first time.
     *
     * @param itemstack The initialized itemstack.
     * @param world     The world the player is in.
     * @param entity    The entity whose inventory contains the itemstack.
     * @param slot      In what slot of the inventory is the itemstack contained.
     * @param selected  Whether the itemstack is selected by the entity or not.
     * @apiNote This will add NBT data to the given itemstack. Items stacks of this item that have not been initialized
     * yet may result in non stacking itemstacks.
     */
    protected void onInitializeStack(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected)
    {
    }
}
