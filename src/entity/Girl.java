package entity;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

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
import org.newdawn.slick.opengl.Texture;

public class Girl implements InterfaceHuman {

	private enum HumanStates {
		STAND, WALK, RUN, JUMP, INAIR;
	}

	private Texture[] standAnimation;
	private Texture[] walkAnimation;
	private Texture[] runAnimation;
	private Texture[] jumpAnimation;
	private Texture[] inAirAnimation;
	// Texture[] glide;

	private int AnimationFrame = 0;

	private float x, y;
	private float xRadius, yRadius;

	private HumanStates state = HumanStates.INAIR;
	private int MTPRatio = 40;
	private float girlXRadius = 0.53f;
	private float girlYRadius = 0.8f;
	private float jumpForce = -5;
	private float walkForce = 3;

	private Body body;
	private Body footSensor;

	public Girl(float x, float y, float xRadius, float yRadius, World world) {
		this.x = x;
		this.y = y;

		BodyDef girlDef = new BodyDef();
		girlDef.position.set(x, y);
		girlDef.type = BodyType.DYNAMIC;
		PolygonShape girlShape = new PolygonShape();
		girlShape.setAsBox(girlXRadius, girlYRadius);
		body = world.createBody(girlDef);
		FixtureDef girlFixture = new FixtureDef();
		girlFixture.density = 1f;
		girlFixture.friction = 1f;
		girlFixture.shape = girlShape;
		body.createFixture(girlFixture);
		body.setFixedRotation(true);
		body.setUserData("girl");

		BodyDef footSensorDef = new BodyDef();
		footSensorDef.position.set(x, y - (10 / MTPRatio));
		footSensorDef.type = BodyType.STATIC;
		PolygonShape footSensorShape = new PolygonShape();
		footSensorShape.setAsBox(girlXRadius, girlYRadius);
		body = world.createBody(footSensorDef);
		FixtureDef footSensorFixture = new FixtureDef();
		footSensorFixture.shape = footSensorShape;
		body.createFixture(footSensorFixture);
		body.setFixedRotation(true);
		body.setUserData("footSensor");
	}

	@Override
	public ContactListener getContactListener() {
		return new ContactListener() {

			@Override
			public void beginContact(Contact contact) {

				final Fixture figureA = contact.getFixtureA();
				final Fixture figureB = contact.getFixtureB();

				if (figureA.getBody().getUserData() != null && figureB.getBody().getUserData() != null) {
					if ((figureA.getBody().getUserData().equals("footSensor") && figureB.getBody().getUserData()
							.equals(
									"floor"))
							|| (figureA.getBody().getUserData().equals("floor") && figureB.getBody().getUserData()
									.equals("footSensor"))) {
						state = HumanStates.STAND;
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
						state = HumanStates.INAIR;
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
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		switch (state) {
		case STAND: {
			glBindTexture(GL_TEXTURE_2D, standAnimation[AnimationFrame].getTextureID());
			break;
		}
		case INAIR: {
			glBindTexture(GL_TEXTURE_2D, inAirAnimation[AnimationFrame].getTextureID());
			break;
		}
		case JUMP: {
			glBindTexture(GL_TEXTURE_2D, jumpAnimation[AnimationFrame].getTextureID());
			break;
		}
		case RUN: {
			glBindTexture(GL_TEXTURE_2D, runAnimation[AnimationFrame].getTextureID());
			break;
		}
		case WALK: {
			glBindTexture(GL_TEXTURE_2D, walkAnimation[AnimationFrame].getTextureID());
			break;
		}
		default: {
			glBindTexture(GL_TEXTURE_2D, standAnimation[AnimationFrame].getTextureID());
			break;
		}
		}

		float drawX = 106.5f;
		float drawY = 106.5f;

		Vec2 position = new Vec2(x, y);

		glPushMatrix();
		glLoadIdentity();
		Vec2 bodyPosition = position.mul(MTPRatio);
		glTranslatef(bodyPosition.x - (getGirlXRadius() * 40), bodyPosition.y - (getGirlYRadius() * 40), 0);
		glRotated(Math.toDegrees(body.getAngle()), 0, 0, 1);
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

	}

	@Override
	public void jump() {
		body.getLinearVelocity().y = jumpForce;
	}

	@Override
	public void walkRight() {
		body.setLinearVelocity(new Vec2(walkForce, body.getLinearVelocity().y));

	}

	@Override
	public void walkLeft() {
		body.setLinearVelocity(new Vec2(-walkForce, body.getLinearVelocity().y));

	}
	public Texture[] getStandAnimation() {
		return standAnimation;
	}

	public void setStandAnimation(Texture[] standAnimation) {
		this.standAnimation = standAnimation;
	}

	public Texture[] getWalkAnimation() {
		return walkAnimation;
	}

	public void setWalkAnimation(Texture[] walkAnimation) {
		this.walkAnimation = walkAnimation;
	}

	public Texture[] getRunAnimation() {
		return runAnimation;
	}

	public void setRunAnimation(Texture[] runAnimation) {
		this.runAnimation = runAnimation;
	}

	public Texture[] getJumpAnimation() {
		return jumpAnimation;
	}

	public void setJumpAnimation(Texture[] jumpAnimation) {
		this.jumpAnimation = jumpAnimation;
	}

	public int getAnimationFrame() {
		return AnimationFrame;
	}

	public void setAnimationFrame(int animationFrame) {
		AnimationFrame = animationFrame;
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

	public boolean isInAir() {
		if (state.equals(HumanStates.INAIR))
			return true;
		else
			return false;
	}

	public Texture[] getInAirAnimation() {
		return inAirAnimation;
	}

	public void setInAirAnimation(Texture[] inAirAnimation) {
		this.inAirAnimation = inAirAnimation;
	}

	public float getGirlXRadius() {
		return girlXRadius;
	}

	public void setGirlXRadius(float girlXRadius) {
		this.girlXRadius = girlXRadius;
	}

	public float getGirlYRadius() {
		return girlYRadius;
	}

	public void setGirlYRadius(float girlYRadius) {
		this.girlYRadius = girlYRadius;
	}

	public float getyRadius() {
		return yRadius;
	}

	public void setyRadius(float yRadius) {
		this.yRadius = yRadius;
	}

	public float getxRadius() {
		return xRadius;
	}

	public void setxRadius(float xRadius) {
		this.xRadius = xRadius;
	}

	@Override
	public void step() {
		footSensor.setTransform(body.getPosition(), body.getAngle());

	}

}
