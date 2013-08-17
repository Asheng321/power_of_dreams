package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.google.common.base.Preconditions;

public class HiddenKongAction implements Action {

	private final PlayerWind actor;
	private final int id1;
	private final int id2;
	private final int id3;
	private final int id4;
	
	private HiddenKongAction(PlayerWind actor, int id1, int id2, int id3, int id4) {
		this.actor = Preconditions.checkNotNull(actor);
		this.id1 = id1;
		this.id2 = id2;
		this.id3 = id3;
		this.id4 = id4;
	}
	
	@Override
	public ActionType getActionType() {
		return ActionType.HIDDEN_KONG;
	}

	@Override
	public PlayerWind getActor() {
		return actor;
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

	public int getId4() {
		return id4;
	}
	
	public static HiddenKongAction of(PlayerWind actor, int id1, int id2, int id3, int id4) {
		return new HiddenKongAction(actor, id1, id2, id3, id4);
	}
}
