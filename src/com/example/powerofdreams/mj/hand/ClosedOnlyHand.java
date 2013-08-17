package com.example.powerofdreams.mj.hand;

import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;

public abstract class ClosedOnlyHand extends AbstractHand {

	@Override
	public int getFaan(WinningPlayerHand winnningPlayerHand) {
		if (winnningPlayerHand.madeCall() || !isApplicable(winnningPlayerHand)) {
			return 0;
		}
		return getFaanOnApplicable();
	}
}
