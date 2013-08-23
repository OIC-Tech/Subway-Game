package game;

import static utility.util.loadTexture;

import java.io.FileNotFoundException;
import java.io.IOException;

import level.Level;
import level.LevelList;
import level.Level_01;

import org.newdawn.slick.opengl.Texture;

// 38 pixel >> 1 meter

public class Game implements GameState {

	private LevelList levelInfo = LevelList.LEVEL_01;
	private Level level;

	Texture fg;
	Texture mg;
	Texture bg;

	public Game() throws FileNotFoundException,
			IOException {
		setLevel(levelInfo);
	}

	public void setLevel(LevelList levelInfo)
			throws FileNotFoundException,
			IOException {

		this.levelInfo = levelInfo;

		fg = loadTexture(levelInfo.fgLocation);
		mg = loadTexture(levelInfo.mgLocation);
		bg = loadTexture(levelInfo.bgLocation);

		switch (levelInfo) {
		case LEVEL_01: {
			level = new Level_01(
					levelInfo.fgLocation,
					levelInfo.mgLocation,
					levelInfo.bgLocation,
					levelInfo.girlTexture);
		}

		}

		level.setFgCoord(levelInfo.fgCoord);
		level.setMgCoord(levelInfo.mgCoord);
		level.setBgCoord(levelInfo.bgCoord);
		level.setupMap();

	}

	@Override
	public void draw() {
		level.draw();
	}

	@Override
	public void step()
			throws FileNotFoundException,
			IOException {
		level.step(Boot.currentDelta);

	}

}
