package com.rose.gods_retribution.content.key_handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class SwitchGamemodeKeyHandlers
{
    public static void SwitchGamemodeSurvival(ServerPlayer serverPlayer)
    {
        if (serverPlayer.gameMode.isSurvival())
        {
            serverPlayer.setGameMode(GameType.CREATIVE);
            return;
        }

        serverPlayer.setGameMode(GameType.SURVIVAL);
    }

    public static void SwitchGamemodeSpectator(ServerPlayer serverPlayer)
    {
        if (serverPlayer.isSpectator())
        {
            serverPlayer.setGameMode(GameType.CREATIVE);
            return;
        }

        serverPlayer.setGameMode(GameType.SPECTATOR);
    }
}