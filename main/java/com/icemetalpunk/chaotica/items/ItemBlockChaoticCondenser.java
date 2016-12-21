package com.icemetalpunk.chaotica.items;

import java.util.List;

import com.icemetalpunk.chaotica.blocks.ChaoticaBlockRegistry;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBlockChaoticCondenser extends ChaoticaItemBlockBase {

	public ItemBlockChaoticCondenser() {
		super("chaotic_condenser", ChaoticaBlockRegistry.CHAOTIC_CONDENSER);
		this.register();
		this.registerModel();
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add("Creates Corrosive Chaos from dissimilarity in the world.");
		if (GuiScreen.isShiftKeyDown()) {
			int itemFilledAmount = 0;
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag")) {
				NBTTagCompound tag = stack.getTagCompound().getCompoundTag("BlockEntityTag");
				NBTTagCompound tankTag = tag.getCompoundTag("Tank");
				if (!tankTag.hasKey("Empty")) {
					itemFilledAmount = tankTag.getInteger("Amount");
				}
			}
			tooltip.add(ChatFormatting.AQUA + "Amount Filled: " + itemFilledAmount + " mb" + ChatFormatting.RESET);
		}
		else {
			tooltip.add(ChatFormatting.DARK_GRAY + "Press SHIFT for more info." + ChatFormatting.RESET);
		}
	}

}
