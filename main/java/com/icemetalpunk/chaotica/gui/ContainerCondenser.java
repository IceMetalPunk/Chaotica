package com.icemetalpunk.chaotica.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class ContainerCondenser extends ChaoticaContainerBase {

	public ContainerCondenser(IInventory playerInv, IInventory tile) {
		// Last 4 parameters to super constructor are (x,y) of container and
		// player inventories on the screen, respectively.
		super(playerInv, tile, 44, 20, 8, 51);

	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
