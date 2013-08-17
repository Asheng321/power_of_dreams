package com.example.powerofdreams.mj.player;

import com.example.powerofdreams.mj.tiles.BasicTile;
import com.example.powerofdreams.mj.tiles.Tile;
import com.example.powerofdreams.mj.tiles.TileType;
import com.google.common.base.Preconditions;

public class DiscardedTile implements Tile {

	Tile tile;
	boolean isDrawn;
	boolean isCalled;
	boolean isReach;
	
	private DiscardedTile(Tile tile, boolean isDrawn, boolean isCalled, boolean isReach) {
		this.tile = Preconditions.checkNotNull(tile);
		this.isDrawn = isDrawn;
		this.isCalled = isCalled;
		this.isReach = isReach;
	}
	
	@Override
	public TileType getTileType() {
		return tile.getTileType();
	}

	@Override
	public boolean isNumber() {
		return tile.isNumber();
	}

	@Override
	public int getNumber() {
		return tile.getNumber();
	}

	@Override
	public boolean isEquivalentTo(Tile other) {
		return tile.isEquivalentTo(other);
	}

	@Override
	public DiscardedTile safeInstance() {
		return this;
	}
	
	@Override
	public BasicTile asBasicTile() {
		return tile.asBasicTile();
	}
	
	public Tile getTile() {
		return tile;
	}

	public boolean isDrawn() {
		return isDrawn;
	}
	
	public boolean isCalled() {
		return isCalled;
	}
	
	public boolean isReach() {
		return isReach;
	}
	
	public static DiscardedTile of(Tile tile, boolean isDrawn, boolean isCalled, boolean isReach) {
		return new DiscardedTile(tile, isDrawn, isCalled, isReach);
	}
}
