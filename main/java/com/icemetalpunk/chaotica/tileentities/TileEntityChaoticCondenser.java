package com.icemetalpunk.chaotica.tileentities;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

import com.icemetalpunk.chaotica.Chaotica;
import com.icemetalpunk.chaotica.ChaoticaUtils;
import com.icemetalpunk.chaotica.fluids.FluidTankChaos;
import com.icemetalpunk.chaotica.handlers.ChaoticaMessage;
import com.icemetalpunk.chaotica.handlers.ChaoticaPacketHandler;
import com.icemetalpunk.chaotica.sounds.ChaoticaSoundRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileEntityChaoticCondenser extends TileEntity implements ICapabilityProvider, ITickable {

	protected Fluid fluid = Chaotica.fluids.CORROSIVE_CHAOS;
	protected int capacity = 5 * Fluid.BUCKET_VOLUME;
	protected FluidTankChaos tank = new FluidTankChaos(this.capacity);
	protected int countdown = 40;
	protected int maxCountdown = 60; // Max amount it resets to

	public TileEntityChaoticCondenser() {
		this.tank.setTileEntity(this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank);
		}
		return super.getCapability(capability, facing);
	}

	// Ticks until the next check for blocks to convert to chaos
	public int getCountdown() {
		return this.countdown;
	}

	public int getMaxCountdown() {
		return this.maxCountdown;
	}

	public Fluid getFluid() {
		if (tank.getFluid() == null) {
			return null;
		}
		return this.tank.getFluid().getFluid();
	}

	public void setFluidAmount(int amount) {
		if (tank.getFluid() == null) {
			tank.setFluid(new FluidStack(this.fluid, 0));
		}
		tank.getFluid().amount = amount;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound tankTag = tag.getCompoundTag("Tank");
		this.tank.readFromNBT(tankTag);
		this.countdown = tag.getInteger("Countdown");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagCompound tankTag = new NBTTagCompound();
		tank.writeToNBT(tankTag);
		tag.setTag("Tank", tankTag);
		tag.setInteger("Countdown", this.countdown);
		return tag;
	}

	public int fill(int amount, boolean doFill) {
		return this.tank.fill(new FluidStack(this.fluid, amount), doFill);
	}

	@Override
	public void update() {
		String[] debug = new String[] { "server", "client" };
		if (--this.countdown == 0) {
			this.countdown = this.maxCountdown;
			if (!this.worldObj.isRemote) {
				IBlockState east = this.getWorld().getBlockState(this.pos.east());
				IBlockState west = this.getWorld().getBlockState(this.pos.west());
				IBlockState north = this.getWorld().getBlockState(this.pos.north());
				IBlockState south = this.getWorld().getBlockState(this.pos.south());
				IBlockState up = this.getWorld().getBlockState(this.pos.up());
				IBlockState down = this.getWorld().getBlockState(this.pos.down());

				// Iterate over the amounts map and add the appropriate amount
				// of fluid if applicable
				boolean playSound = false;
				Iterator<Map.Entry<ChaoticaUtils.BlockPair, Integer>> it = ChaoticaUtils.pairAmounts.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<ChaoticaUtils.BlockPair, Integer> entry = it.next();
					ChaoticaUtils.BlockPair pair = entry.getKey();
					int amount = entry.getValue().intValue();

					if (pair.isPair(east.getBlock(), west.getBlock())) {
						if (this.fill(amount, true) > 0) {
							playSound = true;
							this.getWorld().destroyBlock(this.pos.east(), false);
							this.getWorld().destroyBlock(this.pos.west(), false);
						}
					}
					if (pair.isPair(north.getBlock(), south.getBlock())) {
						if (this.fill(amount, true) > 0) {
							playSound = true;
							this.getWorld().destroyBlock(this.pos.north(), false);
							this.getWorld().destroyBlock(this.pos.south(), false);
						}
					}
					if (pair.isPair(up.getBlock(), down.getBlock())) {
						if (this.fill(amount, true) > 0) {
							playSound = true;
							this.getWorld().destroyBlock(this.pos.up(), false);
							this.getWorld().destroyBlock(this.pos.down(), false);
						}
					}
				}
				if (playSound) {
					this.worldObj.playSound(null, this.getPos(), ChaoticaSoundRegistry.CONDENSE_CHAOS, SoundCategory.BLOCKS, 1.0f, 1.0f);
					ChaoticaPacketHandler.INSTANCE.sendToAll(new ChaoticaMessage(ChaoticaMessage.MessageTypes.CONDENSER_LEVEL, this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.tank.getFluidAmount()));
				}
			}
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);

		IBlockState state = this.worldObj.getBlockState(this.pos);
		Block block = state.getBlock();
		int metadata = block.getMetaFromState(state);

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
