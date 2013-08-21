package game;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import utility.GameStates;
import entity.BlockGrid;
import entity.BlockType;
import entity.World;
import static org.lwjgl.opengl.GL11.*;

public class Boot {

	/** time at last frame */
	long lastFrame;

	/** frames per second */
	int fps;
	/** last FPS time */
	long lastFPS;

	private GameState game;
	private GameState menu;
	public static GameStates state = GameStates.MENU;

	private boolean fullscreen = false;

	private void update() {

	}

	private void logic() throws FileNotFoundException, IOException {

		// TODO
		switch (state) {
		case GAME: {
			game.logic();
		}
		case INTRO:
			break;
		case MENU:
			menu.logic();
			break;
		case SETTINGS:
			break;
		default:
			break;
		}
	}

	private void render() {

		// TODO
		glClear(GL_COLOR_BUFFER_BIT);
		switch (state) {
		case GAME: {
			game.draw();
		}
		case INTRO:
			break;
		case MENU: {
			menu.draw();
			break;
		}
		case SETTINGS:
			break;
		default:
			break;
		}

	}

	private void setupDisplay() throws LWJGLException {
		try {

			DisplayMode displayMode = null;
			DisplayMode[] modes = Display.getAvailableDisplayModes();

			for (int i = 0; i < modes.length; i++) {
				if (modes[i].getWidth() == World.SCREEN_WIDTH && modes[i].getHeight() == World.SCREEN_HEIGHT && modes[i].isFullscreenCapable()) {
					displayMode = modes[i];
					break;
				}
			}

			Display.setDisplayMode(displayMode);
			Display.setFullscreen(fullscreen);
			Display.create();

		} catch (LWJGLException e) {
			e.printStackTrace();
		}

	}

	private void setupOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, World.SCREEN_WIDTH, World.SCREEN_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

	}

	private void setupEntities() {
		try {
			game = new Game();
			menu = new Menu();
		} catch (Exception e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(0);
		}
	}

	private void setupTimer() {
		getDelta(); // call once before loop to initialize lastFrame
		lastFPS = getTime(); // call before loop to initialize FPS timer

	}

	public static void main(String[] args) throws LWJGLException {
		try {
			new Boot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boot() throws LWJGLException, FileNotFoundException, IOException {
		setupDisplay();
		setupOpenGL();
		setupEntities();
		setupTimer();

		boolean isRunning = true;

		while (isRunning) {
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				isRunning = false;
			}
			if (Display.isCloseRequested()) {
				isRunning = false;
			}
			render();
			logic();
			update();
			updateFPS();

			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);

	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
}
