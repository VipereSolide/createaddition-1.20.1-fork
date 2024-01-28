package com.rose.gods_retribution.content.item;

import com.rose.gods_retribution.content.AllSounds;
import com.rose.gods_retribution.content.entity.projectiles.LaserProjectileEntity;
import com.rose.gods_retribution.fundation.Lang;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Blaster extends Item
{
    protected BlasterProperties blasterProperties;
    protected float currentHeat;
    protected int coolingDownProcessTimer = 0;
    protected boolean hasOverheated;

    public Blaster(Properties itemProperties, BlasterProperties blasterProperties)
    {
        super(itemProperties);
        this.blasterProperties = blasterProperties;
    }

    public BlasterProperties getBlasterProperties()
    {
        return blasterProperties;
    }

    public void setBlasterProperties(BlasterProperties blasterProperties)
    {
        this.blasterProperties = blasterProperties;

        // Just a security so it doesn't crash if you didn't pass any custom fire sound.
        if (this.blasterProperties.fireSound == null)
            this.blasterProperties.fireSound = AllSounds.BLASTER_E11_FIRE.get();
    }

    public static class BlasterProperties
    {
        /**
         * The amount of damage a projectile does to any hit entity.
         */
        public float damage = 4;

        /**
         * The number of ticks of cooldown between every shot.
         */
        public int ticksBetweenShots = 3;

        /**
         * How fast the projectile travels in blocks/second.
         */
        public float velocity = 2.5F;

        /**
         * How large the spread angle will be when firing a projectile.
         */
        public float inaccuracy = 0.5F;

        /**
         * How many ticks must be waited after each shot for the weapon to start cooling down.
         */
        public int startRecoveryTimer = 15;

        /**
         * The amount of heat gained per shot. 1 would overheat the weapon with one shot. Set it to 0 to disable the
         * heating process all together.
         */
        public float heatPerShot = 0.04F;

        /**
         * How fast will the weapon cool down after the recovery timer ran down.
         */
        public float recoverySpeed = 0.0125F;

        /**
         * How fast will the weapon cool down to 0 after the weapon has overheated.
         */
        public float overheatedRecoverySpeed = 0.005F;

        /**
         * The sound played whenever an entity shoots the blaster.
         */
        public SoundEvent fireSound;

        public BlasterProperties disableHeating()
        {
            this.heatPerShot = 0F;
            return this;
        }

        /**
         * Sets the sound played whenever an entity shoots the blaster.
         */
        public BlasterProperties fireSound(SoundEvent fireSound)
        {
            this.fireSound = fireSound;
            return this;
        }

        /**
         * Sets how fast will the weapon cool down to 0 after the weapon has overheated.
         */
        public BlasterProperties overheatedRecoverySpeed(float overheatedRecoverySpeed)
        {
            this.overheatedRecoverySpeed = overheatedRecoverySpeed;
            return this;
        }

        /**
         * Sets how many ticks must be waited after each shot for the weapon to start cooling down.
         */
        public BlasterProperties startRecoveryTimer(int startRecoveryTimer)
        {
            this.startRecoveryTimer = startRecoveryTimer;
            return this;
        }

        /**
         * Sets how fast will the weapon cool down after the recovery timer ran down.
         */
        public BlasterProperties recoverySpeed(float recoverySpeed)
        {
            this.recoverySpeed = recoverySpeed;
            return this;
        }

        /**
         * Sets the amount of heat gained per shot. 1 would overheat the weapon with one shot. To disable the heating
         * process all together, see {@link #disableHeating()}
         */
        public BlasterProperties heatPerShot(float heatPerShot)
        {
            this.heatPerShot = heatPerShot;
            return this;
        }

        /**
         * Sets the amount of damage a projectile does to any hit entity.
         */
        public BlasterProperties damage(float damage)
        {
            this.damage = damage;
            return this;
        }

        /**
         * Sets the number of ticks of cooldown between every shot.
         */
        public BlasterProperties ticksBetweenShots(int ticksBetweenShots)
        {
            this.ticksBetweenShots = ticksBetweenShots;
            return this;
        }

        /**
         * Sets how fast the projectile travels in blocks/second.
         */
        public BlasterProperties velocity(float velocity)
        {
            this.velocity = velocity;
            return this;
        }

        /**
         * Sets how large the spread angle will be when firing a projectile.
         */
        public BlasterProperties inaccuracy(float inaccuracy)
        {
            this.inaccuracy = inaccuracy;
            return this;
        }
    }

    /**
     * The key used by the player to fire the blaster.
     */
    protected static KeyMapping fireKey()
    {
        return Minecraft.getInstance().options.keyAttack;
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

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer)
    {
        return false;
    }

    @Override
    public boolean canBeDepleted()
    {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level level, Entity entity, int pSlotId, boolean pIsSelected)
    {
        if (level.isClientSide())
        {
            if (entity instanceof Player player)
                manageHeatOnTick(pIsSelected, player);
        }
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
        if (player.getCooldowns().isOnCooldown(stack.getItem()))
            return;

        player.sendSystemMessage(Component.literal("Is Client Side: " + level.isClientSide()));

        // Play the weapon firing sound.
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                blasterProperties.fireSound,
                SoundSource.PLAYERS,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        // Managing heat on client side so the heat is not synced between the entire server LOL
        if (level.isClientSide())
        {
            manageHeatOnShot(level, player);
        }
        // We spawn the projectile on server side so all players see it and not only the player who shot the weapon.
        else
        {
            spawnProjectile(level, player, stack);

            // Adding the rate of fire cool-down.
            if (!hasOverheated)
                player.getCooldowns().addCooldown(stack.getItem(), blasterProperties.ticksBetweenShots);
        }

        // So the game knows we used this item.
        player.awardStat(Stats.ITEM_USED.get(this));
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
        if (currentHeat >= 1 && !hasOverheated)
        {
            // The player has to wait for the weapon to fully cool down before being able to shoot again.
            hasOverheated = true;

            player.getCooldowns().addCooldown(this, 1500);

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

    protected void manageHeatOnTick(boolean isSelected, Player player)
    {
        // We only do it on the selected item so having multiple of the same item type in the inventory doesn't
        // accelerate the cooling process.
        if (true)
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
        {
            hasOverheated = false;
            player.getCooldowns().removeCooldown(this);
        }

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

    public MutableComponent getToolPropertyComponent(String toolPropertyName)
    {
        MutableComponent property = Lang.item("blaster", "properties", toolPropertyName);
        property.setStyle(Style.EMPTY.withColor(ChatFormatting.BLUE));

        return property;
    }
}
