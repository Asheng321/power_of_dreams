package com.example.powerofdreams.mj.match.event;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.playerhand.PlayerHand.ImmutablePlayerHand;
import com.google.common.base.Preconditions;

public class TsumoEvent implements Event {

	private final PlayerWind winner;
	private final ImmutablePlayerHand hand;
	
	private TsumoEvent(PlayerWind winner, ImmutablePlayerHand hand) {
		this.winner = Preconditions.checkNotNull(winner);
		this.hand = Preconditions.checkNotNull(hand);
	}
	
	@Override
	public EventType getEventType() {
		return EventType.TSUMO;
	}

	public PlayerWind getWinner() {
		return winner;
	}
	
	public ImmutablePlayerHand getHand() {
		return hand;
	}
	
	public static TsumoEvent of(PlayerWind winner, ImmutablePlayerHand hand) {
		return new TsumoEvent(winner, hand);
	}
}
