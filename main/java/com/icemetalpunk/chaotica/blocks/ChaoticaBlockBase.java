package com.icemetalpunk.chaotica.blocks;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ChaoticaBlockBase extends Block {

	public ChaoticaBlockBase(String name, Material materialIn) {
		super(materialIn);
		this.setUnlocalizedName(name).setRegistryName(new ResourceLocation(Chaotica.MODID, name)).setCreativeTab(Chaotica.tab);
	}

	protected void register() {
		GameRegistry.register(this);
		if (this instanceof ChaoticaTEBlock) {
			GameRegistry.registerTileEntity(((ChaoticaTEBlock) this).getTileEntityClass(), ((ChaoticaTEBlock) this).getTileEntityName());
		}

		String[] oreDict = this.getOreDict();
		if (oreDict != null) {
			for (String entry : oreDict) {
				OreDictionary.registerOre(entry, this);
			}
		}
	}

	// Override this if this block has an oredict entry.
	public String[] getOreDict() {
		return null;
	}

}
