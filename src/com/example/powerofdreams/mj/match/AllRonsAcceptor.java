package com.example.powerofdreams.mj.match;

import java.util.List;

import com.example.powerofdreams.mj.player.action.RonAction;

/**
 * An implementation of ActionResolver.
 * When multiple rons occurs at the same time, accept all of them (i.e. allow double-ron and
 * triple-ron).
 *
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public class AllRonsAcceptor implements ActionResolver<RonAction> {

	@Override
	public List<RonAction> resolve(List<RonAction> actions) {
		return actions;
	}

}
