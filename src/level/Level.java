package level;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public interface Level {
	public void reset();

	public void draw();

	public float[] getFgCoord();

	public void setFgCoord(float[] fgCoord);

	public float[] getMgCoord();

	public void setMgCoord(float[] mgCoord);

	public float[] getBgCoord();

	public void setBgCoord(float[] bgCoord);

	public Body[] getMap();

	public void setMap(Body[] map);

	public void step(float delta);

	public void setupMap();

	public World getWorld();

}
