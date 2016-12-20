package com.icemetalpunk.chaotica.tileentities;

import com.icemetalpunk.chaotica.blocks.ChaoticaTEBlock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class ChaoticaTEBase extends TileEntity {
	public boolean shouldHarvest() {
		return true;
	}

	public abstract ItemStack getHarvest();

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);

		IBlockState state = this.worldObj.getBlockState(this.pos);

		if (state == null || !(state.getBlock() instanceof ChaoticaTEBlock)) {
			return null;
		}

		Block block = state.getBlock();
		int metadata = block.getMetaFromState(state);

		System.out.println("Pos: " + this.pos + "; Meta: " + metadata + "; State: " + state + "; Tag: " + tag);
		return new SPacketUpdateTileEntity(this.pos, metadata, tag);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
	}
}
