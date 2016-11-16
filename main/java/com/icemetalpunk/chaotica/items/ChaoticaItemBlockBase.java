package com.icemetalpunk.chaotica.items;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ChaoticaItemBlockBase extends ItemBlock {
	public ChaoticaItemBlockBase(String name, Block block) {
		super(block);
		this.setUnlocalizedName(name).setRegistryName(new ResourceLocation(Chaotica.MODID, name))
				.setCreativeTab(Chaotica.tab);
	}

	protected void register() {
		GameRegistry.register(this);
		String[] oreDict = this.getOreDict();
		if (oreDict != null) {
			for (String entry : oreDict) {
				OreDictionary.registerOre(entry, this);
			}
		}
	}

	protected void registerModel() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		ModelResourceLocation modelLocation = new ModelResourceLocation(this.block.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(this, 0, modelLocation);
	}

	// Override this if this item has an oredict entry.
	public String[] getOreDict() {
		return null;
	}
}
