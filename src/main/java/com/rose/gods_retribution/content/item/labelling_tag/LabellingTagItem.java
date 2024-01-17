package com.rose.gods_retribution.content.item.labelling_tag;

import com.rose.gods_retribution.fundation.Lang;
import com.rose.gods_retribution.fundation.client.ClientHooks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LabellingTagItem extends Item
{
    public static final String LABEL_NBT = "label";

    public LabellingTagItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand)
    {
        if (pLevel.isClientSide())
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.OpenLabellingTagItemScreen(pUsedHand));

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        String currentLabelString = pStack.getOrCreateTag().getString("label");
        if (currentLabelString.isEmpty())
            return;

        MutableComponent currentLabelTitle = Lang.item("labelling_tag", "tooltip", "current_label").withStyle(Style.EMPTY.withColor(ChatFormatting.GOLD));
        MutableComponent currentLabel = Component.literal(currentLabelString).withStyle(Style.EMPTY.withColor(ChatFormatting.GRAY));

        pTooltipComponents.add(Component.literal(" "));
        pTooltipComponents.add(currentLabelTitle.append(currentLabel));
    }
}
