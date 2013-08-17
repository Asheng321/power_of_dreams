package com.example.powerofdreams.mj.match.event;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.playerhand.handtile.HandTile;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class InitializeEvent implements Event {

	private final Map<PlayerWind, ImmutableList<HandTile>> initialHands;
	
	private InitializeEvent(Map<PlayerWind, ImmutableList<HandTile>> initialHands) {
		this.initialHands = initialHands;
	}
	
	public Map<PlayerWind, ImmutableList<HandTile>> getInitialHands() {
		Map<PlayerWind, ImmutableList<HandTile>> result = Maps.newEnumMap(PlayerWind.class);
		for (Entry<PlayerWind, ImmutableList<HandTile>> entry : initialHands.entrySet()) {
			result.put(entry.getKey(), ImmutableList.copyOf(safeInstances(entry.getValue())));
		}
		return result;
	}
	
	@Override
	public EventType getEventType() {
		return EventType.INITIALIZE;
	}

	public static InitializeEvent of(Map<PlayerWind, List<HandTile>> initialHands) {
		Map<PlayerWind, ImmutableList<HandTile>> map = Maps.newEnumMap(PlayerWind.class);
		for (Entry<PlayerWind, List<HandTile>> entry : initialHands.entrySet()) {
			map.put(entry.getKey(), ImmutableList.copyOf(safeInstances(entry.getValue())));
		}
		return new InitializeEvent(map);
	}
	
	private static List<HandTile> safeInstances(List<HandTile> tiles) {
		List<HandTile> result = Lists.newArrayList();
		for (HandTile tile : tiles) {
			result.add(tile.safeInstance());
		}
		return result;
	}

}
