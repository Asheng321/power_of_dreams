package com.example.powerofdreams.mj.hand;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.example.powerofdreams.mj.tiles.Tile;
import com.example.powerofdreams.mj.tiles.TileType;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class FullFlush extends EatAndDecreaseHand {

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		Collection<Meld> melds = Collections2.filter(winningPlayerHand.getMelds(),
				Predicates.not(Constants.SIDE_PREDICATE));
		Set<TileType> tileTypes = EnumSet.noneOf(TileType.class);
		for (Meld meld : melds) {
			for (Tile tile : meld.getTiles()) {
				tileTypes.add(tile.getTileType());
			}
		}
		return tileTypes.equals(EnumSet.of(TileType.MANZU))
				|| tileTypes.equals(EnumSet.of(TileType.PINZU))
				|| tileTypes.equals(EnumSet.of(TileType.SOUZU));
	}

	@Override
	protected int getFaanOnApplicable() {
		return 6;
	}

}
