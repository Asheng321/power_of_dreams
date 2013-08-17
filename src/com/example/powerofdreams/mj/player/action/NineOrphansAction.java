package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.google.common.base.Preconditions;

public class NineOrphansAction implements Action {

	private PlayerWind actor;
	
	private NineOrphansAction(PlayerWind actor) {
		this.actor = Preconditions.checkNotNull(actor);
	}
	
	@Override
	public ActionType getActionType() {
		return ActionType.NINE_ORPHANS;
	}
	
	public PlayerWind getActor() {
		return actor;
	}

	public static NineOrphansAction of(PlayerWind actor) {
		return new NineOrphansAction(actor);
	}
}
