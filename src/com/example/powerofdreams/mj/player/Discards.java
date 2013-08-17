package com.example.powerofdreams.mj.player;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class Discards {

	private final List<DiscardedTile> discards;
	
	public Discards() {
		this.discards = Lists.newArrayList();
	}
	
	public Discards(List<DiscardedTile> discards) {
		this.discards = Lists.newArrayList();
		for (DiscardedTile tile : discards) {
			add(tile);
		}
	}
	
	public Discards add(DiscardedTile tile) {
		this.discards.add(tile.safeInstance());
		return this;
	}
	
	public List<DiscardedTile> getDiscards() {
		List<DiscardedTile> result = Lists.newArrayList();
		for (DiscardedTile tile : discards) {
			result.add(tile.safeInstance());
		}
		return result;
	}
	
	public DiscardedTile get(int index) {
		return discards.get(index).safeInstance();
	}

	public int size() {
		return discards.size();
	}
	
	public void callLastTile() {
		call(size() - 1);
	}
	
	private void call(int index) {
		Preconditions.checkElementIndex(index, discards.size());
		DiscardedTile tile = discards.get(index);
		DiscardedTile calledTile = DiscardedTile.of(tile.getTile(), tile.isDrawn(), true, tile.isReach());
		discards.set(index, calledTile);
	} 
	
	public Discards safeInstance() {
		return new Discards(discards);
	}
}
