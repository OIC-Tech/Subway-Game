package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import utility.GameStates;
import entity.BlockGrid;
import entity.BlockType;
import entity.Button;
import entity.World;
import static org.lwjgl.opengl.GL11.*;

public class Menu implements GameState {

	private Texture background;
	private Texture girl_01;
	private Texture girl_02;
	private int hairFlying;
	private Texture[] girl;
	private Random random = new Random();
	private Button startButton;

	public Menu() throws FileNotFoundException, IOException {

		background = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/menu/background.png")));
		girl = new Texture[2];
		girl[0] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/menu/girl_01.png")));
		girl[1] = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/menu/girl_02.png")));
		hairFlying = 0;

		startButton = new Button(100, 500, 512, 128, TextureLoader.getTexture("PNG", new FileInputStream(new File("res/menu/start_button.png"))));
	}

	@Override
	public void draw() {

		drawBG();
		drawButton();
		drawGirl();

	}

	// TODO

	private void drawButton() {
		startButton.draw();

	}

	@Override
	public void logic() throws FileNotFoundException, IOException {
		if (startButton.isClicked(Mouse.getX(), World.SCREEN_HEIGHT - Mouse.getY())) {
			Boot.state = GameStates.GAME;
		}

	}

	private void drawBG() {
		glBindTexture(GL_TEXTURE_2D, background.getTextureID());
		glPushMatrix();
		glLoadIdentity();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); // Upper left

		glTexCoord2f(1, 0);
		glVertex2f(2048, 0); // Upper right

		glTexCoord2f(1, 1);
		glVertex2f(2048, 1024); // Lower right

		glTexCoord2f(0, 1);
		glVertex2f(0, 1024); // Lower left
		glEnd();
		glPopMatrix();
	}

	private void drawGirl() {

		if (random.nextFloat() > 0.9) {
			if (hairFlying == 0) {
				hairFlying++;
			}
		}

		if (random.nextFloat() > 0.95) {
			if (hairFlying == 1) {
				hairFlying--;
			}
		}
		glPushMatrix();
		glLoadIdentity();
		glBindTexture(GL_TEXTURE_2D, girl[hairFlying].getTextureID());
		glTranslatef(734f, 301f, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); // Upper left

		glTexCoord2f(1, 0);
		glVertex2f(girl[hairFlying].getImageWidth(), 0); // Upper right

		glTexCoord2f(1, 1);
		glVertex2f(girl[hairFlying].getImageWidth(), girl[hairFlying].getImageHeight()); // Lower
																							// right

		glTexCoord2f(0, 1);
		glVertex2f(0, girl[hairFlying].getImageHeight()); // Lower left
		glEnd();
		glPopMatrix();
	}

}
