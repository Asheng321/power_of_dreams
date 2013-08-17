package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;

public class RobbingKongAction extends AbstractAction {

	private final int id1;
	private final int id2;
	private final int id3;
	
	private RobbingKongAction(PlayerWind actor, int id1, int id2, int id3) {
		super(actor);
		this.id1 = id1;
		this.id2 = id2;
		this.id3 = id3;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.ROBBING_KONG;
	}

	public int getId1() {
		return id1;
	}

	public int getId2() {
		return id2;
	}

	public int getId3() {
		return id3;
	}

	public static RobbingKongAction of(PlayerWind actor, int id1, int id2, int id3) {
		return new RobbingKongAction(actor, id1, id2, id3);
	}
}
