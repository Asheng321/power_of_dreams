package com.example.powerofdreams.mj.match;

/**
 * An enum that represents a type of result of a single match.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public enum MatchResultType {
	WIN,  // player(s) win a hand
	DRAW,  // nobody wins a hand (aka. ryukyoku)
	ABORTIVE_DRAW,  // aka. special ryukyoku, e.g. four kongs or four reaches
	CHOMBO,  // illegal actions
}
