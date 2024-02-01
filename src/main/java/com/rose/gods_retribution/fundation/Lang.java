package com.rose.gods_retribution.fundation;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;

import static com.rose.gods_retribution.GodsRetribution.MOD_ID;

/**
 * Utility methods concerning lang files.
 */
public class Lang
{
    /**
     * Gets a key for the mod using a category and a key name (example: category = "item" and keyName = "example_item"
     * will return "item.gods_retribution.exampleItem").
     *
     * @param category What type of key you wish to get (example: "item", "block", "gui", etc.).
     * @param keyName  The name of the key in the given category (example: "my_example_item", "my_example_block", etc.).
     * @return A String containing the key from that category and with the given key name.
     */
    public static String keyFromCategory(String category, String keyName)
    {
        return category + "." + MOD_ID + "." + keyName;
    }

    /**
     * Gets a key for the mod using a category and a key name (example: category = "item" and keyName = "example_item"
     * will return "item.gods_retribution.exampleItem"), with followup extensions.
     *
     * @param extensions All the extensions you want to add at the end of the key (example: "tooltip", "summary").
     * @param category   What type of key you wish to get (example: "item", "block", "gui", etc.).
     * @param keyName    The name of the key in the given category (example: "my_example_item", "my_example_block", etc.).
     * @return A String containing the key from that category and with the given key name.
     * @apiNote In this example, we first give the category of the item, then it's ID name, and after that, all the other
     * strings are extensions:
     * <br />
     * {@code String myItemTooltipKey = keyFromCategory("item", "my_example_item", "tooltip", "summary"); }
     * <br />
     * The output will then be: "item.gods_retribution.my_example_item.tooltip.summary".
     * </code>
     */
    public static String keyFromCategory(String category, String keyName, String... extensions)
    {
        StringBuilder itemKey = new StringBuilder(keyFromCategory(category, keyName));
        for (var extension : extensions)
            itemKey.append(".").append(extension);

        return itemKey.toString();
    }

    /**
     * Gets the translation of a given item's name using its ID.
     *
     * @param itemName The ID of the item you wish to get the translation for.
     * @return A MutableComponent containing the translation for the item's ID.
     */
    public static MutableComponent item(String itemName)
    {
        return Component.translatable(keyFromCategory("item", itemName));
    }

    /**
     * Gets the translation of a given item's name, followed up by a list of given extensions. For more information on
     * what are the extensions, see {@link Lang#keyFromCategory(String, String, String...) Lang.keyFromCategory}.
     *
     * @param itemName   The name of the item you want to get the translation for.
     * @param extensions What extensions will follow the item's translation key (example: "tooltip", "summary" to get a
     *                   Create summary of the item).
     * @return A MutableComponent containing the translation of the given item's name followed by the extensions.
     */
    public static MutableComponent item(String itemName, String... extensions)
    {
        return Component.translatable(keyFromCategory("item", itemName, extensions));
    }

    /**
     * Gets the translation of an item's name using a given Item.
     *
     * @param item What item you wish to get the name of.
     * @return A MutableComponent containing the translation of the given item's name.
     */
    public static MutableComponent item(Item item)
    {
        return (MutableComponent) item.getDescription();
    }

    public static MutableComponent block(String blockName)
    {
        return Component.translatable(keyFromCategory("block", blockName));
    }

    public static MutableComponent block(String blockName, String... extensions)
    {
        return Component.translatable(keyFromCategory("block", blockName, extensions));
    }
}
