package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;

public class PongAction extends AbstractAction {

	private final int id1;
	private final int id2;

	private PongAction(PlayerWind actor, int id1, int id2) {
		super(actor);
		this.id1 = id1;
		this.id2 = id2;
	}
	@Override
	public ActionType getActionType() {
		return ActionType.PONG;
	}

	public int getId1() {
		return id1;
	}
	
	public int getId2() {
		return id2;
	}
	
	public static PongAction of(PlayerWind actor, int id1, int id2) {
		return new PongAction(actor, id1, id2);
	}
}
