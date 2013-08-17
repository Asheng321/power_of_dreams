package com.example.powerofdreams.mj.hand;

import java.util.List;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class DoubleSequence extends ClosedOnlyHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		List<Meld> sequences = Lists.newArrayList(
				Collections2.filter(winningPlayerHand.getMelds(), Constants.SEQUENCE_PREDICATE));
		for (int i = 0; i < sequences.size(); ++i) {
			for (int j = 0; j < i; ++i) {
				if (sequences.get(i).equals(sequences.get(j))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 1;
	}

}
