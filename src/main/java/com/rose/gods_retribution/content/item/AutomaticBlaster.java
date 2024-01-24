package com.rose.gods_retribution.content.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AutomaticBlaster extends Blaster
{
    public AutomaticBlaster(Properties itemProperties, BlasterProperties blasterProperties)
    {
        super(itemProperties, blasterProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        MutableComponent spacing = Component.literal(" ");

        // Adding more weapon statistics.
        pTooltipComponents.add(getToolPropertyComponent("automatic"));
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level level, Entity entity, int pSlotId, boolean isSelected)
    {
        super.inventoryTick(itemstack, level, entity, pSlotId, isSelected);

        if (!isSelected)
            return;

        if (entity instanceof Player player)
            if (fireKey().isDown() && canShoot())
                shoot(level, player, itemstack);
    }
}