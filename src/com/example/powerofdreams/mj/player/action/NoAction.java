package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.google.common.base.Preconditions;


public class NoAction implements Action {

	private PlayerWind actor;
	
	private NoAction(PlayerWind actor) {
		this.actor = Preconditions.checkNotNull(actor);
	}
	
	public PlayerWind getActor() {
		return actor;
	}
	
	@Override
	public ActionType getActionType() {
		return ActionType.NO_ACTION;
	}

	public NoAction of(PlayerWind actor) {
		return new NoAction(actor);
	}
}
