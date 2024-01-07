package com.rose.gods_retribution.content.item.labelling_tag;

import com.simibubi.create.content.trains.station.NoShadowFontWrapper;
import com.simibubi.create.foundation.gui.AbstractSimiScreen;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.IconButton;
import com.simibubi.create.foundation.gui.widget.Label;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import static com.rose.gods_retribution.GodsRetribution.MOD_ID;

public class LabellingTagItemScreen extends AbstractSimiScreen
{
    private static final Component title =
            Component.translatable("gui." + MOD_ID + ".labelling_tag.title");
    private static final ResourceLocation background =
            new ResourceLocation(MOD_ID, "textures/gui/labelling_tag_screen.png");
    private final InteractionHand hand;
    private ItemStack itemInHand;
    private Player player;

    // Components
    private IconButton confirmButton;
    private EditBox labelBox;

    public LabellingTagItemScreen(InteractionHand hand)
    {
        super(title);

        this.hand = hand;
    }

    @Override
    protected void init()
    {
        setWindowSize(176, 59);
        super.init();
        clearWidgets();
        int x = guiLeft;
        int y = guiTop;

        player = this.minecraft.player;
        itemInHand = player.getItemInHand(hand);

        Label label = new Label(x + 4, y + 3, title);
        label.setFGColor(0xCCCCCC);
        addRenderableWidget(label);

        labelBox = new EditBox(new NoShadowFontWrapper(font),
                x + 32,
                y + 32,
                118,
                18,
                Components.literal(""));
        labelBox.setMaxLength(28);
        labelBox.setTextColor(0x141414);
        labelBox.setValue(player.getItemInHand(hand).getOrCreateTag().getString(LabellingTagItem.LABEL_NBT));
        labelBox.setFocused(false);
        labelBox.mouseClicked(0, 0, 0);
        labelBox.setBordered(false);
        labelBox.active = true;
        addRenderableWidget(labelBox);

        confirmButton = new IconButton(x + width - 33, y + height - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);
        confirmButton.visible = true;
        addRenderableWidget(confirmButton);
    }

    @Override
    public void onClose()
    {
        player.getItemInHand(hand).getOrCreateTag().putString(LabellingTagItem.LABEL_NBT, labelBox.getValue());
        super.onClose();
    }

    @Override
    protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        int x = guiLeft;
        int y = guiTop;

        graphics.blit(background, x, y, 0, 0, 176, 59);
    }

}
