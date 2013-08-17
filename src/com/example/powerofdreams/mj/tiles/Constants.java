package com.example.powerofdreams.mj.tiles;

import java.util.EnumSet;

import com.example.powerofdreams.mj.player.playerhand.EyePredicate;
import com.example.powerofdreams.mj.player.playerhand.Meld;
import com.example.powerofdreams.mj.player.playerhand.QuadPredicate;
import com.example.powerofdreams.mj.player.playerhand.SequencePredicate;
import com.example.powerofdreams.mj.player.playerhand.SidePredicate;
import com.example.powerofdreams.mj.player.playerhand.TripletPredicate;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;

/**
 * Constants that are used commonly and frequently.
 * 
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public class Constants {

	// Predicate instances
	// TODO: Refactor these predicates using enum.
	public static final Predicate<Meld> SEQUENCE_PREDICATE = new SequencePredicate<Meld>();
	public static final Predicate<Meld> TRIPLET_PREDICATE = new TripletPredicate<Meld>();
	public static final Predicate<Meld> QUAD_PREDICATE = new QuadPredicate<Meld>();
	public static final Predicate<Meld> EYE_PREDICATE = new EyePredicate<Meld>();
	public static final Predicate<Meld> SIDE_PREDICATE = new SidePredicate<Meld>();

	public static final Predicate<Meld> TRIPLET_OR_QUAD_PREDICATE = Predicates.or(
			TRIPLET_PREDICATE, QUAD_PREDICATE);
	public static final EnumSet<TileType> THREE_COLORS = 
			EnumSet.of(TileType.MANZU, TileType.PINZU, TileType.SOUZU);
	
	// Set of named tiles
	// Dragon tiles (aka. sangen-pai)
	public static final ImmutableSet<Tile> DRAGON_TILES = 
			ImmutableSet.<Tile>of(BasicTile.HAKU, BasicTile.HATSU, BasicTile.CHUN);
	// Wind tiles
	public static final ImmutableSet<Tile> WIND_TILES =
			ImmutableSet.<Tile>of(BasicTile.TON, BasicTile.NAN, BasicTile.SHA, BasicTile.PEI);
	
	// Not instantiable
	private Constants() {
		throw new UnsupportedOperationException();
	}
}
