package game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class WorldSettings {
	public static final int BLOCK_SIZE = 32;
	public static final int WORLD_WIDTH = 40, WORLD_HEIGHT = 25;
	public static final int SCREEN_HEIGHT = 800, SCREEN_WIDTH = 1280;
	public static final World world = new World(new Vec2(0, -9.8f));
	public static final int meterToPixel = 40;

	public static final boolean FULLSCREEN = false;
}
