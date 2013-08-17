package com.example.powerofdreams.mj.match;

import java.util.List;
import java.util.Map;

import com.example.powerofdreams.mj.match.deck.Deck;
import com.example.powerofdreams.mj.match.event.Event;
import com.example.powerofdreams.mj.player.Player;
import com.example.powerofdreams.mj.player.PlayerWind;
import com.google.common.base.Preconditions;

public class MatchState {

	private Map<PlayerWind, Player> players;
	private Deck deck;
	private List<Event> events;

	public MatchState(Map<PlayerWind, Player> players, Deck deck, List<Event> events) {
		this.players = Preconditions.checkNotNull(players);
		this.deck = Preconditions.checkNotNull(deck);
		this.events = Preconditions.checkNotNull(events);
	}
	
	public Map<PlayerWind, Player> getPlayers() {
		return players;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	public Event getLastEvent() {
		return events.get(events.size() - 1);
	}
}
