package com.icemetalpunk.chaotica.fluids;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.util.ResourceLocation;

public class FluidCorrosiveChaos extends FluidChaosBase {

	// TODO: Add actual textures for these resource locations.
	protected static final ResourceLocation stillModel = new ResourceLocation(Chaotica.MODID, "fluids/corrosive_chaos_still");
	protected static final ResourceLocation flowingModel = new ResourceLocation(Chaotica.MODID, "fluids/corrosive_chaos_flowing");

	public FluidCorrosiveChaos() {
		super("corrosive_chaos", stillModel, flowingModel);
		this.setColor(245, 255, 163, 255);
	}

}
