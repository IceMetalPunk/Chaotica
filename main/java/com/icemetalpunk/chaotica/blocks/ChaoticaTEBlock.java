package com.icemetalpunk.chaotica.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ChaoticaTEBlock extends ChaoticaBlockBase {

	public ChaoticaTEBlock(String name, Material materialIn) {
		super(name, materialIn);
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
