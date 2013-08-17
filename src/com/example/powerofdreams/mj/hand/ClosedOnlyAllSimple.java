package com.example.powerofdreams.mj.hand;

import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;

public class ClosedOnlyAllSimple extends OpenableAllSimple {

	@Override
	public int getFaan(WinningPlayerHand winningPlayerHand) {
		if (winningPlayerHand.madeCall()) {
			return 0;
		}
		return super.getFaan(winningPlayerHand);
	}
}
