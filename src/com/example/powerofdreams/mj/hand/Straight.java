package com.example.powerofdreams.mj.hand;

import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.SequencePredicate;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.TileType;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Straight extends EatAndDecreaseHand {

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		List<Meld> sequences = Lists.newArrayList(
				Collections2.filter(winningPlayerHand.getMelds(), new SequencePredicate<Meld>()));
		EnumMap<TileType, Set<Integer>> firstNumberMap = new EnumMap<TileType, Set<Integer>>(TileType.class);
		for (Meld sequence : sequences) {
			if (!sequence.getTiles().get(0).isNumber()) {
				continue;
			}
			int firstNumber = sequence.getTiles().get(0).getNumber();
			TileType tileType = sequence.getTiles().get(0).getTileType();
			if (firstNumberMap.containsKey(tileType)) {
				firstNumberMap.get(tileType).add(firstNumber);
			} else {
				firstNumberMap.put(tileType, Sets.newHashSet(firstNumber));
			}
		}
		for (Set<Integer> set : firstNumberMap.values()) {
			if (set.containsAll(Lists.newArrayList(1, 4, 7))) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected int getFaanOnApplicable() {
		return 2;
	}

}
