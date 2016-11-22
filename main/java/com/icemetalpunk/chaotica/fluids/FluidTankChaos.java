package com.icemetalpunk.chaotica.fluids;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FluidTankChaos extends FluidTank {

	public FluidTankChaos(int capacity) {
		super(capacity);
	}

	// Render the fluid tank to a GUI.
	public void render(GuiScreen gui, int left, int bottom, int width, int height) {
		int right = left + width;
		int top = bottom - height;

		// Get fluid display data
		int color = 0xFFFFFFFF, amount = 0;
		Fluid fluid = this.getFluid().getFluid();
		if (fluid != null) {
			color = fluid.getColor();
			amount = this.getFluidAmount();
		}
		top = bottom - (int) (height * (double) amount / (double) this.getCapacity());

		// Draw fluid tank filling. Needs to be scaled down by 0.5 for some
		// weird reason or else scaling is all off.
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		gui.drawRect(left, top, right, bottom, color);
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
