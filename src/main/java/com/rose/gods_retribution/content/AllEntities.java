package com.rose.gods_retribution.content;

import com.rose.gods_retribution.content.entity.projectiles.LaserProjectileEntity;
import com.rose.gods_retribution.content.entity.projectiles.LaserProjectileRenderer;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.DisplayRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.MobCategory;

import static com.rose.gods_retribution.GodsRetribution.REGISTRATE;

/**
 * Contains all the mod's entities.
 */
public class AllEntities
{
    public static EntityEntry<LaserProjectileEntity> LASER_PROJECTILE = REGISTRATE
            .<LaserProjectileEntity>entity("laser_projectile", LaserProjectileEntity::new, MobCategory.MISC)
            .properties((p) -> p.sized(0.5F, 0.5F))
            .renderer(() -> LaserProjectileRenderer::new)
            .register();

    /**
     * Loads this class.
     */
    public static void register()
    {

    }
}