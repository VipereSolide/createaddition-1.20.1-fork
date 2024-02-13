package com.rose.gods_retribution.content.block.waste_processor;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rose.gods_retribution.GodsRetribution;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WasteProcessorScreen extends AbstractContainerScreen<WasteProcessorMenu>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(GodsRetribution.MOD_ID, "textures/gui/waste_processor_screen.png");

	public WasteProcessorScreen(WasteProcessorMenu pMenu, Inventory pPlayerInventory, Component pTitle)
	{
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY)
	{
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);

		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

		renderProgressArrow(guiGraphics, x, y);
	}

	private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y)
	{
		if (menu.isCrafting())
			guiGraphics.blit(TEXTURE, x + 85, y + 30, 176, 0, 8, menu.getScaledProgress());
	}

	@Override
	protected void init()
	{
		super.init();
		inventoryLabelY = 10000;
		titleLabelY = 10000;
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float delta)
	{
		renderBackground(pGuiGraphics);
		super.render(pGuiGraphics, pMouseX, pMouseY, delta);
		renderTooltip(pGuiGraphics, pMouseX, pMouseY);
	}
}
