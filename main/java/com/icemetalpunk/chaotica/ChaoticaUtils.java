package com.icemetalpunk.chaotica;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/* A class filled with handy utilities for other classes to use. */
public class ChaoticaUtils {

	public static int RGBAtoInt(short r, short g, short b, short a) {
		return ((a << 24) & 0xff000000) | ((r << 16) & 0x00ff0000) | ((g << 8) & 0x0000ff00) | (b & 0x000000ff);
	}

	public static int RGBAtoInt(short[] col) {
		return ChaoticaUtils.RGBAtoInt(col[0], col[1], col[2], col[3]);
	}

	/*
	 * A simple way of storing pairs of blocks and checking them in an
	 * order-irrelevant way. Since chaos magic is about dissimilarities, this
	 * will be used a lot.
	 */
	public static class BlockPair {
		public Block first;
		public Block second;

		public BlockPair(Block one, Block two) {
			this.first = one;
			this.second = two;
		}

		// Order-irrelevant check to see if the two blocks make up this pair.
		public boolean isPair(Block one, Block two) {
			return (this.first == one && this.second == two) || (this.first == two && this.second == one);
		}
	}

	/*
	 * The map of block pairs to amounts of chaos produced by them
	 */
	public static HashMap<ChaoticaUtils.BlockPair, Integer> pairAmounts = new HashMap<ChaoticaUtils.BlockPair, Integer>();

	static {
		// Hot and cold
		pairAmounts.put(new BlockPair(Blocks.ICE, Blocks.FIRE), 10);
		pairAmounts.put(new BlockPair(Blocks.PACKED_ICE, Blocks.LAVA), 20);
		pairAmounts.put(new BlockPair(Blocks.ICE, Blocks.LAVA), 15);
		pairAmounts.put(new BlockPair(Blocks.PACKED_ICE, Blocks.FIRE), 15);

		// Lava and water can both flow/break infinitely, so make them worthless
		pairAmounts.put(new BlockPair(Blocks.LAVA, Blocks.WATER), 2);

		pairAmounts.put(new BlockPair(Blocks.PACKED_ICE, Blocks.field_189877_df), 10); // Magma;
																						// not
																						// deobfuscated?
		pairAmounts.put(new BlockPair(Blocks.ICE, Blocks.field_189877_df), 5); // Magma;
																				// not
																				// deobfuscated?

		// Hard and soft
		pairAmounts.put(new BlockPair(Blocks.SLIME_BLOCK, Blocks.OBSIDIAN), 30);

		// Heavy and light
		pairAmounts.put(new BlockPair(Blocks.WOOL, Blocks.ANVIL), 20);

		// Alive and dead
		pairAmounts.put(new BlockPair(Blocks.LEAVES, Blocks.field_189880_di), 17); // Bone
																					// blocks;
																					// not
																					// deobfuscated?
		pairAmounts.put(new BlockPair(Blocks.LEAVES2, Blocks.field_189880_di), 17); // Bone
																					// blocks;
																					// not
																					// deobfuscated?
	}
}
