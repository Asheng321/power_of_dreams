package com.example.powerofdreams.mj.hand;

import java.util.Collection;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.example.powerofdreams.mj.tiles.Tile;
import com.example.powerofdreams.mj.tiles.TilesUtil;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class FullOutSide extends EatAndDecreaseHand {

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		Collection<Meld> nonSide = Collections2.filter(winningPlayerHand.getMelds(),
				Predicates.not(Constants.SIDE_PREDICATE));
		boolean allHasNonTerminal = true;
		for (Meld meld : nonSide) {
			boolean hasNonTerminal = false;
			for (Tile tile : meld.getTiles()) {
				hasNonTerminal = hasNonTerminal || TilesUtil.isTerminal(tile);
			}
			allHasNonTerminal = allHasNonTerminal && hasNonTerminal;
		}
		return allHasNonTerminal;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 3;
	}

}
