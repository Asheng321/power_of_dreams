package com.example.powerofdreams.mj.match;

import java.util.List;

import com.example.powerofdreams.mj.player.action.Action;

/**
 * An interface of ActionResolver.
 * Sometimes multiple players can make actions on a single event, while only one of them
 * can be accepted (e.g. precedence of pong against chow, atama-hane on double-ron, etc).
 * ActionResolver receives coincided several actions and filters out unacceptable ones.
 *
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public interface ActionResolver<T extends Action> {

	/**
	 * Filters out unacceptable actions.
	 * @param actions actions to be filtered. Should not be an empty list.
	 * @return acceptable actions.
	 */
	public List<T> resolve(List<T> actions);
}
