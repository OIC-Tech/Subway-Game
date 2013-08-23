package entity;

import static game.WorldSettings.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BlockGrid {
	private Block[][] blocks = new Block[WORLD_WIDTH][WORLD_HEIGHT];

	public BlockGrid() throws FileNotFoundException, IOException {
		for (int x = 0; x < WORLD_WIDTH; x++) {
			for (int y = 0; y < WORLD_HEIGHT; y++) {
				blocks[x][y] = new Block(BlockType.AIR, x * BLOCK_SIZE, y * BLOCK_SIZE);
			}
		}
	}

	public void setBlock(int x, int y, BlockType type) throws FileNotFoundException, IOException {
		blocks[x][y] = new Block(type, x * BLOCK_SIZE, y * BLOCK_SIZE);
	}

	public Block getBlock(int x, int y, BlockType type) {
		return blocks[x][y];
	}

	public void draw() {
		for (int x = 0; x < WORLD_WIDTH; x++) {
			for (int y = 0; y < WORLD_HEIGHT; y++) {
				blocks[x][y].draw();
			}
		}
	}
}
