package com.rose.gods_retribution.content.entity.projectiles;

import com.rose.gods_retribution.content.AllEntities;
import com.rose.gods_retribution.content.AllItems;
import com.rose.gods_retribution.content.item.Blaster;
import com.simibubi.create.foundation.damageTypes.CreateDamageSources;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class LaserProjectileEntity extends ThrowableItemProjectile
{
    public Blaster.BlasterProperties properties;
    public int lifetime;

    public LaserProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel);
    }

    public LaserProjectileEntity(Level pLevel, LivingEntity livingEntity, Blaster parent)
    {
        super(AllEntities.LASER_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected @NotNull Item getDefaultItem()
    {
        return AllItems.BLASTER_E11.asItem();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult)
    {
        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult)
    {
        super.onHitEntity(pResult);

        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity livingEntity)
        {
            livingEntity.invulnerableTime = 0;
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), (properties == null ? 0 : properties.damage));
        }
    }

    @Override
    protected void onHit(@NotNull HitResult pResult)
    {
        super.onHit(pResult);
        destroy();
    }

    public void destroy()
    {
        if (!level().isClientSide)
        {
            level().broadcastEntityEvent(this, (byte) 3);
            discard();
        }
    }

    @Override
    public boolean isNoGravity()
    {
        return true;
    }

    @Override
    public void tick()
    {
        super.tick();
        lifetime++;

        if (lifetime >= 20 * 15)
            destroy();
    }
}
