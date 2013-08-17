package com.example.powerofdreams.mj.match.deck;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

import com.example.powerofdreams.mj.tiles.IdentifiedTile;
import com.example.powerofdreams.mj.tiles.Tile;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class Deck {

	private final List<Tile> tiles;
	private final int wanPaiCount;
	
	private final Queue<IdentifiedTile> deck;
	private final List<IdentifiedTile> wanPai;

	private int doraCount;
	
	public Deck(List<Tile> tiles, int wanPaiCount) {
		this.tiles = Preconditions.checkNotNull(tiles);
		this.wanPaiCount = wanPaiCount;
		this.deck = Lists.newLinkedList();
		this.wanPai = Lists.newArrayList();
		this.doraCount = 0;
		initialize();
	}
	
	private void initialize() {
		Preconditions.checkState(wanPaiCount <= tiles.size(),
				"Unable to construct wan pai, required %d but there are %d tiles",
				wanPaiCount, tiles.size());
		List<Tile> shuffledTiles = Lists.newArrayList(tiles);
		Collections.shuffle(shuffledTiles);

		List<IdentifiedTile> identifiedTiles = Lists.newArrayList();
		for (int i = 0; i < shuffledTiles.size(); ++i) {
			identifiedTiles.add(new IdentifiedTile(shuffledTiles.get(i), i));
		}
		for (int i = 0; i < wanPaiCount; ++i) {
			wanPai.add(identifiedTiles.get(i));
		}
		for (int i = wanPaiCount; i < tiles.size(); ++i) {
			deck.add(identifiedTiles.get(i));
		}
	}
	
	public IdentifiedTile draw() {
		if (deck.isEmpty()) {
			throw new EmptyDeckException();
		}
		return deck.poll();
	}
	
	public int getDeckSize() {
		return deck.size();
	}
	
	public int getMaxDoraCount() {
		return (wanPaiCount - 4) / 2;
	}

	public void discloseDora() {
		if (doraCount == getMaxDoraCount()) {
			throw new IllegalStateException("Maxmum number of dora is already opened: dora = " + doraCount);
		}
		++doraCount;
	}
	
	public List<Tile> getDora() {
		List<Tile> result = Lists.newArrayList();
		for (int i = 0; i < doraCount * 2; i += 2) {
			result.add(wanPai.get(i));
		}
		return result;
	}
	
	public List<Tile> getDoraAndInnerDora() {
		List<Tile> result = Lists.newArrayList();
		for (int i = 0; i < doraCount * 2; ++i) {
			result.add(wanPai.get(i));
		}		
		return result;
	}
}
