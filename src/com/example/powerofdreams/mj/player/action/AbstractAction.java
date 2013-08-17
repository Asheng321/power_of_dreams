package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.google.common.base.Preconditions;

public abstract class AbstractAction implements Action {

	protected final PlayerWind actor;
	
	protected AbstractAction(PlayerWind actor) {
		this.actor = Preconditions.checkNotNull(actor);
	}
	
	@Override
	public PlayerWind getActor() {
		return actor;
	}
}
