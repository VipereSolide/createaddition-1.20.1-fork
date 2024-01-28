package com.rose.gods_retribution.fundation.client;

import com.rose.gods_retribution.content.item.labelling_tag.LabellingTagItemScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;

/**
 * Utility class for managing menus and opening screens.
 */
public class ClientHooks
{
    /**
     * Opens the labelling tag screen using the player's hand.
     * @param hand What hand is the player holding the labelling tag item with.
     */
    public static void OpenLabellingTagItemScreen(InteractionHand hand)
    {
        Minecraft.getInstance().setScreen(new LabellingTagItemScreen(hand));
    }
}