package com.rose.gods_retribution.content.item;

import com.rose.gods_retribution.content.entity.projectiles.LaserProjectileEntity;
import com.rose.gods_retribution.fundation.Lang;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SingleFireBlaster extends Blaster
{
    protected float currentHeat;
    protected int coolingDownProcessTimer = 0;
    protected boolean hasOverheated;

    public SingleFireBlaster(Properties itemProperties, BlasterProperties blasterProperties)
    {
        super(itemProperties, blasterProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        MutableComponent spacing = Component.literal(" ");

        // Adding a space before the rest so if there's any enchantment or lore, it doesn't display close to that.
        pTooltipComponents.add(Components.immutableEmpty());
        // The name of the itemstack in gray and not in italic.
        pTooltipComponents.add(((MutableComponent) pStack.getHoverName()).append(Component.literal(":"))
                .withStyle(ChatFormatting.GRAY)
                .withStyle(Style.EMPTY.withItalic(false)));

        // Adding the weapon statistics.
        pTooltipComponents.add(spacing.plainCopy().append(getToolPropertyComponent("attack_damage", blasterProperties.damage)));
        pTooltipComponents.add(spacing.plainCopy().append(getToolPropertyComponent("cooldown", blasterProperties.ticksBetweenShots)));
        pTooltipComponents.add(spacing.plainCopy().append(getToolPropertyComponent("inaccuracy", blasterProperties.inaccuracy)));
        pTooltipComponents.add(spacing.plainCopy().append(getToolPropertyComponent("velocity", blasterProperties.velocity)));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand)
    {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (!canShoot())
            return InteractionResultHolder.fail(itemstack);

        shoot(pLevel, pPlayer, itemstack);

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected)
    {
        manageHeatOnTick(pIsSelected);
    }

    @Override
    public int getBarWidth(ItemStack pStack)
    {
        return Math.round(13 * currentHeat);
    }

    @Override
    public int getBarColor(ItemStack pStack)
    {
        return 0xFFFFFF;
    }

    @Override
    public boolean isBarVisible(ItemStack pStack)
    {
        return true;
    }

    /**
     * Whether the weapon is able to fire or not on this tick, depending on its heat.
     */
    protected boolean canShoot()
    {
        return currentHeat < 1 && !hasOverheated;
    }

    public int getCoolingDownProcessTimer()
    {
        return coolingDownProcessTimer;
    }

    public float getCurrentHeat()
    {
        return currentHeat;
    }

    public boolean getHasOverheated()
    {
        return this.hasOverheated;
    }

    public void setHasOverheated(boolean hasOverheated)
    {
        this.hasOverheated = hasOverheated;
    }

    public void setCurrentHeat(float currentHeat)
    {
        this.currentHeat = currentHeat;
    }

    public void setCoolingDownProcessTimer(int coolingDownProcessTimer)
    {
        this.coolingDownProcessTimer = coolingDownProcessTimer;
    }

    protected void shoot(Level level, Player player, ItemStack stack)
    {
        manageHeatOnShot(level, player);

        // Play the weapon firing sound.
        level.playSound((Player) null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.STONE_BREAK,
                SoundSource.PLAYERS,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        // We spawn the projectile on server side so all players see it and not only the player who shot the weapon.
        if (!level.isClientSide)
            spawnProjectile(level, player, stack);

        // So the game knows we used this item.
        player.awardStat(Stats.ITEM_USED.get(this));

        // Adding the rate of fire cool-down.
        player.getCooldowns().addCooldown(this.asItem(), blasterProperties.ticksBetweenShots);
    }

    protected void spawnProjectile(Level level, Player player, ItemStack stack)
    {
        // Creating the projectile and setting up the required variables.
        LaserProjectileEntity projectile = new LaserProjectileEntity(level, player, this);
        projectile.properties = this.blasterProperties;
        projectile.setItem(stack);
        // Shooting the new projectile from the player orientation with the blaster properties of the weapon.
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, blasterProperties.velocity, blasterProperties.inaccuracy);

        // Registering the new entity.
        level.addFreshEntity(projectile);
    }

    protected void manageHeatOnShot(Level level, Player player)
    {
        // Setting the timer before the weapon can cool down again.
        this.coolingDownProcessTimer = blasterProperties.startRecoveryTimer;

        // Increase the current heat on shot but don't let it be greater than 1.
        this.currentHeat = Math.min(1, currentHeat + blasterProperties.heatPerShot);

        // If we reached the overheating point...
        if (currentHeat >= 1)
        {
            // The player has to wait for the weapon to fully cool down before being able to shoot again.
            hasOverheated = true;

            // Play the lava extinguishing sound to mimic a weapon overheating.
            level.playSound((Player) null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.LAVA_EXTINGUISH,
                    SoundSource.PLAYERS,
                    0.5F,
                    1F);
        }
    }

    protected void manageHeatOnTick(boolean isSelected)
    {
        // We only do it on the selected item so having multiple of the same item type in the inventory doesn't
        // accelerate the cooling process.
        if (isSelected)
        {
            // If the weapon can cool down, decrease it's current heat by either the slow value (if the weapon was
            // overheated) or by the normal value (if the weapon just stopped firing).
            if (currentHeat > 0 && coolingDownProcessTimer <= 0)
            {
                float recovery = hasOverheated ?
                        blasterProperties.overheatedRecoverySpeed :
                        blasterProperties.recoverySpeed;

                float newCurrentHeat = currentHeat - recovery;
                currentHeat = Math.max(0, newCurrentHeat);
            }
        }

        // Stop waiting for the weapon to cool down if the heat has reached zero again.
        if (currentHeat <= 0 && hasOverheated)
            hasOverheated = false;

        // Decrease the time it takes before cooling down the weapon again until it reaches zero.
        if (coolingDownProcessTimer > 0)
            coolingDownProcessTimer = Math.max(0, coolingDownProcessTimer - 1);
    }

    public MutableComponent getToolPropertyComponent(String toolPropertyName, float value)
    {
        MutableComponent property = Component.literal(String.valueOf(value).replace(".0", ""));
        property.append(Lang.item("blaster", "properties", toolPropertyName));
        property.setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN));

        return property;
    }
}