package com.rose.gods_retribution.content.item;

import com.rose.gods_retribution.content.AllFonts;
import com.rose.gods_retribution.content.block.engraved_blocks.EngravedLimestoneBlock;
import com.rose.gods_retribution.content.block.engraved_blocks.EngravedLimestoneBlockEntity;
import com.rose.gods_retribution.fundation.Lang;
import com.rose.gods_retribution.fundation.items.SmartItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EngravingChisel extends Item
{
    public EngravingChisel(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack,
                                @Nullable Level pLevel,
                                @NotNull List<Component> pTooltipComponents,
                                @NotNull TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        MutableComponent summary1 = Lang.item("engraving_chisel", "summary1");
        summary1.setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY));
        pTooltipComponents.add(summary1);

        MutableComponent summary2 = Lang.item("engraving_chisel", "summary2");
        summary2.setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY));
        pTooltipComponents.add(summary2);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        if (pContext.getLevel().getBlockEntity(pContext.getClickedPos()) instanceof EngravedLimestoneBlockEntity be)
        {
            if (pContext.getPlayer().isCrouching())
            {
                var pState = be.getBlockState();
                int state = pState.getValue(EngravedLimestoneBlock.FONT_TYPE);
                state++;

                if (state > AllFonts.values().length - 1)
                {
                    state = 0;
                }

                pContext.getLevel().setBlock(pContext.getClickedPos(), pState.setValue(EngravedLimestoneBlock.FONT_TYPE, state), 2);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
