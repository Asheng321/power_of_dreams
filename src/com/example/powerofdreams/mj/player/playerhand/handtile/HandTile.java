package com.example.powerofdreams.mj.player.playerhand.handtile;

import com.example.powerofdreams.mj.tiles.IdentifiedTile;
import com.example.powerofdreams.mj.tiles.BasicTile;
import com.example.powerofdreams.mj.tiles.Tile;
import com.example.powerofdreams.mj.tiles.TileType;
import com.google.common.base.Preconditions;

public class HandTile extends IdentifiedTile {

	public static final HandTile EMPTY_TILE = HandTile.of(
			BasicTile.EMPTY, IdentifiedTile.ID_EMPTY, DrawSource.DECK, true, 0);
	
	private DrawSource drawSource;
	private boolean isPublic;
	private int acquiredTurn;
	
	private HandTile(Tile tile, int id, DrawSource drawSource, boolean isPublic, int acquiredTurn) {
		super(tile, id);
		this.drawSource = Preconditions.checkNotNull(drawSource);
		this.isPublic = isPublic;
		this.acquiredTurn = acquiredTurn;
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
	public HandTile safeInstance() {
		return this;
	}

	@Override
	public BasicTile asBasicTile() {
		return tile.asBasicTile();
	}

	public Tile getTile() {
		return tile;
	}
	
	public DrawSource getDrawSource() {
		return drawSource;
	}
	
	public boolean isPublic() {
		return isPublic;
	}
	
	public int getAcquiredTurn() {
		return acquiredTurn;
	}
	
	public static HandTile of(Tile tile, int id, DrawSource drawSource, boolean isPublic, int acquiredTurn) {
		return new HandTile(tile, id, drawSource, isPublic, acquiredTurn);
	}
	
	public static HandTile of(IdentifiedTile tile, DrawSource drawSource, boolean isPublic, int acquiredTurn) {
		return new HandTile(tile, tile.getId(), drawSource, isPublic, acquiredTurn);
	}
}
