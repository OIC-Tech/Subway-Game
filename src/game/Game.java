package game;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Mouse;

import entity.BlockGrid;
import entity.BlockType;
import entity.World;

public class Game implements GameState {

	BlockGrid grid;

	public Game() throws FileNotFoundException, IOException {
		grid = new BlockGrid();

	}

	public void logic() throws FileNotFoundException, IOException {

		int mouseX = Mouse.getX() - 1;
		int mouseY = World.SCREEN_HEIGHT - Mouse.getY() - 1;
		boolean rightMouseClicked = Mouse.isButtonDown(1);
		boolean leftMouseClicked = Mouse.isButtonDown(0);
		if (rightMouseClicked) {
			int mouseGridX = Math.round(mouseX / World.BLOCK_SIZE);
			int mouseGridY = Math.round(mouseY / World.BLOCK_SIZE);
			System.out.println(mouseGridX + ", " + mouseGridY);
			grid.setBlock(mouseGridX, mouseGridY, BlockType.STONE);
		}
		if (leftMouseClicked) {
			int mouseGridX = Math.round(mouseX / World.BLOCK_SIZE);
			int mouseGridY = Math.round(mouseY / World.BLOCK_SIZE);
			System.out.println(mouseGridX + ", " + mouseGridY);
			grid.setBlock(mouseGridX, mouseGridY, BlockType.AIR);
		}
	}
	
	public void draw() {
		grid.draw();
	}

}
