package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.google.common.base.Preconditions;

public abstract class AbstractMeldingEvent implements Event {

	protected final PlayerWind actor;
	protected final Meld meld;

	protected AbstractMeldingEvent(PlayerWind actor, Meld meld) {
		this.actor = Preconditions.checkNotNull(actor);
		this.meld = Preconditions.checkNotNull(meld);
	}
	
	public PlayerWind getActor() {
		return actor;
	}
	
	public Meld getMeld() {
		return meld;
	}
}
