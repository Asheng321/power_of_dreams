package com.example.powerofdreams.mj.tiles;

import com.google.common.base.Preconditions;


public class IdentifiedTile implements Tile {

	public static final int ID_EMPTY = -1;
	
	protected Tile tile;
	protected int id;

	public IdentifiedTile(Tile tile, int id) {
		this.tile = Preconditions.checkNotNull(tile);
		this.id = id;
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
	public BasicTile asBasicTile() {
		return tile.asBasicTile();
	}

	public int getId() {
		return id;
	}

	@Override
	public IdentifiedTile safeInstance() {
		return new IdentifiedTile(tile, id);
	}	
}
