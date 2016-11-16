package com.icemetalpunk.chaotica.gui;

import java.util.HashMap;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ChaoticaGuiHandler implements IGuiHandler {

	public static enum Guis {
		CONDENSER
	};

	private static HashMap<Integer, Class<? extends Container>> containers = new HashMap<Integer, Class<? extends Container>>();
	private static HashMap<Integer, Class<? extends GuiScreen>> uis = new HashMap<Integer, Class<? extends GuiScreen>>();

	// This constructor initializes the associations between server and
	// client-side containers and GUI elements.
	public ChaoticaGuiHandler() {
		containers.put(Guis.CONDENSER.ordinal(), ContainerCondenser.class);
		uis.put(Guis.CONDENSER.ordinal(), GuiCondenser.class);
	}

	// Generic getElement to be called on server or client according to the
	// other two methods.
	private Object getElement(int ID, EntityPlayer player, World world, int x, int y, int z, HashMap list) {
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if (list.containsKey(Integer.valueOf(ID))) {
			try {
				return ((Class) (list.get(Integer.valueOf(ID)))).getConstructors()[0].newInstance(player.inventory,
						(IInventory) tile);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return getElement(ID, player, world, x, y, z, containers);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return getElement(ID, player, world, x, y, z, uis);
	}

}
