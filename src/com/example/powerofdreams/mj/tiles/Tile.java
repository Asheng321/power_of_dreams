package com.example.powerofdreams.mj.tiles;

public interface Tile {

	/**
	 * @return type of the tile.
	 */
	public TileType getTileType();
	
	/**
	 * Returns true if the tile is a number tile.
	 * @return true if a number tile.
	 */
	public boolean isNumber();
	
	/**
	 * Returns true if the tile is a character tile.
	 * @return true if a character tile.
	 */
	
	/**
	 * Returns the number of the tile. If the tile is a character tile, raise an exception.
	 * @return the number of the tile.
	 */
	public int getNumber();
	
	/**
	 * Returns true if a given tile is equivalent to this tile.
	 * An example pair of tiles which is equivalent but not same is an Upin and a Red Upin.
	 * @return true if equivalent.
	 */
	public boolean isEquivalentTo(Tile other);
	
	/**
	 * Returns a copy of the tile.
	 * @return a copy.
	 */
	public Tile safeInstance();
	
	/**
	 * Returns a equivalent BasicTile.
	 * @return a BasicTile
	 */
	public BasicTile asBasicTile();
}
