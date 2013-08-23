package entity;

import static game.WorldSettings.*;
import game.WorldSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class Block {
	private BlockType type = BlockType.AIR;
	private float x, y;
	private Texture texture;

	public Block(BlockType type, float x, float y) throws FileNotFoundException, IOException {
		this.type = type;
		this.x = x;
		this.y = y;
		this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.location)));
	}

	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void draw() {
		// TODO make draw for block
		texture.bind();
        glPushMatrix();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); 
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(WorldSettings.BLOCK_SIZE, 0);
		glTexCoord2f(1, 1);
		glVertex2f(WorldSettings.BLOCK_SIZE, WorldSettings.BLOCK_SIZE);
		glTexCoord2f(0, 1);
		glVertex2f(0, WorldSettings.BLOCK_SIZE);
		glEnd();
        glPopMatrix();
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

}
