package com.example.powerofdreams.mj.tiles;

/**
 * An enum class representing tiles.
 * TODO: Merge BasicTile with Tile.
 *
 * @author tayama0324@gmail.com (Takashi Tayama)
 */
public enum BasicTile implements Tile {
	IWAN(TileType.MANZU, 1),
	RYANWAN(TileType.MANZU, 2),
	SANWAN(TileType.MANZU, 3),
	SUWAN(TileType.MANZU, 4),
	UWAN(TileType.MANZU, 5),
	ROWAN(TileType.MANZU, 6),
	CHIWAN(TileType.MANZU, 7),
	PAWAN(TileType.MANZU, 8),
	CHUWAN(TileType.MANZU, 9),

	IPIN(TileType.PINZU, 1),
	RYANPIN(TileType.PINZU, 2),
	SANPIN(TileType.PINZU, 3),
	SUPIN(TileType.PINZU, 4),
	UPIN(TileType.PINZU, 5),
	ROPIN(TileType.PINZU, 6),
	CHIPIN(TileType.PINZU, 7),
	PAPIN(TileType.PINZU, 8),
	CHUPIN(TileType.PINZU, 9),

	ISOU(TileType.SOUZU, 1),
	RYANSOU(TileType.SOUZU, 2),
	SANSOU(TileType.SOUZU, 3),
	SUSOU(TileType.SOUZU, 4),
	USOU(TileType.SOUZU, 5),
	ROSOU(TileType.SOUZU, 6),
	CHISOU(TileType.SOUZU, 7),
	PASOU(TileType.SOUZU, 8),
	CHUSOU(TileType.SOUZU, 9),

	TON(TileType.JIHAI, 0),
	NAN(TileType.JIHAI, 0),
	SHA(TileType.JIHAI, 0),
	PEI(TileType.JIHAI, 0),
	HAKU(TileType.JIHAI, 0),
	HATSU(TileType.JIHAI, 0),
	CHUN(TileType.JIHAI, 0),
	
	AKA_UWAN(TileType.MANZU, 5),
	AKA_UPIN(TileType.PINZU, 5),
	AKA_USOU(TileType.SOUZU, 5),
	
	EMPTY(TileType.OTHER, 0);
	
	private final TileType tileType;
	private final int number;
	
	BasicTile(TileType tileType, int number) {
		this.tileType = tileType;
		this.number = number;
	}
	
	@Override
	public TileType getTileType() {
		return tileType;
	}

	@Override
	public boolean isNumber() {
		return tileType == TileType.MANZU || tileType == TileType.PINZU || tileType == TileType.SOUZU;
	}

	@Override
	public int getNumber() {
		if (!isNumber()) {
			throw new UnsupportedOperationException();
		}
		return number;
	}

	@Override
	public boolean isEquivalentTo(Tile other) {
		if (isNumber()) {
			return getTileType() == other.getTileType() && getNumber() == other.getNumber();
		}
		return this == other;
	}

	@Override
	public Tile safeInstance() {
		return this;
	}
	
	@Override
	public BasicTile asBasicTile() {
		return this;
	}
}
