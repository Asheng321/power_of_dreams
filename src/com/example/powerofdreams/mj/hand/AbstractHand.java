package com.example.powerofdreams.mj.hand;

import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;

/**
 * Abstract implementation of a hand.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public abstract class AbstractHand implements Hand {

	@Override
	public int getFaan(WinningPlayerHand winningPlayerHand) {
		return isApplicable(winningPlayerHand) ? getFaanOnApplicable() : 0;
	}

	protected abstract boolean isApplicable(WinningPlayerHand winningPlayerHand);
	
	protected abstract int getFaanOnApplicable();
}
