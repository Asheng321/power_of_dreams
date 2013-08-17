package com.example.powerofdreams.mj.player;

import com.example.powerofdreams.mj.player.playerhand.PlayerHand;
import com.google.common.base.Preconditions;

public class PlayerInfo {

	private PlayerHand hand;
	private Discards discards;
	private int score;
	private PlayerWind wind;

	public PlayerInfo(PlayerHand hand, Discards discards, int score, PlayerWind wind) {
		this.hand = Preconditions.checkNotNull(hand);
		this.discards = Preconditions.checkNotNull(discards);
		this.score = score;
		this.wind = Preconditions.checkNotNull(wind);
	}
	
	public PlayerHand getHand() {
		return hand;
	}
	
	public Discards getDiscards() {
		return discards;
	}
	
	public int getScore() {
		return score;
	}
	
	public PlayerWind getWind() {
		return wind;
	}
	
	public PlayerInfo setHand(PlayerHand hand) {
		this.hand = Preconditions.checkNotNull(hand);
		return this;
	}
	
	public PlayerInfo setDiscards(Discards discards) {
		this.discards = Preconditions.checkNotNull(discards);
		return this;
	}
	
	public PlayerInfo setScore(int score) {
		this.score = score;
		return this;
	}
	
	public PlayerInfo setWind(PlayerWind wind) {
		this.wind = wind;
		return this;
	}
	
	public PlayerInfo safeInstance() {
		return new PlayerInfo(hand.safeInstance(), discards.safeInstance(), score, wind);
	}
}
