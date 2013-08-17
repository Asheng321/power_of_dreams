package com.example.powerofdreams.mj.player;

import com.google.common.base.Preconditions;

/**
 * A class representing a player.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public class Player {

	private final PlayerInfo playerInfo;
	private final DecisionMaker decisionMaker;
	
	public Player(PlayerInfo playerInfo, DecisionMaker decisionMaker) {
		this.playerInfo = Preconditions.checkNotNull(playerInfo);
		this.decisionMaker = Preconditions.checkNotNull(decisionMaker);
	}
	
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	
	public DecisionMaker getDecisionMaker() {
		return decisionMaker;
	}
}
