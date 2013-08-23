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
import static org.lwjgl.opengl.GL11.*;

public class Boot {

	/** time at last frame */
	public static long lastFrame;

	/** frames per second */
	public static int fpsCount;
	/** last FPS time */
	public static long lastFPSTime;
	public static int currentFPS;
	public static float currentDelta;
	private GameState game;
	private GameState menu;
	public static GameStates state = GameStates.MENU;

	private void update() {

	}

	private void logic() throws FileNotFoundException, IOException {

		// TODO
		switch (state) {
		case GAME: {
			game.step();
		}
		case INTRO:
			break;
		case MENU:
			menu.step();
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
				if (modes[i].getWidth() == WorldSettings.SCREEN_WIDTH && modes[i].getHeight() == WorldSettings.SCREEN_HEIGHT && modes[i].isFullscreenCapable()) {
					displayMode = modes[i];
					break;
				}
			}

			Display.setDisplayMode(displayMode);
			Display.setFullscreen(WorldSettings.FULLSCREEN);
			Display.create();

		} catch (LWJGLException e) {
			e.printStackTrace();
		}

	}

	private void setupOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WorldSettings.SCREEN_WIDTH, WorldSettings.SCREEN_HEIGHT, 0, 1, -1);
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
		updateDelta(); // call once before loop to initialize lastFrame
		lastFPSTime = getTime(); // call before loop to initialize FPS timer

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
		setupTimer();
		setupEntities();

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
			updateDelta();

			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);

	}



	public static void updateDelta() {
		long time = getTime();
		currentDelta = (time - lastFrame);
		lastFrame = time;
		
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public static void updateFPS() {
		if (getTime() - lastFPSTime > 1000) {
			currentFPS = fpsCount;
			fpsCount = 0;
			lastFPSTime += 1000;
		}
		fpsCount++;
	}

}
