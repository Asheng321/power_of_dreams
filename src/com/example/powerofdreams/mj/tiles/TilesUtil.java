package com.example.powerofdreams.mj.tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Utility methods for tiles.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public class TilesUtil {
	
	// Not instantiable
	private TilesUtil() {
		throw new UnsupportedOperationException();
	}
	
	public static List<Tile> sortTilesByNumber(List<Tile> tiles) {
		List<Tile> copyOfTiles = new ArrayList<Tile>(tiles);
		Collections.sort(copyOfTiles, StandardTileComparator.INSTANCE);
		return copyOfTiles;
	}
	
	public static List<Tile> sortTilesByNumber(Tile... tiles) {
		return sortTilesByNumber(new ArrayList<Tile>(Arrays.asList(tiles)));
	}
	
	/**
	 * Checks if the tile is simple (aka. non-yao-chu), that is, number tiles between two and eight.
	 * @param tile Tile to be checked
	 * @return true if simple.
	 */
	public static boolean isSimple(Tile tile) {
		return tile.isNumber() && 2 <= tile.getNumber() && tile.getNumber() <= 8;
	}
	
	public static boolean isTerminal(Tile tile) {
		return tile.isNumber() && (tile.getNumber() == 1 || tile.getNumber() == 9);
	}
	
	public static List<Tile> safeInstances(List<Tile> tiles) {
		List<Tile> result = Lists.newArrayList();
		for (Tile tile : tiles) {
			result.add(tile.safeInstance());
		}
		return result;
	}
	
	public static <T extends Tile> List<T> safeInstances(Class<T> clazz, List<T> tiles) {
		List<T> result = Lists.newArrayList();
		for (T tile : tiles) {
			result.add(clazz.cast(tile.safeInstance()));
		}
		return result;
	}
}
