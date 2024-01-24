package com.rose.gods_retribution.content;

import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum AllArmorMaterials implements ArmorMaterial
{
    LIGHT_ARMOUR("light_armour",
            400,
            400,
            400,
            400,
            2,
            4,
            5,
            2,
            1,
            SoundEvents.ARMOR_EQUIP_TURTLE,
            0.1F,
            0.1F,
            () -> Ingredient.of(Items.LEATHER))

    ;

    private final String name;
    private final int[] protectionAmount;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private final int[] durability;

    AllArmorMaterials(String name,
                      float durabilityMultiplier,
                      int helmetProtectionAmount,
                      int chestplateProtectionAmount,
                      int leggingsProtectionAmount,
                      int bootsProtectionAmount,
                      int enchantmentValue,
                      SoundEvent equipSound,
                      float toughness,
                      float knockbackResistance,
                      Supplier<Ingredient> repairIngredient)
    {
        this.name = name;
        this.durability = new int[]
                {
                        Math.round(11 * durabilityMultiplier),
                        Math.round(16 * durabilityMultiplier),
                        Math.round(16 * durabilityMultiplier),
                        Math.round(13 * durabilityMultiplier)
                };

        this.protectionAmount = new int[]
                {
                        helmetProtectionAmount,
                        chestplateProtectionAmount,
                        leggingsProtectionAmount,
                        bootsProtectionAmount
                };

        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    AllArmorMaterials(String name,
                      int helmetDurability,
                      int chestplateDurability,
                      int leggingsDurability,
                      int bootsDurability,
                      int helmetProtectionAmount,
                      int chestplateProtectionAmount,
                      int leggingsProtectionAmount,
                      int bootsProtectionAmount,
                      int enchantmentValue,
                      SoundEvent equipSound,
                      float toughness,
                      float knockbackResistance,
                      Supplier<Ingredient> repairIngredient)
    {
        this.name = name;
        this.durability = new int[]
                {
                        helmetDurability,
                        chestplateDurability,
                        leggingsDurability,
                        bootsDurability
                };

        this.protectionAmount = new int[]
                {
                        helmetProtectionAmount,
                        chestplateProtectionAmount,
                        leggingsProtectionAmount,
                        bootsProtectionAmount
                };

        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType)
    {
        return this.durability[pType.ordinal()];
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType)
    {
        return this.protectionAmount[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound()
    {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    @Override
    public String getName()
    {
        return GodsRetribution.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness()
    {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return this.knockbackResistance;
    }
}
