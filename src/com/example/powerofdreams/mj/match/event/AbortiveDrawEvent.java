package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.google.common.base.Preconditions;

public class AbortiveDrawEvent implements Event {

	public enum Reason {
		NINE_ORPHANS,
		TRIPLE_RONS,
		FOUR_WINDS,
		FOUR_REACHES,
		FOUR_QUADS,
	}
	
	private final Reason reason;
	private final PlayerWind nine_orphans_actor;
	
	private AbortiveDrawEvent(Reason reason, PlayerWind nine_orphans_actor) {
		if (reason == Reason.NINE_ORPHANS) {
			Preconditions.checkNotNull(nine_orphans_actor);
		}
		this.reason = Preconditions.checkNotNull(reason);
		this.nine_orphans_actor = nine_orphans_actor;
	}

	@Override
	public EventType getEventType() {
		return EventType.ABORTIVE_DRAW;
	}
	
	public Reason getReason() {
		return reason;
	}
	
	public PlayerWind getNineOrphansActor() {
		Preconditions.checkState(reason == Reason.NINE_ORPHANS);
		return nine_orphans_actor;
	}
	
	public static AbortiveDrawEvent ofNineOrphans(PlayerWind actor) {
		return new AbortiveDrawEvent(Reason.NINE_ORPHANS, actor);
	}
	
	public static AbortiveDrawEvent ofTripleRons() {
		return new AbortiveDrawEvent(Reason.TRIPLE_RONS, null);
	}

	public static AbortiveDrawEvent ofFourWinds() {
		return new AbortiveDrawEvent(Reason.FOUR_WINDS, null);
	}

	public static AbortiveDrawEvent ofFourReaches() {
		return new AbortiveDrawEvent(Reason.FOUR_REACHES, null);
	}
	
	public static AbortiveDrawEvent ofFourQuads() {
		return new AbortiveDrawEvent(Reason.FOUR_QUADS, null);
	}
}
