package com.rose.gods_retribution;

import com.rose.gods_retribution.content.*;
import com.rose.gods_retribution.content.network.PacketHandler;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.TooltipModifier;

/**
 * The mod's entry point.
 */
@Mod(GodsRetribution.MOD_ID)
public class GodsRetribution
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "gods_retribution";
    public static final String NAME = "Gods' Retribution";

    public static boolean IMMERSIVE_ENGINEERING_ACTIVE = false;
    public static boolean COMPUTER_CRAFT_ACTIVE = false;
    public static boolean AE2_ACTIVE = false;

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(GodsRetribution.MOD_ID);

    private static final String PROTOCOL = "1";
    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(MOD_ID, "main"))
            .clientAcceptedVersions(PROTOCOL::equals)
            .serverAcceptedVersions(PROTOCOL::equals)
            .networkProtocolVersion(() -> PROTOCOL)
            .simpleChannel();

    static
    {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }

    public GodsRetribution()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
        //FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(RecipeSerializer.class, CARecipes::register);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        IMMERSIVE_ENGINEERING_ACTIVE = ModList.get().isLoaded("immersiveengineering");
        COMPUTER_CRAFT_ACTIVE = ModList.get().isLoaded("computercraft");
        AE2_ACTIVE = ModList.get().isLoaded("ae2");

        REGISTRATE.registerEventListeners(eventBus);

        AllCreativeTabs.register(eventBus);
        AllSounds.register(eventBus);

        AllBlocks.register();
        AllBlockEntityTypes.register();

        AllFluids.register();
        AllItems.register();
        AllMenuTypes.register();
        AllEntities.register();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            PacketHandler.register();
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
    }

    /**
     * Ran when the mod's loading is done.
     *
     * @param evt
     */
    public void postInit(FMLLoadCompleteEvent evt)
    {
        System.out.println("Gods' Retribution Initialized!");
    }

    @SubscribeEvent
    public void onRegisterCommandEvent(RegisterCommandsEvent event)
    {
    }

    public static ResourceLocation asResource(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            //EntityRenderers.register(AllEntities.LASER_PROJECTILE.get(), );
        }
    }
}
