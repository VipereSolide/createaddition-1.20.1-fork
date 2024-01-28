package com.rose.gods_retribution.content.item;

import net.minecraft.network.chat.Component;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AutomaticBlaster extends Blaster
{
    public AutomaticBlaster(Properties itemProperties, BlasterProperties blasterProperties)
    {
        super(itemProperties, blasterProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack,
                                @Nullable Level pLevel,
                                List<Component> pTooltipComponents,
                                TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        // Adding a special "Automatic" text in the blaster's description.
        pTooltipComponents.add(1, getToolPropertyComponent("automatic"));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world,
                                                           Player player,
                                                           @NotNull InteractionHand hand)
    {
        var stack = player.getItemInHand(hand);

        if (!hasOverheated)
            player.startUsingItem(hand);

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack)
    {
        return true;
    }

    public void onUseTick(@NotNull Level world,
                          @NotNull LivingEntity living,
                          @NotNull ItemStack itemstack,
                          int pRemainingUseDuration)
    {
        if (living instanceof Player player)
        {
            if (canShoot())
                shoot(world, player, itemstack);
        }
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack)
    {
        return Math.round(1F / blasterProperties.heatPerShot * blasterProperties.ticksBetweenShots);
    }
}