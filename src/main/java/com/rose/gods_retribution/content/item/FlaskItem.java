package com.rose.gods_retribution.content.item;

import com.rose.gods_retribution.content.AllItems;
import com.rose.gods_retribution.fundation.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlaskItem extends Item
{
    public final ItemStack content;

    public FlaskItem(Properties pProperties, ItemStack content)
    {
        super(pProperties);
        this.content = content;
    }

    public FlaskItem(Properties pProperties, Item content)
    {
        super(pProperties);
        this.content = new ItemStack(content);
    }

    public FlaskItem(Properties pProperties)
    {
        super(pProperties);
        this.content = ItemStack.EMPTY;
    }

    @Override
    public Component getDescription()
    {
        return Lang.item("empty_glass_flask");
    }

    @Override
    public Component getName(ItemStack pStack)
    {
        return Lang.item("empty_glass_flask");
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        if (this.content.isEmpty())
        {
            pTooltipComponents.add(Lang
                    .item("flask_item", "tooltip", "empty")
                    .withStyle(ChatFormatting.DARK_GRAY));

            return;
        }

        MutableComponent title = Lang
                .item("flask_item", "tooltip", "content")
                .withStyle(ChatFormatting.DARK_GRAY);

        MutableComponent contentText = (MutableComponent) content.getHoverName();
        contentText.setStyle(Style
                .EMPTY
                .withItalic(false)
                .withColor(ChatFormatting.GRAY));

        pTooltipComponents.add(title.append(contentText));
    }
}
