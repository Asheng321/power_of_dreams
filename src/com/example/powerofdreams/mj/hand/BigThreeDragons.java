package com.example.powerofdreams.mj.hand;

import java.util.Collection;
import java.util.List;

import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.WinningPlayerHand;
import com.example.powerofdreams.mj.tiles.BasicTile;
import com.example.powerofdreams.mj.tiles.Constants;
import com.example.powerofdreams.mj.tiles.Tile;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class BigThreeDragons extends AbstractHand {

	@Override
	public boolean isLimitHand() {
		return true;
	}

	@Override
	protected boolean isApplicable(WinningPlayerHand winningPlayerHand) {
		Collection<Meld> triplets = Collections2.filter(
				winningPlayerHand.getMelds(), Constants.TRIPLET_OR_QUAD_PREDICATE);
		List<Tile> tripletTiles = Lists.newArrayList();
		for (Meld meld : triplets) {
			tripletTiles.add(meld.getTiles().get(0));
		}
		return tripletTiles.containsAll(ImmutableList.of(BasicTile.HAKU, BasicTile.HATSU, BasicTile.CHUN));
	}

	@Override
	protected int getFaanOnApplicable() {
		return 13;
	}

}
