package com.example.powerofdreams.mj.player;

import com.example.powerofdreams.mj.player.action.Action;

/**
 * An interface representing a decision maker, which is an abstraction of a player's thinking routine.
 *
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public interface DecisionMaker {

	Action getActionOnDraw();

	Action getActionOnDiscard();
}
