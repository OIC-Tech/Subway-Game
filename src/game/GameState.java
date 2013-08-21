package game;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GameState {
	public void draw();
	public void logic() throws FileNotFoundException, IOException;
}
