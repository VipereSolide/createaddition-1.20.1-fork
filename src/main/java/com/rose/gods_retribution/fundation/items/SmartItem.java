package com.rose.gods_retribution.fundation.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * An improved item.
 */
public abstract class SmartItem extends Item
{
    protected boolean isInitialized = false;

    public SmartItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected)
    {
        if (!isInitialized)
        {
            onInitializeStack(pStack, pLevel, pEntity, pSlotId, pIsSelected);
            isInitialized = true;
        }

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    /**
     * Called automatically the first time an itemstack of this item is in any inventory.
     */
    protected void onInitializeStack(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected)
    {
    }
}
