package com.example.powerofdreams.mj.hand;

import java.util.Collection;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class ThreeClosedTriplets extends AbstractHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		Predicate<Meld> closedTripletPredicate = Predicates.and(
				Constants.TRIPLET_OR_QUAD_PREDICATE,
				new Predicate<Meld>() {
					public boolean apply(Meld meld) {
						return meld.getIsClosed();
					}
				});
		Collection<Meld> closedTriplets = Collections2.filter(winningPlayerHand.getMelds(), closedTripletPredicate);
		return closedTriplets.size() >= 3;
	}
	
	@Override
	protected int getFaanOnApplicable() {
		return 2;
	}

}
