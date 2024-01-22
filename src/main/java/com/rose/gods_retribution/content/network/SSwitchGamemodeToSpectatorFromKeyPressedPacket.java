package com.rose.gods_retribution.content.network;

import com.rose.gods_retribution.content.key_handlers.SwitchGamemodeKeyHandlers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SSwitchGamemodeToSpectatorFromKeyPressedPacket
{
    public SSwitchGamemodeToSpectatorFromKeyPressedPacket()
    {
    }

    public SSwitchGamemodeToSpectatorFromKeyPressedPacket(FriendlyByteBuf buffer)
    {
    }

    public void encode(FriendlyByteBuf buffer)
    {
    }

    public void handle(Supplier<NetworkEvent.ServerCustomPayloadEvent.Context> context)
    {
        ServerPlayer player = context.get().getSender();

        if (player == null)
            return;

        SwitchGamemodeKeyHandlers.SwitchGamemodeSpectator(player);
    }
}