package entity;

import org.newdawn.slick.opengl.Texture;

public abstract class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity {
	

	public AbstractMoveableEntity(float x, float y, float width, float height, Texture texture) {
		super(x, y, width, height, texture);
	}
	
	public AbstractMoveableEntity(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public double DX;
	public double DY;

	public double getDX() {
		return DX;
	}

	public void setDX(double dX) {
		DX = dX;
	}

	public double getDY() {
		return DY;
	}

	public void setDY(double dY) {
		DY = dY;
	}


}
