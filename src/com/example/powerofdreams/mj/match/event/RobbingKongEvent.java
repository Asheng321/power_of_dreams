package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.Meld.MeldType;
import com.google.common.base.Preconditions;

public final class RobbingKongEvent extends AbstractCallEvent {

	protected RobbingKongEvent(PlayerWind actor, PlayerWind callee, Meld meld) {
		super(actor, callee, meld);
		Preconditions.checkArgument(meld.getMeldType() == MeldType.QUAD);
	}

	@Override
	public EventType getEventType() {
		return EventType.ROBBING_KONG;
	}

	public static RobbingKongEvent of(PlayerWind actor, PlayerWind callee, Meld meld) {
		return new RobbingKongEvent(actor, callee, meld);
	}
}
