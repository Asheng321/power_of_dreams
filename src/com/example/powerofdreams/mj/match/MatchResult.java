package com.example.powerofdreams.mj.match;

import java.util.Collection;

/**
 * An interface representing a result of a single match.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public interface MatchResult {

	/**
	 * Returns a type of match result.
	 * @return a match result
	 */
	public MatchResultType getType();
	
	/**
	 * Returns a list of score move.
	 * @return a list of score move.
	 */
	public Collection<ScoreMove> getScoreMoves();
}
