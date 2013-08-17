package com.example.powerofdreams.mj.tiles;

import java.util.Comparator;

import com.google.common.collect.ImmutableList;

/**
 * Compares two tiles. Ordering rule is;
 * <pre>
 *   Iwan - Ryanwan - ... - Uwan - Aka-uwan - Rowan - ... - Chuwan -
 *   Ipin - Ryanpin - ... - Upin - Aka-upin - Ropin - ... - Chupin -
 *   Isou - Ryansou - ... - Usou - Aka-usou - Rosou - ... - Chusou -
 *   Ton - Nan - Sha - Pei - Haku - Hatsu - Chun.
 * </pre>
 * 
 * This class is a singleton. Use StandardTileComparator.INSTANCE to get an instance.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public enum StandardTileComparator implements Comparator<Tile> {
	INSTANCE;
	
	private static final ImmutableList<Tile> ordering = ImmutableList.<Tile>of(
			BasicTile.IWAN,
			BasicTile.RYANWAN,
			BasicTile.SANWAN,
			BasicTile.SUWAN,
			BasicTile.UWAN,
			BasicTile.AKA_UWAN,
			BasicTile.ROWAN,
			BasicTile.CHIWAN,
			BasicTile.PAWAN,
			BasicTile.CHUWAN,

			BasicTile.IPIN,
			BasicTile.RYANPIN,
			BasicTile.SANPIN,
			BasicTile.SUPIN,
			BasicTile.UPIN,
			BasicTile.AKA_UPIN,
			BasicTile.ROPIN,
			BasicTile.CHIPIN,
			BasicTile.PAPIN,
			BasicTile.CHUPIN,

			BasicTile.ISOU,
			BasicTile.RYANSOU,
			BasicTile.SANSOU,
			BasicTile.SUSOU,
			BasicTile.USOU,
			BasicTile.AKA_USOU,
			BasicTile.ROSOU,
			BasicTile.CHISOU,
			BasicTile.PASOU,
			BasicTile.CHUSOU,

			BasicTile.TON,
			BasicTile.NAN,
			BasicTile.SHA,
			BasicTile.PEI,
			BasicTile.HAKU,
			BasicTile.HATSU,
			BasicTile.CHUN);
	
	@Override
	public int compare(Tile lhs, Tile rhs) {
		return ordering.indexOf(lhs.asBasicTile()) - ordering.indexOf(rhs.asBasicTile());
	}

}
