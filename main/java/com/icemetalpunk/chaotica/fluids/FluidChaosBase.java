package com.icemetalpunk.chaotica.fluids;

import com.icemetalpunk.chaotica.ChaoticaUtils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidChaosBase extends Fluid {

	protected short[] color = new short[] { 255, 255, 255, 255 };

	public FluidChaosBase(String fluidName, ResourceLocation still, ResourceLocation flowing) {
		super(fluidName, still, flowing);
		this.setUnlocalizedName(fluidName).setGaseous(false);
		FluidRegistry.registerFluid(this);
	}

	@Override
	public int getColor() {
		return ChaoticaUtils.RGBAtoInt(this.color);
	}

	public void setColor(short r, short g, short b, short a) {
		this.color[0] = (short) (r & 0xff);
		this.color[1] = (short) (g & 0xff);
		this.color[2] = (short) (b & 0xff);
		this.color[3] = (short) (a & 0xff);
	}

	public void setColor(int r, int g, int b, int a) {
		this.color[0] = (short) (r & 0xff);
		this.color[1] = (short) (g & 0xff);
		this.color[2] = (short) (b & 0xff);
		this.color[3] = (short) (a & 0xff);
	}

}
