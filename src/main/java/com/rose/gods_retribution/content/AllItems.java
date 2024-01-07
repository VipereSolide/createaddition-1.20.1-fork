package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

public class AllItems
{
    static
    {
        REGISTRATE.setCreativeTab(AllCreativeTabs.MAIN);
    }

    public static final ItemEntry<Item> SCREW =
            REGISTRATE.item("screw", Item::new)
                    .register();

    public static final ItemEntry<Item> LABELLING_TAG =
            REGISTRATE.item("labelling_tag", Item::new)
                    .register();

    /**
     * Loads this class
      */
    public static void register() {}
}