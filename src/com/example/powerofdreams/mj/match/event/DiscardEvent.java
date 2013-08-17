package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.playerhand.handtile.HandTile;
import com.google.common.base.Preconditions;

public class DiscardEvent implements Event {

	private final PlayerWind actor;
	private final HandTile tile;

	private DiscardEvent(PlayerWind actor, HandTile tile) {
		this.actor = Preconditions.checkNotNull(actor);
		this.tile = Preconditions.checkNotNull(tile).safeInstance();
	} 
	
	@Override
	public EventType getEventType() {
		return EventType.DISCARD;
	}
	
	public PlayerWind getActor() {
		return actor;
	}
	
	public HandTile getTile() {
		return tile.safeInstance();
	}
	
	public static DiscardEvent of(PlayerWind actor, HandTile tile) {
		return new DiscardEvent(actor, tile);
	}
}
