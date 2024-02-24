package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

/**
 * This class contains references to the custom tags added by the mod. Beware : adding a tag in
 * here does not create a tag ingame. You have to create the tags in the data generation tags
 * provider, then reference it here.
 */
public class AllTags
{
	/**
	 * The inner class for the blocks' tags.
	 */
	public static class Blocks
	{
		/**
		 * Only for demonstration purposes.
		 */
		public static final TagKey<Block> WOODY = tag("woody");
		/**
		 * Contains all the concrete blocks and their variants.
		 */
		public static final TagKey<Block> CONCRETE = tag("concrete");
		public static final TagKey<Block> VERTICAL_SLABS = tag("vertical_slabs");
		/**
		 * Use this tag for writing only. For reading, use instead the {@code AllTags.Blocks.WASTE_EX} tag.
		 */
		public static final TagKey<Block> WASTE = tag("waste");
		/**
		 * Blocks marked with this tag are considered as waste, and cannot be processed by the waste processor.
		 * <p>
		 * This tag surrounds the {@code #gods_retribution:waste} tag. It is not datagenerated. The items and blocks
		 * from the vanilla game or other mods must be added manually, in the json file of this tag
		 * ({@code waste_ex.json}). The blocks of this mod must instead be added in the code to the
		 * {@code AllTags.Blocks.WASTE} tag.
		 */
		public static final TagKey<Block> WASTE_EX = tag("waste_ex");

		/**
		 * Used for the flint block.
		 */
		public static final TagKey<Block> FORGE_STORAGE_BLOCKS_FLINT = foreignTag("forge", "storage_blocks/flint");
		/**
		 * Used for the transparent metal wiremesh block.
		 */
		public static final TagKey<Block> FORGE_STORAGE_BLOCKS_IRON_BARS = foreignTag("forge", "storage_blocks/iron_bars");
		public static final TagKey<Block> FORGE_STORAGE_BLOCKS_WASTE = foreignTag("forge", "storage_blocks/waste");
		public static final TagKey<Block> FORGE_STORAGE_BLOCKS_ASHES = foreignTag("forge", "storage_blocks/ashes");

		/**
		 * Helper method used to create the blocks' tags in this very class.
		 *
		 * @param name the tag's ID
		 * @return the tag
		 */
		private static TagKey<Block> tag(String name)
		{
			return BlockTags.create(new ResourceLocation(GodsRetribution.MOD_ID, name));
		}

		/**
		 * Helper method used to create a block tag in another namespace that the one used by the mod (for example : a
		 * minecraft:something tag).
		 *
		 * @param namespace the tag's namespace (for example "minecraft" or "forge")
		 * @param name the tag's name (for example "something")
		 * @return the tag, usable by minecraft
		 */
		private static TagKey<Block> foreignTag(String namespace, String name)
		{
			return BlockTags.create(new ResourceLocation(namespace, name));
		}
	}

	/**
	 * The inner class for the items' tags.
	 */
	public static class Items
	{
		/**
		 * Contains the items that can be used to make create's superglue.
		 */
		public static final TagKey<Item> GLUE_MATERIAL = tag("glue_material");
		public static final TagKey<Item> CONCRETE = tag("concrete");
		public static final TagKey<Item> VERTICAL_SLABS = tag("vertical_slabs");
		/**
		 * Use this tag for writing only. For reading, use instead the {@code AllTags.Items.WASTE_EX} tag.
		 */
		public static final TagKey<Item> WASTE = tag("waste");
		/**
		 * Items marked with this tag are considered as waste, and cannot be processed by the waste processor.
		 * <p>
		 * This tag surrounds the {@code #gods_retribution:waste} tag. It is not datagenerated. The items and blocks
		 * from the vanilla game or other mods must be added manually, in the json file of this tag
		 * ({@code waste_ex.json}). The items of this mod must instead be added in the code to the
		 * {@code AllTags.Items.WASTE} tag.
		 */
		public static final TagKey<Item> WASTE_EX = tag("waste_ex");

		/**
		 * Used by the flint block.
		 */
		public static final TagKey<Item> FORGE_STORAGE_BLOCKS_FLINT = foreignTag("forge", "storage_blocks/flint");
		/**
		 * Used by the transparent metal wiremesh block.
		 */
		public static final TagKey<Item> FORGE_STORAGE_BLOCKS_IRON_BARS = foreignTag("forge", "storage_blocks/iron_bars");
		public static final TagKey<Item> FORGE_STORAGE_BLOCKS_WASTE = foreignTag("forge", "storage_blocks/waste");
		public static final TagKey<Item> FORGE_STORAGE_BLOCKS_ASHES = foreignTag("forge", "storage_blocks/ashes");
		public static final TagKey<Item> FORGE_RODS_IRON = foreignTag("forge", "rods/iron");
		public static final TagKey<Item> FORGE_RODS_ALL_METAL = foreignTag("forge", "rods/all_metal");

		/**
		 * Helper method used to create the items' tags in this very class.
		 *
		 * @param name the tag's ID
		 * @return the tag
		 */
		private static TagKey<Item> tag(String name)
		{
			return ItemTags.create(new ResourceLocation(GodsRetribution.MOD_ID, name));
		}

		/**
		 * Helper method used to create an item tag in another namespace that the one used by the mod (for example : a
		 * minecraft:something tag).
		 *
		 * @param namespace The tag's namespace (for example "minecraft" or "forge")
		 * @param name The tag's name (for example "something")
		 * @return The tag, usable by minecraft
		 */
		private static TagKey<Item> foreignTag(String namespace, String name)
		{
			return ItemTags.create(new ResourceLocation(namespace, name));
		}
	}
}
