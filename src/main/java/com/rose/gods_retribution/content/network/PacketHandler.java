package com.rose.gods_retribution.content.network;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler
{
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
                    new ResourceLocation(GodsRetribution.MOD_ID, "gods_retribution_main"))
            .serverAcceptedVersions(s -> true)
            .clientAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    public static void register()
    {
        INSTANCE.messageBuilder(SSwitchGamemodeToSurvivalFromKeyPressedPacket.class, NetworkDirection.PLAY_TO_SERVER.ordinal())
                .encoder(SSwitchGamemodeToSurvivalFromKeyPressedPacket::encode)
                .decoder(SSwitchGamemodeToSurvivalFromKeyPressedPacket::new)
                .consumerMainThread(SSwitchGamemodeToSurvivalFromKeyPressedPacket::handle)
                .add();

        INSTANCE.messageBuilder(SSwitchGamemodeToSpectatorFromKeyPressedPacket.class, NetworkDirection.PLAY_TO_SERVER.ordinal())
                .encoder(SSwitchGamemodeToSpectatorFromKeyPressedPacket::encode)
                .decoder(SSwitchGamemodeToSpectatorFromKeyPressedPacket::new)
                .consumerMainThread(SSwitchGamemodeToSpectatorFromKeyPressedPacket::handle)
                .add();
    }
}