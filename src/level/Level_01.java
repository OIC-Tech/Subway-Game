package level;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRectf;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;
import org.lwjgl.input.Keyboard;

public class Level_01 extends AbstractLevel {

	private Body girl;
	private float girlXRadius = 0.53f;
	private float girlYRadius = 0.8f;
	private int MTPRatio = 40;
	private LevelList levelInfo = LevelList.LEVEL_01;
	private boolean inAir;
	private Body ground;
	private Set<Body> map = new HashSet<Body>();
	

	public Level_01(String fgLocation, String mgLocation, String bgLocation, String girlLocation)
			throws FileNotFoundException, IOException {
		super(fgLocation, mgLocation, bgLocation, new World(new Vec2(0, 9.8f)), girlLocation);

	}

	public void setupMap() {
		// Test Girl

		BodyDef girlDef = new BodyDef();
		girlDef.position.set(2, 18);
		girlDef.type = BodyType.DYNAMIC;
		PolygonShape girlShape = new PolygonShape();
		girlShape.setAsBox(girlXRadius, girlYRadius);
		girl = getWorld().createBody(girlDef);
		FixtureDef girlFixture = new FixtureDef();
		girlFixture.density = 1f;
		girlFixture.friction = 1f;
		girlFixture.shape = girlShape;
		girl.createFixture(girlFixture);
		girl.setFixedRotation(true);
		girl.setUserData("girl");
		
		   
//        BodyDef boxDef = new BodyDef();
//        boxDef.position.set(320 / 30 / 2f, 240 / 30 / 2f);
//        boxDef.type = BodyType.DYNAMIC;
//        PolygonShape boxShape = new PolygonShape();
//        boxShape.setAsBox(0.75f, 0.75f);
//        Body box = getWorld().createBody(boxDef);
//        FixtureDef boxFixture = new FixtureDef();
//        boxFixture.density = 0.1f;
//        boxFixture.shape = boxShape;
//        box.createFixture(boxFixture);
//        map.add(box);

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
		ground.setUserData("floor");

		ContactListener contactListener = new ContactListener() {

			@Override
			public void beginContact(Contact contact) {

				final Fixture figureA = contact.getFixtureA();
				final Fixture figureB = contact.getFixtureB();

				if (figureA.getBody().getUserData() != null && figureB.getBody().getUserData() != null) {
					if ((figureA.getBody().getUserData().equals("girl") && figureB.getBody().getUserData().equals(
							"floor"))
							|| (figureA.getBody().getUserData().equals("floor") && figureB.getBody().getUserData()
									.equals("girl"))) {
						inAir = true;
					}
				}
			}

			@Override
			public void endContact(Contact contact) {
				final Fixture figureA = contact.getFixtureA();
				final Fixture figureB = contact.getFixtureB();

				if (figureA.getBody().getUserData() != null && figureB.getBody().getUserData() != null) {
					if ((figureA.getBody().getUserData().equals("girl") && figureB.getBody().getUserData().equals(
							"floor"))
							|| (figureA.getBody().getUserData().equals("floor") && figureB.getBody().getUserData()
									.equals("girl"))) {
						inAir = false;
					}
				}

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub

			}
		};
		getWorld().setContactListener(contactListener);

	}

	public void draw() {
		drawBG(getBgCoord());
		drawMG(getMgCoord());
		drawGirl(girl.getPosition(), 0);
		drawFG(getFgCoord());

	}

	public void drawGirl(Vec2 position, int frame) {
		
		glBindTexture(GL_TEXTURE_2D, girlTextures[frame].getTextureID());
		float drawX = 106.5f;
		float drawY = 106.5f;
	
		glPushMatrix();
		glLoadIdentity();
		Vec2 bodyPosition = position.mul(MTPRatio);
		glTranslatef(bodyPosition.x - (girlXRadius * 40), bodyPosition.y - (girlYRadius * 40), 0);
		glRotated(Math.toDegrees(girl.getAngle()), 0, 0, 1);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); // Upper left

		glTexCoord2f(1, 0);
		glVertex2f(drawX, 0); // Upper right

		glTexCoord2f(1, 1);
		glVertex2f(drawX, drawY); // Lower
												// right

		glTexCoord2f(0, 1);
		glVertex2f(0, drawY); // Lower left
		glEnd();
		glPopMatrix();
//		
//        for (Body body : map) {
//            if (body.getType() == BodyType.DYNAMIC) {
//                glPushMatrix();
//        		glLoadIdentity();
//                Vec2 boxPosition = body.getPosition().mul(40);
//                glTranslatef(boxPosition.x, boxPosition.y, 0);
//                glRotated(Math.toDegrees(body.getAngle()), 0, 0, 1);
//                glRectf(-0.75f * 40, -0.75f * 40, 0.75f * 40, 0.75f * 40);
//                glPopMatrix();
//            }
//        }
	}

	public void drawFG(float[] coord) {
		glBindTexture(GL_TEXTURE_2D, fg.getTextureID());
		glPushMatrix();
		glLoadIdentity();
		glTranslatef(coord[0], coord[1], 0);
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

	public void drawMG(float[] coord) {
		glBindTexture(GL_TEXTURE_2D, mg.getTextureID());
		glPushMatrix();
		glLoadIdentity();
		glTranslatef(coord[0], coord[1], 0);
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

	public void drawBG(float[] coord) {
		glBindTexture(GL_TEXTURE_2D, bg.getTextureID());
		glPushMatrix();
		glLoadIdentity();
		glTranslatef(coord[0], coord[1], 0);
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
		getWorld().step(delta / 1000, 8, 3);
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			girl.setLinearVelocity(new Vec2(3, girl.getLinearVelocity().y));
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			girl.setLinearVelocity(new Vec2(-3, girl.getLinearVelocity().y));
		}

		if ((Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_W)) && inAir) {
			girl.getLinearVelocity().y = -5;
		}
	}

}