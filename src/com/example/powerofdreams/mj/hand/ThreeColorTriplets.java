package com.example.powerofdreams.mj.hand;

import java.util.Collection;
import java.util.EnumSet;

import android.util.SparseArray;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.Constants;
import com.example.powerofdreams.mj.tiles.Tile;
import com.example.powerofdreams.mj.tiles.TileType;
import com.google.common.collect.Collections2;

public class ThreeColorTriplets extends AbstractHand {

	@Override
	public boolean isLimitHand() {
		return false;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		Collection<Meld> triplets = Collections2.filter(
				winningPlayerHand.getMelds(), Constants.TRIPLET_OR_QUAD_PREDICATE);
		SparseArray<EnumSet<TileType>> colorMap = new SparseArray<EnumSet<TileType>>();
		for (Meld triplet : triplets) {
			Tile firstTile = triplet.getTiles().get(0);
			if (!firstTile.isNumber()) {
				continue;
			}
			if (colorMap.indexOfKey(firstTile.getNumber()) < 0) {
				colorMap.put(firstTile.getNumber(), EnumSet.of(firstTile.getTileType()));
			} else {
				colorMap.get(firstTile.getNumber()).add(firstTile.getTileType());
			}
		}
		for (int i = 0; i < colorMap.size(); ++i) {
			EnumSet<TileType> set = colorMap.valueAt(i);
			if (set.containsAll(Constants.THREE_COLORS)) {
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
