package com.example.powerofdreams.mj.player.action;

import com.example.powerofdreams.mj.player.PlayerWind;

public interface Action {

	PlayerWind getActor();
	
	ActionType getActionType();
}
