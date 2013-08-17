package com.example.powerofdreams.mj.match.rule;

import java.util.List;

import com.example.powerofdreams.mj.match.ActionResolver;
import com.example.powerofdreams.mj.player.action.Action;
import com.example.powerofdreams.mj.player.action.RonAction;
import com.example.powerofdreams.mj.tiles.Tile;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class RuleSet {

	private final ImmutableList<Tile> tileSet;
	private final ActionResolver<Action> onDiscardActionResolver;
	private final ActionResolver<RonAction> multipleRonResolver;
	
	public RuleSet(
			List<Tile> tileSet,
			ActionResolver<Action> onDiscardActionResolver,
			ActionResolver<RonAction> multipleRonResolver) {
		this.tileSet = ImmutableList.copyOf(Preconditions.checkNotNull(tileSet));
		this.onDiscardActionResolver = Preconditions.checkNotNull(onDiscardActionResolver);
		this.multipleRonResolver = Preconditions.checkNotNull(multipleRonResolver);
	}
	
	public List<Tile> getTileSet() {
		// TODO: return safeInstances
		return Lists.newArrayList(tileSet);
	}
	
	public ActionResolver<Action> getOnDiscardActionResolver() {
		return onDiscardActionResolver;
	}
	
	public ActionResolver<RonAction> getMultipleRonResolver() {
		return multipleRonResolver;
	}
}
