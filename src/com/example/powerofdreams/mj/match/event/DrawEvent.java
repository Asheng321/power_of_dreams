package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.tiles.Tile;
import com.google.common.base.Preconditions;

public class DrawEvent implements Event {

	private PlayerWind drawer;
	private Tile drawnTile;

	private DrawEvent(PlayerWind drawer, Tile drawnTile) {
		this.drawer = Preconditions.checkNotNull(drawer);
		this.drawnTile = Preconditions.checkNotNull(drawnTile).safeInstance();
	}
	
	@Override
	public EventType getEventType() {
		return EventType.DRAW;
	}

	public PlayerWind getDrawer() {
		return drawer;
	}
	
	public Tile getDrawnTile() {
		return drawnTile.safeInstance();
	}
	
	public static DrawEvent of(PlayerWind drawer, Tile drawnTile) {
		return new DrawEvent(drawer, drawnTile);
	}
}
