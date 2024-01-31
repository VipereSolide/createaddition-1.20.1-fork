package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllSounds
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
            .create(ForgeRegistries.SOUND_EVENTS, GodsRetribution.MOD_ID);

    public static final RegistryObject<SoundEvent> BLASTER_E11_FIRE =
            registerSound("blaster_e11_fire");

    public static final RegistryObject<SoundEvent> DOOR_LOCK =
            registerSound("door_lock");

    public static final RegistryObject<SoundEvent> DOOR_UNLOCK =
            registerSound("door_unlock");


    private static RegistryObject<SoundEvent> registerSound(String name)
    {
        return SOUND_EVENTS.register(name, () -> SoundEvent
                .createVariableRangeEvent(new ResourceLocation(GodsRetribution.MOD_ID, name)));
    }

    public static void register(IEventBus bus)
    {
        SOUND_EVENTS.register(bus);
    }
}
