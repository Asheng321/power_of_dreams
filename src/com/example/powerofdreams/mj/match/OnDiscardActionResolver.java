package com.example.powerofdreams.mj.match;

import java.util.List;

import com.example.powerofdreams.mj.player.action.Action;
import com.example.powerofdreams.mj.player.action.ActionType;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class OnDiscardActionResolver implements ActionResolver<Action> {

	@Override
	public List<Action> resolve(List<Action> actions) {
		Preconditions.checkNotNull(actions);
		Preconditions.checkArgument(!actions.isEmpty());
		List<Action> result = Lists.newArrayList();
		for (Action action : actions) {
			if (action.getActionType() == ActionType.RON) {
				result.add(action);
			}
		}
		if (!result.isEmpty()) {
			// Multiple ron is allowed.
			return result;
		}
		
		for (Action action : actions) {
			if (action.getActionType() == ActionType.ROBBING_KONG) {
				return Lists.newArrayList(action);
			}
		}
		for (Action action : actions) {
			if (action.getActionType() == ActionType.PONG) {
				return Lists.newArrayList(action);
			}
		}
		for (Action action : actions) {
			if (action.getActionType() == ActionType.CHOW) {
				return Lists.newArrayList(action);
			}
		}
		return Lists.newArrayList(actions.get(0));
	}

}
