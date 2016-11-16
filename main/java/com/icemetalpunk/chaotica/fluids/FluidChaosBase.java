package com.icemetalpunk.chaotica.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidChaosBase extends Fluid {

	protected byte[] color = new byte[] { (byte) 255, (byte) 255, (byte) 255, (byte) 255 };

	public FluidChaosBase(String fluidName, ResourceLocation still, ResourceLocation flowing) {
		super(fluidName, still, flowing);
		this.setUnlocalizedName(fluidName).setGaseous(false);
		FluidRegistry.registerFluid(this);
	}

	@Override
	public int getColor() {
		return this.color[0] << 24 | this.color[1] << 16 | this.color[2] << 8 | this.color[3];
	}

	public void setColor(byte r, byte g, byte b, byte a) {
		this.color[0] = r;
		this.color[1] = g;
		this.color[2] = b;
		this.color[3] = a;
	}

	public void setColor(int r, int g, int b, int a) {
		this.color[0] = (byte) r;
		this.color[1] = (byte) g;
		this.color[2] = (byte) b;
		this.color[3] = (byte) a;
	}

}
