package level;

import static utility.util.loadTexture;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.opengl.Texture;

public abstract class AbstractLevel implements Level {

	private float[] fgCoord = new float[2];
	private float[] mgCoord = new float[2];
	private float[] bgCoord = new float[2];

	private float[] girlCoord = new float[2];
	private float width;
	private float height;

	Texture fg;
	Texture mg;
	Texture bg;

	Texture[] girlTextures = new Texture[7];

	private Body[] map;
	private World world = new World(new Vec2(0, 9.8f));

	public AbstractLevel(float width, float height, String fgLocation, String mgLocation, String bgLocation, World world,
			String girlLocation)
			throws FileNotFoundException, IOException {
		this.setWidth(width);
		this.setHeight(height);
		this.setWorld(world);
		this.fg = loadTexture(fgLocation);
		this.mg = loadTexture(mgLocation);
		this.bg = loadTexture(bgLocation);

		for (int i = 0; i <= 6; i++) {
			girlTextures[i] = loadTexture(girlLocation + (i + 1) + ".png");
		}

	}

	public float[] getFgCoord() {
		return fgCoord;
	}

	public void setFgCoord(float[] fgCoord) {
		this.fgCoord = fgCoord;
	}

	public float[] getMgCoord() {
		return mgCoord;
	}

	public void setMgCoord(float[] mgCoord) {
		this.mgCoord = mgCoord;
	}

	public float[] getBgCoord() {
		return bgCoord;
	}

	public void setBgCoord(float[] bgCoord) {
		this.bgCoord = bgCoord;
	}

	public Body[] getMap() {
		return map;
	}

	public void setMap(Body[] map) {
		this.map = map;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public float[] getGirlCoord() {
		return girlCoord;
	}

	public void setGirlCoord(float[] girlCoord) {
		this.girlCoord = girlCoord;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
