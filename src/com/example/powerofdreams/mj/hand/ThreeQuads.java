package com.example.powerofdreams.mj.hand;

import java.util.Collection;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.google.common.collect.Collections2;

public class ThreeQuads extends AbstractHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		Collection<Meld> quads = Collections2.filter(winningPlayerHand.getMelds(), Constants.QUAD_PREDICATE);
		return quads.size() >= 3;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 2;
	}

}
