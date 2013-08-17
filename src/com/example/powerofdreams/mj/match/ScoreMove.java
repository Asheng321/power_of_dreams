package com.example.powerofdreams.mj.match;

import com.example.powerofdreams.mj.player.Player;
import com.google.common.base.Preconditions;

/**
 * A class representing a single score move.
 *
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public class ScoreMove {

	public enum Reason {
		TSUMO,
		RON,
		NOTEN_PENALTY,
		OUTSIDE_THE_LAW,
		NAGASHI_MANGAN,
		MANEMAN,
	}
	
	private final Reason reason;
	private final Player from;
	private final Player to;
	private final int score;
	
	public ScoreMove(Reason reason, Player from, Player to, int score) {
		this.reason = reason;
		this.from = Preconditions.checkNotNull(from);
		this.to = Preconditions.checkNotNull(to);
		this.score = score;
	}
	
	public Reason getReason() {
		return reason;
	}
	
	public Player getFrom() {
		return from;
	}
	
	public Player getTo() {
		return to;
	}
	
	public int getScore() {
		return score;
	}
}
