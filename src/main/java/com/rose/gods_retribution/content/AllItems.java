package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.item.Blaster;
import com.rose.gods_retribution.content.item.SingleFireBlaster;
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
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<LabellingTagItem> LABELLING_TAG = REGISTRATE
            .item("labelling_tag", LabellingTagItem::new)
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<FluidVacuumItem> FLUID_VACUUM = REGISTRATE
            .item("fluid_vacuum", FluidVacuumItem::new)
            .register();

    public static final ItemEntry<SingleFireBlaster> TESTING_RIFLE = REGISTRATE
            .item("testing_rifle", (properties) ->
                    new SingleFireBlaster(
                            properties,
                            new Blaster.BlasterProperties()
                                    .damage(5)
                                    .inaccuracy(0.5F)
                                    .ticksBetweenShots(3)
                                    .velocity(2.5F)
                                    .overheatedRecoverySpeed(0.008F)
                                    .recoverySpeed(0.016F)
                    ))
            .properties((p) -> p.stacksTo(1))
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<Item> PLASTIC_BALL = REGISTRATE.item("plastic_ball", Item::new)
            .tab(AllCreativeTabs.MAIN.getKey())
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    /**
     * Loads this class
     */
    public static void register()
    {
    }
}