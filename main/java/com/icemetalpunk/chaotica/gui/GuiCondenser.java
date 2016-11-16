package com.icemetalpunk.chaotica.gui;

import com.icemetalpunk.chaotica.tileentities.TileEntityChaoticCondenser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class GuiCondenser extends ChaoticaUIBase {

	public GuiCondenser(IInventory playerInv, IInventory tile) {
		super(playerInv, tile, "container/chaos_condenser");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

		/* FIXME: Implement proper fluid tank rendering. */
		int left = 168, top = 30, right = 186, bottom = 90;
		IFluidTankProperties[] tanks = ((TileEntityChaoticCondenser) this.tileEntity).getTankProperties();
		IFluidTankProperties tank = tanks[0];
		int color = 0xFFFFFFFF, amount = 0;
		if (tank.getContents() != null && tank.getContents().getFluid() != null) {
			color = tank.getContents().getFluid().getColor();
			amount = tank.getContents().amount;
		}

		// Render fluid amount in text
		FontRenderer fontRender = Minecraft.getMinecraft().fontRendererObj;
		fontRender.drawString(amount + " mb", 5, 5, 0, false);

		this.drawRect(left, top, right, bottom, color);
	}

}
