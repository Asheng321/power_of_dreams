package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;

public class RonAction extends AbstractAction {
	
	protected RonAction(PlayerWind actor) {
		super(actor);
	}

	@Override
	public ActionType getActionType() {
		return ActionType.RON;
	}

	public static RonAction of(PlayerWind actor) {
		return new RonAction(actor);
	}
}
