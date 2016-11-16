package com.icemetalpunk.chaotica.gui;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class ChaoticaUIBase extends GuiContainer {

	protected ResourceLocation texture;
	protected IInventory tileEntity;

	public ChaoticaUIBase(IInventory playerInv, IInventory tile, String textureName) {
		super(new ContainerCondenser(playerInv, tile));
		this.tileEntity = tile;
		this.texture = new ResourceLocation(Chaotica.MODID, "textures/gui/" + textureName + ".png");
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(this.texture);
		int marginHorizontal = (width - xSize) / 2;
		int marginVertical = (height - ySize) / 2;
		drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);
	}

}
