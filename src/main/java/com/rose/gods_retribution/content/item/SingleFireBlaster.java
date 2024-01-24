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
    public SingleFireBlaster(Properties itemProperties, BlasterProperties blasterProperties)
    {
        super(itemProperties, blasterProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        MutableComponent spacing = Component.literal(" ");

        // Adding more weapon statistics.
        pTooltipComponents.add(getToolPropertyComponent("single_fire"));
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand)
    {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (canShoot())
            shoot(pLevel, pPlayer, itemstack);

        return InteractionResultHolder.fail(itemstack);
    }
}