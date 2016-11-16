package com.icemetalpunk.chaotica.sounds;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ChaoticaSoundRegistry {
	public static final SoundEvent CONDENSE_CHAOS = new ChaoticaSound("condense_chaos",
			new ResourceLocation(Chaotica.MODID, "condense_chaos"));
}
