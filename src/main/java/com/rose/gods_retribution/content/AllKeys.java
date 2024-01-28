package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import com.rose.gods_retribution.content.network.PacketHandler;
import com.rose.gods_retribution.content.network.SSwitchGamemodeToSpectatorFromKeyPressedPacket;
import com.rose.gods_retribution.content.network.SSwitchGamemodeToSurvivalFromKeyPressedPacket;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.function.Predicate;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public enum AllKeys
{
    SWITCH_GAMEMODE_SPECTATOR("switch_gamemode_spectator", GLFW.GLFW_KEY_LEFT_CONTROL, e ->
    {
        PacketHandler.INSTANCE.sendToServer(new SSwitchGamemodeToSpectatorFromKeyPressedPacket());
        return true;
    }),

    SWITCH_GAMEMODE_SURVIVAL("switch_gamemode_survival", GLFW.GLFW_KEY_LEFT_ALT, e ->
    {
        PacketHandler.INSTANCE.sendToServer(new SSwitchGamemodeToSurvivalFromKeyPressedPacket());
        return true;
    }),

    ;

    private KeyMapping keybind;
    private String description;
    private int key;
    private boolean modifiable;

    private Predicate onHitKey;
    private boolean isGlobalKey;

    private AllKeys(String description, int defaultKey)
    {
        this.description = GodsRetribution.MOD_ID + ".keyinfo." + description;
        this.key = defaultKey;
        this.modifiable = !description.isEmpty();
        this.onHitKey = null;
        this.isGlobalKey = false;
    }

    private AllKeys(String description, int defaultKey, Predicate onHitKey)
    {
        this.description = GodsRetribution.MOD_ID + ".keyinfo." + description;
        this.key = defaultKey;
        this.modifiable = !description.isEmpty();
        this.onHitKey = onHitKey;
        this.isGlobalKey = true;
    }

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event)
    {
        for (AllKeys key : values())
        {
            key.keybind = new KeyMapping(key.description, key.key, GodsRetribution.NAME);
            if (!key.modifiable)
                continue;

            event.register(key.keybind);
        }
    }

    public KeyMapping getKeybind()
    {
        return keybind;
    }

    public boolean isPressed()
    {
        if (!modifiable)
            return isKeyDown(key);

        return keybind.isDown();
    }

    public String getBoundKey()
    {
        return keybind.getTranslatedKeyMessage()
                .getString()
                .toUpperCase();
    }

    public int getBoundCode()
    {
        return keybind.getKey()
                .getValue();
    }

    public Predicate getOnHitKey()
    {
        return onHitKey;
    }

    public boolean isGlobalKey()
    {
        return isGlobalKey;
    }

    public static boolean isKeyDown(int key)
    {
        return InputConstants.isKeyDown(Minecraft.getInstance()
                .getWindow()
                .getWindow(), key);
    }

    public static boolean isMouseButtonDown(int button)
    {
        return GLFW.glfwGetMouseButton(Minecraft.getInstance()
                .getWindow()
                .getWindow(), button) == 1;
    }

    public static boolean ctrlDown()
    {
        return Screen.hasControlDown();
    }

    public static boolean shiftDown()
    {
        return Screen.hasShiftDown();
    }

    public static boolean altDown()
    {
        return Screen.hasAltDown();
    }
}