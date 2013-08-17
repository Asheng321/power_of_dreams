package com.example.powerofdreams.mj.hand;

import java.util.List;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.QuadPredicate;
import com.example.powerofdreams.mj.player.playerhand.TripletPredicate;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class AllTriplet extends AbstractHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		List<Meld> triplets = Lists.newArrayList(
				Collections2.filter(winningPlayerHand.getMelds(), new TripletPredicate<Meld>()));
		List<Meld> quads = Lists.newArrayList(
				Collections2.filter(winningPlayerHand.getMelds(), new QuadPredicate<Meld>()));
		return triplets.size() + quads.size() == 4;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 2;
	}

}
