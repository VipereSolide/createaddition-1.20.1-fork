package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import net.minecraft.world.level.block.Block;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

public class AllCreativeTabs
{
    public static final Component MAIN_TAB_TITLE =
            Component.translatable("creative_tab.gods_retribution.gods_retribution_tab");

    public static final Component DECORATION_TAB_TITLE =
            Component.translatable("creative_tab.gods_retribution.gods_retribution_decoration_tab");

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GodsRetribution.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN =
            CREATIVE_TABS.register("main_creative_tab",
                    () -> CreativeModeTab.builder()
                            .title(MAIN_TAB_TITLE)
                            .icon(() -> new ItemStack(AllItems.LABELLING_TAG.get()))
                            .displayItems((displayParams, output) ->
                            {
                                output.accept(AllItems.LABELLING_TAG);
                                output.accept(AllBlocks.LABELLING_MACHINE);

                                output.accept(AllBlocks.PLASTIC_MOSS);
                                output.accept(AllBlocks.FLINT_BLOCK);

                                output.accept(AllBlocks.WOOD);
                                output.accept(AllItems.SCREW);
                            })
                            .build()
            );

    public static final RegistryObject<CreativeModeTab> DECORATION =
            CREATIVE_TABS.register("decoration_blocks_creative_tab",
                    () -> CreativeModeTab.builder()
                            .title(DECORATION_TAB_TITLE)
                            .icon(() -> new ItemStack(AllBlocks.AERATION_BLOCK.get()))
                            .displayItems((displayParams, output) ->
                            {
                                output.accept(AllBlocks.AERATION_BLOCK);
                                output.accept(AllBlocks.AIR_VENT);
                                output.accept(AllBlocks.TILES_BATHROOM);

                                output.accept(AllBlocks.CONCRETE);
                            })
                            .build()
            );

    public static void register(IEventBus eventBus)
    {
        CREATIVE_TABS.register(eventBus);
    }
}
