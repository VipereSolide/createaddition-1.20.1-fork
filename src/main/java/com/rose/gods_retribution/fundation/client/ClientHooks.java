package com.rose.gods_retribution.fundation.client;

import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineScreen;
import com.rose.gods_retribution.content.item.labelling_tag.LabellingTagItemScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientHooks
{
    public static void OpenLabellingTagItemScreen(InteractionHand hand)
    {
        Minecraft.getInstance().setScreen(new LabellingTagItemScreen(hand));
    }

    public static void OpenLabellingMachineScreen(InteractionHand hand)
    {
        // Minecraft.getInstance().setScreen(new LabellingMachineScreen());
    }
}