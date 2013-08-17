package com.example.powerofdreams.mj.hand;

import java.util.List;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class DoubleDoubleRun extends ClosedOnlyHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		List<Meld> sequences = Lists.newArrayList(
				Collections2.filter(winningPlayerHand.getMelds(), Constants.SEQUENCE_PREDICATE));
		if (sequences.size() != 4) {
			return false;
		}
		if (sequences.get(0).equals(sequences.get(1)) && sequences.get(2).equals(sequences.get(3))
				|| sequences.get(0).equals(sequences.get(2)) && sequences.get(1).equals(sequences.get(3))
				|| sequences.get(0).equals(sequences.get(3)) && sequences.get(1).equals(sequences.get(2))) {
			return true;
		}
		return false;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 3;
	}

}
