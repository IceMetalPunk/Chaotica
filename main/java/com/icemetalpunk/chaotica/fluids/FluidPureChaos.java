package com.icemetalpunk.chaotica.fluids;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.util.ResourceLocation;

public class FluidPureChaos extends FluidChaosBase {

	protected static final ResourceLocation stillModel = new ResourceLocation(Chaotica.MODID, "textures/fluids/pure_chaos_still.png");
	protected static final ResourceLocation flowingModel = new ResourceLocation(Chaotica.MODID, "textures/fluids/pure_chaos_flowing.png");

	public FluidPureChaos() {
		super("pure_chaos", stillModel, flowingModel);
	}

}
