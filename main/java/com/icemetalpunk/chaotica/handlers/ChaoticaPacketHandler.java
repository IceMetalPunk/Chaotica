package com.icemetalpunk.chaotica.handlers;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ChaoticaPacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Chaotica.MODID);
}
