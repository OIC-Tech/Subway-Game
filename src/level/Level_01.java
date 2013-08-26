package level;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;

import utility.Camera;
import static utility.MapComponents.*;
import entity.Girl;
import game.WorldSettings;

public class Level_01 extends AbstractLevel {

	private float girlXRadius = 0.53f;
	private float girlYRadius = 0.8f;
	private int MTPRatio = 40;
	private Body ground;
	// private Set<Body> map = new HashSet<Body>();
	private Girl girl;

	private Camera camera;

	public Level_01(float width, float height, String fgLocation, String mgLocation, String bgLocation,
			String girlLocation)
			throws FileNotFoundException, IOException {
		super(width, height, fgLocation, mgLocation, bgLocation, new World(new Vec2(0, 9.8f)), girlLocation);

	}

	public void setupMap() {

		girl = new Girl(2f, 15f, girlXRadius, girlYRadius, getWorld());
		girl.setStandAnimation(girlTextures);
		girl.setInAirAnimation(girlTextures);
		girl.setWalkAnimation(girlTextures);
		getWorld().setContactListener(girl.getContactListener());

		// BodyDef boxDef = new BodyDef();
		// boxDef.position.set(320 / 30 / 2f, 240 / 30 / 2f);
		// boxDef.type = BodyType.DYNAMIC;
		// PolygonShape boxShape = new PolygonShape();
		// boxShape.setAsBox(0.75f, 0.75f);
		// Body box = getWorld().createBody(boxDef);
		// FixtureDef boxFixture = new FixtureDef();
		// boxFixture.density = 0.1f;
		// boxFixture.shape = boxShape;
		// box.createFixture(boxFixture);
		// map.add(box);

		// Floor
		BodyDef groundDef = new BodyDef();
		groundDef.position.set(0, 750f / MTPRatio);
		groundDef.type = BodyType.STATIC;
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsBox(1280 / MTPRatio, 0);
		ground = getWorld().createBody(groundDef);
		FixtureDef groundFixture = new FixtureDef();
		groundFixture.density = 1;
		groundFixture.friction = 1.5f;
		groundFixture.shape = groundShape;
		ground.createFixture(groundFixture);
		ground.setUserData(FLOOR);

		camera = new Camera(50, 0, 1280, 800);

	}

	public void draw() {
		// xOffset = (-girl.getX() * MTPRatio) + WorldSettings.SCREEN_WIDTH / 2;
		// yOffset = (-girl.getY() * MTPRatio) + WorldSettings.SCREEN_HEIGHT /
		// 2;

		/*
		 * float cameraX = (camera.getX()); float cameraY = (camera.getY());
		 * float girlRenderX = bodyPosition.x - cameraX; float girlRenderY =
		 * bodyPosition.y - cameraY;
		 */
		drawBG();
		drawMG();
		girl.draw(camera);
		drawFG();

	}

	public void drawFG() {
		float xOffset = (getFgCoord()[0]) - camera.getX();
		float yOffset = (getFgCoord()[1]) - camera.getY();
		glBindTexture(GL_TEXTURE_2D, fg.getTextureID());
		glPushMatrix();
		glLoadIdentity();
		glTranslatef(xOffset, yOffset, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); // Upper left

		glTexCoord2f(1, 0);
		glVertex2f(fg.getImageWidth(), 0); // Upper right

		glTexCoord2f(1, 1);
		glVertex2f(fg.getImageWidth(), fg.getImageHeight()); // Lower right

		glTexCoord2f(0, 1);
		glVertex2f(0, fg.getImageHeight()); // Lower left
		glEnd();
		glPopMatrix();

	}

	public void drawMG() {
		float xOffset = (getMgCoord()[0]) - camera.getX();
		float yOffset = (getMgCoord()[1]) - camera.getY();
		glBindTexture(GL_TEXTURE_2D, mg.getTextureID());
		glPushMatrix();
		glLoadIdentity();
		glTranslatef(xOffset, yOffset, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); // Upper left

		glTexCoord2f(1, 0);
		glVertex2f(mg.getImageWidth(), 0); // Upper right

		glTexCoord2f(1, 1);
		glVertex2f(mg.getImageWidth(), mg.getImageHeight()); // Lower right

		glTexCoord2f(0, 1);
		glVertex2f(0, mg.getImageHeight()); // Lower left
		glEnd();
		glPopMatrix();

	}

	public void drawBG() {
		float xOffset = (getBgCoord()[0]) - camera.getX();
		float yOffset = (getBgCoord()[1]) - camera.getY();
		glBindTexture(GL_TEXTURE_2D, bg.getTextureID());
		glPushMatrix();
		glLoadIdentity();
		glTranslatef(xOffset, yOffset, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); // Upper left

		glTexCoord2f(1, 0);
		glVertex2f(bg.getImageWidth(), 0); // Upper right

		glTexCoord2f(1, 1);
		glVertex2f(bg.getImageWidth(), bg.getImageHeight()); // Lower right

		glTexCoord2f(0, 1);
		glVertex2f(0, bg.getImageHeight()); // Lower left
		glEnd();
		glPopMatrix();

	}

	@Override
	public void reset() {
		// TODO Make reset
	}

	public void step(float delta) {
		camera.step(delta, girl.getPosition(), 0.05f);
		getWorld().step(delta / 1000, 8, 3);
		girl.step();
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			girl.walkRight();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			girl.walkLeft();
		}

		if ((Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_W)) && !girl.isInAir()) {
			girl.jump();
		}
	}

}