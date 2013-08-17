package com.example.powerofdreams.mj.player.playerhand;

import java.util.List;

import com.example.powerofdreams.mj.tiles.Tile;
import com.example.powerofdreams.mj.tiles.TilesUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Class represents a single meld (mentsu).
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public class Meld {
	public enum MeldType {
		SEQUENCE,  // shuntsu
		TRIPLET,  // kotsu
		QUAD,  // kantsu
		EYE,  // atama
		SIDE;  // nuki dora
	}
	
	private MeldType meldType;
	private ImmutableList<Tile> tiles;
	/**
	 * True if all tiles which consists the meld comes from an owner's draw.
	 * This value does not stands for that the meld is not created by pong, chew or kong
	 * because a meld created by a rong is considered to be non-closed as well.
	 * Moreover, hidden kongs (an-kan) is considered to be a closed meld.
	 */
	private boolean isClosed;

	private Meld(MeldType meldType, List<Tile> tiles, boolean isClosed) {
		this.meldType = meldType;
		this.tiles = ImmutableList.<Tile>copyOf(tiles);
		this.isClosed = isClosed;
	}
	
	public MeldType getMeldType() {
		return meldType;
	}
	
	public ImmutableList<Tile> getTiles() {
		return  tiles;
	}
	
	public boolean getIsClosed() {
		return isClosed;
	}
	
	/**
	 * Checks if two meld are equal, that is, two meld has identical meld type and
	 * i-th tiles of the melds are equivalent respectively.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Meld)) {
			return false;
		}
		Meld meld = (Meld) other;
		if (getMeldType() != meld.getMeldType()) {
			return false;
		}
		return Iterables.elementsEqual(getTiles(), meld.getTiles());
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + meldType.hashCode();
		for (Tile tile : getTiles()) {
			result = result * 31 + tile.hashCode();
		}
		return result;
	}
	
	public Meld safeInstance() {
		return new Meld(meldType, TilesUtil.safeInstances(tiles), isClosed);
	}

	public static Meld createSequence(Tile first, Tile second, Tile third, boolean isClosed) {
		List<Tile> tiles = TilesUtil.sortTilesByNumber(first, second, third);
		Preconditions.checkArgument(
				tiles.get(0).getTileType() == tiles.get(1).getTileType() &&
				tiles.get(0).getTileType() == tiles.get(2).getTileType());
		Preconditions.checkArgument(
				tiles.get(0).getNumber() + 1 == tiles.get(1).getNumber() &&
				tiles.get(1).getNumber() + 1 == tiles.get(2).getNumber());
		return new Meld(MeldType.SEQUENCE, tiles, isClosed);
	}
	
	public static Meld createTriplet(Tile first, Tile second, Tile third, boolean isClosed) {
		List<Tile> tiles = TilesUtil.sortTilesByNumber(first, second, third);
		Preconditions.checkArgument(
				tiles.get(0).isEquivalentTo(tiles.get(1)) && 
				tiles.get(0).isEquivalentTo(tiles.get(2)));
		return new Meld(MeldType.TRIPLET, tiles, isClosed);
	}

	public static Meld createQuad(Tile first, Tile second, Tile third, Tile fourth, boolean isClosed) {
		List<Tile> tiles = TilesUtil.sortTilesByNumber(first, second, third, fourth);
		Preconditions.checkArgument(
				tiles.get(0).isEquivalentTo(tiles.get(1)) && 
				tiles.get(0).isEquivalentTo(tiles.get(2)) && 
				tiles.get(0).isEquivalentTo(tiles.get(3)));
		return new Meld(MeldType.TRIPLET, tiles, isClosed);		
	}
	
	public static Meld createEye(Tile first, Tile second, boolean isClosed) {
		Preconditions.checkArgument(first.isEquivalentTo(second));
		return new Meld(MeldType.EYE, Lists.newArrayList(first, second), isClosed);
	}
	
	public static Meld createSide(Tile tile) {
		return new Meld(MeldType.SIDE, Lists.newArrayList(tile), false);
	}
}
