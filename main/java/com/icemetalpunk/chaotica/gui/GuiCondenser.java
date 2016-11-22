package com.icemetalpunk.chaotica.gui;

import com.icemetalpunk.chaotica.fluids.FluidTankChaos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class GuiCondenser extends ChaoticaUIBase {

	public GuiCondenser(IInventory playerInv, TileEntity tile) {
		super(playerInv, tile, "container/chaos_condenser");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

		int width = 17, height = 60;
		int left = 168, bottom = 90;

		// Draw container title
		FontRenderer fontRender = Minecraft.getMinecraft().fontRendererObj;
		String title = new TextComponentTranslation("container.chaotic_condenser").getUnformattedText();
		fontRender.drawString(title, this.xSize / 2 - fontRender.getStringWidth(title) / 2, 5, 0, false);

		// Get fluid information
		FluidTankChaos tank = (FluidTankChaos) (((ICapabilityProvider) this.tileEntity).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null));
		int amount = tank.getFluidAmount();

		// Render fluid amount in text
		// TODO: Make this a tooltip when moused over the rendered tank.
		if (mouseX >= left - this.guiLeft && mouseY >= bottom - height - this.guiTop && mouseX <= left + width - this.guiLeft && mouseY <= bottom - this.guiTop) {
			// fontRender.drawString(amount + " mb", mouseX - this.guiLeft,
			// mouseY - this.guiTop, 0, false);
		}
		// fontRender.drawString("(" + mouseX + ", " + mouseY + ")", 5, 30, 0,
		// false);
		fontRender.drawString(amount + " mb", 5, 30, 0, false);

		// Render tank
		tank.render(this, left, bottom, width, height);

	}

}
