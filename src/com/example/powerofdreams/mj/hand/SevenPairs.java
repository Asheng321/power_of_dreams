package com.example.powerofdreams.mj.hand;

import java.util.List;

import com.example.powerofdreams.mj.player.playerhand.EyePredicate;
import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Tile;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class SevenPairs extends ClosedOnlyHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		List<Meld> eyes = Lists.newArrayList(
				Collections2.filter(winningPlayerHand.getMelds(), new EyePredicate<Meld>()));
		if (eyes.size() != 7) {
			return false;
		}
		// Normally seven pairs have to be distinct.
		for (int i = 0; i < eyes.size(); ++i) {
			for (int j = 0; j < i; ++j) {
				Tile a = eyes.get(i).getTiles().get(0);
				Tile b = eyes.get(j).getTiles().get(0);
				if (a.isEquivalentTo(b)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 2;
	}

}
