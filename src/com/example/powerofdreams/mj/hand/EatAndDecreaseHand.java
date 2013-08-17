package com.example.powerofdreams.mj.hand;

import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;

public abstract class EatAndDecreaseHand extends AbstractHand {
		
	@Override
	public boolean isLimitHand() {
		return false;
	}

	public int getFaan(WinningPlayerHand winningPlayerHand) {
		int decrement = winningPlayerHand.madeCall() ? getDecrement() : 0;
		return isApplicable(winningPlayerHand) 
				? Math.max(getFaanOnApplicable() - decrement, 0) 
				: 0;
	}
	
	protected int getDecrement() {
		return 1;
	}
}
