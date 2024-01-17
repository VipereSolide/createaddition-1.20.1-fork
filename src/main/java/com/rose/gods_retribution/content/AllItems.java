package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.item.fluid_vacuum.FluidVacuumItem;
import com.rose.gods_retribution.content.item.labelling_tag.LabellingTagItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

public class AllItems
{
    public static final ItemEntry<Item> SCREW = REGISTRATE
            .item("screw", Item::new)
            .register();

    public static final ItemEntry<LabellingTagItem> LABELLING_TAG = REGISTRATE
            .item("labelling_tag", LabellingTagItem::new)
            .register();

    public static final ItemEntry<FluidVacuumItem> FLUID_VACUUM = REGISTRATE
            .item("fluid_vacuum", FluidVacuumItem::new)
            .register();

    /**
     * Loads this class
     */
    public static void register()
    {
    }
}