package com.example.powerofdreams.mj.hand;

import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;

/**
 * An interface that represents a hand (yaku).
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public interface Hand {

	/**
	 * Returns true if the hand is a limit hand (yakuman).
	 * Normally a limit hand does not coincide with non-limit hand.
	 * @return true if a limit hand.
	 */
	boolean isLimitHand();
	
	/**
	 * Get the number of faan of the hand. Should return multiple of 13 on a limit hand.
	 * If the hand can not be applied to the player's hand, returns 0.
	 * May return a different number depending on whether if the player made a call.
	 * @return faan number or 0.
	 */
	int getFaan(WinningPlayerHand winningPlayerHand);
	
}
