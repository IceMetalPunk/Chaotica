package com.icemetalpunk.chaotica.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class ChaoticaTEBlock extends ChaoticaBlockBase {

	public ChaoticaTEBlock(String name, Material materialIn) {
		super(name, materialIn);
	}

	// Make sure when the block is dropped, the item retains the TE's data tags
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public final ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		int meta = state.getBlock().getMetaFromState(state);
		ItemStack ret = new ItemStack(this, 1, meta);
		NBTTagCompound nbt = new NBTTagCompound();
		TileEntity te = world.getTileEntity(pos);
		te.writeToNBT(nbt);
		ret.setTagCompound(nbt);
		return Lists.newArrayList(new ItemStack(this, 1, meta));
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	// When placed, if the item has a tag, set the tile entity's tag
	/*
	 * FIXME: When placed like this, tags are properly set, but tile entity
	 * fails to update ever after.
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag")) {
			NBTTagCompound tag = stack.getTagCompound().getCompoundTag("BlockEntityTag");
			worldIn.getTileEntity(pos).readFromNBT(tag);
		}
	}

	// Tile entity providers should provide the class and name of their tile
	// entity here for registration.
	public abstract Class<? extends TileEntity> getTileEntityClass();

	public abstract String getTileEntityName();

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	// Generic createNewTileEntity so only the getTileEntityClass needs to be
	// specified.
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		try {
			return this.getTileEntityClass().newInstance();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
