package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.google.common.base.Preconditions;

public class AdditiveKongAction implements Action {

	private final PlayerWind actor;
	private final int tileId;
	
	private AdditiveKongAction(PlayerWind actor, int tileId) {
		this.actor = Preconditions.checkNotNull(actor); 
		this.tileId = tileId;
	}
	
	@Override
	public ActionType getActionType() {
		return ActionType.ADDITIVE_KONG;
	}

	@Override
	public PlayerWind getActor() {
		return actor;
	}
	
	public int getTileId() {
		return tileId;
	}
}
