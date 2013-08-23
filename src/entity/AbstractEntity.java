package entity;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public abstract class AbstractEntity implements InterfaceEntity {

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected Rectangle collisionBox = new Rectangle();
	protected Texture texture;
	protected boolean textured;
	
	public AbstractEntity(float x, float y, float width, float height, Texture texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.textured = true;
	}

	public AbstractEntity(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void transform(float amountX, float amountY) {
		x += amountX;
		y += amountY;
	}

	@Override
	public boolean intersects(InterfaceEntity other) {
		collisionBox.setBounds((int) x, (int) y, (int) width, (int) height);
		return collisionBox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeigth());
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeigth() {
		return height;
	}

	public void setHeigth(float height) {
		this.height = height;
	}

	public static Texture loadTexture(String filename) throws FileNotFoundException, IOException {
		return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + filename + ".png")));
	}
}
