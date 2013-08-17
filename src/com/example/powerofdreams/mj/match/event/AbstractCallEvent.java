package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.playerhand.Meld;

public abstract class AbstractCallEvent extends AbstractMeldingEvent {

	protected final PlayerWind callee;
	
	protected AbstractCallEvent(PlayerWind actor, PlayerWind callee, Meld meld) {
		super(actor, meld);
		this.callee = callee;
	}
	
	public PlayerWind getCallee() {
		return callee;
	}
}
