package com.example.powerofdreams.mj.hand;

import java.util.Collection;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.example.powerofdreams.mj.tiles.Tile;
import com.example.powerofdreams.mj.tiles.TilesUtil;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class OpenableAllSimple extends AbstractHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		Collection<Meld> melds = Collections2.filter(winningPlayerHand.getMelds(),
				Predicates.not(Constants.SIDE_PREDICATE));
		for (Meld meld : melds) {
			for (Tile tile : meld.getTiles()) {
				if (!TilesUtil.isSimple(tile)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 1;
	}

}
