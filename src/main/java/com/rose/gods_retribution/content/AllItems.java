package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import com.rose.gods_retribution.content.item.*;
import com.rose.gods_retribution.content.item.fluid_vacuum.FluidVacuumItem;
import com.rose.gods_retribution.content.item.labelling_tag.LabellingTagItem;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

/**
 * Contains all the mod's items.
 *
 * The new items are to be registered here, in the form of a public static final ItemEntry
 */
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

    public static final ItemEntry<SingleFireBlaster> BLASTER_E11 = REGISTRATE
            .item("e11_blaster", (properties) ->
                    new SingleFireBlaster(
                            properties,
                            new Blaster.BlasterProperties()
                                    .damage(7)
                                    .inaccuracy(0.025F)
                                    .ticksBetweenShots(6)
                                    .velocity(4.5F)
                                    .overheatedRecoverySpeed(0.018F)
                                    .recoverySpeed(0.02F)
                                    .startRecoveryTimer(10)
                                    .heatPerShot(0.12F)
                                    .fireSound(AllSounds.BLASTER_E11_FIRE.get())
                    ))
            .properties(p -> p.stacksTo(1))
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<AutomaticBlaster> BLASTER_E11_AUTO = REGISTRATE
            .item("e11_blaster_auto", (properties) ->
                    new AutomaticBlaster(
                            properties,
                            new Blaster.BlasterProperties()
                                    .damage(3)
                                    .inaccuracy(0.15F)
                                    .ticksBetweenShots(2)
                                    .velocity(4F)
                                    .overheatedRecoverySpeed(0.01F)
                                    .recoverySpeed(0.014F)
                                    .heatPerShot(0.075F)
                                    .fireSound(AllSounds.BLASTER_E11_FIRE.get())
                    ))
            .properties(p -> p.stacksTo(1))
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<Item> PLASTIC_BALL = REGISTRATE
            .item("plastic_ball", Item::new)
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<Item> EMPTY_PACKET = REGISTRATE
            .item("empty_packet", Item::new)
            .tab(AllCreativeTabs.MAIN.getKey())
            .recipe((context, consumer) -> ShapelessRecipeBuilder
                    .shapeless(RecipeCategory.MISC, context.get())
                    .requires(Items.PAPER, 2)
                    .unlockedBy("has_paper", RegistrateRecipeProvider.has(Items.PAPER))
                    .save(consumer, new ResourceLocation(GodsRetribution.MOD_ID,
                            "crafting/" + context.getName() + "_from_paper")))
            .model((context, provider) -> new ResourceLocation(GodsRetribution.MOD_ID, "empty_packet"))
            .register();

    public static final ItemEntry<Item> SUGAR_PACKET = REGISTRATE
            .item("sugar_packet", Item::new)
            .tab(AllCreativeTabs.MAIN.getKey())
            .recipe((c, p) -> ShapelessRecipeBuilder
                    .shapeless(RecipeCategory.MISC, c.get())
                    .requires(AllItems.EMPTY_PACKET)
                    .requires(Items.SUGAR, 8)
                    .unlockedBy("has_sugar", RegistrateRecipeProvider.has(Items.SUGAR))
                    .save(p, new ResourceLocation(GodsRetribution.MOD_ID,
                            "crafting/" + c.getName() + "_from_empty_packet")))
            .model((context, provider) -> new ResourceLocation(GodsRetribution.MOD_ID, "sugar_packet"))
            .register();

    public static final ItemEntry<FlaskItem> EMPTY_GLASS_FLASK = REGISTRATE
            .item("empty_glass_flask", FlaskItem::new)
            .tab(AllCreativeTabs.MAIN.getKey())
            .recipe((c, p) -> ShapelessRecipeBuilder
                    .shapeless(RecipeCategory.MISC, c.get())
                    .requires(AllItems.PLASTIC_BALL)
                    .requires(Items.GLASS_BOTTLE)
                    .unlockedBy("has_plastic", RegistrateRecipeProvider.has(AllItems.PLASTIC_BALL))
                    .save(p, new ResourceLocation(GodsRetribution.MOD_ID,
                            "crafting/" + c.getName() + "_from_glass_bottle_and_plastic")))
            .model((context, provider) -> new ResourceLocation(GodsRetribution.MOD_ID, "empty_glass_flask"))
            .register();

    public static final ItemEntry<FlaskItem> GINGER_GLASS_FLASK = REGISTRATE
            .item("ginger_glass_flask", p -> new FlaskItem(p, ItemLookup.ginger()))
            .tab(AllCreativeTabs.MAIN.getKey())
            .recipe((c, p) -> ShapelessRecipeBuilder
                    .shapeless(RecipeCategory.MISC, c.get())
                    .requires(AllItems.EMPTY_GLASS_FLASK)
                    .requires(ItemLookup.ginger(), 2)
                    .unlockedBy("has_ginger", RegistrateRecipeProvider.has(ItemLookup.ginger()))
                    .save(p, new ResourceLocation(GodsRetribution.MOD_ID,
                            "crafting/" + c.getName() + "_from_glass_bottle_and_plastic")))
            .model((context, provider) -> new ResourceLocation(GodsRetribution.MOD_ID, "ginger_glass_flask"))
            .register();

    public static final ItemEntry<LightArmourItem> LIGHT_ARMOUR_CHESTPLATE = REGISTRATE
            .item("light_armour_chestplate", properties ->
                    new LightArmourItem(AllArmorMaterials.LIGHT_ARMOUR, ArmorItem.Type.CHESTPLATE, properties))
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<LightArmourItem> LIGHT_ARMOUR_LEGGINGS = REGISTRATE
            .item("light_armour_leggings", properties ->
                    new LightArmourItem(AllArmorMaterials.LIGHT_ARMOUR, ArmorItem.Type.LEGGINGS, properties))
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<LightArmourItem> LIGHT_ARMOUR_BOOTS = REGISTRATE
            .item("light_armour_boots", properties ->
                    new LightArmourItem(AllArmorMaterials.LIGHT_ARMOUR, ArmorItem.Type.BOOTS, properties))
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    public static final ItemEntry<Item> GELATINE = REGISTRATE
            .item("gelatin", Item::new)
            .tab(AllCreativeTabs.MAIN.getKey())
            .register();

    /**
     * Loads this class.
     */
    public static void register()
    {
    }
}