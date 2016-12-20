package com.icemetalpunk.chaotica.fluids;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.util.ResourceLocation;

public class FluidPureChaos extends FluidChaosBase {

	// TODO: Add actual textures for these resource locations.
	protected static final ResourceLocation stillModel = new ResourceLocation(Chaotica.MODID, "fluids/pure_chaos_still.png");
	protected static final ResourceLocation flowingModel = new ResourceLocation(Chaotica.MODID, "fluids/pure_chaos_flowing.png");

	public FluidPureChaos() {
		super("pure_chaos", stillModel, flowingModel);
	}

}
