package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
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
	}
}
