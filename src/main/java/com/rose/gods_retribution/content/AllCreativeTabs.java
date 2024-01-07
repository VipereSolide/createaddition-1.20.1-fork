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
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import net.minecraft.world.level.block.Block;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

public class AllCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GodsRetribution.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = CREATIVE_TABS.register("main_creative_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_tab.gods_retribution.gods_retribution_tab"))
                    .icon(() -> new ItemStack(AllItems.SCREW.get()))
                    .displayItems(new RegistrateDisplayItemsGenerator())
                    .build()
    );

    public static void register(IEventBus eventBus)
    {
        CREATIVE_TABS.register(eventBus);
    }

    public static class RegistrateDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator
    {
        private List<Item> collectBlocks(RegistryObject<CreativeModeTab> tab, Predicate<Item> exclusionPredicate)
        {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Block> entry : REGISTRATE.getAll(Registries.BLOCK))
            {
                if (!REGISTRATE.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get()
                        .asItem();
                if (item == Items.AIR)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
            return items;
        }

        private List<Item> collectItems(RegistryObject<CreativeModeTab> tab, Predicate<Item> exclusionPredicate)
        {
            List<Item> items = new ReferenceArrayList<>();

            for (RegistryEntry<Item> entry : REGISTRATE.getAll(Registries.ITEM))
            {
                if (!REGISTRATE.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            return items;
        }

        private static void outputAll(CreativeModeTab.Output output, List<Item> items)
        {
            for (Item item : items)
            {
                output.accept(item);
            }
        }

        List<Item> exclude = new ArrayList<>();

        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output)
        {
            List<Item> items = new LinkedList<>();

            items.addAll(collectBlocks(MAIN, (item) -> true));
            items.addAll(collectItems(MAIN, (item) -> exclude.contains(item)));

            outputAll(output, items);
        }
    }
}
