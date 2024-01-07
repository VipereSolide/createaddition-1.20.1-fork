package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.item.labelling_tag.LabellingTagItem;
import com.tterrag.registrate.util.entry.ItemEntry;
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

    public static final ItemEntry<LabellingTagItem> LABELLING_TAG =
            REGISTRATE.item("labelling_tag", LabellingTagItem::new)
                    .register();

    /**
     * Loads this class
      */
    public static void register() {}
}