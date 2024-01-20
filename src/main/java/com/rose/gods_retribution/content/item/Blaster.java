package com.rose.gods_retribution.content.item;

import net.minecraft.world.item.Item;

public abstract class Blaster extends Item
{
    protected BlasterProperties blasterProperties;

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

        public BlasterProperties disableHeating()
        {
            this.heatPerShot = 0F;
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
}
