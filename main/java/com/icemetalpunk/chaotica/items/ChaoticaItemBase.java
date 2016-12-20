package com.icemetalpunk.chaotica.items;

import com.icemetalpunk.chaotica.Chaotica;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ChaoticaItemBase extends Item {
	public ChaoticaItemBase(String name) {
		super();
		this.setUnlocalizedName(name).setRegistryName(new ResourceLocation(Chaotica.MODID, name)).setCreativeTab(Chaotica.tab);
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
		ModelResourceLocation modelLocation = new ModelResourceLocation(this.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(this, 0, modelLocation);
	}

	// Override this if this item has an oredict entry.
	public String[] getOreDict() {
		return null;
	}
}
