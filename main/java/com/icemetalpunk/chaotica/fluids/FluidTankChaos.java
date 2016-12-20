package com.icemetalpunk.chaotica.fluids;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.client.config.GuiUtils;

public class FluidTankChaos extends FluidTank {

	public FluidTankChaos(int capacity) {
		super(capacity);
	}

	public void render(GuiScreen gui, int left, int bottom, int width, int height) {
		render(gui, left, bottom, width, height, 0.0f);
	}

	// Render the fluid tank to a GUI.
	public void render(GuiScreen gui, int left, int bottom, int width, int height, float zLevel) {
		int right = left + width;
		int top = bottom - height;

		// Get fluid display data
		int color = 0xFFFFFFFF, amount = 0;
		ResourceLocation tex = null;
		FluidStack fluidStack = this.getFluid();
		if (fluidStack != null) {
			Fluid fluid = this.getFluid().getFluid();
			if (fluid != null) {
				color = fluid.getColor();
				amount = this.getFluidAmount();
				tex = fluid.getStill();
			}
		}
		int renderHeight = (int) (height * (double) amount / (double) this.getCapacity());
		top = bottom - renderHeight;

		// Draw fluid tank filling. Needs to be scaled down by 0.5 for some
		// weird reason or else scaling is all off.
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5f, 0.5f, 0.5f);

		// If not texture found for the fluid, draw based on registered color.
		if (tex == null) {
			gui.drawRect(left, top, right, bottom, color);
		}
		else {
			// Or draw the texture.
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
			GuiUtils.drawTexturedModalRect(left, top, 0, 0, width, renderHeight, zLevel);
			GlStateManager.enableBlend();
		}

		GlStateManager.popMatrix();
	}

	@Override
	public boolean canFillFluidType(FluidStack stack) {
		if (this.fluid == null) {
			return true;
		}
		boolean isSame = this.fluid.amount > 0 && stack.isFluidEqual(this.fluid);
		return this.canFill && (isSame || (this.fluid.amount <= 0 && (stack.getFluid() instanceof FluidChaosBase)));
	}

}
