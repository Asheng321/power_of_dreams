package com.example.powerofdreams.mj.player.playerhand;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * A class representing a player's winning hand. Sometimes a player's winning hand
 * can be interpreted as multiple ways of combination of melds. This class corresponds to
 * a single interpretation of melds.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public class WinningPlayerHand {

	private ImmutableList<Meld> melds;
	private boolean called;
	
	public WinningPlayerHand(List<Meld> melds, boolean called) {
		this.melds = ImmutableList.copyOf(melds);
		this.called = called;
	}
	
	public ImmutableList<Meld> getMelds() {
		return melds;
	}
	
	public boolean madeCall() {
		return called;
	}
}
