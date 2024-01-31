package com.rose.gods_retribution.content.item.gold_key;

import com.rose.gods_retribution.content.AllBlocks;
import com.rose.gods_retribution.content.AllSounds;
import com.rose.gods_retribution.content.block.keyholes.KeyholeBlock;
import com.rose.gods_retribution.fundation.CompoundTagHelper;
import com.rose.gods_retribution.fundation.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoldKeyItem extends Item
{
    public static final String BOUND_POS_TAG = "bound_position";

    public GoldKeyItem(Properties pProperties)
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

        MutableComponent component = Lang.item("gold_key", "summary");
        component.setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY));

        pTooltipComponents.add(component);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx)
    {
        var world = ctx.getLevel();
        var pos = ctx.getClickedPos();
        var state = world.getBlockState(pos);

        ctx.getPlayer().getCooldowns().addCooldown(this, 20);

        // If the clicked block is a keyhole.
        if (state.getBlock() == AllBlocks.LIMESTONE_KEYHOLE.get())
        {
            // If user clicked on the front face.
            if (ctx.getClickedFace() == state.getValue(KeyholeBlock.FACING))
            {
                var stack = ctx.getItemInHand();

                if (state.getValue(KeyholeBlock.LOCKED))
                {
                    if (stack.getOrCreateTag().contains(BOUND_POS_TAG))
                    {
                        var boundPos = CompoundTagHelper.getBlockPos(stack.getOrCreateTag(), BOUND_POS_TAG);
                        if (boundPos.getX() == pos.getX() && boundPos.getY() == pos.getY() && boundPos.getZ() == pos.getZ())
                        {
                            KeyholeBlock.setActive(world, pos, state);
                            world.playSound((Player) null, pos, AllSounds.DOOR_UNLOCK.get(), SoundSource.BLOCKS);

                            return InteractionResult.SUCCESS;
                        }

                        world.playSound((Player) null, pos, AllSounds.DOOR_LOCK.get(), SoundSource.BLOCKS);
                        if (world.isClientSide())
                        {
                            ctx.getPlayer().displayClientMessage(Lang
                                    .item("gold_key", "wrong_key")
                                    .withStyle(Style.EMPTY
                                            .withColor(ChatFormatting.RED)
                                            .withItalic(true)), true);
                        }

                        return InteractionResult.FAIL;
                    }
                }
                else
                {
                    world.setBlock(pos, state.setValue(KeyholeBlock.LOCKED, true), 2);
                    CompoundTagHelper.putBlockPos(stack.getOrCreateTag(), BOUND_POS_TAG, pos);

                    if (world.isClientSide())
                    {
                        ctx.getPlayer().displayClientMessage(Lang
                                .item("gold_key", "bound_keyhole")
                                .withStyle(Style.EMPTY
                                        .withColor(ChatFormatting.GRAY)
                                        .withItalic(true)), true);
                    }

                    world.playSound((Player) null, pos, AllSounds.DOOR_LOCK.get(), SoundSource.BLOCKS);

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.FAIL;
    }
}
