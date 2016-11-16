package com.icemetalpunk.chaotica.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ChaoticaSound extends SoundEvent {

	protected String stringName;

	public ChaoticaSound(String name, ResourceLocation soundLocation) {
		super(soundLocation);
		this.stringName = name;
		GameRegistry.register(this, soundLocation);
	}

}
