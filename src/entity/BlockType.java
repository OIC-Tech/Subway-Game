package entity;

public enum BlockType {
	STONE("res/stone.png"), AIR("res/air.png"), GRASS("res/grass.png"), DIRT("red/dirt.png");
	public final String location;

	BlockType(String location) {
		this.location = location;
	}
}
