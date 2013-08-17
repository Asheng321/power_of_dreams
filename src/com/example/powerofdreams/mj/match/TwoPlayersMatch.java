package com.example.powerofdreams.mj.match;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.powerofdreams.mj.match.deck.Deck;
import com.example.powerofdreams.mj.match.event.AbortiveDrawEvent;
import com.example.powerofdreams.mj.match.event.AdditiveKongEvent;
import com.example.powerofdreams.mj.match.event.ChowEvent;
import com.example.powerofdreams.mj.match.event.DiscardEvent;
import com.example.powerofdreams.mj.match.event.DrawEvent;
import com.example.powerofdreams.mj.match.event.Event;
import com.example.powerofdreams.mj.match.event.HiddenKongEvent;
import com.example.powerofdreams.mj.match.event.InitializeEvent;
import com.example.powerofdreams.mj.match.event.PongEvent;
import com.example.powerofdreams.mj.match.event.RobbingKongEvent;
import com.example.powerofdreams.mj.match.event.TsumoEvent;
import com.example.powerofdreams.mj.match.rule.RuleSet;
import com.example.powerofdreams.mj.player.DecisionMaker;
import com.example.powerofdreams.mj.player.Discards;
import com.example.powerofdreams.mj.player.Player;
import com.example.powerofdreams.mj.player.PlayerInfo;
import com.example.powerofdreams.mj.player.PlayerWind;
import com.example.powerofdreams.mj.player.action.Action;
import com.example.powerofdreams.mj.player.action.AdditiveKongAction;
import com.example.powerofdreams.mj.player.action.ChowAction;
import com.example.powerofdreams.mj.player.action.DiscardAction;
import com.example.powerofdreams.mj.player.action.HiddenKongAction;
import com.example.powerofdreams.mj.player.action.PongAction;
import com.example.powerofdreams.mj.player.action.RobbingKongAction;
import com.example.powerofdreams.mj.player.action.RonAction;
import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.PlayerHand;
import com.example.powerofdreams.mj.player.playerhand.handtile.DrawSource;
import com.example.powerofdreams.mj.player.playerhand.handtile.HandTile;
import com.example.powerofdreams.mj.tiles.IdentifiedTile;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class TwoPlayersMatch implements MatchManager {

	private static final ImmutableMap<PlayerWind, PlayerWind> NEXT_TURN = ImmutableMap.of(
			PlayerWind.TON, PlayerWind.NAN,
			PlayerWind.NAN, PlayerWind.TON);
	
	private final RuleSet ruleSet;
	private final int scoreFirst;
	private final int scoreSecond;
	private final PlayerWind windFirst;
	private final PlayerWind windSecond;
	private final DecisionMaker decisionMakerFirst;
	private final DecisionMaker decisionMakerSecond;
	
	
	private MatchState matchState;
	
	public TwoPlayersMatch(
			RuleSet ruleSet,
			int scoreFirst,
			int scoreSecond,
			PlayerWind windFirst,
			PlayerWind windSecond,
			DecisionMaker decisionMakerFirst,
			DecisionMaker decisionMakerSecond,
			boolean isFirstDealer) {
		this.ruleSet = Preconditions.checkNotNull(ruleSet);
		this.scoreFirst = scoreFirst;
		this.scoreSecond = scoreSecond;
		Preconditions.checkArgument(Sets.newHashSet(windFirst, windSecond).equals(
				Sets.newHashSet(PlayerWind.TON, PlayerWind.NAN)));
		this.windFirst = windFirst;
		this.windSecond = windSecond;
		this.decisionMakerFirst = Preconditions.checkNotNull(decisionMakerFirst);
		this.decisionMakerSecond = Preconditions.checkNotNull(decisionMakerSecond);
		this.matchState = null;
	}

	@Override
	public MatchResult play() {
		initialize();
		PlayerWind turn = PlayerWind.TON;
		while (true) {
			Event lastEvent = matchState.getLastEvent();
			switch(lastEvent.getEventType()) {
			case ABORTIVE_DRAW:
				break;
			case ADDITIVE_KONG:
				onAdditiveKong(turn);
				break;
			case CHOW:
				onChow(turn);
				break;
			case DISCARD:
				onDiscard(turn);
				break;
			case DRAW:
				onDraw(turn);
				break;
			case HIDDEN_KONG:
				onHiddenKong(turn);
				break;
			case INITIALIZE:
				onInitialize(turn);
				break;
			case NO_SIDE:
				break;
			case PONG:
				onPong(turn);
			case ROBBING_KONG:
				onRobbingKong(turn);
				break;
			case RON:
				break;
			case TSUMO:
				break;
			default:
				// Unreachable state
				throw new UnsupportedOperationException();
			}
		}
	}
	

	private void initialize() {
		Deck deck = new Deck(Lists.newArrayList(ruleSet.getTileSet()), 0);
		
		List<HandTile> tilesFirst = Lists.newArrayList();
		List<HandTile> tilesSecond = Lists.newArrayList();
		for (int i = 0; i < 13; ++i) {
			tilesFirst.add(HandTile.of(deck.draw(), DrawSource.DECK, false, 0));
			tilesSecond.add(HandTile.of(deck.draw(), DrawSource.DECK, false, 0));
		}

		PlayerHand handFirst = PlayerHand.newInstance();
		handFirst.initialize(tilesFirst);
		PlayerHand handSecond = PlayerHand.newInstance();
		handSecond.initialize(tilesSecond);
		
		Player firstPlayer = new Player(
				new PlayerInfo(handFirst, new Discards(), scoreFirst, windFirst),
				decisionMakerFirst);
		Player secondPlayer = new Player(
				new PlayerInfo(handSecond, new Discards(), scoreSecond, windSecond),
				decisionMakerSecond);
		Map<PlayerWind, Player> players = new EnumMap<PlayerWind, Player>(ImmutableMap.of(
				windFirst, firstPlayer,
				windSecond, secondPlayer));
		
		List<Event> events = Lists.<Event>newArrayList(InitializeEvent.of(ImmutableMap.of(
				windFirst, handFirst.getImmutableHand().getTiles(),
				windSecond, handSecond.getImmutableHand().getTiles())));
		
		matchState = new MatchState(players, deck, events);
	}

	private void onRobbingKong(PlayerWind turn) {
		// TODO Auto-generated method stub
		
	}

	private void onPong(PlayerWind turn) {
		// TODO Auto-generated method stub
		
	}

	private void onHiddenKong(PlayerWind turn) {
		// TODO Auto-generated method stub
		
	}

	private void onDraw(PlayerWind turn) {
		Player player = matchState.getPlayers().get(turn);
		Action action = player.getDecisionMaker().getActionOnDraw();
		switch (action.getActionType()) {
		case ADDITIVE_KONG:
			doAdditiveKongAction(turn, action);
			break;
		case CHOW:
			throw new InvalidActionException();
		case DISCARD:
			doDiscardAction(turn, action);
			break;
		case HIDDEN_KONG:
			doHiddenKongAction(turn, action);
		case NINE_ORPHANS:
			doNineOrphansAction(turn, action);
		case PONG:
			throw new InvalidActionException();
		case ROBBING_KONG:
			throw new InvalidActionException();
		case RON:
			throw new InvalidActionException();
		case TSUMO:
			doTsumoAction(turn, action);
		default:
			throw new InvalidActionException();			
		}
	}

	private void onDiscard(PlayerWind turn) {
		Event discardEvent = matchState.getLastEvent();
		List<Action> actions = Lists.newArrayList();
		for (Entry<PlayerWind, Player> entry : matchState.getPlayers().entrySet()) {
			Player player = entry.getValue();
			if (entry.getKey() == turn) {
				continue;
			}
			actions.add(player.getDecisionMaker().getActionOnDiscard());
		}
		List<Action> resolvedActions = ruleSet.getOnDiscardActionResolver().resolve(actions);
		switch (resolvedActions.get(0).getActionType()) {
		case ADDITIVE_KONG:
			throw new InvalidActionException();
		case CHOW:
			Preconditions.checkState(resolvedActions.size() == 1);
			doChowAction(turn, discardEvent, resolvedActions.get(0));
		case DISCARD:
			throw new InvalidActionException();
		case HIDDEN_KONG:
			throw new InvalidActionException();
		case NINE_ORPHANS:
			throw new InvalidActionException();
		case NO_ACTION:
			// Nothing to do.
			break;
		case PONG:
			Preconditions.checkState(resolvedActions.size() == 1);
			doPongAction(turn, discardEvent, resolvedActions.get(0));
		case ROBBING_KONG:
			Preconditions.checkState(resolvedActions.size() == 1);
			doRobbingKongAction(turn, discardEvent, resolvedActions.get(0));
		case RON:
			doRonAction(turn, discardEvent, resolvedActions);
		case TSUMO:
			throw new InvalidActionException();
		default:
			throw new InvalidActionException();
		}
	}

	private void onChow(PlayerWind turn) {
		// TODO Auto-generated method stub
		
	}

	private void onAdditiveKong(PlayerWind turn) {
		// TODO Auto-generated method stub
		
	}

	private void onInitialize(PlayerWind wind) {
		normalDraw(wind);
	}

	private void doChowAction(PlayerWind turn, Event event, Action action) {
		Preconditions.checkArgument(event instanceof DiscardEvent);
		Preconditions.checkArgument(action instanceof ChowAction);
		DiscardEvent discardEvent = (DiscardEvent) event;
		ChowAction chowAction = (ChowAction) action;
		Player callee = matchState.getPlayers().get(discardEvent.getActor());
		Player caller = matchState.getPlayers().get(chowAction.getActor());
		callee.getPlayerInfo().getDiscards().callLastTile();
		Meld meld = caller.getPlayerInfo().getHand().chow(
				chowAction.getId1(), chowAction.getId2(), discardEvent.getTile());
		Event chowEvent = ChowEvent.of(chowAction.getActor(), meld);
		matchState.getEvents().add(chowEvent);
		turn = chowAction.getActor();
	}

	private void doPongAction(PlayerWind turn, Event event, Action action) {
		Preconditions.checkArgument(event instanceof DiscardEvent);
		Preconditions.checkArgument(action instanceof PongAction);
		DiscardEvent discardEvent = (DiscardEvent) event;
		PongAction pongAction = (PongAction) action;
		Player callee = matchState.getPlayers().get(discardEvent.getActor());
		Player caller = matchState.getPlayers().get(pongAction.getActor());
		callee.getPlayerInfo().getDiscards().callLastTile();
		Meld meld = caller.getPlayerInfo().getHand().chow(
				pongAction.getId1(), pongAction.getId2(), discardEvent.getTile());
		Event pongEvent = PongEvent.of(pongAction.getActor(), meld);
		matchState.getEvents().add(pongEvent);
		turn = pongAction.getActor();
	}

	private void doRobbingKongAction(PlayerWind turn, Event event, Action action) {
		Preconditions.checkArgument(event instanceof DiscardEvent);
		Preconditions.checkArgument(action instanceof RobbingKongAction);
		DiscardEvent discardEvent = (DiscardEvent) event;
		RobbingKongAction robbingKongAction = (RobbingKongAction) action;
		Player callee = matchState.getPlayers().get(discardEvent.getActor());
		Player caller = matchState.getPlayers().get(robbingKongAction.getActor());
		callee.getPlayerInfo().getDiscards().callLastTile();
		Meld meld = caller.getPlayerInfo().getHand().robbingKong(
				robbingKongAction.getId1(),
				robbingKongAction.getId2(),
				robbingKongAction.getId3(),
				discardEvent.getTile());
		Event robbingKongEvent = RobbingKongEvent.of(
				robbingKongAction.getActor(), discardEvent.getActor(), meld);
		matchState.getEvents().add(robbingKongEvent);
		turn = robbingKongAction.getActor();
	}

	private void doAdditiveKongAction(PlayerWind wind, Action action) {
		// TODO: Support chan-kong.
		// TODO: Disclose kong-dora.
		Preconditions.checkArgument(action instanceof AdditiveKongAction);
		AdditiveKongAction additiveKongAction = (AdditiveKongAction) action;
		Player player = matchState.getPlayers().get(wind);
		Meld meld = player.getPlayerInfo().getHand().additiveKong(additiveKongAction.getTileId());
		Event event = AdditiveKongEvent.of(wind, meld);
		matchState.getEvents().add(event);
	}

	private void doDiscardAction(PlayerWind wind, Action action) {
		Preconditions.checkArgument(action instanceof DiscardAction);
		DiscardAction discardAction = (DiscardAction) action;
		Player player = matchState.getPlayers().get(wind);
		HandTile tile = player.getPlayerInfo().getHand().discard(discardAction.getTileId());
		Event event = DiscardEvent.of(wind, tile);
		matchState.getEvents().add(event);
	}
	
	private void doHiddenKongAction(PlayerWind wind, Action action) {
		// TODO: Support chan-kong (thirteen orphans).
		Preconditions.checkArgument(action instanceof HiddenKongAction);
		HiddenKongAction hiddenKongAction = (HiddenKongAction) action;
		Player player = matchState.getPlayers().get(wind);
		Meld meld = player.getPlayerInfo().getHand().hiddenKong(
				hiddenKongAction.getId1(),
				hiddenKongAction.getId2(),
				hiddenKongAction.getId3(),
				hiddenKongAction.getId4());
		Event event = HiddenKongEvent.of(wind, meld);
		matchState.getEvents().add(event);
	}

	private void doNineOrphansAction(PlayerWind wind, Action action) {
		// TODO: Validate. Check that he has nine orphans and it's the first turn.
		Event event = AbortiveDrawEvent.ofNineOrphans(wind);
		matchState.getEvents().add(event);
	}
	
	private void doTsumoAction(PlayerWind wind, Action action) {
		Player player = matchState.getPlayers().get(wind);
		// TODO: Validate. 
		Event event = TsumoEvent.of(wind, player.getPlayerInfo().getHand().getImmutableHand());
		matchState.getEvents().add(event);
	}

	private void doRonAction(PlayerWind turn, Event discardEvent, List<Action> actions) {
		List<RonAction> ronActions = Lists.newArrayList();
		for (Action action : actions) {
			Preconditions.checkArgument(action instanceof RonAction);
			ronActions.add((RonAction) action);
		}
		// TODO: Ron validation
		List<RonAction> resolvedActions = ruleSet.getMultipleRonResolver().resolve(ronActions);
		
	}

	private void normalDraw(PlayerWind wind) {
		draw(wind, DrawSource.DECK);
	}
	
	private void rinshanDraw(PlayerWind wind) {
		draw(wind, DrawSource.DECK_RINSHAN);
	}
	
	private void draw(PlayerWind wind, DrawSource drawSource) {
		Player player = matchState.getPlayers().get(wind);
		IdentifiedTile tile = matchState.getDeck().draw();
		HandTile handTile = HandTile.of(
				tile, tile.getId(), drawSource, true, player.getPlayerInfo().getDiscards().size());
		player.getPlayerInfo().getHand().draw(handTile);
		matchState.getEvents().add(DrawEvent.of(wind, handTile));		
	}
}
