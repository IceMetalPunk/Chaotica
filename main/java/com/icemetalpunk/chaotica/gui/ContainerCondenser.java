package com.icemetalpunk.chaotica.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class ContainerCondenser extends ChaoticaContainerBase {

	public ContainerCondenser(IInventory playerInv, TileEntity tile) {
		// Last 4 parameters to super constructor are (x,y) of container and
		// player inventories on the screen, respectively.
		// 44,20
		super(playerInv, tile, 0, 0, 8, 51);

	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
