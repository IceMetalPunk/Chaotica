/* TODO: NEXT:
 * ~ Items and ItemBlocks for BlockChaoticCondenser
 * ~ Assets (models, textures, lang files, etc.) for items and blocks
 * ~ GUI for BlockChaoticCondenser (does nothing but show fluid tank)
 * ~ Blocks and buckets for in-world fluids
 * ~~> Including corrosive chaos
 */
/*  TODO: FUTURE: See original 1.7.10 Chaotica.java source for details: 
*  ~ Chaos Cores (upgrades)
*  ~ Chaos purifier (converts corrosive chaos into pure chaos)
*  ~ Chaotic tank (general fluid tank)
*  ~ Chaotic Pipes (for fluids and, maybe, items)
*  ~ Chaotic Destroyer (block breaker)
*  ~ Chaotic Actualizer (block placer)
*  ~ Chaotic Freezer (produces ice, packed ice, stone, and obsidian from liquids)
*  ~ Entropic Absorber (chaos-powered battery)
*  ~ Entropic Infuser (chaos infuser to charge absorber)
*  ~ Nickel, Flammelium & Nickelous Flamelium
*  ~~> Used in higher-tier recipes & Taskmaster tools (mind-controlling ones)
*  ~Taskmaster Tools (given to enemies to make them work for you by altering AI)
*  ~~> Mining Taskmaster = pickaxe = mines for you
*  ~~> Warring Taskmaster = sword = fights hostiles & angry neutrals around you
*  ~~> Harvesting Taskmaster = hoe = harvests and replants crops
*  ~~> Felling Taskmaster = axe = chops and replants trees
*  ~~> Digging Taskmaster = shovel = ?? digs straight down to bedrock in a 3x3 ??
*  ~ Task Perimeter (all mobs with Taskmasters in the delineated area won't leave it)
*  ~ Chaos Staff (uses absorber to power spells based on items in offhand)
*  ~ Chaotic Transporter (item to teleport you to Chaotic Hubs using absorber chaos power)
*  ~ Chaotic Hub (blocks that set locations to transport to with transporter)
*  ~ Transporter Pedestal (displays tranporter and uses block's own chaos to power teleportations when activated)
*  ~ Entropy Analyzer ("net" to catch mob types)
*  ~ Chaotic Spawner (...spawner)
*  ~ Chaotic Levitators (boots that use absorber chaos to fly)
*  ~ Chaotic Grinder (mob grinder that uses chaos as fuel)
*  ~ Chaotic Yielder (harvester that uses chaos as fuel)
*  ~ Chaos Root (plant that reverses/prevents crop growth in its range & causes wither)
*  ~ Chaotic Planter (planter that uses chaos as fuel; risks planting Chaos Root)
*  ~ Chaotic Dispeller (teleports mobs out of range using chaos as fuel)
*  ~ Chaotic Lure (attracts mobs)
*  ~ Chaotic XP Bank
*  ~ Chaotic Collector (vacuum with specific interactions with corrosive chaos blocks)
*  ~ Guide Book
*  ~ WAILA / OneProbe integration
*/

package com.icemetalpunk.chaotica;

import com.icemetalpunk.chaotica.blocks.ChaoticaBlockRegistry;
import com.icemetalpunk.chaotica.fluids.ChaoticaFluidRegistry;
import com.icemetalpunk.chaotica.gui.ChaoticaGuiHandler;
import com.icemetalpunk.chaotica.handlers.ChaoticaMessage;
import com.icemetalpunk.chaotica.handlers.ChaoticaMessage.ChaoticaMessageHandler;
import com.icemetalpunk.chaotica.handlers.ChaoticaPacketHandler;
import com.icemetalpunk.chaotica.items.ChaoticaItemRegistry;
import com.icemetalpunk.chaotica.sounds.ChaoticaSoundRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Chaotica.MODID, version = Chaotica.VERSION)
public class Chaotica {
	public static final String MODID = "chaotica";
	public static final String VERSION = "1.0";

	@Instance
	public static Chaotica instance = new Chaotica();

	public static CreativeTabs tab = new CreativeTabs(CreativeTabs.getNextID(), "chaotica") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.STONE); // Change to a better
														// icon
		}
	};

	public static ChaoticaFluidRegistry fluids = new ChaoticaFluidRegistry();
	public static ChaoticaBlockRegistry blocks = new ChaoticaBlockRegistry();
	public static ChaoticaItemRegistry items = new ChaoticaItemRegistry();
	public static ChaoticaSoundRegistry sounds = new ChaoticaSoundRegistry();

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Chaotica.instance, new ChaoticaGuiHandler());

		// Message registration
		int messageID = 0;
		ChaoticaPacketHandler.INSTANCE.registerMessage(ChaoticaMessageHandler.class, ChaoticaMessage.class, messageID++, Side.CLIENT);
	}
}
