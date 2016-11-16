package com.icemetalpunk.chaotica.blocks;

import javax.annotation.Nullable;

import com.icemetalpunk.chaotica.Chaotica;
import com.icemetalpunk.chaotica.gui.ChaoticaGuiHandler;
import com.icemetalpunk.chaotica.tileentities.TileEntityChaoticCondenser;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/* TODO: Drop tagged/filled itemstack when harvested */

public class BlockChaoticCondenser extends ChaoticaTEBlock {

	public BlockChaoticCondenser() {
		super("chaotic_condenser", Material.ROCK);
		this.setHardness(3.5F);
		this.setSoundType(SoundType.STONE);
		this.register();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack item, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Chaotica.instance, ChaoticaGuiHandler.Guis.CONDENSER.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityChaoticCondenser.class;
	}

	@Override
	public String getTileEntityName() {
		return "ChaoticCondenser";
	}

}
