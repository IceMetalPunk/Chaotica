package com.icemetalpunk.chaotica.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class GuiCondenser extends ChaoticaUIBase {

	public GuiCondenser(IInventory playerInv, TileEntity tile) {
		super(playerInv, tile, "container/chaos_condenser");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

		int left = 168, top = 30, right = 186, bottom = 90;
		FluidTank tank = (FluidTank) (((ICapabilityProvider) this.tileEntity).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null));
		int color = 0xFFFFFFFF, amount = 0;
		if (tank.getFluid().getFluid() != null) {
			color = tank.getFluid().getFluid().getColor();
			amount = tank.getFluidAmount();
		}

		// Draw container title
		FontRenderer fontRender = Minecraft.getMinecraft().fontRendererObj;
		String title = new TextComponentTranslation("container.chaotic_condenser").getUnformattedText();
		fontRender.drawString(title, this.xSize / 2 - fontRender.getStringWidth(title) / 2, 5, 0, false);
		// Render fluid amount in text
		fontRender.drawString(amount + " mb", 5, 25, 0, false);

		/* FIXME: Implement proper fluid tank rendering. */
		GlStateManager.disableTexture2D();
		this.drawRect(left, top, right, bottom, color);
		GlStateManager.enableTexture2D();
	}

}
