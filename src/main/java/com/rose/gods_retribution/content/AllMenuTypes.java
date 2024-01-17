package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineMenu;
import com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineScreen;
import com.simibubi.create.content.schematics.table.SchematicTableMenu;
import com.simibubi.create.content.schematics.table.SchematicTableScreen;
import com.tterrag.registrate.builders.MenuBuilder;
import com.tterrag.registrate.builders.MenuBuilder.ForgeMenuFactory;
import com.tterrag.registrate.builders.MenuBuilder.ScreenFactory;
import com.tterrag.registrate.util.entry.MenuEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

public class AllMenuTypes
{
    public static final MenuEntry<LabellingMachineMenu> LABELLING_MACHINE =
            register("labelling_machine", LabellingMachineMenu::new, () -> LabellingMachineScreen::new);

    private static <C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> MenuEntry<C> register(
            String name,
            MenuBuilder.ForgeMenuFactory<C> factory,
            NonNullSupplier<ScreenFactory<C, S>> screenFactory)
    {
        return REGISTRATE
                .menu(name, factory, screenFactory)
                .register();
    }

    public static void register()
    {
    }
}