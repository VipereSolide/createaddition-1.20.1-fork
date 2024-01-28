package com.rose.gods_retribution.content.block.labelling_machine;

import com.simibubi.create.foundation.gui.menu.AbstractSimiContainerScreen;
import com.simibubi.create.foundation.gui.widget.Label;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import static com.rose.gods_retribution.GodsRetribution.MOD_ID;

public class LabellingMachineScreen extends AbstractSimiContainerScreen<LabellingMachineMenu>
{
    public static final Component title =
            Component.translatable("gui." + MOD_ID + ".labelling_machine.title");

    private static final ResourceLocation background =
            new ResourceLocation(MOD_ID, "textures/gui/labelling_machine_screen.png");

    private Player player;

    public LabellingMachineScreen(LabellingMachineMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init()
    {
        setWindowSize(176, 166);
        super.init();
        clearWidgets();
        int x = leftPos;
        int y = topPos;

        player = this.getMinecraft().player;
        createScreenNameLabel(x, y);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY)
    {
        int x = leftPos;
        int y = topPos;
        pGuiGraphics.blit(background, x, y, 0, 0, 176, 166);
        renderProgression(pGuiGraphics);
    }

    private void createScreenNameLabel(int x, int y)
    {
        Label label = new Label(x + 4, y + 3, title);
        label.setFGColor(0xCCCCCC);
        addRenderableWidget(label);
    }

    private void renderProgression(GuiGraphics pGuiGraphics)
    {
        var inventory = menu.contentHolder.inventory;
        boolean isInputSlotEmpty = inventory.getStackInSlot(LabellingMachineBlockEntity.INPUT_SLOT_ID).isEmpty();
        boolean isPaperSlotEmpty = inventory.getStackInSlot(LabellingMachineBlockEntity.PAPER_SLOT_ID).isEmpty();
        boolean isLabellingTagSlotEmpty = inventory.getStackInSlot(LabellingMachineBlockEntity.LABELLING_TAG_SLOT_ID).isEmpty();

        if (isInputSlotEmpty || isPaperSlotEmpty || isLabellingTagSlotEmpty)
            return;

        int x = leftPos;
        int y = topPos;

        float progression = 1 - ((float) menu.contentHolder.timer / menu.contentHolder.getProcessingDuration());
        int width = (int) (Math.floor(progression * 16));

        pGuiGraphics.blit(background, x + 113, y + 39, 177, 1, width, 16);
    }
}
