package com.icemetalpunk.chaotica.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ChaoticaContainerBase extends Container {

	protected TileEntity tileEntity;
	protected IInventory tileInv = null;
	protected int firstPlayerSlot;

	public ChaoticaContainerBase(IInventory playerInv, TileEntity tile, int blockInvX, int blockInvY, int playerInvX,
			int playerInvY) {
		this.tileEntity = tile;
		this.firstPlayerSlot = 0;
		if (tile instanceof IInventory) {
			this.tileInv = (IInventory) tile;
			this.firstPlayerSlot = this.tileInv.getSizeInventory();
		}

		/*
		 * For the sake of clarity, I'm defining some numbers to avoid confusing
		 * magic numbers in the code
		 */
		int slotWidth = 18;
		int slotHeight = 18;
		int mainInvHeight = 58;
		int hotbarSize = 9;

		// Block inventory
		if (this.tileInv != null) {
			for (int slotInd = 0; slotInd < tileInv.getSizeInventory(); ++slotInd) {
				this.addSlotToContainer(new Slot(tileInv, slotInd, blockInvX + slotInd * slotWidth, blockInvY));
			}
		}

		// Hotbar inventory
		for (int hotbarSlot = 0; hotbarSlot < hotbarSize; ++hotbarSlot) {
			this.addSlotToContainer(new Slot(playerInv, hotbarSlot, playerInvX + hotbarSlot * slotWidth, playerInvY + mainInvHeight));
		}

		// Player main inventory; 3 rows of 9 slots each
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				this.addSlotToContainer(new Slot(playerInv, row * 9 + col + hotbarSize, playerInvX + col * slotWidth, playerInvY + row * slotHeight));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}

	// The Shift+Click method. If not overridden here, it crashes!
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		ItemStack ret = null;
		Slot slot = this.inventorySlots.get(slotID);

		// Only do stuff if there's an item stack in the slot.
		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			ret = current.copy();

			// If they shift+click inside the container's slots...
			if (this.tileInv != null && slotID < this.tileInv.getSizeInventory()) {

				// Try to merge into their inventory, returning null if it's not
				// possible.
				if (!this.mergeItemStack(current, this.tileInv.getSizeInventory(), this.tileInv.getSizeInventory() + 36, false)) {
					return null;
				}

				slot.onSlotChange(current, ret);
			}

			// If they shift+click inside their inventory...
			else {

				// Try to merge into the container's slots. If you can't...
				if (this.tileInv == null || !this.mergeItemStack(current, 0, this.tileInv.getSizeInventory(), false)) {

					// If they shift+clicked in their hotbar, try to merge into
					if (slotID >= this.firstPlayerSlot && slotID < this.firstPlayerSlot + 9 && !this.mergeItemStack(current, this.firstPlayerSlot + 9, this.firstPlayerSlot + 36, false)) {
						// the main inventory, returning null if failed.
						return null;
					}

					// If they shift+clicked in the main inventory, try to merge
					// into their hotbar.
					else if (slotID >= this.firstPlayerSlot + 9 && slotID < this.firstPlayerSlot + 36 && !this.mergeItemStack(current, this.firstPlayerSlot, this.firstPlayerSlot + 9, false)) {
						return null;
					}
				}
			}

			// If the item stack was changed, replace it in the slot, removing
			// it if it's empty.
			if (current.stackSize <= 0) {
				slot.putStack(null);
			}
			else {
				slot.onSlotChanged();
			}

			// Fire the event that something's been picked up and then return
			// the resulting item stack.
			slot.onPickupFromSlot(player, current);
			return ret;
		}

		return null;
	}

}
