package com.icemetalpunk.chaotica.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class GuiCondenser extends ChaoticaUIBase {

	public GuiCondenser(IInventory playerInv, TileEntity tile) {
		super(playerInv, tile, "container/chaos_condenser");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

		int width = 17, height = 60;
		int left = 168, right = left + width, bottom = 90, top = bottom - height;

		// Get fluid information
		FluidTank tank = (FluidTank) (((ICapabilityProvider) this.tileEntity).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null));
		int color = 0xFFFFFFFF, amount = 0;
		Fluid fluid = tank.getFluid().getFluid();
		if (fluid != null) {
			color = fluid.getColor();
			amount = tank.getFluidAmount();
		}
		top = bottom - (int) (height * (double) amount / (double) tank.getCapacity());

		// Draw container title
		FontRenderer fontRender = Minecraft.getMinecraft().fontRendererObj;
		String title = new TextComponentTranslation("container.chaotic_condenser").getUnformattedText();
		fontRender.drawString(title, this.xSize / 2 - fontRender.getStringWidth(title) / 2, 5, 0, false);

		// Render fluid amount in text
		// TODO: Make this a tooltip when moused over the rendered tank.
		fontRender.drawString(amount + " mb", 5, 25, 0, false);

		// Draw fluid tank filling. Needs to be scaled down by 0.5 for some
		// weird reason or else scaling is all off.
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		this.drawRect(left, top, right, bottom, color);
		GlStateManager.popMatrix();

	}

}
