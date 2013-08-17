package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;

public class ChowAction extends AbstractAction {

	private final int id1;
	private final int id2;

	private ChowAction(PlayerWind actor, int id1, int id2) {
		super(actor);
		this.id1 = id1;
		this.id2 = id2;
	}
	
	@Override
	public ActionType getActionType() {
		return ActionType.CHOW;
	}

	public int getId1() {
		return id1;
	}
	
	public int getId2() {
		return id2;
	}
}
