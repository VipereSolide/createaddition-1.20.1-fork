package com.rose.gods_retribution.content.key_handlers;

import com.rose.gods_retribution.GodsRetribution;
import com.rose.gods_retribution.content.AllKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GodsRetribution.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientKeyHandler
{
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event)
    {
        Minecraft minecraft = Minecraft.getInstance();

        for(var key : AllKeys.values())
        {
            if (!key.isGlobalKey())
                continue;

            if (key.getKeybind().consumeClick())
                key.getOnHitKey().test(null);
        }
    }
}