package com.icemetalpunk.chaotica.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ChaoticaTEBlock extends ChaoticaBlockBase implements ITileEntityProvider {

	public ChaoticaTEBlock(String name, Material materialIn) {
		super(name, materialIn);
	}

	// Tile entity providers should provide the class and name of their tile
	// entity here for registration.
	public abstract Class<? extends TileEntity> getTileEntityClass();

	public abstract String getTileEntityName();

	// Generic createNewTileEntity so only the getTileEntityClass needs to be
	// specified.
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
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
