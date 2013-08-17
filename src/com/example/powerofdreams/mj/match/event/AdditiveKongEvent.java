package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.google.common.base.Preconditions;

public class AdditiveKongEvent implements Event {
	
	private final PlayerWind actor;
	private final Meld meld;

	private AdditiveKongEvent(PlayerWind actor, Meld meld) {
		this.actor = Preconditions.checkNotNull(actor);
		this.meld = Preconditions.checkNotNull(meld);
	}

	@Override
	public EventType getEventType() {
		return EventType.ADDITIVE_KONG;
	}
	
	public PlayerWind getActor() {
		return actor;
	}
	
	public Meld getMeld() {
		return meld;
	}
	
	public static AdditiveKongEvent of(PlayerWind actor, Meld meld) {
		return new AdditiveKongEvent(actor, meld);
	}
}
