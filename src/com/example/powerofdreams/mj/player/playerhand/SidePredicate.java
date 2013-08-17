package com.example.powerofdreams.mj.player.playerhand;

import com.example.powerofdreams.mj.player.playerhand.Meld.MeldType;
import com.google.common.base.Predicate;

/**
 * A predicate that checks if the meld is a side tile.
 * @author tayama0324@gmail.com (Takashi Tayama)
 *
 * @param <T> Should be subclass of Meld.
 */
public class SidePredicate<T extends Meld> implements Predicate<T> {

	@Override
	public boolean apply(T meld) {
		return meld.getMeldType() == MeldType.SIDE;
	}

}
