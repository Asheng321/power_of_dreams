package com.example.powerofdreams.mj.player.playerhand;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.powerofdreams.common.collect.Collection3;
import com.example.powerofdreams.mj.player.playerhand.Meld.MeldType;
import com.example.powerofdreams.mj.player.playerhand.handtile.DrawSource;
import com.example.powerofdreams.mj.player.playerhand.handtile.HandTile;
import com.example.powerofdreams.mj.tiles.BasicTile;
import com.example.powerofdreams.mj.tiles.IdentifiedTile;
import com.example.powerofdreams.mj.tiles.StandardTileComparator;
import com.example.powerofdreams.mj.tiles.Tile;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class PlayerHand {

	public static class ImmutablePlayerHand {
		private List<HandTile> tiles;
		private HandTile drawnTile;
		private List<Meld> melds;
		private boolean isClosed;  // i.e. isMenzen

		private ImmutablePlayerHand(List<HandTile> tiles, HandTile drawnTile,
				List<Meld> melds, boolean isClosed) {
			this.tiles = Lists.newArrayList(tiles);
			this.drawnTile = Preconditions.checkNotNull(drawnTile);
			this.melds = Lists.newArrayList(melds);
			this.isClosed = isClosed;
		}

		public List<HandTile> getTiles() {
			return ImmutableList.<HandTile>copyOf(tiles);
		}

		public HandTile getDrawnTile() {
			return drawnTile;
		}

		public List<Meld> getMelds() {
			return ImmutableList.<Meld>copyOf(melds);
		}

		public boolean isClosed() {
			return isClosed;
		}
		
		private static ImmutablePlayerHand of(
				List<HandTile> tiles,
				HandTile drawnTile,
				List<Meld> melds,
				boolean isClosed) {
			return new ImmutablePlayerHand(tiles, drawnTile, melds, isClosed);
		}
		
		private boolean hasUnmeldedTile(int id) {
			for (HandTile tile : tiles) {
				if (tile.getId() == id) {
					return true;
				}
			}
			return drawnTile.getId() == id;
		}

		private HandTile getUnmeldedTile(int id) {
			for (HandTile tile : tiles) {
				if (tile.getId() == id) {
					return tile.safeInstance();
				}
			}
			if (drawnTile.getId() == id) {
				return drawnTile.safeInstance();
			}
			throw new NoSuchTileException();
		}

	}
	
	private ImmutablePlayerHand hand;
	
	private PlayerHand(ImmutablePlayerHand hand) {
		this.hand = Preconditions.checkNotNull(hand);
	}
	
	public ImmutablePlayerHand getImmutableHand() {
		return hand;
	}
		
	// --------
	// Mutation helpers
	// TODO: Consider changing them to private	
	public PlayerHand setDrawnTile(HandTile tile) {
		hand.drawnTile = Preconditions.checkNotNull(tile);
		return this;
	}
	
	public PlayerHand storeDrawnTile() {
		if (hand.drawnTile.isEquivalentTo(BasicTile.EMPTY)) {
			return this;
		}
		hand.tiles.add(hand.drawnTile);
		hand.drawnTile = HandTile.EMPTY_TILE;
		return this;
	}

	public HandTile removeUnmeldedTile(int id) {
		if (!hand.hasUnmeldedTile(id)) {
			throw new NoSuchTileException();
		}
		List<HandTile> result = Lists.newArrayList();
		HandTile removed = null;
		for (HandTile tile : hand.tiles) {
			if (tile.getId() != id) {
				result.add(tile);
			} else {
				removed = tile;
			}
		}
		hand.tiles = result;
		return removed;
	}

	public void addMeld(Meld meld) {
		hand.melds.add(meld);
	}
	
	public void removeMeld(Meld meld) {
		if (!hand.melds.remove(meld)) {
			throw new NoSuchMeldException();
		}
	}

	// --------
	// Mutators
	
	public void initialize(List<HandTile> tiles) {
		for (HandTile tile : tiles) {
			draw(tile.safeInstance());
		}
	}

	public PlayerHand draw(HandTile tile) {
		storeDrawnTile();
		hand.drawnTile = Preconditions.checkNotNull(tile).safeInstance();
		return this;
	}
	
	public HandTile discard(int id) {
		HandTile removed = removeUnmeldedTile(id);
		storeDrawnTile();
		return removed;
	}
	
	public Meld pong(int materialId1, int materialId2, Tile calledTile) {
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId1));
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId2));
		Preconditions.checkArgument(materialId1 != materialId2);
		
		HandTile material1 = hand.getUnmeldedTile(materialId1);
		HandTile material2 = hand.getUnmeldedTile(materialId2);
		removeUnmeldedTile(materialId1);
		removeUnmeldedTile(materialId2);
		Meld meld = Meld.createTriplet(material1, material2, calledTile, false);
		addMeld(meld);
		return meld;
	}
	
	public Meld chow(int materialId1, int materialId2, Tile calledTile) {
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId1));
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId2));
		Preconditions.checkArgument(materialId1 != materialId2);
		
		HandTile material1 = hand.getUnmeldedTile(materialId1);
		HandTile material2 = hand.getUnmeldedTile(materialId2);
		removeUnmeldedTile(materialId1);
		removeUnmeldedTile(materialId2);
		Meld meld = Meld.createSequence(material1, material2, calledTile, false);
		addMeld(meld);
		return meld;
	}

	public Meld hiddenKong(int materialId1, int materialId2, int materialId3, int materialId4) {
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId1));
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId2));
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId3));
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId4));
		List<Integer> ids = Lists.newArrayList(materialId1, materialId2, materialId3, materialId4);
		Preconditions.checkArgument(Collection3.unique(ids).size() == 4);
		
		List<HandTile> materials = Lists.newArrayList();
		for (int id : ids) {
			materials.add(hand.getUnmeldedTile(id));
			removeUnmeldedTile(id);
		}
		Meld meld = Meld.createQuad(
				materials.get(0), materials.get(1), materials.get(2), materials.get(3), true);
		addMeld(meld);
		return meld;
	}

	public Meld additiveKong(int id) {
		Preconditions.checkArgument(hand.hasUnmeldedTile(id));
		HandTile tile = hand.getUnmeldedTile(id);
		Meld triplet = null;
		for (Meld meld : hand.getMelds()) {
			if (meld.getMeldType() == MeldType.TRIPLET && meld.getTiles().get(0).isEquivalentTo(tile)) {
				triplet = meld;
				break;
			} 
		}
		if (triplet == null) {
			throw new InvalidKongException();
		}
		removeMeld(triplet);
		Meld meld = Meld.createQuad(
				triplet.getTiles().get(0), 
				triplet.getTiles().get(1),
				triplet.getTiles().get(2),
				tile,
				false);
		addMeld(meld);
		return meld;
	}
	
	public Meld robbingKong(int materialId1, int materialId2, int materialId3, Tile calledTile) {
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId1));
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId2));
		Preconditions.checkArgument(hand.hasUnmeldedTile(materialId3));
		List<Integer> ids = Lists.newArrayList(materialId1, materialId2, materialId3);
		Preconditions.checkArgument(Collection3.unique(ids).size() == 3);
		
		HandTile material1 = hand.getUnmeldedTile(materialId1);
		HandTile material2 = hand.getUnmeldedTile(materialId2);
		HandTile material3 = hand.getUnmeldedTile(materialId3);
		removeUnmeldedTile(materialId1);
		removeUnmeldedTile(materialId2);
		removeUnmeldedTile(materialId3);
		Meld meld = Meld.createQuad(material1, material2, material3, calledTile, false);
		addMeld(meld);
		return meld;
	}
	
	public Meld side(int id) {
		HandTile sideTile = removeUnmeldedTile(id);
		Meld meld = Meld.createSide(sideTile);
		addMeld(meld);
		return meld;
	}

	public void sort() {
		sort(StandardTileComparator.INSTANCE);
	}
	
	public void sort(Comparator<? super Tile> comparator) {
		Collections.sort(hand.tiles, comparator);
	}
	
	public static PlayerHand newInstance() {
		return new PlayerHand(ImmutablePlayerHand.of(
				Lists.<HandTile>newArrayList(),
				HandTile.of(BasicTile.EMPTY, IdentifiedTile.ID_EMPTY, DrawSource.DECK, true, 0),
				Lists.<Meld>newArrayList(),
				true));
	}
}
