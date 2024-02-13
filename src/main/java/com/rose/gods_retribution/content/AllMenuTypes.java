package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineMenu;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineScreen;
import com.rose.gods_retribution.content.block.waste_processor.WasteProcessorMenu;
import com.rose.gods_retribution.content.block.waste_processor.WasteProcessorScreen;
import com.tterrag.registrate.builders.MenuBuilder;
import com.tterrag.registrate.builders.MenuBuilder.ScreenFactory;
import com.tterrag.registrate.util.entry.MenuEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

/**
 * Contains all the mod's menus.
 *
 * The new menus are to be registered in here.
 */
public class AllMenuTypes
{
    public static final MenuEntry<LabellingMachineMenu> LABELLING_MACHINE =
            register("labelling_machine", LabellingMachineMenu::new, () -> LabellingMachineScreen::new);

    public static final MenuEntry<WasteProcessorMenu> WASTE_PROCESSOR = REGISTRATE
            .menu("waste_processor", WasteProcessorMenu::new, () -> WasteProcessorScreen::new)
            .register();

    private static <C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> MenuEntry<C> register(
            String name,
            MenuBuilder.ForgeMenuFactory<C> factory,
            NonNullSupplier<ScreenFactory<C, S>> screenFactory)
    {
        return REGISTRATE
                .menu(name, factory, screenFactory)
                .register();
    }

    /**
     * Loads this class.
     */
    public static void register()
    {
    }
}